package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.dynamicreg.DynamicBlockRegistry;
import com.revolvingmadness.sculk.dynamicreg.DynamicItemRegistry;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.ScriptTag;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.errors.NameError;
import com.revolvingmadness.sculk.language.errors.SyntaxError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

public class BlocksClassType extends BuiltinClassType {
    public static final BlocksClassType TYPE = new BlocksClassType();

    private BlocksClassType() {
        super("Blocks");

        this.variableScope.declare(List.of(TokenType.CONST), "get", new Get());
        this.variableScope.declare(List.of(TokenType.CONST), "register", new Register());
        this.variableScope.declare(List.of(TokenType.CONST), "registerWithItem", new RegisterWithItem());
    }

    private static class Get extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("get", arguments, List.of(StringClassType.TYPE));

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

    private static class Register extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("register", arguments, List.of(BlockClassType.TYPE));

            if (interpreter.scriptTag != ScriptTag.START) {
                throw new SyntaxError("Blocks can only be registered in the 'start' script tag");
            }

            BlockInstance block = arguments.get(0).toBlockInstance();

            DynamicBlockRegistry.register(block.id, block.value);

            return block;
        }
    }

    private static class RegisterWithItem extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("registerWithItem", arguments, List.of(BlockClassType.TYPE));

            if (interpreter.scriptTag != ScriptTag.START) {
                throw new SyntaxError("Blocks can only be registered in the 'start' script tag");
            }

            BlockInstance block = arguments.get(0).toBlockInstance();

            DynamicBlockRegistry.register(block.id, block.value);
            DynamicItemRegistry.register(block.id, block.value);

            return block;
        }
    }
}
