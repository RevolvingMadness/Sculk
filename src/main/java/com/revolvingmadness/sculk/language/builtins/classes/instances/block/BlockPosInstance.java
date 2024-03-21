package com.revolvingmadness.sculk.language.builtins.classes.instances.block;

import com.revolvingmadness.sculk.language.NBTSerializer;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockPosClassType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Objects;

public class BlockPosInstance extends NBTBuiltinClass {
    public final BlockPos value;

    public BlockPosInstance(BlockPos value) {
        super(BlockPosClassType.TYPE);
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
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public BlockPos toBlockPos() {
        return this.value;
    }

    @Override
    public NbtElement toNBTElement() {
        return NBTSerializer.serializeBlockPos(this.value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
