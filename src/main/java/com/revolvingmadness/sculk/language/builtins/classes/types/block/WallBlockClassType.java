package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.WallBlockInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.block.WallBlock;
import net.minecraft.util.Identifier;

import java.util.List;

public class WallBlockClassType extends BuiltinClassType {
    public static final WallBlockClassType TYPE = new WallBlockClassType();

    private WallBlockClassType() {
        super("WallBlock", BlockClassType.TYPE);
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(StringClassType.TYPE, BlockSettingsClassType.TYPE));

        return new WallBlockInstance(new Identifier(interpreter.identifier.getNamespace(), arguments.get(0).toString()), new WallBlock(arguments.get(1).toBlockSettings()));
    }
}
