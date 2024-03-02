package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.FloatInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.item.ItemInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

import java.util.List;

public class BlockClassType extends BuiltinClassType {
    public static final BlockClassType TYPE = new BlockClassType();

    private BlockClassType() {
        super("Block");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "asItem", new AsItem());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getBlastResistance", new GetBlastResistance());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getName", new GetName());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getSlipperiness", new GetSlipperiness());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(StringClassType.TYPE, BlockSettingsClassType.TYPE));

        return new BlockInstance(new Identifier(interpreter.identifier.getNamespace(), arguments.get(0).toString()), new Block(arguments.get(1).toBlockSettings()));
    }

    private static class AsItem extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("asItem", arguments);

            return new ItemInstance(this.boundClass.toBlock().asItem());
        }
    }

    private static class GetBlastResistance extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getBlastResistance", arguments);

            return new FloatInstance(this.boundClass.toBlock().getBlastResistance());
        }
    }

    private static class GetName extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getName", arguments);

            return new StringInstance(this.boundClass.toBlock().getName().getString());
        }
    }

    private static class GetSlipperiness extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getSlipperiness", arguments);

            return new FloatInstance(this.boundClass.toBlock().getSlipperiness());
        }
    }
}
