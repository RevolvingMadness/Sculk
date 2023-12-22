package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
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
    public String toString() {
        throw new NotImplementedException();
    }
}
