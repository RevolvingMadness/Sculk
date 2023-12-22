package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Map;

public class DictionaryClass extends BaseClassExpressionNode {
    public final Map<ExpressionNode, ExpressionNode> value;

    public DictionaryClass(Map<ExpressionNode, ExpressionNode> value) {
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
