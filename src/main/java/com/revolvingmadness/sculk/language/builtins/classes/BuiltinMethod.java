package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.MethodType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;

import java.util.List;
import java.util.Objects;

public abstract class BuiltinMethod extends BuiltinClass {
    public BuiltinClass boundClass;
    public BuiltinClass boundSuperClass;

    public BuiltinMethod() {
        super(MethodType.TYPE);
    }

    public void bind(BuiltinClass clazz, BuiltinClass superClass) {
        this.boundClass = clazz;
        this.boundSuperClass = superClass;
    }

    public abstract BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments);

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        BuiltinMethod that = (BuiltinMethod) o;
        return Objects.equals(this.boundClass, that.boundClass) && Objects.equals(this.boundSuperClass, that.boundSuperClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.boundClass, this.boundSuperClass);
    }
}
