package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class ModuleClassType extends BuiltinClassType {
    public static final ModuleClassType TYPE = new ModuleClassType();

    private ModuleClassType() {
        super("Module");
    }
}
