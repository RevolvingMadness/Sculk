package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.BlockPosType;
import net.minecraft.util.math.BlockPos;

public class BlockPosInstance extends BuiltinClass {
    public final BlockPos value;

    public BlockPosInstance(BlockPos value) {
        this.value = value;
    }

    @Override
    public BuiltinType getType() {
        return new BlockPosType();
    }

    @Override
    public String toStringType() {
        return this.value.toString();
    }
}
