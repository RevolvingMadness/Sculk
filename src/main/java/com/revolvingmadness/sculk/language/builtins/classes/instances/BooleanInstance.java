package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.BooleanType;

import java.util.Objects;

public class BooleanInstance extends BuiltinClass {
    public final boolean value;

    public BooleanInstance(boolean value) {
        this.value = value;
    }

    @Override
    public BooleanInstance booleanAnd(BuiltinClass other) {
        return new BooleanInstance(this.value && other.toBoolean());
    }

    @Override
    public BooleanInstance booleanOr(BuiltinClass other) {
        return new BooleanInstance(this.value || other.toBoolean());
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
        return this.value == that.value;
    }

    @Override
    public BuiltinType getType() {
        return new BooleanType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public BuiltinClass logicalNot() {
        return new BooleanInstance(!this.value);
    }

    @Override
    public boolean toBoolean() {
        return this.value;
    }

    @Override
    public double toFloat() {
        return this.value ? 1.0 : 0.0;
    }

    @Override
    public long toInteger() {
        return this.value ? 1L : 0L;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
