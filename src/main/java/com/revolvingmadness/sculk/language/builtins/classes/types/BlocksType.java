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
    public static final BlocksType TYPE = new BlocksType();

    private BlocksType() {
        super("Blocks");

        this.variableScope.declare(List.of(TokenType.CONST), "get", new Get());
    }

    private static class Get extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("get", 1, arguments.size());
            }

            BuiltinClass identifierClass = arguments.get(0);

            if (!identifierClass.instanceOf(ResourceType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "get", ResourceType.TYPE, identifierClass.getType());
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
