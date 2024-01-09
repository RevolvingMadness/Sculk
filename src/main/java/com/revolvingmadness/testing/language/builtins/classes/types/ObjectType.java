package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.VariableScope;

import java.util.List;

public class ObjectType extends BuiltinType {
    public ObjectType() {
        super("Object", null, new VariableScope());
        this.typeVariableScope.declare(true, "toString", new ToString());
        this.typeVariableScope.declare(true, "add", new Add());
        this.typeVariableScope.declare(true, "subtract", new Subtract());
        this.typeVariableScope.declare(true, "multiply", new Multiply());
        this.typeVariableScope.declare(true, "divide", new Divide());
        this.typeVariableScope.declare(true, "exponentiate", new Exponentiate());
        this.typeVariableScope.declare(true, "mod", new Mod());
        this.typeVariableScope.declare(true, "negate", new Negate());
        this.typeVariableScope.declare(true, "equalTo", new EqualTo());
        this.typeVariableScope.declare(true, "notEqualTo", new NotEqualTo());
        this.typeVariableScope.declare(true, "instanceOf", new InstanceOf());
        this.typeVariableScope.declare(true, "lessThan", new LessThan());
        this.typeVariableScope.declare(true, "lessThanOrEqualTo", new LessThanOrEqualTo());
        this.typeVariableScope.declare(true, "greaterThan", new GreaterThan());
        this.typeVariableScope.declare(true, "greaterThanOrEqualTo", new GreaterThanOrEqualTo());
        this.typeVariableScope.declare(true, "logicalNot", new LogicalNot());
    }

    private static class Add extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("add", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("+", this.boundClass.getType(), other.getType());
        }
    }

    private static class Divide extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("divide", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("/", this.boundClass.getType(), other.getType());
        }
    }

    private static class EqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("equalTo", 1, arguments.size());
            }

            BuiltinClass o = arguments.get(0);

            return new BooleanInstance(this.boundClass.equals(o));
        }
    }

    private static class Exponentiate extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("exponentiate", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("^", this.boundClass.getType(), other.getType());
        }
    }

    private static class GreaterThan extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("greaterThan", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes(">", this.boundClass.getType(), other.getType());
        }
    }

    private static class GreaterThanOrEqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("greaterThanOrEqualTo", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes(">=", this.boundClass.getType(), other.getType());
        }
    }

    private static class InstanceOf extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("instanceOf", 1, arguments.size());
            }

            BuiltinClass o = arguments.get(0);

            if (!(o instanceof BuiltinType type)) {
                throw ErrorHolder.canOnlyCheckInstanceOfTypes();
            }

            return new BooleanInstance(this.boundClass.instanceOf(type));
        }
    }

    private static class LessThan extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("lessThan", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("<", this.boundClass.getType(), other.getType());
        }
    }

    private static class LessThanOrEqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("lessThanOrEqualTo", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("<=", this.boundClass.getType(), other.getType());
        }
    }

    private static class LogicalNot extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("logicalNot", 0, arguments.size());
            }

            throw ErrorHolder.cannotApplyUnaryOperatorToType("!", this.boundClass.getType());
        }
    }

    private static class Mod extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("mod", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("%", this.boundClass.getType(), other.getType());
        }
    }

    private static class Multiply extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("multiply", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("*", this.boundClass.getType(), other.getType());
        }
    }

    private static class Negate extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("negate", 0, arguments.size());
            }

            throw ErrorHolder.cannotApplyUnaryOperatorToType("-", this.boundClass.getType());
        }
    }

    private static class NotEqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("notEqualTo", 1, arguments.size());
            }

            BuiltinClass o = arguments.get(0);

            return new BooleanInstance(!this.boundClass.equals(o));
        }
    }

    private static class Subtract extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("subtract", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("-", this.boundClass.getType(), other.getType());
        }
    }

    private static class ToString extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return new StringInstance("<Class '" + this.boundClass.getType() + "'>");
        }
    }
}