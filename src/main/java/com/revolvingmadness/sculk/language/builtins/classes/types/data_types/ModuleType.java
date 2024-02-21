package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class ModuleType extends BuiltinType {
    public static final ModuleType TYPE = new ModuleType();

    private ModuleType() {
        super("Module");
    }
}
