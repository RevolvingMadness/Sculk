package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.ClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.errors.StackOverflowError;
import com.revolvingmadness.testing.language.interpreter.errors.*;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.*;

import java.util.List;

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
            case PLUS, DOUBLE_PLUS -> left.call(this, new IdentifierExpressionNode("add"), List.of(right));
            case HYPHEN, DOUBLE_HYPHEN -> left.call(this, new IdentifierExpressionNode("subtract"), List.of(right));
            case STAR -> left.call(this, new IdentifierExpressionNode("multiply"), List.of(right));
            case FSLASH -> left.call(this, new IdentifierExpressionNode("divide"), List.of(right));
            case CARET -> left.call(this, new IdentifierExpressionNode("exponentiate"), List.of(right));
            case PERCENT -> left.call(this, new IdentifierExpressionNode("mod"), List.of(right));
            case EQUAL_TO -> left.call(this, new IdentifierExpressionNode("equalTo"), List.of(right));
            case NOT_EQUAL_TO -> left.call(this, new IdentifierExpressionNode("notEqualTo"), List.of(right));
            case GREATER_THAN -> left.call(this, new IdentifierExpressionNode("greaterThan"), List.of(right));
            case GREATER_THAN_OR_EQUAL_TO ->
                    left.call(this, new IdentifierExpressionNode("greaterThanOrEqualTo"), List.of(right));
            case LESS_THAN -> left.call(this, new IdentifierExpressionNode("lessThan"), List.of(right));
            case LESS_THAN_OR_EQUAL_TO ->
                    left.call(this, new IdentifierExpressionNode("lessThanOrEqualTo"), List.of(right));
            case DOUBLE_AMPERSAND -> left.call(this, new IdentifierExpressionNode("booleanAnd"), List.of(right));
            case DOUBLE_PIPE -> left.call(this, new IdentifierExpressionNode("booleanOr"), List.of(right));
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

        return callee.call(this, callExpression.arguments);
    }

    @Override
    public void visitClassDeclarationStatement(ClassDeclarationStatementNode classDeclarationStatement) {
        this.variableTable.enterScope();

        classDeclarationStatement.body.forEach(this::visitStatement);

        VariableScope variableScope = this.variableTable.exitScope();

        BaseClassExpressionNode superClass = null;

        if (classDeclarationStatement.superClassName != null) {
            Variable superClassVariable = this.variableTable.getOrThrow(classDeclarationStatement.superClassName);

            superClass = superClassVariable.value;
        }

        this.variableTable.declare(classDeclarationStatement.isConstant, classDeclarationStatement.name, new ClassExpressionNode(classDeclarationStatement.name, superClass, variableScope));
    }

    @Override
    public void visitContinueStatement(ContinueStatementNode continueStatement) {
        throw new Continue();
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

        } else {
            throw new InterpreterError("Unsupported node to interpret '" + expression.getClass().getSimpleName() + "'");
        }
    }

    @Override
    public void visitExpressionStatement(ExpressionStatementNode expressionStatement) {
        this.visitExpression(expressionStatement.expression);
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
                throw new TypeError("For-loop update requires type 'int' but got '" + condition.getType() + "'");
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

    }

    @Override
    public BaseClassExpressionNode visitGetExpression(GetExpressionNode getExpression) {
        BaseClassExpressionNode expression = this.visitExpression(getExpression.expression);

        return expression.getProperty(getExpression.propertyName);
    }

    @Override
    public BaseClassExpressionNode visitIdentifierExpression(IdentifierExpressionNode identifierExpression) {
        return this.variableTable.getOrThrow(identifierExpression).value;
    }

    @Override
    public void visitIfStatement(IfStatementNode ifStatement) {
        BaseClassExpressionNode condition = this.visitExpression(ifStatement.condition);

        if (!condition.getType().equals("Boolean")) {
            throw new TypeError("For-loop update requires type 'int' but got '" + condition.getType() + "'");
        }

        if (((BooleanClass) condition).value) {
            for (StatementNode statement : ifStatement.body) {
                try {
                    this.visitStatement(statement);
                } catch (Break ignored) {
                    break;
                }
            }
        }
    }

    @Override
    public void visitReturnStatement(ReturnStatementNode returnStatement) {
        BaseClassExpressionNode value = this.visitExpression(returnStatement.value);

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
        } else {
            throw new InterpreterError("Unsupported node to interpret '" + statement.getClass().getSimpleName() + "'");
        }
    }

    @Override
    public BaseClassExpressionNode visitUnaryExpression(UnaryExpressionNode unaryExpression) {
        BaseClassExpressionNode value = this.visitExpression(unaryExpression.value);

        return switch (unaryExpression.operator) {
            case EXCLAMATION_MARK -> value.call(this, new IdentifierExpressionNode("logicalNot"), List.of());
            case HYPHEN -> value.call(this, new IdentifierExpressionNode("negate"), List.of());
            default -> throw new InterpreterError("Unknown unary operator '" + unaryExpression.operator + "'");
        };
    }

    @Override
    public BaseClassExpressionNode visitVariableAssignmentExpression(VariableAssignmentExpressionNode variableAssignmentExpression) {
        BaseClassExpressionNode value = this.visitExpression(variableAssignmentExpression.value);

        this.variableTable.assign(variableAssignmentExpression.expression, value);

        return value;
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
                throw new TypeError("For-loop update requires type 'int' but got '" + condition.getType() + "'");
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
