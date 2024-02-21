package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringType;
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
            this.validateCall("get", arguments, List.of(StringType.TYPE));

            String identifierClass = arguments.get(0).toString();

            Identifier identifier = Identifier.tryParse(identifierClass);

            if (identifier == null) {
                throw ErrorHolder.invalidIdentifier(identifierClass);
            }

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
