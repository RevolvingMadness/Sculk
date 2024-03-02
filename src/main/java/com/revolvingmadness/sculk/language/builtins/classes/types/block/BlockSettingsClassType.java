package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockSettingsInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class BlockSettingsClassType extends BuiltinClassType {
    public static final BlockSettingsClassType TYPE = new BlockSettingsClassType();

    private BlockSettingsClassType() {
        super("BlockSettings");
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments);

        return new BlockSettingsInstance();
    }
}
