package com.revolvingmadness.sculk.language.builtins.classes.instances.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockClassType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

public class BlockInstance extends BuiltinClass {
    public final Identifier id;
    public final Block value;

    public BlockInstance(Block value) {
        this(Registries.BLOCK.getId(value), value);
    }

    public BlockInstance(Identifier id, Block value) {
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
        BlockInstance that = (BlockInstance) o;
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
        return this;
    }
}
