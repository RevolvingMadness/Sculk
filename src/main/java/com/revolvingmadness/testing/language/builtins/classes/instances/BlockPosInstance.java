package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.BlockPosType;
import com.revolvingmadness.testing.language.lexer.TokenType;
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
    public BlockPos toBlockPos() {
        return this.value;
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
        return new BlockPosType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toStringType() {
        return this.value.toString();
    }
}
