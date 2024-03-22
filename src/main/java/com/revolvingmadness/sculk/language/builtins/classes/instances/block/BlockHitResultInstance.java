package com.revolvingmadness.sculk.language.builtins.classes.instances.block;

import com.revolvingmadness.sculk.language.NBTSerializer;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockHitResultClassType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.block.Block;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class BlockHitResultInstance extends NBTBuiltinClass {
    public final Block block;
    public final BlockPos pos;
    public final boolean succeeded;

    public BlockHitResultInstance(BlockPos pos, Block block, boolean succeeded) {
        super(BlockHitResultClassType.TYPE);
        this.pos = pos;
        this.block = block;
        this.succeeded = succeeded;

        this.variableScope.declare(List.of(TokenType.CONST), "pos", new BlockPosInstance(pos));
        this.variableScope.declare(List.of(TokenType.CONST), "block", new BlockInstance(block));
        this.variableScope.declare(List.of(TokenType.CONST), "succeeded", new BooleanInstance(succeeded));
    }

    @Override
    public NbtElement toNBTElement() {
        return NBTSerializer.serializeBlockHitResult(this.block, this.pos, this.succeeded);
    }
}
