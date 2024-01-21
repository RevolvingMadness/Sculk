package com.revolvingmadness.sculk.language.interpreter;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.backend.SculkScript;
import com.revolvingmadness.sculk.backend.SculkScriptLoader;
import com.revolvingmadness.sculk.gamerules.SculkGamerules;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.SwitchExpressionCase;
import com.revolvingmadness.sculk.language.SwitchStatementCase;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.*;
import com.revolvingmadness.sculk.language.errors.SyntaxError;
import com.revolvingmadness.sculk.language.interpreter.errors.StackOverflowError;
import com.revolvingmadness.sculk.language.interpreter.errors.*;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import com.revolvingmadness.sculk.language.parser.nodes.ScriptNode;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.*;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.literal_expression_nodes.*;
import com.revolvingmadness.sculk.language.parser.nodes.statement_nodes.*;
import net.minecraft.util.Pair;

import java.util.*;

public class Interpreter implements Visitor {
    public final SculkScriptLoader loader;
    public final VariableTable variableTable;

    public Interpreter(SculkScriptLoader loader) {
        this.loader = loader;

        this.variableTable = new VariableTable();
    }

    @Override
    public BuiltinClass visitBinaryExpression(BinaryExpressionNode binaryExpression) {
        BuiltinClass left = this.visitExpression(binaryExpression.left);
        BuiltinClass right = this.visitExpression(binaryExpression.right);

        return switch (binaryExpression.operator) {
            case PLUS -> left.call(this, "add", List.of(right));
            case HYPHEN -> left.call(this, "subtract", List.of(right));
            case STAR -> left.call(this, "multiply", List.of(right));
            case FSLASH -> left.call(this, "divide", List.of(right));
            case CARET -> left.call(this, "exponentiate", List.of(right));
            case PERCENT -> left.call(this, "mod", List.of(right));
            case EQUAL_TO -> left.call(this, "equalTo", List.of(right));
            case NOT_EQUAL_TO -> left.call(this, "notEqualTo", List.of(right));
            case GREATER_THAN -> left.call(this, "greaterThan", List.of(right));
            case GREATER_THAN_OR_EQUAL_TO -> left.call(this, "greaterThanOrEqualTo", List.of(right));
            case LESS_THAN -> left.call(this, "lessThan", List.of(right));
            case LESS_THAN_OR_EQUAL_TO -> left.call(this, "lessThanOrEqualTo", List.of(right));
            case DOUBLE_AMPERSAND -> left.call(this, "booleanAnd", List.of(right));
            case DOUBLE_PIPE -> left.call(this, "booleanOr", List.of(right));
            case INSTANCE_OF -> left.call(this, "instanceOf", List.of(right));
            case SPACESHIP -> {
                BuiltinClass lessThan = left.call(this, "lessThan", List.of(right));

                if (lessThan.toBoolean()) {
                    yield new IntegerInstance(-1);
                }

                BuiltinClass equalTo = left.call(this, "equalTo", List.of(right));

                if (equalTo.toBoolean()) {
                    yield new IntegerInstance(0);
                }

                BuiltinClass greaterThan = left.call(this, "greaterThan", List.of(right));

                if (greaterThan.toBoolean()) {
                    yield new IntegerInstance(1);
                }

                throw new InterpreterError("Unreachable");
            }
            default -> throw ErrorHolder.unsupportedBinaryOperator(binaryExpression.operator);
        };
    }

    @Override
    public BuiltinClass visitBooleanExpression(BooleanExpressionNode booleanExpression) {
        return new BooleanInstance(booleanExpression.value);
    }

    @Override
    public void visitBreakStatement(BreakStatementNode breakStatement) {
        throw new Break();
    }

    @Override
    public BuiltinClass visitCallExpression(CallExpressionNode callExpression) {
        BuiltinClass callee = this.visitExpression(callExpression.callee);

        List<BuiltinClass> arguments = new ArrayList<>();

        callExpression.arguments.forEach(argumentExpression -> arguments.add(this.visitExpression(argumentExpression)));

        return callee.call(this, arguments);
    }

    @Override
    public void visitClassDeclarationStatement(ClassDeclarationStatementNode classDeclarationStatement) {
        this.variableTable.enterScope();

        classDeclarationStatement.body.forEach(this::visitStatement);

        VariableScope variableScope = this.variableTable.exitScope();

        BuiltinType superClass;

        if (classDeclarationStatement.superClassName != null) {
            Variable superClassVariable = this.variableTable.getOrThrow(classDeclarationStatement.superClassName);

            if (!(superClassVariable.value instanceof BuiltinType superClassType)) {
                throw ErrorHolder.cannotExtendFromNonType(superClassVariable.value.getType());
            }

            superClass = superClassType;
        } else {
            superClass = new ObjectType();
        }

        this.variableTable.declare(classDeclarationStatement.accessModifiers, classDeclarationStatement.name, new UserDefinedType(classDeclarationStatement.accessModifiers, classDeclarationStatement.name, superClass, variableScope));
    }

    @Override
    public void visitContinueStatement(ContinueStatementNode continueStatement) {
        throw new Continue();
    }

    @Override
    public void visitDeleteStatement(DeleteStatementNode deleteStatement) {
        if (deleteStatement.expression instanceof IdentifierExpressionNode identifierExpression) {
            this.variableTable.deleteOrThrow(identifierExpression.value);
        } else if (deleteStatement.expression instanceof GetExpressionNode getExpression) {
            BuiltinClass assignee = this.visitExpression(getExpression.expression);

            assignee.deleteProperty(getExpression.propertyName);
        } else if (deleteStatement.expression instanceof IndexExpressionNode indexExpression) {
            BuiltinClass assignee = this.visitExpression(indexExpression.expression);
            BuiltinClass index = this.visitExpression(indexExpression.index);

            assignee.deleteIndex(index);
        } else {
            throw new SyntaxError("Cannot delete r-value");
        }
    }

    @Override
    public BuiltinClass visitDictionaryExpression(DictionaryExpressionNode dictionaryExpression) {
        Map<BuiltinClass, BuiltinClass> dictionary = new HashMap<>();

        dictionaryExpression.value.forEach((key, value) -> dictionary.put(this.visitExpression(key), this.visitExpression(value)));

        return new DictionaryInstance(dictionary);
    }

    @Override
    public void visitEnumDeclarationStatement(EnumDeclarationStatementNode enumDeclarationStatement) {
        this.variableTable.declare(enumDeclarationStatement.accessModifiers, enumDeclarationStatement.name, new UserDefinedEnumType(enumDeclarationStatement.accessModifiers, enumDeclarationStatement.name, enumDeclarationStatement.values));
    }

    @Override
    public BuiltinClass visitExpression(ExpressionNode expression) {
        if (expression instanceof BinaryExpressionNode binaryExpression) {
            return this.visitBinaryExpression(binaryExpression);
        } else if (expression instanceof CallExpressionNode callExpression) {
            return this.visitCallExpression(callExpression);
        } else if (expression instanceof UnaryExpressionNode unaryExpression) {
            return this.visitUnaryExpression(unaryExpression);
        } else if (expression instanceof VariableAssignmentExpressionNode variableAssignmentExpression) {
            return this.visitVariableAssignmentExpression(variableAssignmentExpression);
        } else if (expression instanceof IdentifierExpressionNode identifierExpression) {
            return this.visitIdentifierExpression(identifierExpression);
        } else if (expression instanceof GetExpressionNode getExpression) {
            return this.visitGetExpression(getExpression);
        } else if (expression instanceof BuiltinClass baseClassExpression) {
            return baseClassExpression;
        } else if (expression instanceof IndexExpressionNode indexExpression) {
            return this.visitIndexExpression(indexExpression);
        } else if (expression instanceof PostfixExpressionNode postfixExpression) {
            return this.visitPostfixExpression(postfixExpression);
        } else if (expression instanceof LiteralExpressionNode literalExpression) {
            return this.visitLiteralExpression(literalExpression);
        } else if (expression instanceof SwitchExpressionNode switchExpression) {
            return this.visitSwitchExpression(switchExpression);
        } else if (expression instanceof TernaryExpressionNode ternaryExpression) {
            return this.visitTernaryExpression(ternaryExpression);
        } else {
            throw ErrorHolder.unsupportedExpressionNodeToInterpret(expression);
        }
    }

    @Override
    public void visitExpressionStatement(ExpressionStatementNode expressionStatement) {
        this.visitExpression(expressionStatement.expression);
    }

    @Override
    public void visitFieldDeclarationStatement(FieldDeclarationStatementNode fieldDeclarationStatement) {
        BuiltinClass value = this.visitExpression(fieldDeclarationStatement.value);

        this.variableTable.declare(fieldDeclarationStatement.accessModifiers, fieldDeclarationStatement.name, value);
    }

    @Override
    public BuiltinClass visitFloatExpression(FloatExpressionNode floatExpression) {
        return new FloatInstance(floatExpression.value);
    }

    @Override
    public void visitForStatement(ForStatementNode forStatement) {
        int loops = 0;
        long maxLoops = Sculk.server.getGameRules().getInt(SculkGamerules.MAX_LOOPS);

        if (forStatement.initialization != null) {
            this.visitStatement(forStatement.initialization);
        }

        while_loop:
        while (true) {
            this.variableTable.enterScope();

            BuiltinClass condition = this.visitExpression(forStatement.condition);

            if (!condition.instanceOf(new BooleanType())) {
                throw ErrorHolder.invalidForLoopUpdateType(new IntegerType(), condition.getType());
            }

            if (!condition.toBoolean()) {
                break;
            }

            for (StatementNode statement : forStatement.body) {
                try {
                    this.visitStatement(statement);
                } catch (Break ignored) {
                    break while_loop;
                } catch (Continue ignored) {
                    break;
                }
            }

            if (forStatement.update != null) {
                this.visitExpression(forStatement.update);
            }

            if (++loops > maxLoops) {
                throw new StackOverflowError("For-loop ran more than " + maxLoops + " times");
            }

            this.variableTable.exitScope();
        }
    }

    @Override
    public void visitForeachStatement(ForeachStatementNode foreachStatement) {
        int loops = 0;
        long maxLoops = Sculk.server.getGameRules().getInt(SculkGamerules.MAX_LOOPS);

        Iterator<BuiltinClass> variableIterator = this.visitExpression(foreachStatement.variableToIterate).asIterator();

        while_loop:
        while (variableIterator.hasNext()) {
            this.variableTable.enterScope();

            BuiltinClass iteratorValue = variableIterator.next();

            this.variableTable.declare(List.of(TokenType.CONST), foreachStatement.variableName, iteratorValue);

            for (StatementNode statement : foreachStatement.body) {
                try {
                    this.visitStatement(statement);
                } catch (Break ignored) {
                    break while_loop;
                } catch (Continue ignored) {
                    break;
                }
            }

            if (++loops > maxLoops) {
                throw new StackOverflowError("Foreach-loop ran more than " + maxLoops + " times");
            }

            this.variableTable.exitScope();
        }
    }

    @Override
    public void visitFromStatement(FromStatementNode fromStatement) {
        SculkScript script = this.loader.scripts.get(fromStatement.identifier);

        if (script == null) {
            throw ErrorHolder.cannotFindScript(fromStatement.identifier);
        }

        this.variableTable.enterScope();
        script.import_(this);
        VariableScope variableScope = this.variableTable.exitScope();

        fromStatement.variablesToImport.forEach(name -> this.variableTable.declare(List.of(), name, variableScope.getOrThrow(name).value));
    }

    @Override
    public void visitFunctionDeclarationStatement(FunctionDeclarationStatementNode functionDeclarationStatement) {
        this.variableTable.declare(functionDeclarationStatement.accessModifiers, functionDeclarationStatement.name, new FunctionInstance(functionDeclarationStatement.name, functionDeclarationStatement.arguments, functionDeclarationStatement.body));
    }

    @Override
    public BuiltinClass visitFunctionExpression(FunctionExpressionNode functionExpression) {
        return new FunctionInstance(functionExpression.name, functionExpression.arguments, functionExpression.body);
    }

    @Override
    public BuiltinClass visitGetExpression(GetExpressionNode getExpression) {
        BuiltinClass expression = this.visitExpression(getExpression.expression);

        return expression.getProperty(getExpression.propertyName);
    }

    @Override
    public BuiltinClass visitIdentifierExpression(IdentifierExpressionNode identifierExpression) {
        return this.variableTable.getOrThrow(identifierExpression.value).value;
    }

    @Override
    public void visitIfStatement(IfStatementNode ifStatement) {
        BuiltinClass ifCondition = this.visitExpression(ifStatement.ifConditionPair.getLeft());

        if (!ifCondition.instanceOf(new BooleanType())) {
            throw ErrorHolder.ifStatementConditionRequiresType(new BooleanType(), ifCondition.getType());
        }

        if (ifCondition.toBoolean()) {
            for (StatementNode statement : ifStatement.ifConditionPair.getRight()) {
                this.visitStatement(statement);
            }
            return;
        }

        for (Pair<ExpressionNode, List<StatementNode>> elseIfConditionPair : ifStatement.elseIfConditionPairs) {
            BuiltinClass elseIfCondition = this.visitExpression(elseIfConditionPair.getLeft());
            List<StatementNode> elseIfBody = elseIfConditionPair.getRight();

            if (!elseIfCondition.instanceOf(new BooleanType())) {
                throw ErrorHolder.elseIfStatementConditionRequiresType(new BooleanType(), elseIfCondition.getType());
            }

            if (elseIfCondition.toBoolean()) {
                for (StatementNode statement : elseIfBody) {
                    this.visitStatement(statement);
                }
                return;
            }
        }

        for (StatementNode statement : ifStatement.elseBody) {
            this.visitStatement(statement);
        }
    }

    @Override
    public void visitImportStatement(ImportStatementNode importStatement) {
        SculkScript script = this.loader.scripts.get(importStatement.identifier);

        if (script == null) {
            throw ErrorHolder.cannotFindScript(importStatement.identifier);
        }

        this.variableTable.enterScope();
        script.import_(this);
        VariableScope variableScope = this.variableTable.exitScope();

        if (importStatement.importAs != null) {
            this.variableTable.declare(List.of(), importStatement.importAs, new ModuleInstance(variableScope));
        }
    }

    @Override
    public BuiltinClass visitIndexExpression(IndexExpressionNode indexExpression) {
        BuiltinClass expression = this.visitExpression(indexExpression.expression);
        BuiltinClass index = this.visitExpression(indexExpression.index);

        return expression.getIndex(index);
    }

    @Override
    public BuiltinClass visitIntegerExpression(IntegerExpressionNode integerExpression) {
        return new IntegerInstance(integerExpression.value);
    }

    @Override
    public BuiltinClass visitListExpression(ListExpressionNode listExpression) {
        List<BuiltinClass> list = new ArrayList<>();

        listExpression.value.forEach(expression -> list.add(this.visitExpression(expression)));

        return new ListInstance(list);
    }

    @Override
    public BuiltinClass visitLiteralExpression(LiteralExpressionNode literalExpression) {
        if (literalExpression instanceof BooleanExpressionNode booleanExpression) {
            return this.visitBooleanExpression(booleanExpression);
        } else if (literalExpression instanceof DictionaryExpressionNode dictionaryExpression) {
            return this.visitDictionaryExpression(dictionaryExpression);
        } else if (literalExpression instanceof FloatExpressionNode floatExpression) {
            return this.visitFloatExpression(floatExpression);
        } else if (literalExpression instanceof FunctionExpressionNode functionExpression) {
            return this.visitFunctionExpression(functionExpression);
        } else if (literalExpression instanceof IntegerExpressionNode integerExpression) {
            return this.visitIntegerExpression(integerExpression);
        } else if (literalExpression instanceof ListExpressionNode listExpression) {
            return this.visitListExpression(listExpression);
        } else if (literalExpression instanceof NullExpressionNode nullExpression) {
            return this.visitNullExpression(nullExpression);
        } else if (literalExpression instanceof ResourceExpressionNode resourceExpression) {
            return this.visitResourceExpression(resourceExpression);
        } else if (literalExpression instanceof StringExpressionNode stringExpression) {
            return this.visitStringExpression(stringExpression);
        } else {
            throw ErrorHolder.unsupportedLiteralExpressionNodeToInterpret(literalExpression);
        }
    }

    @Override
    public void visitMethodDeclarationStatement(MethodDeclarationStatementNode methodDeclarationStatement) {
        this.variableTable.declare(methodDeclarationStatement.accessModifiers, methodDeclarationStatement.name, new MethodInstance(methodDeclarationStatement.accessModifiers, methodDeclarationStatement.name, methodDeclarationStatement.arguments, methodDeclarationStatement.body));
    }

    @Override
    public BuiltinClass visitNullExpression(NullExpressionNode nullExpression) {
        return new NullInstance();
    }

    @Override
    public BuiltinClass visitPostfixExpression(PostfixExpressionNode postfixExpression) {
        if (postfixExpression.expression instanceof IdentifierExpressionNode identifierExpression) {
            Variable variable = this.variableTable.getOrThrow(identifierExpression.value);
            BuiltinClass value = this.visitExpression(variable.value);

            return switch (postfixExpression.operator) {
                case DOUBLE_PLUS -> {
                    BuiltinClass incrementedValue = value.call(this, "increment", List.of());

                    this.variableTable.assign(identifierExpression.value, incrementedValue);

                    yield value;
                }
                case DOUBLE_HYPHEN -> {
                    BuiltinClass decrementedValue = value.call(this, "decrement", List.of());

                    this.variableTable.assign(identifierExpression.value, decrementedValue);

                    yield value;
                }
                default -> throw ErrorHolder.unsupportedPostfixOperator(postfixExpression.operator);
            };
        } else if (postfixExpression.expression instanceof GetExpressionNode getExpression) {
            BuiltinClass value = this.visitExpression(getExpression.expression);

            return switch (postfixExpression.operator) {
                case DOUBLE_PLUS -> {
                    BuiltinClass incrementedValue = value.call(this, "increment", List.of());

                    value.setProperty(getExpression.propertyName, incrementedValue);

                    yield value;
                }
                case DOUBLE_HYPHEN -> {
                    BuiltinClass decrementedValue = value.call(this, "decrement", List.of());

                    value.setProperty(getExpression.propertyName, decrementedValue);

                    yield value;
                }
                default -> throw ErrorHolder.unsupportedPostfixOperator(postfixExpression.operator);
            };
        } else if (postfixExpression.expression instanceof IndexExpressionNode indexExpression) {
            BuiltinClass value = this.visitExpression(indexExpression.expression);

            return switch (postfixExpression.operator) {
                case DOUBLE_PLUS -> {
                    BuiltinClass incrementedValue = value.call(this, "increment", List.of());

                    BuiltinClass index = this.visitExpression(indexExpression.index);

                    value.setIndex(index, incrementedValue);

                    yield value;
                }
                case DOUBLE_HYPHEN -> {
                    BuiltinClass decrementedValue = value.call(this, "decrement", List.of());

                    BuiltinClass index = this.visitExpression(indexExpression.index);

                    value.setIndex(index, decrementedValue);

                    yield value;
                }
                default -> throw ErrorHolder.unsupportedPostfixOperator(postfixExpression.operator);
            };
        } else {
            throw new SyntaxError("Cannot apply postfix operator to r-value");
        }
    }

    @Override
    public BuiltinClass visitResourceExpression(ResourceExpressionNode resourceExpression) {
        return new ResourceInstance(resourceExpression.value);
    }

    @Override
    public void visitReturnStatement(ReturnStatementNode returnStatement) {
        BuiltinClass value = this.visitExpression(returnStatement.value);

        throw new Return(value);
    }

    @Override
    public void visitScript(ScriptNode script) {
        script.statements.forEach(this::visitStatement);
    }

    @Override
    public void visitStatement(StatementNode statement) {
        if (statement instanceof BreakStatementNode breakStatement) {
            this.visitBreakStatement(breakStatement);
        } else if (statement instanceof ClassDeclarationStatementNode classDeclarationStatement) {
            this.visitClassDeclarationStatement(classDeclarationStatement);
        } else if (statement instanceof ContinueStatementNode continueStatement) {
            this.visitContinueStatement(continueStatement);
        } else if (statement instanceof ExpressionStatementNode expressionStatement) {
            this.visitExpressionStatement(expressionStatement);
        } else if (statement instanceof ForStatementNode forStatement) {
            this.visitForStatement(forStatement);
        } else if (statement instanceof FunctionDeclarationStatementNode functionDeclarationStatement) {
            this.visitFunctionDeclarationStatement(functionDeclarationStatement);
        } else if (statement instanceof IfStatementNode ifStatement) {
            this.visitIfStatement(ifStatement);
        } else if (statement instanceof ReturnStatementNode returnStatement) {
            this.visitReturnStatement(returnStatement);
        } else if (statement instanceof VariableDeclarationStatementNode variableDeclarationStatement) {
            this.visitVariableDeclarationStatement(variableDeclarationStatement);
        } else if (statement instanceof WhileStatementNode whileStatement) {
            this.visitWhileStatement(whileStatement);
        } else if (statement instanceof MethodDeclarationStatementNode methodDeclarationStatement) {
            this.visitMethodDeclarationStatement(methodDeclarationStatement);
        } else if (statement instanceof FieldDeclarationStatementNode fieldDeclarationStatement) {
            this.visitFieldDeclarationStatement(fieldDeclarationStatement);
        } else if (statement instanceof DeleteStatementNode deleteStatement) {
            this.visitDeleteStatement(deleteStatement);
        } else if (statement instanceof ForeachStatementNode foreachStatement) {
            this.visitForeachStatement(foreachStatement);
        } else if (statement instanceof EnumDeclarationStatementNode enumDeclarationStatement) {
            this.visitEnumDeclarationStatement(enumDeclarationStatement);
        } else if (statement instanceof ImportStatementNode importStatement) {
            this.visitImportStatement(importStatement);
        } else if (statement instanceof FromStatementNode fromStatementNode) {
            this.visitFromStatement(fromStatementNode);
        } else if (statement instanceof SwitchStatementNode switchStatementNode) {
            this.visitSwitchStatement(switchStatementNode);
        } else if (statement instanceof YieldStatementNode yieldStatement) {
            this.visitYieldStatement(yieldStatement);
        } else {
            throw ErrorHolder.unsupportedStatementNodeToInterpret(statement);
        }
    }

    @Override
    public BuiltinClass visitStringExpression(StringExpressionNode stringExpression) {
        return new StringInstance(stringExpression.value);
    }

    @Override
    public BuiltinClass visitSwitchExpression(SwitchExpressionNode switchExpression) {
        BuiltinClass expression = this.visitExpression(switchExpression.toSwitch);

        for (SwitchExpressionCase switchCase : switchExpression.switchBody.body) {
            for (ExpressionNode condition : switchCase.expressionNodes) {
                BuiltinClass conditionClass = this.visitExpression(condition);

                if (expression.equals(conditionClass)) {
                    try {
                        switchCase.statementNodes.forEach(this::visitStatement);
                    } catch (Yield yield) {
                        return this.visitExpression(yield.expression);
                    }

                    throw ErrorHolder.switchCaseDoesntYieldAValue(conditionClass);
                }
            }
        }

        if (switchExpression.switchBody.defaultCase != null) {
            try {
                switchExpression.switchBody.defaultCase.forEach(this::visitStatement);
            } catch (Yield yield) {
                return this.visitExpression(yield.expression);
            }
        }

        throw new InterpreterError("Unreachable");
    }

    @Override
    public void visitSwitchStatement(SwitchStatementNode switchStatement) {
        BuiltinClass expression = this.visitExpression(switchStatement.toSwitch);

        for (SwitchStatementCase switchCase : switchStatement.switchBody.body) {
            for (ExpressionNode condition : switchCase.expressionNodes) {
                if (expression.equals(this.visitExpression(condition))) {
                    switchCase.statementNodes.forEach(this::visitStatement);

                    return;
                }
            }
        }

        if (switchStatement.switchBody.defaultCase != null) {
            switchStatement.switchBody.defaultCase.forEach(this::visitStatement);
        }
    }

    @Override
    public BuiltinClass visitTernaryExpression(TernaryExpressionNode ternaryExpression) {
        return this.visitExpression(ternaryExpression.condition).toBoolean() ? this.visitExpression(ternaryExpression.ifTrue) : this.visitExpression(ternaryExpression.ifFalse);
    }

    @Override
    public BuiltinClass visitUnaryExpression(UnaryExpressionNode unaryExpression) {
        if (unaryExpression.expression instanceof IdentifierExpressionNode identifierExpression) {
            Variable variable = this.variableTable.getOrThrow(identifierExpression.value);
            BuiltinClass value = this.visitExpression(variable.value);

            return switch (unaryExpression.operator) {
                case DOUBLE_PLUS -> {
                    BuiltinClass incrementedValue = value.call(this, "increment", List.of());

                    this.variableTable.assign(identifierExpression.value, incrementedValue);

                    yield incrementedValue;
                }
                case DOUBLE_HYPHEN -> {
                    BuiltinClass decrementedValue = value.call(this, "decrement", List.of());

                    this.variableTable.assign(identifierExpression.value, decrementedValue);

                    yield decrementedValue;
                }
                default -> throw ErrorHolder.unsupportedPostfixOperator(unaryExpression.operator);
            };
        } else if (unaryExpression.expression instanceof GetExpressionNode getExpression) {
            BuiltinClass value = this.visitExpression(getExpression.expression);

            return switch (unaryExpression.operator) {
                case DOUBLE_PLUS -> {
                    BuiltinClass incrementedValue = value.call(this, "increment", List.of());

                    value.setProperty(getExpression.propertyName, incrementedValue);

                    yield incrementedValue;
                }
                case DOUBLE_HYPHEN -> {
                    BuiltinClass decrementedValue = value.call(this, "decrement", List.of());

                    value.setProperty(getExpression.propertyName, decrementedValue);

                    yield decrementedValue;
                }
                default -> throw ErrorHolder.unsupportedPostfixOperator(unaryExpression.operator);
            };
        } else if (unaryExpression.expression instanceof IndexExpressionNode indexExpression) {
            BuiltinClass value = this.visitExpression(indexExpression.expression);

            return switch (unaryExpression.operator) {
                case DOUBLE_PLUS -> {
                    BuiltinClass incrementedValue = value.call(this, "increment", List.of());

                    BuiltinClass index = this.visitExpression(indexExpression.index);

                    value.setIndex(index, incrementedValue);

                    yield incrementedValue;
                }
                case DOUBLE_HYPHEN -> {
                    BuiltinClass decrementedValue = value.call(this, "decrement", List.of());

                    BuiltinClass index = this.visitExpression(indexExpression.index);

                    value.setIndex(index, decrementedValue);

                    yield decrementedValue;
                }
                default -> throw ErrorHolder.unsupportedPostfixOperator(unaryExpression.operator);
            };
        } else {
            BuiltinClass value = this.visitExpression(unaryExpression.expression);

            return switch (unaryExpression.operator) {
                case EXCLAMATION_MARK -> value.call(this, "logicalNot", List.of());
                case HYPHEN -> value.call(this, "negate", List.of());
                default -> throw ErrorHolder.unsupportedUnaryOperator(unaryExpression.operator);
            };
        }
    }

    @Override
    public BuiltinClass visitVariableAssignmentExpression(VariableAssignmentExpressionNode variableAssignmentExpression) {
        BuiltinClass value = this.visitExpression(variableAssignmentExpression.value);

        if (variableAssignmentExpression.expression instanceof IdentifierExpressionNode identifierExpression) {
            this.variableTable.assign(identifierExpression.value, value);

            return value;
        } else if (variableAssignmentExpression.expression instanceof GetExpressionNode getExpression) {
            BuiltinClass assignee = this.visitExpression(getExpression.expression);

            assignee.setProperty(getExpression.propertyName, value);

            return value;
        } else if (variableAssignmentExpression.expression instanceof IndexExpressionNode indexExpression) {
            BuiltinClass assignee = this.visitExpression(indexExpression.expression);
            BuiltinClass index = this.visitExpression(indexExpression.index);

            assignee.setIndex(index, value);

            return value;
        }

        throw new SyntaxError("Cannot assign to r-value");
    }

    @Override
    public void visitVariableDeclarationStatement(VariableDeclarationStatementNode variableDeclarationStatement) {
        BuiltinClass value = this.visitExpression(variableDeclarationStatement.value);

        this.variableTable.declare(variableDeclarationStatement.accessModifiers, variableDeclarationStatement.name, value);
    }

    @Override
    public void visitWhileStatement(WhileStatementNode whileStatement) {
        int loops = 0;
        int maxLoops = Sculk.server.getGameRules().getInt(SculkGamerules.MAX_LOOPS);

        while_loop:
        while (true) {
            this.variableTable.enterScope();

            BuiltinClass condition = this.visitExpression(whileStatement.condition);

            if (!condition.instanceOf(new BooleanType())) {
                throw ErrorHolder.invalidWhileLoopConditionType(new BooleanType(), condition.getType());
            }

            if (!condition.toBoolean()) {
                break;
            }

            for (StatementNode statement : whileStatement.body) {
                try {
                    this.visitStatement(statement);
                } catch (Break ignored) {
                    break while_loop;
                } catch (Continue ignored) {
                    break;
                }
            }

            if (++loops > maxLoops) {
                throw new StackOverflowError("While-loop ran more than " + maxLoops + " times");
            }

            this.variableTable.exitScope();
        }
    }

    @Override
    public void visitYieldStatement(YieldStatementNode yieldStatement) {
        throw new Yield(yieldStatement.expression);
    }
}
