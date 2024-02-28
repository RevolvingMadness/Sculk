package com.revolvingmadness.sculk.language.builtins.classes.instances.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.DictionaryClassType;
import com.revolvingmadness.sculk.language.errors.NameError;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.Map;
import java.util.Objects;

public class DictionaryInstance extends BuiltinClass {
    public final Map<BuiltinClass, BuiltinClass> value;

    public DictionaryInstance(Map<BuiltinClass, BuiltinClass> value) {
        super(DictionaryClassType.TYPE);
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
        DictionaryInstance that = (DictionaryInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinClass getIndex(BuiltinClass index) {
        BuiltinClass result = this.value.get(index);

        if (result == null) {
            throw new NameError("Dictionary has no key '" + index + "'");
        }

        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public void setIndex(BuiltinClass index, BuiltinClass value) {
        this.value.put(index, value);
    }

    @Override
    public NbtElement toNBT() {
        NbtCompound nbtCompound = new NbtCompound();

        this.value.forEach((key, value) -> nbtCompound.put(key.toString(), value.toNBT()));

        return nbtCompound;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        this.value.forEach((key, value) -> {
            stringBuilder.append(key.toRepresentation());
            stringBuilder.append(": ");
            stringBuilder.append(value.toRepresentation());
            stringBuilder.append(", ");
        });

        String result = stringBuilder.toString();

        if (this.value.size() != 0) {
            result = stringBuilder.substring(0, stringBuilder.length() - 2);
        }

        return result + "}";
    }
}
