package com.revolvingmadness.sculk.language.builtins.classes.instances.data_types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.ListType;
import com.revolvingmadness.sculk.language.errors.SyntaxError;
import com.revolvingmadness.sculk.language.errors.TypeError;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

public class ListInstance extends BuiltinClass {
    public final List<BuiltinClass> value;

    public ListInstance(List<BuiltinClass> value) {
        this.value = value;
    }

    private boolean containsOnlyOneType() {
        if (this.value.size() == 0 || this.value.size() == 1) {
            return true;
        }

        BuiltinType type = this.value.get(0).getType();

        for (BuiltinClass item : this.value) {
            if (!item.getType().equals(type)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void deleteIndex(BuiltinClass index) {
        if (!index.instanceOf(IntegerType.TYPE)) {
            throw ErrorHolder.cannotIndexTypeByType(this.getType(), index.getType());
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
        if (!index.instanceOf(IntegerType.TYPE)) {
            throw ErrorHolder.cannotIndexTypeByType(this.getType(), index.getType());
        }

        int indexInteger = (int) index.toInteger();

        if (indexInteger < 0 || indexInteger >= this.value.size()) {
            throw new SyntaxError("Index " + indexInteger + " out of bounds for length " + this.value.size());
        }

        return this.value.get(indexInteger);
    }

    @Override
    public BuiltinType getType() {
        return ListType.TYPE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public void setIndex(BuiltinClass index, BuiltinClass value) {
        if (!index.instanceOf(IntegerType.TYPE)) {
            throw ErrorHolder.cannotIndexTypeByType(this.getType(), index.getType());
        }

        this.value.set((int) index.toInteger(), value);
    }

    @Override
    public List<BuiltinClass> toList() {
        return this.value;
    }

    @Override
    public NbtElement toNBT() {
        NbtList list = new NbtList();

        if (!this.containsOnlyOneType()) {
            throw new TypeError("List NBT element can only contain one type");
        }

        this.value.forEach(value -> list.add(value.toNBT()));

        return list;
    }

    @Override
    public String toString() {
        return StringUtils.joinWith(", ", this.value);
    }
}