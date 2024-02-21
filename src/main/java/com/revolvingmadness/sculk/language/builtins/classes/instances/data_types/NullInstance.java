package com.revolvingmadness.sculk.language.builtins.classes.instances.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.NullType;

public class NullInstance extends BuiltinClass {
    public NullInstance() {
        super(NullType.TYPE);
    }

    @Override
    public String toString() {
        return "null";
    }
}
