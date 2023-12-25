package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.errors.TypeError;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Objects;

public class ListClass extends BaseClassExpressionNode {
    public final List<BaseClassExpressionNode> value;

    public ListClass(List<BaseClassExpressionNode> value) {
        this.value = value;
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
    public void deleteIndex(BaseClassExpressionNode index) {
        if (!index.getType().equals("Integer")) {
            throw new TypeError("Cannot index list by non-integer");
        }

        int integerIndex = ((IntegerClass) index).value;

        this.value.remove(integerIndex);
    }

    @Override
    public BaseClassExpressionNode getIndex(BaseClassExpressionNode index) {
        if (!index.getType().equals("Integer")) {
            throw new TypeError("Cannot index list by non-integer");
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
            throw new TypeError("Cannot index list by non-integer");
        }

        this.value.set(((IntegerClass) index).value, value);
    }

    @Override
    public String toString() {
        throw new NotImplementedException();
    }
}
