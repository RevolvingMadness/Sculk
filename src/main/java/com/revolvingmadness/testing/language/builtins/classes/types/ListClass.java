package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Objects;

public class ListClass extends BaseClassExpressionNode {
    public final List<BaseClassExpressionNode> value;

    public ListClass(List<BaseClassExpressionNode> value) {
        this.value = value;

        this.variableScope.declare(true, new IdentifierExpressionNode("equalTo"), new EqualTo());
        this.variableScope.declare(true, new IdentifierExpressionNode("notEqualTo"), new NotEqualTo());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        ListClass listClass = (ListClass) o;
        return Objects.equals(this.value, listClass.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String getType() {
        return "List";
    }

    @Override
    public BaseClassExpressionNode getIndex(BaseClassExpressionNode index) {
        if (!index.getType().equals("Integer")) {
            throw new TypeError("Cannot index list by non-integer");
        }

        return this.value.get(((IntegerClass) index).value);
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

    public class EqualTo extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'equalTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(ListClass.this.equals(o));
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class NotEqualTo extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'notEqualTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(!ListClass.this.equals(o));
        }

        @Override
        public String getType() {
            return "Function";
        }
    }
}
