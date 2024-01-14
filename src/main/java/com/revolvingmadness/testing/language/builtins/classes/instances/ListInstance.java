package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.IntegerType;
import com.revolvingmadness.testing.language.builtins.classes.types.ListType;

import java.util.List;
import java.util.Objects;

public class ListInstance extends BuiltinClass {
    public final List<BuiltinClass> value;

    public ListInstance(List<BuiltinClass> value) {
        this.value = value;
    }

    @Override
    public void deleteIndex(BuiltinClass index) {
        if (!index.instanceOf(new IntegerType())) {
            throw ErrorHolder.cannotIndexListByType(index.getType());
        }

        long integerIndex = index.toInteger();

        this.value.remove((int) integerIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        ListInstance that = (ListInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinClass getIndex(BuiltinClass index) {
        if (!index.instanceOf(new IntegerType())) {
            throw ErrorHolder.cannotIndexListByType(index.getType());
        }

        int indexInteger = (int) index.toInteger();

        if (indexInteger < 0 || indexInteger >= this.value.size())
            throw ErrorHolder.indexOutOfBounds(indexInteger, this.value.size());

        return this.value.get(indexInteger);
    }

    @Override
    public BuiltinType getType() {
        return new ListType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public void setIndex(BuiltinClass index, BuiltinClass value) {
        if (!index.instanceOf(new IntegerType())) {
            throw ErrorHolder.cannotIndexListByType(index.getType());
        }

        this.value.set((int) index.toInteger(), value);
    }

    @Override
    public List<BuiltinClass> toList() {
        return this.value;
    }
}
