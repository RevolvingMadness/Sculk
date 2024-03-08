package com.revolvingmadness.sculk.language.builtins.classes.instances.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockClassType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

public class SlabBlockInstance extends BuiltinClass {
    public final Identifier id;
    public final SlabBlock value;

    public SlabBlockInstance(Identifier id, SlabBlock value) {
        super(BlockClassType.TYPE);
        this.id = id;
        this.value = value;

        this.variableScope.declare(List.of(TokenType.CONST), "id", new StringInstance(id.toString()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        SlabBlockInstance that = (SlabBlockInstance) o;
        return Objects.equals(this.id, that.id) && Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.value);
    }

    @Override
    public Block toBlock() {
        return this.value;
    }

    @Override
    public BlockInstance toBlockInstance() {
        return new BlockInstance(this.id, this.value);
    }
}
