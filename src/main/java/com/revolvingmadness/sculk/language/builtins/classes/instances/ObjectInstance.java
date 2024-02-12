package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.ObjectType;

@SuppressWarnings("unused")
public class ObjectInstance extends BuiltinClass {
    @Override
    public BuiltinType getType() {
        return ObjectType.TYPE;
    }
}
