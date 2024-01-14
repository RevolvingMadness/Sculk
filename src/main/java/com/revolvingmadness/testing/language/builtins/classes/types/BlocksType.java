package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.BlockInstance;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.lexer.TokenType;
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

            BuiltinClass id = arguments.get(0);

            if (!id.instanceOf(new ResourceType())) {
                throw ErrorHolder.argumentRequiresType(1, "get", new ResourceType(), id.getType());
            }

            Identifier identifier = id.toResource();

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
