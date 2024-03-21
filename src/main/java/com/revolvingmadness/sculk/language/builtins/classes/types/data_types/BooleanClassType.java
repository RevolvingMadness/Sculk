package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;

public class BooleanClassType extends NBTBuiltinClassType {
    public static final BooleanClassType TYPE = new BooleanClassType();

    private BooleanClassType() {
        super("Boolean");
    }

    @Override
    public BuiltinClass fromNBTBoolean(BooleanInstance boolean_) {
        return boolean_;
    }
}
