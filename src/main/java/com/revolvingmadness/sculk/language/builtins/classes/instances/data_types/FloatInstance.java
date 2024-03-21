package com.revolvingmadness.sculk.language.builtins.classes.instances.data_types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.FloatClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtElement;

import java.util.Objects;

public class FloatInstance extends NBTBuiltinClass {
    public final double value;

    public FloatInstance(double value) {
        super(FloatClassType.TYPE);
        this.value = value;
    }

    @Override
    public BuiltinClass add(BuiltinClass other) {
        if (other.instanceOf(FloatClassType.TYPE)) {
            return new FloatInstance(this.value + other.toFloat());
        }

        if (other.instanceOf(IntegerClassType.TYPE)) {
            return new FloatInstance(this.value + other.toInteger());
        }

        throw ErrorHolder.unsupportedBinaryOperator("+", this.type, other.type);
    }

    @Override
    public BuiltinClass decrement() {
        return new FloatInstance(this.value - 1);
    }

    @Override
    public BuiltinClass divide(BuiltinClass other) {
        if (other.instanceOf(FloatClassType.TYPE)) {
            return new FloatInstance(this.value / other.toFloat());
        }

        if (other.instanceOf(IntegerClassType.TYPE)) {
            return new FloatInstance(this.value / other.toInteger());
        }

        throw ErrorHolder.unsupportedBinaryOperator("/", this.type, other.type);
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
        return Double.compare(this.value, that.value) == 0;
    }

    @Override
    public BuiltinClass exponentiate(BuiltinClass other) {
        if (other.instanceOf(FloatClassType.TYPE)) {
            return new FloatInstance(Math.pow(this.value, other.toFloat()));
        }

        if (other.instanceOf(IntegerClassType.TYPE)) {
            return new FloatInstance(Math.pow(this.value, other.toInteger()));
        }

        throw ErrorHolder.unsupportedBinaryOperator("^", this.type, other.type);
    }

    @Override
    public BuiltinClass fromNBTFloat(FloatInstance float_) {
        return float_;
    }

    @Override
    public BooleanInstance greaterThan(BuiltinClass other) {
        if (other.instanceOf(FloatClassType.TYPE)) {
            return new BooleanInstance(this.value > other.toFloat());
        }

        if (other.instanceOf(IntegerClassType.TYPE)) {
            return new BooleanInstance(this.value > other.toInteger());
        }

        throw ErrorHolder.unsupportedBinaryOperator(">", this.type, other.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public BuiltinClass increment() {
        return new FloatInstance(this.value + 1);
    }

    @Override
    public BooleanInstance lessThan(BuiltinClass other) {
        if (other.instanceOf(FloatClassType.TYPE)) {
            return new BooleanInstance(this.value < other.toFloat());
        }

        if (other.instanceOf(IntegerClassType.TYPE)) {
            return new BooleanInstance(this.value < other.toInteger());
        }

        throw ErrorHolder.unsupportedBinaryOperator("<", this.type, other.type);
    }

    @Override
    public BuiltinClass mod(BuiltinClass other) {
        if (other.instanceOf(FloatClassType.TYPE)) {
            return new FloatInstance(this.value % other.toFloat());
        }

        if (other.instanceOf(IntegerClassType.TYPE)) {
            return new FloatInstance(this.value % other.toInteger());
        }

        throw ErrorHolder.unsupportedBinaryOperator("%", this.type, other.type);
    }

    @Override
    public BuiltinClass multiply(BuiltinClass other) {
        if (other.instanceOf(FloatClassType.TYPE)) {
            return new FloatInstance(this.value * other.toFloat());
        }

        if (other.instanceOf(IntegerClassType.TYPE)) {
            return new FloatInstance(this.value * other.toInteger());
        }

        throw ErrorHolder.unsupportedBinaryOperator("*", this.type, other.type);
    }

    @Override
    public BuiltinClass negate() {
        return new FloatInstance(-this.value);
    }

    @Override
    public BuiltinClass subtract(BuiltinClass other) {
        if (other.instanceOf(FloatClassType.TYPE)) {
            return new FloatInstance(this.value - other.toFloat());
        }

        if (other.instanceOf(IntegerClassType.TYPE)) {
            return new FloatInstance(this.value - other.toInteger());
        }

        throw ErrorHolder.unsupportedBinaryOperator("-", this.type, other.type);
    }

    @Override
    public boolean toBoolean() {
        return this.value != 0.0;
    }

    @Override
    public double toFloat() {
        return this.value;
    }

    @Override
    public long toInteger() {
        return (long) this.value;
    }

    @Override
    public NbtElement toNBT() {
        return NbtDouble.of(this.value);
    }

    @Override
    public NbtElement toNBTElement() {
        return NbtDouble.of(this.value);
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
