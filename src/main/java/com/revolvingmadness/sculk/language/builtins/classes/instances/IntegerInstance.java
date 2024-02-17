package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.FloatType;
import com.revolvingmadness.sculk.language.builtins.classes.types.IntegerType;
import com.revolvingmadness.sculk.language.errors.DivisionByZeroError;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtLong;

import java.util.Objects;

public class IntegerInstance extends BuiltinClass {
    public final long value;

    public IntegerInstance(long value) {
        this.value = value;
    }

    @Override
    public BuiltinClass add(BuiltinClass other) {
        if (other.instanceOf(IntegerType.TYPE)) {
            return new IntegerInstance(this.value + other.toInteger());
        }

        if (other.instanceOf(FloatType.TYPE)) {
            return new FloatInstance(this.value + other.toFloat());
        }

        throw ErrorHolder.unsupportedBinaryOperator("+", this.getType(), other.getType());
    }

    @Override
    public BuiltinClass decrement() {
        return new IntegerInstance(this.value - 1);
    }

    @Override
    public BuiltinClass divide(BuiltinClass other) {
        if (other.instanceOf(IntegerType.TYPE)) {
            if (other.toInteger() == 0) {
                throw new DivisionByZeroError();
            }

            return new FloatInstance((double) this.value / other.toInteger());
        }

        if (other.instanceOf(FloatType.TYPE)) {
            if (other.toFloat() == 0.0) {
                throw new DivisionByZeroError();
            }

            return new FloatInstance(this.value / other.toFloat());
        }

        throw ErrorHolder.unsupportedBinaryOperator("/", this.getType(), other.getType());
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
        if (other.instanceOf(IntegerType.TYPE)) {
            return new FloatInstance(Math.pow(this.value, other.toInteger()));
        }

        if (other.instanceOf(FloatType.TYPE)) {
            return new FloatInstance(Math.pow(this.value, other.toFloat()));
        }

        throw ErrorHolder.unsupportedBinaryOperator("^", this.getType(), other.getType());
    }

    @Override
    public BuiltinType getType() {
        return IntegerType.TYPE;
    }

    @Override
    public BooleanInstance greaterThan(BuiltinClass other) {
        if (other.instanceOf(IntegerType.TYPE)) {
            return new BooleanInstance(this.value > other.toInteger());
        }

        if (other.instanceOf(FloatType.TYPE)) {
            return new BooleanInstance(this.value > other.toFloat());
        }

        throw ErrorHolder.unsupportedBinaryOperator(">", this.getType(), other.getType());
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
        if (other.instanceOf(IntegerType.TYPE)) {
            return new BooleanInstance(this.value < other.toInteger());
        }

        if (other.instanceOf(FloatType.TYPE)) {
            return new BooleanInstance(this.value < other.toFloat());
        }

        throw ErrorHolder.unsupportedBinaryOperator("<", this.getType(), other.getType());
    }

    @Override
    public BuiltinClass mod(BuiltinClass other) {
        if (other.instanceOf(IntegerType.TYPE)) {
            return new IntegerInstance(this.value % other.toInteger());
        }

        if (other.instanceOf(FloatType.TYPE)) {
            return new FloatInstance(this.value % other.toFloat());
        }

        throw ErrorHolder.unsupportedBinaryOperator("%", this.getType(), other.getType());
    }

    @Override
    public BuiltinClass multiply(BuiltinClass other) {
        if (other.instanceOf(IntegerType.TYPE)) {
            return new IntegerInstance(this.value * other.toInteger());
        }

        if (other.instanceOf(FloatType.TYPE)) {
            return new FloatInstance(this.value * other.toFloat());
        }

        throw ErrorHolder.unsupportedBinaryOperator("*", this.getType(), other.getType());
    }

    @Override
    public BuiltinClass negate() {
        return new IntegerInstance(-this.value);
    }

    @Override
    public BuiltinClass subtract(BuiltinClass other) {
        if (other.instanceOf(IntegerType.TYPE)) {
            return new IntegerInstance(this.value - other.toInteger());
        }

        if (other.instanceOf(FloatType.TYPE)) {
            return new FloatInstance(this.value - other.toFloat());
        }

        throw ErrorHolder.unsupportedBinaryOperator("-", this.getType(), other.getType());
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
    public NbtElement toNBT() {
        return NbtLong.of(this.value);
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
