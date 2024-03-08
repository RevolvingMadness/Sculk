package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockSettingsInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

import java.util.List;

public class BlockSettingsClassType extends BuiltinClassType {
    public static final BlockSettingsClassType TYPE = new BlockSettingsClassType();

    private BlockSettingsClassType() {
        super("BlockSettings");

        this.variableScope.declare(List.of(TokenType.CONST), "of", new Of());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments);

        return new BlockSettingsInstance();
    }

    private static class Of extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("of", arguments, List.of(BlockClassType.TYPE));

            Block block = arguments.get(0).toBlock();

            return new BlockSettingsInstance(FabricBlockSettings.copyOf(block));
        }
    }
}
