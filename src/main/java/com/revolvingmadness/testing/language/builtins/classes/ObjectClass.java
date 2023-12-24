package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
import com.revolvingmadness.testing.language.builtins.classes.types.StringClass;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
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

    public class Add extends BaseFunctionExpressionNode {
        public Add() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'add' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw new TypeError("Cannot apply binary operator '+' to types '" + this.boundClass.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class Divide extends BaseFunctionExpressionNode {
        public Divide() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'divide' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw new TypeError("Cannot apply binary operator '/' to types '" + this.boundClass.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class EqualTo extends BaseFunctionExpressionNode {
        public EqualTo() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'equalTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(this.boundClass.equals(o));
        }
    }

    public class Exponentiate extends BaseFunctionExpressionNode {
        public Exponentiate() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'exponentiate' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw new TypeError("Cannot apply binary operator '^' to types '" + this.boundClass.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class InstanceOf extends BaseFunctionExpressionNode {
        public InstanceOf() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'instanceOf' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(this.boundClass.instanceOf(o));
        }
    }

    public class Mod extends BaseFunctionExpressionNode {
        public Mod() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'mod' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw new TypeError("Cannot apply binary operator '%' to types '" + this.boundClass.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class Multiply extends BaseFunctionExpressionNode {
        public Multiply() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'multiply' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw new TypeError("Cannot apply binary operator '*' to types '" + this.boundClass.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class Negate extends BaseFunctionExpressionNode {
        public Negate() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'negate' requires 0 arguments but got " + arguments.size() + " argument(s)");
            }

            throw new SyntaxError("Cannot apply unary operator '-' to type '" + this.boundClass.getType() + "'");
        }
    }

    public class NotEqualTo extends BaseFunctionExpressionNode {
        public NotEqualTo() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'notEqualTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(!this.boundClass.equals(o));
        }
    }

    public class Subtract extends BaseFunctionExpressionNode {
        public Subtract() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'subtract' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw new TypeError("Cannot apply binary operator '-' to types '" + this.boundClass.getType() + "' and '" + other.getType() + "'");
        }
    }

    public class ToString extends BaseFunctionExpressionNode {
        public ToString() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'toString' requires 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringClass("<Class '" + this.boundClass.getType() + "'>");
        }
    }
}