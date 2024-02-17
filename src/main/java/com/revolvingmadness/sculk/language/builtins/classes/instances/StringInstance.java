package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.StringType;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringInstance extends BuiltinClass {
    public final String value;

    public StringInstance(String value) {
        this.value = value;
    }

    @Override
    public BuiltinClass add(BuiltinClass other) {
        if (other.instanceOf(StringType.TYPE)) {
            return new StringInstance(this.value + other);
        }

        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("+", this.getType(), other.getType());
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
    public BuiltinType getType() {
        return StringType.TYPE;
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
    public String toRepresentation() {
        return "\"" + this + "\"";
    }

    @Override
    public String toString() {
        return this.value;
    }
}
