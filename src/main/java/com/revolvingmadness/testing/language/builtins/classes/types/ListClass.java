package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Objects;

public class ListClass extends BaseClassExpressionNode {
    public final List<BaseClassExpressionNode> value;

    public ListClass(List<BaseClassExpressionNode> value) {
        this.value = value;
    }

    @Override
    public void deleteIndex(BaseClassExpressionNode index) {
        if (!index.getType().equals("Integer")) {
            throw ErrorHolder.cannotIndexListByType(index.getType());
        }

        int integerIndex = ((IntegerClass) index).value;

        this.value.remove(integerIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        ListClass listClass = (ListClass) o;
        return Objects.equals(this.value, listClass.value);
    }

    @Override
    public BaseClassExpressionNode getIndex(BaseClassExpressionNode index) {
        if (!index.getType().equals("Integer")) {
            throw ErrorHolder.cannotIndexListByType(index.getType());
        }

        return this.value.get(((IntegerClass) index).value);
    }

    @Override
    public String getType() {
        return "List";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public void setIndex(BaseClassExpressionNode index, BaseClassExpressionNode value) {
        if (!index.getType().equals("Integer")) {
            throw ErrorHolder.cannotIndexListByType(index.getType());
        }

        this.value.set(((IntegerClass) index).value, value);
    }

    @Override
    public String toString() {
        throw new NotImplementedException();
    }
}
