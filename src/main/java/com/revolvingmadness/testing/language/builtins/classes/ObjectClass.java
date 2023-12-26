package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
import com.revolvingmadness.testing.language.builtins.classes.types.StringClass;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;

public class ObjectClass extends BaseClassExpressionNode {
    public ObjectClass() {
        super(null);
        this.variableScope.declare(true, "toString", new ToString());
        this.variableScope.declare(true, "add", new Add());
        this.variableScope.declare(true, "subtract", new Subtract());
        this.variableScope.declare(true, "multiply", new Multiply());
        this.variableScope.declare(true, "divide", new Divide());
        this.variableScope.declare(true, "exponentiate", new Exponentiate());
        this.variableScope.declare(true, "mod", new Mod());
        this.variableScope.declare(true, "negate", new Negate());
        this.variableScope.declare(true, "equalTo", new EqualTo());
        this.variableScope.declare(true, "notEqualTo", new NotEqualTo());
        this.variableScope.declare(true, "instanceOf", new InstanceOf());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ObjectClass;
    }

    @Override
    public String getType() {
        return "Object";
    }

    @Override
    public int hashCode() {
        return ObjectClass.class.hashCode();
    }

    public class Add extends BaseMethodExpressionNode {
        public Add() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("add", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("+", this.boundClass.getType(), other.getType());
        }
    }

    public class Divide extends BaseMethodExpressionNode {
        public Divide() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("divide", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("/", this.boundClass.getType(), other.getType());
        }
    }

    public class EqualTo extends BaseMethodExpressionNode {
        public EqualTo() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("equalTo", 1, arguments.size());
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(this.boundClass.equals(o));
        }
    }

    public class Exponentiate extends BaseMethodExpressionNode {
        public Exponentiate() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("exponentiate", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("^", this.boundClass.getType(), other.getType());
        }
    }

    public class InstanceOf extends BaseMethodExpressionNode {
        public InstanceOf() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("instanceOf", 1, arguments.size());
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(this.boundClass.instanceOf(o));
        }
    }

    public class Mod extends BaseMethodExpressionNode {
        public Mod() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("mod", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("%", this.boundClass.getType(), other.getType());
        }
    }

    public class Multiply extends BaseMethodExpressionNode {
        public Multiply() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("multiply", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("*", this.boundClass.getType(), other.getType());
        }
    }

    public class Negate extends BaseMethodExpressionNode {
        public Negate() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("negate", 0, arguments.size());
            }

            throw ErrorHolder.cannotApplyUnaryOperatorToTypes("-", this.boundClass.getType());
        }
    }

    public class NotEqualTo extends BaseMethodExpressionNode {
        public NotEqualTo() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("notEqualTo", 1, arguments.size());
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(!this.boundClass.equals(o));
        }
    }

    public class Subtract extends BaseMethodExpressionNode {
        public Subtract() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("subtract", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("-", this.boundClass.getType(), other.getType());
        }
    }

    public class ToString extends BaseMethodExpressionNode {
        public ToString() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return new StringClass("<Class '" + this.boundClass.getType() + "'>");
        }
    }
}