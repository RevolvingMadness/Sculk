package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.FloatType;
import com.revolvingmadness.sculk.language.builtins.classes.types.IntegerType;

import java.util.Objects;

public class IntegerInstance extends BuiltinClass {
    public final long value;

    public IntegerInstance(long value) {
        this.value = value;
    }

    @Override
    public BuiltinClass absoluteValue(BuiltinType type) {
        return new IntegerInstance(Math.abs(this.value));
    }

    @Override
    public BuiltinClass add(BuiltinClass other) {
        if (other.instanceOf(new IntegerType())) {
            return new IntegerInstance(this.value + other.toInteger());
        }

        if (other.instanceOf(new FloatType())) {
            return new FloatInstance(this.value + other.toFloat());
        }

        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("+", this.getType(), other.getType());
    }

    @Override
    public BuiltinClass decrement() {
        return new IntegerInstance(this.value - 1);
    }

    @Override
    public BuiltinClass divide(BuiltinClass other) {
        if (other.instanceOf(new IntegerType())) {
            if (other.toInteger() == 0) {
                throw ErrorHolder.cannotDivideByZero();
            }

            return new IntegerInstance(this.value / other.toInteger());
        }

        if (other.instanceOf(new FloatType())) {
            if (other.toFloat() == 0.0) {
                throw ErrorHolder.cannotDivideByZero();
            }

            return new FloatInstance(this.value / other.toFloat());
        }

        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("/", this.getType(), other.getType());
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
        return this.value == that.value;
    }

    @Override
    public BuiltinClass exponentiate(BuiltinClass other) {
        if (other.instanceOf(new IntegerType())) {
            return new FloatInstance(Math.pow(this.value, other.toInteger()));
        }

        if (other.instanceOf(new FloatType())) {
            return new FloatInstance(Math.pow(this.value, other.toFloat()));
        }

        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("^", this.getType(), other.getType());
    }

    @Override
    public BuiltinType getType() {
        return new IntegerType();
    }

    @Override
    public BooleanInstance greaterThan(BuiltinClass other) {
        if (other.instanceOf(new IntegerType())) {
            return new BooleanInstance(this.value > other.toInteger());
        }

        if (other.instanceOf(new FloatType())) {
            return new BooleanInstance(this.value > other.toFloat());
        }

        throw ErrorHolder.cannotApplyBinaryOperatorToTypes(">", this.getType(), other.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public BuiltinClass increment() {
        return new IntegerInstance(this.value + 1);
    }

    @Override
    public BooleanInstance lessThan(BuiltinClass other) {
        if (other.instanceOf(new IntegerType())) {
            return new BooleanInstance(this.value < other.toInteger());
        }

        if (other.instanceOf(new FloatType())) {
            return new BooleanInstance(this.value < other.toFloat());
        }

        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("<", this.getType(), other.getType());
    }

    @Override
    public BuiltinClass mod(BuiltinClass other) {
        if (other.instanceOf(new IntegerType())) {
            return new IntegerInstance(this.value % other.toInteger());
        }

        if (other.instanceOf(new FloatType())) {
            return new FloatInstance(this.value % other.toFloat());
        }

        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("%", this.getType(), other.getType());
    }

    @Override
    public BuiltinClass multiply(BuiltinClass other) {
        if (other.instanceOf(new IntegerType())) {
            return new IntegerInstance(this.value * other.toInteger());
        }

        if (other.instanceOf(new FloatType())) {
            return new FloatInstance(this.value * other.toFloat());
        }

        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("*", this.getType(), other.getType());
    }

    @Override
    public BuiltinClass negate() {
        return new IntegerInstance(-this.value);
    }

    @Override
    public BuiltinClass subtract(BuiltinClass other) {
        if (other.instanceOf(new IntegerType())) {
            return new IntegerInstance(this.value - other.toInteger());
        }

        if (other.instanceOf(new FloatType())) {
            return new FloatInstance(this.value - other.toFloat());
        }

        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("-", this.getType(), other.getType());
    }

    @Override
    public boolean toBoolean() {
        return this.value != 0;
    }

    @Override
    public double toFloat() {
        return this.value;
    }

    @Override
    public long toInteger() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
