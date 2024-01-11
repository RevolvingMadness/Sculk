package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.Vec3dType;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class Vec3dInstance extends BuiltinClass {
    public final Vec3d value;

    public Vec3dInstance(Vec3d value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Vec3dInstance that = (Vec3dInstance) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new Vec3dType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toStringType() {
        return this.value.toString();
    }

    @Override
    public Vec3d toVec3d() {
        return this.value;
    }
}
