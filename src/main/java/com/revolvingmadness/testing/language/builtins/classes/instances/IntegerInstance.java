package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.IntegerType;

import java.util.Objects;

public class IntegerInstance extends BuiltinClass {
    public final Integer value;

    public IntegerInstance(Integer value) {
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
        IntegerInstance that = (IntegerInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new IntegerType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.value);
    }

    @Override
    public Boolean toBoolean() {
        return this.value != 0;
    }

    @Override
    public Double toFloat() {
        return this.value.doubleValue();
    }

    @Override
    public Integer toInteger() {
        return this.value;
    }

    @Override
    public String toStringType() {
        return this.value.toString();
    }
}
