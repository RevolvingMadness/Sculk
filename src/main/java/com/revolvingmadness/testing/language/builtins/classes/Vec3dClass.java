package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.FloatExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;
import net.minecraft.util.math.Vec3d;

public class Vec3dClass implements LiteralExpressionNode {
    public final VariableScope variableScope;

    public Vec3dClass(Vec3d vec3d) {
        this.variableScope = new VariableScope();

        this.variableScope.declare(true, new IdentifierExpressionNode("x"), new FloatExpressionNode(vec3d.getX()));
        this.variableScope.declare(true, new IdentifierExpressionNode("y"), new FloatExpressionNode(vec3d.getY()));
        this.variableScope.declare(true, new IdentifierExpressionNode("z"), new FloatExpressionNode(vec3d.getZ()));
    }

    @Override
    public Variable getProperty(IdentifierExpressionNode propertyName) {
        return this.variableScope.getOrThrow(propertyName);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("Vec3d");
    }
}
