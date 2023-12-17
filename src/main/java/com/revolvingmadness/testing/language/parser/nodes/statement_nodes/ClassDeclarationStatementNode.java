package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.ClassExpressionNode;

import java.util.List;

public class ClassDeclarationStatementNode implements StatementNode {
    public final List<StatementNode> body;
    public final boolean isConstant;
    public final IdentifierExpressionNode name;
    public final IdentifierExpressionNode superClassName;

    public ClassDeclarationStatementNode(boolean isConstant, IdentifierExpressionNode name, IdentifierExpressionNode superClassName, List<StatementNode> body) {
        this.isConstant = isConstant;
        this.name = name;
        this.superClassName = superClassName;
        this.body = body;
    }

    @Override
    public void interpret(ScriptNode script) {
        script.variableTable.enterScope();

        this.body.forEach(statement -> statement.interpret(script));

        VariableScope variableScope = script.variableTable.exitScope();

        ClassExpressionNode superClass = null;

        if (this.superClassName != null) {
            Variable superClassVariable = script.variableTable.getOrThrow(this.superClassName);

            if (!(superClassVariable.value instanceof ClassExpressionNode)) {
                throw new RuntimeException("Cannot extend from type '" + superClassVariable.value.getType() + "'");
            }

            superClass = (ClassExpressionNode) superClassVariable.value;
        }

        script.variableTable.declare(this.isConstant, this.name, new ClassExpressionNode(this.name, superClass, variableScope));
    }
}
