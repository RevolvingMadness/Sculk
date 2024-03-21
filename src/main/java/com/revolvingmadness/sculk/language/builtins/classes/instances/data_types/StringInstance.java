package com.revolvingmadness.sculk.language.builtins.classes.instances.data_types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringInstance extends NBTBuiltinClass {
    public final String value;

    public StringInstance(String value) {
        super(StringClassType.TYPE);
        this.value = value;
    }

    @Override
    public BuiltinClass add(BuiltinClass other) {
        if (other.instanceOf(StringClassType.TYPE)) {
            return new StringInstance(this.value + other);
        }

        throw ErrorHolder.unsupportedBinaryOperator("+", this.type, other.type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        StringInstance that = (StringInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinClass fromNBTString(StringInstance string) {
        return string;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public List<BuiltinClass> toList() {
        List<BuiltinClass> list = new ArrayList<>();

        for (char character : this.value.toCharArray()) {
            list.add(new StringInstance(String.valueOf(character)));
        }

        return list;
    }

    @Override
    public NbtElement toNBT() {
        return NbtString.of(this.value);
    }

    @Override
    public NbtElement toNBTElement() {
        return NbtString.of(this.value);
    }

    @Override
    public String toRepresentation() {
        return "\"" + this + "\"";
    }

    @Override
    public String toString() {
        return this.value;
    }
}
