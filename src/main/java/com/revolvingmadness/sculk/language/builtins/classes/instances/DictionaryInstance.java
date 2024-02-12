package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.DictionaryType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.Map;
import java.util.Objects;

public class DictionaryInstance extends BuiltinClass {
    public final Map<BuiltinClass, BuiltinClass> value;

    public DictionaryInstance(Map<BuiltinClass, BuiltinClass> value) {
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
            throw ErrorHolder.dictionaryHasNoKey(index.toString());
        }

        return result;
    }

    @Override
    public BuiltinType getType() {
        return DictionaryType.TYPE;
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
    public NbtElement toNbtElement() {
        NbtCompound nbtCompound = new NbtCompound();

        this.value.forEach((key, value) -> nbtCompound.put(key.toString(), value.toNbtElement()));

        return nbtCompound;
    }

}
