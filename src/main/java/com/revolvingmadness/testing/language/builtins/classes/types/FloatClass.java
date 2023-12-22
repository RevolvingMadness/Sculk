package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;

public class FloatClass extends BaseClassExpressionNode {
    public final Double value;

    public FloatClass(Double value) {
        this.value = value;
        this.variableScope.declare(true, new IdentifierExpressionNode("toString"), new ToString());
        this.variableScope.declare(true, new IdentifierExpressionNode("add"), new Add());
        this.variableScope.declare(true, new IdentifierExpressionNode("subtract"), new Subtract());
        this.variableScope.declare(true, new IdentifierExpressionNode("multiply"), new Multiply());
        this.variableScope.declare(true, new IdentifierExpressionNode("divide"), new Divide());
        this.variableScope.declare(true, new IdentifierExpressionNode("exponentiate"), new Exponentiate());
        this.variableScope.declare(true, new IdentifierExpressionNode("mod"), new Mod());
    }

    @Override
    public String getType() {
        return "Float";
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public class Add extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'add' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = interpreter.visitExpression(arguments.get(0));

            if (other.getType().equals("Float")) {
                return new FloatClass(FloatClass.this.value + ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(FloatClass.this.value + ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply operator '+' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Divide extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'divide' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = interpreter.visitExpression(arguments.get(0));

            if (other.getType().equals("Float")) {
                return new FloatClass(FloatClass.this.value / ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(FloatClass.this.value / ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply operator '/' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Exponentiate extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'exponentiate' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = interpreter.visitExpression(arguments.get(0));

            if (other.getType().equals("Float")) {
                return new FloatClass(Math.pow(FloatClass.this.value, ((FloatClass) other).value));
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(Math.pow(FloatClass.this.value, ((IntegerClass) other).value));
            }

            throw new TypeError("Cannot apply operator '^' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Mod extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'mod' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = interpreter.visitExpression(arguments.get(0));

            if (other.getType().equals("Float")) {
                return new FloatClass(FloatClass.this.value % ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(FloatClass.this.value % ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply operator '%' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Multiply extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'multiply' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = interpreter.visitExpression(arguments.get(0));

            if (other.getType().equals("Float")) {
                return new FloatClass(FloatClass.this.value * ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(FloatClass.this.value * ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply operator '*' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Subtract extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'subtract' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = interpreter.visitExpression(arguments.get(0));

            if (other.getType().equals("Float")) {
                return new FloatClass(FloatClass.this.value - ((FloatClass) other).value);
            } else if (other.getType().equals("Integer")) {
                return new FloatClass(FloatClass.this.value - ((IntegerClass) other).value);
            }

            throw new TypeError("Cannot apply operator '-' to types '" + FloatClass.this.getType() + "' and '" + other.getType() + "'");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class ToString extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'toString' requires 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringClass(FloatClass.this.value.toString());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }
}
