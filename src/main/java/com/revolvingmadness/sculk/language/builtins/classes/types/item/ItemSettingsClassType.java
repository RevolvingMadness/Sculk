package com.revolvingmadness.sculk.language.builtins.classes.types.item;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.item.ItemSettingsInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;

public class ItemSettingsClassType extends BuiltinClassType {
    public static final ItemSettingsClassType TYPE = new ItemSettingsClassType();

    private ItemSettingsClassType() {
        super("ItemSettings");
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments);

        return new ItemSettingsInstance();
    }
}
