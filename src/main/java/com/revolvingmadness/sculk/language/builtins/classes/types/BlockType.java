package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.FloatInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.ItemInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class BlockType extends BuiltinType {
    public static final BlockType TYPE = new BlockType();

    private BlockType() {
        super("Block");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "asItem", new AsItem());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getBlastResistance", new GetBlastResistance());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getName", new GetName());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getSlipperiness", new GetSlipperiness());
    }

    private static class AsItem extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("asItem", arguments);

            return new ItemInstance(this.boundClass.toBlock().asItem());
        }
    }

    private static class GetBlastResistance extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getBlastResistance", arguments);

            return new FloatInstance(this.boundClass.toBlock().getBlastResistance());
        }
    }

    private static class GetName extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getName", arguments);

            return new StringInstance(this.boundClass.toBlock().getName().getString());
        }
    }

    private static class GetSlipperiness extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getSlipperiness", arguments);

            return new FloatInstance(this.boundClass.toBlock().getSlipperiness());
        }
    }
}
