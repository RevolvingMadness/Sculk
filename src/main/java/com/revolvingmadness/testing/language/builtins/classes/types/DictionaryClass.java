package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Map;

public class DictionaryClass extends BaseClassExpressionNode {
    public final Map<BaseClassExpressionNode, BaseClassExpressionNode> value;

    public DictionaryClass(Map<BaseClassExpressionNode, BaseClassExpressionNode> value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return "Dictionary";
    }

    @Override
    public String toString() {
        throw new NotImplementedException();
    }
}
