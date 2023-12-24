package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.ObjectClass;
import com.revolvingmadness.testing.language.builtins.classes.types.*;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.errors.StackOverflowError;
import com.revolvingmadness.testing.language.interpreter.errors.*;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.*;
import com.revolvingmadness.testing.language.user_defined.UserDefinedClass;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter implements Visitor {
    public final Integer maxArguments;
    public final Integer maxLoops;
    public final VariableTable variableTable;

    public Interpreter() {
        this.maxArguments = Testing.server.getGameRules().getInt(TestingGamerules.MAX_LOOPS);
        this.maxLoops = Testing.server.getGameRules().getInt(TestingGamerules.MAX_ARGUMENTS);
        this.variableTable = new VariableTable();
    }

    @Override
    public BaseClassExpressionNode visitBinaryExpression(BinaryExpressionNode binaryExpression) {
        BaseClassExpressionNode left = this.visitExpression(binaryExpression.left);
        BaseClassExpressionNode right = this.visitExpression(binaryExpression.right);

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
            default -> throw new InterpreterError("Unsupported binary operator '" + binaryExpression.operator + "'");
        };
    }

    @Override
    public void visitBreakStatement(BreakStatementNode breakStatement) {
        throw new Break();
    }

    @Override
    public BaseClassExpressionNode visitCallExpression(CallExpressionNode callExpression) {
        BaseClassExpressionNode callee = this.visitExpression(callExpression.callee);

        List<BaseClassExpressionNode> arguments = new ArrayList<>();

        callExpression.arguments.forEach(argumentExpression -> arguments.add(this.visitExpression(argumentExpression)));

        return callee.call(this, arguments);
    }

    @Override
    public void visitClassDeclarationStatement(ClassDeclarationStatementNode classDeclarationStatement) {
        this.variableTable.enterScope();

        classDeclarationStatement.body.forEach(this::visitStatement);

        VariableScope variableScope = this.variableTable.exitScope();

        BaseClassExpressionNode superClass;

        if (classDeclarationStatement.superClassName != null) {
            Variable superClassVariable = this.variableTable.getOrThrow(classDeclarationStatement.superClassName);

            superClass = superClassVariable.value;
        } else {
            superClass = new ObjectClass();
        }

        this.variableTable.declare(classDeclarationStatement.isConstant, classDeclarationStatement.name, new UserDefinedClass(classDeclarationStatement.name, superClass, variableScope));
    }

    @Override
    public void visitContinueStatement(ContinueStatementNode continueStatement) {
        throw new Continue();
    }

    @Override
    public BaseClassExpressionNode visitDictionaryExpression(DictionaryExpressionNode dictionaryExpression) {
        Map<BaseClassExpressionNode, BaseClassExpressionNode> dictionary = new HashMap<>();

        dictionaryExpression.value.forEach((keyEx, valueEx) -> {
            BaseClassExpressionNode key = this.visitExpression(keyEx);
            BaseClassExpressionNode value = this.visitExpression(valueEx);

            dictionary.put(key, value);
        });

        return new DictionaryClass(dictionary);
    }

    @Override
    public BaseClassExpressionNode visitExpression(ExpressionNode expression) {
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
        } else if (expression instanceof BaseClassExpressionNode baseClassExpression) {
            return baseClassExpression;
        } else if (expression instanceof DictionaryExpressionNode dictionaryExpression) {
            return this.visitDictionaryExpression(dictionaryExpression);
        } else if (expression instanceof IndexExpressionNode indexExpression) {
            return this.visitIndexExpression(indexExpression);
        } else if (expression instanceof ListExpressionNode listExpression) {
            return this.visitListExpression(listExpression);
        } else if (expression instanceof PostfixExpressionNode postfixExpression) {
            return this.visitPostfixExpression(postfixExpression);
        } else {
            throw new InterpreterError("Unsupported node to interpret '" + expression.getClass().getSimpleName() + "'");
        }
    }

    @Override
    public void visitExpressionStatement(ExpressionStatementNode expressionStatement) {
        this.visitExpression(expressionStatement.expression);
    }

    @Override
    public void visitFieldDeclarationStatement(FieldDeclarationStatementNode fieldDeclarationStatement) {
        BaseClassExpressionNode value = this.visitExpression(fieldDeclarationStatement.value);

        this.variableTable.declare(fieldDeclarationStatement.accessModifiers, fieldDeclarationStatement.isConstant, fieldDeclarationStatement.name, value);
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
            BaseClassExpressionNode condition = this.visitExpression(forStatement.condition);

            if (!condition.getType().equals("Boolean")) {
                throw new TypeError("For-loop update requires type 'Integer' but got '" + condition.getType() + "'");
            }

            if (!((BooleanClass) condition).value) {
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
        this.variableTable.declare(functionDeclarationStatement.isConstant, functionDeclarationStatement.name, new FunctionClass(functionDeclarationStatement.name, functionDeclarationStatement.arguments, functionDeclarationStatement.body));
    }

    @Override
    public BaseClassExpressionNode visitGetExpression(GetExpressionNode getExpression) {
        BaseClassExpressionNode expression = this.visitExpression(getExpression.expression);

        return expression.getProperty(getExpression.propertyName);
    }

    @Override
    public BaseClassExpressionNode visitIdentifierExpression(IdentifierExpressionNode identifierExpression) {
        return this.variableTable.getOrThrow(identifierExpression.value).value;
    }

    @Override
    public void visitIfStatement(IfStatementNode ifStatement) {
        BaseClassExpressionNode ifCondition = this.visitExpression(ifStatement.ifConditionPair.getLeft());

        if (!ifCondition.getType().equals("Boolean")) {
            throw new TypeError("If statement requires type 'Boolean' but got '" + ifCondition.getType() + "'");
        }

        if (((BooleanClass) ifCondition).value) {
            for (StatementNode statement : ifStatement.ifConditionPair.getRight()) {
                this.visitStatement(statement);
            }
            return;
        }

        for (Pair<ExpressionNode, List<StatementNode>> elseIfConditionPair : ifStatement.elseIfConditionPairs) {
            BaseClassExpressionNode elseIfCondition = this.visitExpression(elseIfConditionPair.getLeft());
            List<StatementNode> elseIfBody = elseIfConditionPair.getRight();

            if (!elseIfCondition.getType().equals("Boolean")) {
                throw new TypeError("If statement requires type 'Boolean' but got '" + ifCondition.getType() + "'");
            }

            if (((BooleanClass) elseIfCondition).value) {
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
    public BaseClassExpressionNode visitIndexExpression(IndexExpressionNode indexExpression) {
        BaseClassExpressionNode expression = this.visitExpression(indexExpression.expression);
        BaseClassExpressionNode index = this.visitExpression(indexExpression.index);

        return expression.getIndex(index);
    }

    @Override
    public BaseClassExpressionNode visitListExpression(ListExpressionNode listExpression) {
        List<BaseClassExpressionNode> list = new ArrayList<>();

        listExpression.value.forEach(expression -> list.add(this.visitExpression(expression)));

        return new ListClass(list);
    }

    @Override
    public void visitMethodDeclarationStatement(MethodDeclarationStatementNode methodDeclarationStatement) {
        this.variableTable.declare(methodDeclarationStatement.isConstant, methodDeclarationStatement.name, new MethodClass(methodDeclarationStatement.accessModifiers, methodDeclarationStatement.isConstant, methodDeclarationStatement.name, methodDeclarationStatement.arguments, methodDeclarationStatement.body));
    }

    @Override
    public BaseClassExpressionNode visitPostfixExpression(PostfixExpressionNode postfixExpression) {
        BaseClassExpressionNode expression = this.visitExpression(postfixExpression.expression);

        return switch (postfixExpression.operator) {
            case DOUBLE_PLUS -> expression.call(this, "increment", List.of());
            case DOUBLE_HYPHEN -> expression.call(this, "decrement", List.of());
            default -> throw new InterpreterError("Unsupported postfix operator '" + postfixExpression.operator + "'");
        };
    }

    @Override
    public void visitReturnStatement(ReturnStatementNode returnStatement) {
        BaseClassExpressionNode value = this.visitExpression(returnStatement.value);

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
        } else {
            throw new InterpreterError("Unsupported node to interpret '" + statement.getClass().getSimpleName() + "'");
        }
    }

    @Override
    public BaseClassExpressionNode visitUnaryExpression(UnaryExpressionNode unaryExpression) {
        BaseClassExpressionNode value = this.visitExpression(unaryExpression.value);

        return switch (unaryExpression.operator) {
            case EXCLAMATION_MARK -> value.call(this, "logicalNot", List.of());
            case HYPHEN -> value.call(this, "negate", List.of());
            default -> throw new InterpreterError("Unknown unary operator '" + unaryExpression.operator + "'");
        };
    }

    @Override
    public BaseClassExpressionNode visitVariableAssignmentExpression(VariableAssignmentExpressionNode variableAssignmentExpression) {
        BaseClassExpressionNode value = this.visitExpression(variableAssignmentExpression.value);

        if (variableAssignmentExpression.expression instanceof IdentifierExpressionNode identifierExpression) {
            this.variableTable.assign(identifierExpression.value, value);

            return value;
        } else if (variableAssignmentExpression.expression instanceof GetExpressionNode getExpression) {
            BaseClassExpressionNode assignee = this.visitExpression(getExpression.expression);

            assignee.setProperty(getExpression.propertyName, value);

            return value;
        } else if (variableAssignmentExpression.expression instanceof IndexExpressionNode indexExpression) {
            BaseClassExpressionNode assignee = this.visitExpression(indexExpression.expression);
            BaseClassExpressionNode index = this.visitExpression(indexExpression.index);

            assignee.setIndex(index, value);

            return value;
        }

        throw new SyntaxError("Cannot assign to r-value");
    }

    @Override
    public void visitVariableDeclarationStatement(VariableDeclarationStatementNode variableDeclarationStatement) {
        BaseClassExpressionNode value = this.visitExpression(variableDeclarationStatement.value);

        this.variableTable.declare(variableDeclarationStatement.isConstant, variableDeclarationStatement.name, value);
    }

    @Override
    public void visitWhileStatement(WhileStatementNode whileStatement) {
        int loops = 0;

        while_loop:
        while (true) {
            BaseClassExpressionNode condition = this.visitExpression(whileStatement.condition);

            if (!condition.getType().equals("Boolean")) {
                throw new TypeError("For-loop update requires type 'Integer' but got '" + condition.getType() + "'");
            }

            if (!((BooleanClass) condition).value) {
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
