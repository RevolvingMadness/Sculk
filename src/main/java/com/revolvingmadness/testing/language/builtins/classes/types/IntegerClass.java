package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;
import java.util.Objects;

public class IntegerClass extends BaseClassExpressionNode {
    public final Integer value;

    public IntegerClass(Integer value) {
        this.value = value;
        this.variableScope.declare(true, new IdentifierExpressionNode("toString"), new ToString());
        this.variableScope.declare(true, new IdentifierExpressionNode("add"), new Add());
        this.variableScope.declare(true, new IdentifierExpressionNode("subtract"), new Subtract());
        this.variableScope.declare(true, new IdentifierExpressionNode("multiply"), new Multiply());
        this.variableScope.declare(true, new IdentifierExpressionNode("divide"), new Divide());
        this.variableScope.declare(true, new IdentifierExpressionNode("exponentiate"), new Exponentiate());
        this.variableScope.declare(true, new IdentifierExpressionNode("mod"), new Mod());
        this.variableScope.declare(true, new IdentifierExpressionNode("negate"), new Negate());
        this.variableScope.declare(true, new IdentifierExpressionNode("equalTo"), new EqualTo());
        this.variableScope.declare(true, new IdentifierExpressionNode("notEqualTo"), new NotEqualTo());
        this.variableScope.declare(true, new IdentifierExpressionNode("lessThan"), new LessThan());
        this.variableScope.declare(true, new IdentifierExpressionNode("lessThanOrEqualTo"), new LessThanOrEqualTo());
        this.variableScope.declare(true, new IdentifierExpressionNode("greaterThan"), new GreaterThan());
        this.variableScope.declare(true, new IdentifierExpressionNode("greaterThanOrEqualTo"), new GreaterThanOrEqualTo());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        IntegerClass that = (IntegerClass) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public class LessThan extends BaseClassExpressionNode {
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

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class LessThanOrEqualTo extends BaseClassExpressionNode {
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

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GreaterThan extends BaseClassExpressionNode {
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

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GreaterThanOrEqualTo extends BaseClassExpressionNode {
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

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class EqualTo extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'equalTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(IntegerClass.this.equals(o));
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

            return new BooleanClass(!IntegerClass.this.equals(o));
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Negate extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'negate' requires 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(-IntegerClass.this.value);
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Add extends BaseClassExpressionNode {
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

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Divide extends BaseClassExpressionNode {
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

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Exponentiate extends BaseClassExpressionNode {
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

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Mod extends BaseClassExpressionNode {
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

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Multiply extends BaseClassExpressionNode {
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

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Subtract extends BaseClassExpressionNode {
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

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class ToString extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'toString' requires 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringClass(IntegerClass.this.value.toString());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }
}
