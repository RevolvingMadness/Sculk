package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.*;
import com.revolvingmadness.testing.language.builtins.classes.types.BooleanType;
import com.revolvingmadness.testing.language.builtins.classes.types.IntegerType;
import com.revolvingmadness.testing.language.builtins.classes.types.ObjectType;
import com.revolvingmadness.testing.language.builtins.classes.types.UserDefinedType;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.interpreter.errors.Break;
import com.revolvingmadness.testing.language.interpreter.errors.Continue;
import com.revolvingmadness.testing.language.interpreter.errors.Return;
import com.revolvingmadness.testing.language.interpreter.errors.StackOverflowError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.*;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter implements Visitor {
    public final long maxArguments;
    public final long maxLoops;
    public final VariableTable variableTable;

    public Interpreter() {
        this.maxArguments = Testing.server.getGameRules().getInt(TestingGamerules.MAX_ARGUMENTS);
        this.maxLoops = Testing.server.getGameRules().getInt(TestingGamerules.MAX_LOOPS);
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
        long maxLoops = Testing.server.getGameRules().getInt(TestingGamerules.MAX_LOOPS);

        if (forStatement.initialization != null) {
            this.visitStatement(forStatement.initialization);
        }

        while_loop:
        while (true) {
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
        }
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
                throw ErrorHolder.ifStatementConditionRequiresType(new BooleanType(), ifCondition.getType());
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
        BuiltinClass expression = this.visitExpression(postfixExpression.expression);

        return switch (postfixExpression.operator) {
            case DOUBLE_PLUS -> expression.call(this, "increment", List.of());
            case DOUBLE_HYPHEN -> expression.call(this, "decrement", List.of());
            default -> throw ErrorHolder.unsupportedPostfixOperator(postfixExpression.operator);
        };
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
        this.variableTable.reset();
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
        } else {
            throw ErrorHolder.unsupportedStatementNodeToInterpret(statement);
        }
    }

    @Override
    public BuiltinClass visitStringExpression(StringExpressionNode stringExpression) {
        return new StringInstance(stringExpression.value);
    }

    @Override
    public BuiltinClass visitUnaryExpression(UnaryExpressionNode unaryExpression) {
        BuiltinClass value = this.visitExpression(unaryExpression.value);

        return switch (unaryExpression.operator) {
            case EXCLAMATION_MARK -> value.call(this, "logicalNot", List.of());
            case HYPHEN -> value.call(this, "negate", List.of());
            default -> throw ErrorHolder.unsupportedUnaryOperator(unaryExpression.operator);
        };
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

        while_loop:
        while (true) {
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

            if (++loops > this.maxLoops) {
                throw new StackOverflowError("While-loop ran more than " + this.maxLoops + " times");
            }
        }
    }
}
