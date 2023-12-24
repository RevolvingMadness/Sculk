package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.FloatClass;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class Vec3dClass extends BaseClassExpressionNode {
    public final Vec3d vec3d;

    public Vec3dClass(Vec3d vec3d) {
        this.vec3d = vec3d;

        this.variableScope.declare(true, new IdentifierExpressionNode("x"), new FloatClass(this.vec3d.getX()));
        this.variableScope.declare(true, new IdentifierExpressionNode("y"), new FloatClass(this.vec3d.getY()));
        this.variableScope.declare(true, new IdentifierExpressionNode("z"), new FloatClass(this.vec3d.getZ()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Vec3dClass that = (Vec3dClass) o;
        return Objects.equals(this.vec3d, that.vec3d);
    }

    @Override
    public String getType() {
        return "Vec3d";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.vec3d);
    }
}
