package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.FloatClass;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import net.minecraft.util.math.Vec3d;

public class Vec3dClass extends BaseClassExpressionNode {
    public Vec3dClass(Vec3d vec3d) {
        this.variableScope.declare(true, new IdentifierExpressionNode("x"), new FloatClass(vec3d.getX()));
        this.variableScope.declare(true, new IdentifierExpressionNode("y"), new FloatClass(vec3d.getY()));
        this.variableScope.declare(true, new IdentifierExpressionNode("z"), new FloatClass(vec3d.getZ()));
    }

    @Override
    public String getType() {
        return "Vec3d";
    }
}
