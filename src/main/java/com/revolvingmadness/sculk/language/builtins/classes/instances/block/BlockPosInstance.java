package com.revolvingmadness.sculk.language.builtins.classes.instances.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockPosType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Objects;

public class BlockPosInstance extends BuiltinClass {
    public final BlockPos value;

    public BlockPosInstance(BlockPos value) {
        this.value = value;

        this.variableScope.declare(List.of(TokenType.CONST), "x", new IntegerInstance(value.getX()));
        this.variableScope.declare(List.of(TokenType.CONST), "y", new IntegerInstance(value.getY()));
        this.variableScope.declare(List.of(TokenType.CONST), "z", new IntegerInstance(value.getZ()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        BlockPosInstance that = (BlockPosInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return BlockPosType.TYPE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public BlockPos toBlockPos() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
