package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.BaseFunctionExpressionNode;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;
import java.util.Objects;

public class FloatClass extends BaseClassExpressionNode {
    public final Double value;

    public FloatClass(Double value) {
        this.value = value;
        this.variableScope.declare(true, new IdentifierExpressionNode("add"), new Add());
        this.variableScope.declare(true, new IdentifierExpressionNode("subtract"), new Subtract());
        this.variableScope.declare(true, new IdentifierExpressionNode("multiply"), new Multiply());
        this.variableScope.declare(true, new IdentifierExpressionNode("divide"), new Divide());
        this.variableScope.declare(true, new IdentifierExpressionNode("exponentiate"), new Exponentiate());
        this.variableScope.declare(true, new IdentifierExpressionNode("mod"), new Mod());
        this.variableScope.declare(true, new IdentifierExpressionNode("negate"), new Negate());
        this.variableScope.declare(true, new IdentifierExpressionNode("lessThan"), new LessThan());
        this.variableScope.declare(true, new IdentifierExpressionNode("lessThanOrEqualTo"), new LessThanOrEqualTo());
        this.variableScope.declare(true, new IdentifierExpressionNode("greaterThan"), new GreaterThan());
        this.variableScope.declare(true, new IdentifierExpressionNode("greaterThanOrEqualTo"), new GreaterThanOrEqualTo());
        this.variableScope.declare(true, new IdentifierExpressionNode("toString"), new ToString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        FloatClass that = (FloatClass) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public String getType() {
        return "Float";
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
                return new FloatClass(FloatClass.this.value + ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(FloatClass.this.value + ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '+' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
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
                return new FloatClass(FloatClass.this.value / ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(FloatClass.this.value / ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '/' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
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
                return new FloatClass(Math.pow(FloatClass.this.value, ((FloatClass) other).value));
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(Math.pow(FloatClass.this.value, ((IntegerClass) other).value));
            }

            throw new TypeError("Cannot apply binary operator '^' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
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
                return new BooleanClass(FloatClass.this.value > ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new BooleanClass(FloatClass.this.value > ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '>' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
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
                return new BooleanClass(FloatClass.this.value >= ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new BooleanClass(FloatClass.this.value >= ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '>=' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
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
                return new BooleanClass(FloatClass.this.value < ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new BooleanClass(FloatClass.this.value < ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '<' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
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
                return new BooleanClass(FloatClass.this.value <= ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new BooleanClass(FloatClass.this.value <= ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '<=' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
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
                return new FloatClass(FloatClass.this.value % ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(FloatClass.this.value % ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '%' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
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
                return new FloatClass(FloatClass.this.value * ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(FloatClass.this.value * ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '*' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class Negate extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'negate' requires 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new FloatClass(-FloatClass.this.value);
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
                return new FloatClass(FloatClass.this.value - ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(FloatClass.this.value - ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply binary operator '-' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class ToString extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'toString' requires 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringClass(FloatClass.this.value.toString());
        }
    }
}
