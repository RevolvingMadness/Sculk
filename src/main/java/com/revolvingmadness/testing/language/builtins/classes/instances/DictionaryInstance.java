package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.DictionaryType;

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
    public BuiltinClass getIndex(BuiltinClass key) {
        BuiltinClass value = this.value.get(key);

        if (value == null) {
            throw ErrorHolder.dictionaryHasNoKey(key.toStringType());
        }

        return value;
    }

    @Override
    public BuiltinType getType() {
        return new DictionaryType();
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
    public Map<BuiltinClass, BuiltinClass> toDictionary() {
        return this.value;
    }
}
