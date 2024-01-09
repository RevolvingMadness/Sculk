package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.BooleanType;

import java.util.Objects;

public class BooleanInstance extends BuiltinClass {
    public final Boolean value;

    public BooleanInstance(Boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        BooleanInstance that = (BooleanInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new BooleanType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.value);
    }

    @Override
    public Boolean toBoolean() {
        return this.value;
    }

    @Override
    public Double toFloat() {
        return this.value ? 1.0 : 0.0;
    }

    @Override
    public Integer toInteger() {
        return this.value ? 1 : 0;
    }

    @Override
    public String toStringType() {
        return this.value.toString();
    }
}
