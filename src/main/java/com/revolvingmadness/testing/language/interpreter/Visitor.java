package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.*;

public interface Visitor {
    BaseClassExpressionNode visitBinaryExpression(BinaryExpressionNode binaryExpression);

    void visitBreakStatement(BreakStatementNode breakStatement);

    BaseClassExpressionNode visitCallExpression(CallExpressionNode callExpression);

    void visitClassDeclarationStatement(ClassDeclarationStatementNode classDeclarationStatement);

    void visitContinueStatement(ContinueStatementNode continueStatement);

    BaseClassExpressionNode visitExpression(ExpressionNode expression);

    void visitExpressionStatement(ExpressionStatementNode expressionStatement);

    void visitForStatement(ForStatementNode forStatement);

    void visitFunctionDeclarationStatement(FunctionDeclarationStatementNode functionDeclarationStatement);

    BaseClassExpressionNode visitGetExpression(GetExpressionNode getExpression);

    BaseClassExpressionNode visitIdentifierExpression(IdentifierExpressionNode identifierExpression);

    void visitIfStatement(IfStatementNode ifStatement);

    void visitReturnStatement(ReturnStatementNode returnStatement);

    void visitScript(ScriptNode script);

    void visitStatement(StatementNode statement);

    BaseClassExpressionNode visitUnaryExpression(UnaryExpressionNode unaryExpression);

    BaseClassExpressionNode visitVariableAssignmentExpression(VariableAssignmentExpressionNode variableAssignmentExpression);

    void visitVariableDeclarationStatement(VariableDeclarationStatementNode variableDeclarationStatement);

    void visitWhileStatement(WhileStatementNode whileStatement);
}
