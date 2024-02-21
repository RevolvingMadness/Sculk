package com.revolvingmadness.sculk.language.builtins.classes.instances.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockHitResultType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class BlockHitResultInstance extends BuiltinClass {
    public final Block block;
    public final BlockPos pos;
    public final boolean succeeded;

    public BlockHitResultInstance(BlockPos pos, Block block, boolean succeeded) {
        this.pos = pos;
        this.block = block;
        this.succeeded = succeeded;

        this.variableScope.declare(List.of(TokenType.CONST), "pos", new BlockPosInstance(pos));
        this.variableScope.declare(List.of(TokenType.CONST), "block", new BlockInstance(block));
        this.variableScope.declare(List.of(TokenType.CONST), "succeeded", new BooleanInstance(succeeded));
    }

    @Override
    public BuiltinType getType() {
        return BlockHitResultType.TYPE;
    }
}
