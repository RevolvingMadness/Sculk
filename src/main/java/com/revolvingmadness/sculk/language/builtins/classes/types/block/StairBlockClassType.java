package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.StairBlockInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.Identifier;

import java.util.List;

public class StairBlockClassType extends BuiltinClassType {
    public static final StairBlockClassType TYPE = new StairBlockClassType();

    private StairBlockClassType() {
        super("StairBlock", BlockClassType.TYPE);
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(StringClassType.TYPE, BlockClassType.TYPE, BlockSettingsClassType.TYPE));

        return new StairBlockInstance(new Identifier(interpreter.identifier.getNamespace(), arguments.get(0).toString()), new StairsBlock(arguments.get(1).toBlockInstance().value.getDefaultState(), arguments.get(2).toBlockSettings()));
    }
}
