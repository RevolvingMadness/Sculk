package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.SlabBlockInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.Identifier;

import java.util.List;

public class SlabBlockClassType extends BuiltinClassType {
    public static final SlabBlockClassType TYPE = new SlabBlockClassType();

    private SlabBlockClassType() {
        super("SlabBlock", BlockClassType.TYPE);
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(StringClassType.TYPE, BlockSettingsClassType.TYPE));

        return new SlabBlockInstance(new Identifier(interpreter.identifier.getNamespace(), arguments.get(0).toString()), new SlabBlock(arguments.get(1).toBlockSettings()));
    }
}
