package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.BaseFunctionExpressionNode;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;

import java.util.List;
import java.util.Objects;

public class IntegerClass extends BaseFunctionExpressionNode {
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

    public class Add extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'add' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new FloatClass(IntegerClass.this.value + ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new IntegerClass(IntegerClass.this.value + ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '+' to types '" + IntegerClass.this.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class Divide extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'divide' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new FloatClass(IntegerClass.this.value / ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new IntegerClass(IntegerClass.this.value / ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '/' to types '" + IntegerClass.this.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class Exponentiate extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'exponentiate' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new FloatClass(Math.pow(IntegerClass.this.value, ((FloatClass) other).value));
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(Math.pow(IntegerClass.this.value, ((IntegerClass) other).value));
            }

            throw new TypeError("Cannot apply binary operator '^' to types '" + IntegerClass.this.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class GreaterThan extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'greaterThan' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new BooleanClass(IntegerClass.this.value > ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new BooleanClass(IntegerClass.this.value > ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '>' to types '" + IntegerClass.this.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class GreaterThanOrEqualTo extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'greaterThanOrEqualTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new BooleanClass(IntegerClass.this.value >= ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new BooleanClass(IntegerClass.this.value >= ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '>=' to types '" + IntegerClass.this.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class LessThan extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'lessThan' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new BooleanClass(IntegerClass.this.value < ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new BooleanClass(IntegerClass.this.value < ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '<' to types '" + IntegerClass.this.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class LessThanOrEqualTo extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'lessThanOrEqualTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new BooleanClass(IntegerClass.this.value <= ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new BooleanClass(IntegerClass.this.value <= ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '<=' to types '" + IntegerClass.this.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class Mod extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'mod' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new FloatClass(IntegerClass.this.value % ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new IntegerClass(IntegerClass.this.value % ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '%' to types '" + IntegerClass.this.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class Multiply extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'multiply' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new FloatClass(IntegerClass.this.value * ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new IntegerClass(IntegerClass.this.value * ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '*' to types '" + IntegerClass.this.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class Negate extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'negate' requires 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(-IntegerClass.this.value);
        }
    }

    public class Subtract extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'subtract' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            if (other.getType().equals("Float")) {
                return new FloatClass(IntegerClass.this.value - ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new IntegerClass(IntegerClass.this.value - ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '-' to types '" + IntegerClass.this.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class ToString extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'toString' requires 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringClass(IntegerClass.this.value.toString());
        }
    }
}
