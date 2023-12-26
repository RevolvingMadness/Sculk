package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.BaseMethodExpressionNode;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;
import java.util.Objects;

public class IntegerClass extends BaseMethodExpressionNode {
    public final Integer value;

    public IntegerClass(Integer value) {
        this.value = value;
        this.variableScope.declare(true, "add", new Add());
        this.variableScope.declare(true, "subtract", new Subtract());
        this.variableScope.declare(true, "multiply", new Multiply());
        this.variableScope.declare(true, "divide", new Divide());
        this.variableScope.declare(true, "exponentiate", new Exponentiate());
        this.variableScope.declare(true, "mod", new Mod());
        this.variableScope.declare(true, "negate", new Negate());
        this.variableScope.declare(true, "lessThan", new LessThan());
        this.variableScope.declare(true, "lessThanOrEqualTo", new LessThanOrEqualTo());
        this.variableScope.declare(true, "greaterThan", new GreaterThan());
        this.variableScope.declare(true, "greaterThanOrEqualTo", new GreaterThanOrEqualTo());
        this.variableScope.declare(true, "toString", new ToString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        IntegerClass that = (IntegerClass) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public class Add extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("add", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new FloatClass(IntegerClass.this.value + ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new IntegerClass(IntegerClass.this.value + ((IntegerClass) other).value);
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("+", IntegerClass.this.getType(), other.getType());
        }
    }

    public class Divide extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("divide", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new FloatClass(IntegerClass.this.value / ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new IntegerClass(IntegerClass.this.value / ((IntegerClass) other).value);
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("/", IntegerClass.this.getType(), other.getType());
        }
    }

    public class Exponentiate extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("exponentiate", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new FloatClass(Math.pow(IntegerClass.this.value, ((FloatClass) other).value));
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(Math.pow(IntegerClass.this.value, ((IntegerClass) other).value));
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("^", IntegerClass.this.getType(), other.getType());
        }
    }

    public class GreaterThan extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("greaterThan", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new BooleanClass(IntegerClass.this.value > ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new BooleanClass(IntegerClass.this.value > ((IntegerClass) other).value);
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes(">", IntegerClass.this.getType(), other.getType());
        }
    }

    public class GreaterThanOrEqualTo extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("greaterThanOrEqualTo", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new BooleanClass(IntegerClass.this.value >= ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new BooleanClass(IntegerClass.this.value >= ((IntegerClass) other).value);
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes(">=", IntegerClass.this.getType(), other.getType());
        }
    }

    public class LessThan extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("lessThan", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new BooleanClass(IntegerClass.this.value < ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new BooleanClass(IntegerClass.this.value < ((IntegerClass) other).value);
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("<", IntegerClass.this.getType(), other.getType());
        }
    }

    public class LessThanOrEqualTo extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("lessThanOrEqualTo", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new BooleanClass(IntegerClass.this.value <= ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new BooleanClass(IntegerClass.this.value <= ((IntegerClass) other).value);
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("<=", IntegerClass.this.getType(), other.getType());
        }
    }

    public class Mod extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("mod", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new FloatClass(IntegerClass.this.value % ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new IntegerClass(IntegerClass.this.value % ((IntegerClass) other).value);
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("%", IntegerClass.this.getType(), other.getType());
        }
    }

    public class Multiply extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("multiply", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new FloatClass(IntegerClass.this.value * ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new IntegerClass(IntegerClass.this.value * ((IntegerClass) other).value);
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("*", IntegerClass.this.getType(), other.getType());
        }
    }

    public class Negate extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("negate", 0, arguments.size());
            }

            return new IntegerClass(-IntegerClass.this.value);
        }
    }

    public class Subtract extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("subtract", 1, arguments.size());
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new FloatClass(IntegerClass.this.value - ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new IntegerClass(IntegerClass.this.value - ((IntegerClass) other).value);
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("-", IntegerClass.this.getType(), other.getType());
        }
    }

    public class ToString extends BaseMethodExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return new StringClass(IntegerClass.this.value.toString());
        }
    }
}
