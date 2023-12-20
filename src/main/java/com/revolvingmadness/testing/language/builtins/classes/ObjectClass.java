package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;

public class ObjectClass extends BuiltinClass {
    public ObjectClass() {
        super(null);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("Object");
    }
}