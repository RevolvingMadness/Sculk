package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.interpreter.errors.ValueError;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Map;
import java.util.Objects;

public class DictionaryClass extends BaseClassExpressionNode {
    public final Map<BaseClassExpressionNode, BaseClassExpressionNode> value;

    public DictionaryClass(Map<BaseClassExpressionNode, BaseClassExpressionNode> value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        DictionaryClass that = (DictionaryClass) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BaseClassExpressionNode getIndex(BaseClassExpressionNode index) {
        BaseClassExpressionNode value = this.value.get(index);

        if (value == null) {
            throw new ValueError("Dictionary has no key '" + index + "'");
        }

        return value;
    }

    @Override
    public String getType() {
        return "Dictionary";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public void setIndex(BaseClassExpressionNode index, BaseClassExpressionNode value) {
        this.value.put(index, value);
    }

    @Override
    public String toString() {
        throw new NotImplementedException();
    }
}
