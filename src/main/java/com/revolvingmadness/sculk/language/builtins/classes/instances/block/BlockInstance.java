package com.revolvingmadness.sculk.language.builtins.classes.instances.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockClassType;
import net.minecraft.block.Block;

import java.util.Objects;

public class BlockInstance extends BuiltinClass {
    public final Block value;

    public BlockInstance(Block value) {
        super(BlockClassType.TYPE);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        BlockInstance that = (BlockInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public Block toBlock() {
        return this.value;
    }
}
