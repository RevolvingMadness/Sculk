package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.BlockPosType;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class BlockPosInstance extends BuiltinClass {
    public final BlockPos value;

    public BlockPosInstance(BlockPos value) {
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
        BlockPosInstance that = (BlockPosInstance) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new BlockPosType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toStringType() {
        return this.value.toString();
    }
}
