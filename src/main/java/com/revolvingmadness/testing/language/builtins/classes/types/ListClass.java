package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.errors.TypeError;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class ListClass extends BaseClassExpressionNode {
    public final List<BaseClassExpressionNode> value;

    public ListClass(List<BaseClassExpressionNode> value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return "List";
    }

    @Override
    public BaseClassExpressionNode index(BaseClassExpressionNode index) {
        if (!index.getType().equals("Integer")) {
            throw new TypeError("Cannot index list by non-integer");
        }

        return this.value.get(((IntegerClass) index).value);
    }

    @Override
    public String toString() {
        throw new NotImplementedException();
    }
}
