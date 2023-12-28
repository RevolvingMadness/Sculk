package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.*;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.*;

public interface Visitor {
    BaseClassExpressionNode visitBinaryExpression(BinaryExpressionNode binaryExpression);

    BaseClassExpressionNode visitBooleanExpression(BooleanExpressionNode booleanExpression);

    void visitBreakStatement(BreakStatementNode breakStatement);

    BaseClassExpressionNode visitCallExpression(CallExpressionNode callExpression);

    void visitClassDeclarationStatement(ClassDeclarationStatementNode classDeclarationStatement);

    void visitContinueStatement(ContinueStatementNode continueStatement);

    void visitDeleteStatement(DeleteStatementNode deleteStatement);

    BaseClassExpressionNode visitDictionaryExpression(DictionaryExpressionNode dictionaryExpression);

    BaseClassExpressionNode visitExpression(ExpressionNode expression);

    void visitExpressionStatement(ExpressionStatementNode expressionStatement);

    void visitFieldDeclarationStatement(FieldDeclarationStatementNode fieldDeclarationStatement);

    BaseClassExpressionNode visitFloatExpression(FloatExpressionNode floatExpression);

    void visitForStatement(ForStatementNode forStatement);

    void visitFunctionDeclarationStatement(FunctionDeclarationStatementNode functionDeclarationStatement);

    BaseClassExpressionNode visitFunctionExpression(FunctionExpressionNode functionExpression);

    BaseClassExpressionNode visitGetExpression(GetExpressionNode getExpression);

    BaseClassExpressionNode visitIdentifierExpression(IdentifierExpressionNode identifierExpression);

    void visitIfStatement(IfStatementNode ifStatement);

    BaseClassExpressionNode visitIndexExpression(IndexExpressionNode indexExpression);

    BaseClassExpressionNode visitIntegerExpression(IntegerExpressionNode integerExpression);

    BaseClassExpressionNode visitListExpression(ListExpressionNode listExpression);

    BaseClassExpressionNode visitLiteralExpression(LiteralExpressionNode literalExpression);

    void visitMethodDeclarationStatement(MethodDeclarationStatementNode methodDeclarationStatement);

    BaseClassExpressionNode visitNullExpression(NullExpressionNode nullExpression);

    BaseClassExpressionNode visitPostfixExpression(PostfixExpressionNode postfixExpression);

    BaseClassExpressionNode visitResourceExpression(ResourceExpressionNode resourceExpression);

    void visitReturnStatement(ReturnStatementNode returnStatement);

    void visitScript(ScriptNode script);

    void visitStatement(StatementNode statement);

    BaseClassExpressionNode visitStringExpression(StringExpressionNode stringExpression);

    BaseClassExpressionNode visitUnaryExpression(UnaryExpressionNode unaryExpression);

    BaseClassExpressionNode visitVariableAssignmentExpression(VariableAssignmentExpressionNode variableAssignmentExpression);

    void visitVariableDeclarationStatement(VariableDeclarationStatementNode variableDeclarationStatement);

    void visitWhileStatement(WhileStatementNode whileStatement);
}
