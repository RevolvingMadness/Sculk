package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.MethodType;

public abstract class BuiltinMethod extends BuiltinClass {
    public BuiltinClass boundClass;
    public BuiltinClass boundSuperClass;

    public void bind(BuiltinClass clazz, BuiltinClass superClass) {
        this.boundClass = clazz;
        this.boundSuperClass = superClass;
    }

    @Override
    public BuiltinType getType() {
        return new MethodType();
    }
}
