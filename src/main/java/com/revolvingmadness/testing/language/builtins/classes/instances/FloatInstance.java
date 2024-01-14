package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.FloatType;

import java.util.Objects;

public class FloatInstance extends BuiltinClass {
    public final double value;

    public FloatInstance(double value) {
        this.value = value;
    }


    @Override
    public double toFloat() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        FloatInstance that = (FloatInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new FloatType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.value);
    }

    @Override
    public boolean toBoolean() {
        return this.value != 0.0;
    }

    @Override
    public long toInteger() {
        return (long) this.value;
    }

    @Override
    public String toStringType() {
        return String.valueOf(this.value);
    }
}
