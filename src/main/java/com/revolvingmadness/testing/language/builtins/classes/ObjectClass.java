package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
import com.revolvingmadness.testing.language.builtins.classes.types.StringClass;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;

import java.util.List;

public class ObjectClass extends BaseClassExpressionNode {
    public ObjectClass() {
        super(null);
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
        this.variableScope.declare(true, new IdentifierExpressionNode("instanceOf"), new InstanceOf());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ObjectClass;
    }

    @Override
    public int hashCode() {
        return ObjectClass.class.hashCode();
    }

    @Override
    public String getType() {
        return "Object";
    }

    public class Negate extends BaseClassExpressionNode {
        public Negate() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'negate' requires 0 arguments but got " + arguments.size() + " argument(s)");
            }

            throw new SyntaxError("Cannot apply unary operator '-' to type '" + ObjectClass.this.getType() + "'");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class EqualTo extends BaseClassExpressionNode {
        public EqualTo() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'equalTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(ObjectClass.this.equals(o));
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class NotEqualTo extends BaseClassExpressionNode {
        public NotEqualTo() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'notEqualTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(!ObjectClass.this.equals(o));
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class InstanceOf extends BaseClassExpressionNode {
        public InstanceOf() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'instanceOf' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(ObjectClass.this.instanceOf(o));
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Add extends BaseClassExpressionNode {
        public Add() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'add' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw new TypeError("Cannot apply binary operator '+' to types '" + ObjectClass.this.getType() + "' and '" + other.getType() + "'");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Divide extends BaseClassExpressionNode {
        public Divide() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'divide' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw new TypeError("Cannot apply binary operator '/' to types '" + ObjectClass.this.getType() + "' and '" + other.getType() + "'");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Exponentiate extends BaseClassExpressionNode {
        public Exponentiate() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'exponentiate' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw new TypeError("Cannot apply binary operator '^' to types '" + ObjectClass.this.getType() + "' and '" + other.getType() + "'");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Mod extends BaseClassExpressionNode {
        public Mod() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'mod' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw new TypeError("Cannot apply binary operator '%' to types '" + ObjectClass.this.getType() + "' and '" + other.getType() + "'");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Multiply extends BaseClassExpressionNode {
        public Multiply() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'multiply' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw new TypeError("Cannot apply binary operator '*' to types '" + ObjectClass.this.getType() + "' and '" + other.getType() + "'");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class Subtract extends BaseClassExpressionNode {
        public Subtract() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'subtract' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode other = arguments.get(0);

            throw new TypeError("Cannot apply binary operator '-' to types '" + ObjectClass.this.getType() + "' and '" + other.getType() + "'");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class ToString extends BaseClassExpressionNode {
        public ToString() {
            super(ObjectClass.this);
        }

        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'toString' requires 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringClass("<Class '" + ObjectClass.this.getType() + "'>");
        }

        @Override
        public String getType() {
            return "Function";
        }
    }
}