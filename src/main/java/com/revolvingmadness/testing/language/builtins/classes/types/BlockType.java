package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.FloatInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.ItemInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.lexer.TokenType;

import java.util.List;

public class BlockType extends BuiltinType {
    public BlockType() {
        super("Block");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "equalTo", new EqualTo());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "asItem", new AsItem());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getBlastResistance", new GetBlastResistance());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getName", new GetName());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getSlipperiness", new GetSlipperiness());
    }

    private static class AsItem extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("asItem", 0, arguments.size());
            }

            return new ItemInstance(this.boundClass.toBlock().asItem());
        }
    }

    private static class EqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("equalTo", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new BlockType())) {
                return new BooleanInstance(other.toBlock().equals(this.boundClass.toBlock()));
            }

            return new BooleanInstance(false);
        }
    }

    private static class GetBlastResistance extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getBlastResistance", 0, arguments.size());
            }

            return new FloatInstance(this.boundClass.toBlock().getBlastResistance());
        }
    }

    private static class GetName extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getName", 0, arguments.size());
            }

            return new StringInstance(this.boundClass.toBlock().getName().getString());
        }
    }

    private static class GetSlipperiness extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getSlipperiness", 0, arguments.size());
            }

            return new FloatInstance(this.boundClass.toBlock().getSlipperiness());
        }
    }
}