package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.NullType;

public class NullInstance extends BuiltinClass {
    @Override
    public BuiltinType getType() {
        return new NullType();
    }
}
