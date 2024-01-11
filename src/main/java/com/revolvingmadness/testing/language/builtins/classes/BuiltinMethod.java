package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.MethodType;

import java.util.Objects;

public abstract class BuiltinMethod extends BuiltinClass {
    public BuiltinClass boundClass;
    public BuiltinClass boundSuperClass;

    public void bind(BuiltinClass clazz, BuiltinClass superClass) {
        this.boundClass = clazz;
        this.boundSuperClass = superClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        BuiltinMethod that = (BuiltinMethod) o;
        return Objects.equals(boundClass, that.boundClass) && Objects.equals(boundSuperClass, that.boundSuperClass);
    }

    @Override
    public BuiltinType getType() {
        return new MethodType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(boundClass, boundSuperClass);
    }
}
