package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BlockInstance;
import com.revolvingmadness.sculk.language.errors.NameError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

public class BlocksType extends BuiltinType {
    public BlocksType() {
        super("Blocks");

        Registries.BLOCK.forEach(this::registerBlock);
        this.variableScope.declare(List.of(TokenType.CONST), "get", new Get());
    }

    private void registerBlock(Block block) {
        String blockID = Registries.BLOCK.getId(block).getPath();

        this.variableScope.declare(List.of(TokenType.CONST), blockID.toUpperCase(), new BlockInstance(block));
    }

    private static class Get extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("get", 1, arguments.size());
            }

            BuiltinClass identifierClass = arguments.get(0);

            if (!identifierClass.instanceOf(new ResourceType())) {
                throw ErrorHolder.argumentRequiresType(1, "get", new ResourceType(), identifierClass.getType());
            }

            Identifier identifier = identifierClass.toResource();

            if (Objects.equals(identifier, new Identifier("air"))) {
                return new BlockInstance(Blocks.AIR);
            }

            Block block = Registries.BLOCK.get(identifier);

            if (block == Blocks.AIR) {
                throw new NameError("Block '" + identifier + "' does not exist");
            }

            return new BlockInstance(block);
        }
    }
}
