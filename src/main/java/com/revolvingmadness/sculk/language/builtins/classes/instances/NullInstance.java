package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.NullType;

public class NullInstance extends BuiltinClass {
    @Override
    public BuiltinType getType() {
        return new NullType();
    }

    @Override
    public StringInstance toStringMethod() {
        return new StringInstance("null");
    }
}
