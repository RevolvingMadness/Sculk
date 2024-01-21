package com.revolvingmadness.sculk.language.interpreter;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.parser.nodes.ScriptNode;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.*;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.literal_expression_nodes.*;
import com.revolvingmadness.sculk.language.parser.nodes.statement_nodes.*;

public interface Visitor {
    BuiltinClass visitBinaryExpression(BinaryExpressionNode binaryExpression);

    BuiltinClass visitBooleanExpression(BooleanExpressionNode booleanExpression);

    void visitBreakStatement(BreakStatementNode breakStatement);

    BuiltinClass visitCallExpression(CallExpressionNode callExpression);

    void visitClassDeclarationStatement(ClassDeclarationStatementNode classDeclarationStatement);

    void visitContinueStatement(ContinueStatementNode continueStatement);

    void visitDeleteStatement(DeleteStatementNode deleteStatement);

    BuiltinClass visitDictionaryExpression(DictionaryExpressionNode dictionaryExpression);

    void visitEnumDeclarationStatement(EnumDeclarationStatementNode enumDeclarationStatement);

    BuiltinClass visitExpression(ExpressionNode expression);

    void visitExpressionStatement(ExpressionStatementNode expressionStatement);

    void visitFieldDeclarationStatement(FieldDeclarationStatementNode fieldDeclarationStatement);

    BuiltinClass visitFloatExpression(FloatExpressionNode floatExpression);

    void visitForStatement(ForStatementNode forStatement);

    void visitForeachStatement(ForeachStatementNode foreachStatement);

    void visitFromStatement(FromStatementNode fromStatement);

    void visitFunctionDeclarationStatement(FunctionDeclarationStatementNode functionDeclarationStatement);

    BuiltinClass visitFunctionExpression(FunctionExpressionNode functionExpression);

    BuiltinClass visitGetExpression(GetExpressionNode getExpression);

    BuiltinClass visitIdentifierExpression(IdentifierExpressionNode identifierExpression);

    void visitIfStatement(IfStatementNode ifStatement);

    void visitImportStatement(ImportStatementNode importStatement);

    BuiltinClass visitIndexExpression(IndexExpressionNode indexExpression);

    BuiltinClass visitIntegerExpression(IntegerExpressionNode integerExpression);

    BuiltinClass visitListExpression(ListExpressionNode listExpression);

    BuiltinClass visitLiteralExpression(LiteralExpressionNode literalExpression);

    void visitMethodDeclarationStatement(MethodDeclarationStatementNode methodDeclarationStatement);

    BuiltinClass visitNullExpression(NullExpressionNode nullExpression);

    BuiltinClass visitPostfixExpression(PostfixExpressionNode postfixExpression);

    BuiltinClass visitResourceExpression(ResourceExpressionNode resourceExpression);

    void visitReturnStatement(ReturnStatementNode returnStatement);

    void visitScript(ScriptNode script);

    void visitStatement(StatementNode statement);

    BuiltinClass visitStringExpression(StringExpressionNode stringExpression);

    BuiltinClass visitSwitchExpression(SwitchExpressionNode switchExpression);

    void visitSwitchStatement(SwitchStatementNode switchStatement);

    BuiltinClass visitTernaryExpression(TernaryExpressionNode ternaryExpression);

    BuiltinClass visitUnaryExpression(UnaryExpressionNode unaryExpression);

    BuiltinClass visitVariableAssignmentExpression(VariableAssignmentExpressionNode variableAssignmentExpression);

    void visitVariableDeclarationStatement(VariableDeclarationStatementNode variableDeclarationStatement);

    void visitWhileStatement(WhileStatementNode whileStatement);

    void visitYieldStatement(YieldStatementNode yieldStatement);
}
