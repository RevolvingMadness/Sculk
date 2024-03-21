package com.revolvingmadness.sculk.language.builtins.classes.instances.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.BooleanClassType;
import net.minecraft.nbt.NbtByte;
import net.minecraft.nbt.NbtElement;

import java.util.Objects;

public class BooleanInstance extends NBTBuiltinClass {
    public final boolean value;

    public BooleanInstance(boolean value) {
        super(BooleanClassType.TYPE);
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
    public BuiltinClass fromNBTBoolean(BooleanInstance boolean_) {
        return boolean_;
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
    public NbtElement toNBT() {
        return NbtByte.of(this.value);
    }

    @Override
    public NbtElement toNBTElement() {
        return NbtByte.of(this.value);
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
