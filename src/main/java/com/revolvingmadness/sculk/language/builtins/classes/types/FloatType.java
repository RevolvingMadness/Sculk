package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.FloatInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class FloatType extends BuiltinType {
    public FloatType() {
        super("Float");
        this.typeVariableScope.declare(List.of(TokenType.CONST), "add", new Add());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "subtract", new Subtract());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "multiply", new Multiply());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "divide", new Divide());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "exponentiate", new Exponentiate());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "mod", new Mod());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "negate", new Negate());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "lessThan", new LessThan());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "lessThanOrEqualTo", new LessThanOrEqualTo());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "greaterThan", new GreaterThan());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "greaterThanOrEqualTo", new GreaterThanOrEqualTo());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "toString", new ToString());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "increment", new Increment());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "decrement", new Decrement());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "equalTo", new EqualTo());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("init", 1, arguments.size());
        }

        BuiltinClass floatClass = arguments.get(0);

        if (!floatClass.instanceOf(new FloatType())) {
            throw ErrorHolder.argumentRequiresType(1, "init", new FloatType(), floatClass.getType());
        }

        return new FloatInstance(floatClass.toFloat());
    }

    private static class Add extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("add", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new FloatType())) {
                return new FloatInstance(this.boundClass.toFloat() + other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new FloatInstance(this.boundClass.toFloat() + other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("+", new FloatType(), other.getType());
        }
    }

    private static class Decrement extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("decrement", 0, arguments.size());
            }

            return new FloatInstance(this.boundClass.toFloat() - 1);
        }
    }

    private static class Divide extends BuiltinMethod {

        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("divide", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new FloatType())) {
                return new FloatInstance(this.boundClass.toFloat() / other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new FloatInstance(this.boundClass.toFloat() / other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("/", new FloatType(), other.getType());
        }
    }

    private static class EqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("equalTo", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new FloatType())) {
                return new BooleanInstance(other.toFloat() == this.boundClass.toFloat());
            }

            return new BooleanInstance(false);
        }
    }

    private static class Exponentiate extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("exponentiate", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new FloatType())) {
                return new FloatInstance(Math.pow(this.boundClass.toFloat(), other.toFloat()));
            } else if (other.instanceOf(new IntegerType())) {
                return new FloatInstance(Math.pow(this.boundClass.toFloat(), other.toInteger()));
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("^", new FloatType(), other.getType());
        }
    }

    private static class GreaterThan extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("greaterThan", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new FloatType())) {
                return new BooleanInstance(this.boundClass.toFloat() > other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new BooleanInstance(this.boundClass.toFloat() > other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes(">", new FloatType(), other.getType());
        }
    }

    private static class GreaterThanOrEqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("greaterThanOrEqualTo", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new FloatType())) {
                return new BooleanInstance(this.boundClass.toFloat() >= other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new BooleanInstance(this.boundClass.toFloat() >= other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes(">=", new FloatType(), other.getType());
        }
    }

    private static class Increment extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("increment", 0, arguments.size());
            }

            return new FloatInstance(this.boundClass.toFloat() + 1);
        }
    }

    private static class LessThan extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("lessThan", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new FloatType())) {
                return new BooleanInstance(this.boundClass.toFloat() < other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new BooleanInstance(this.boundClass.toFloat() < other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("<", new FloatType(), other.getType());
        }
    }

    private static class LessThanOrEqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("lessThanOrEqualTo", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new FloatType())) {
                return new BooleanInstance(this.boundClass.toFloat() <= other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new BooleanInstance(this.boundClass.toFloat() <= other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("<=", new FloatType(), other.getType());
        }
    }

    private static class Mod extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("mod", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new FloatType())) {
                return new FloatInstance(this.boundClass.toFloat() % other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new FloatInstance(this.boundClass.toFloat() % other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("%", new FloatType(), other.getType());
        }
    }

    private static class Multiply extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("multiply", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new FloatType())) {
                return new FloatInstance(this.boundClass.toFloat() * other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new FloatInstance(this.boundClass.toFloat() * other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("*", new FloatType(), other.getType());
        }
    }


    private static class Negate extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("negate", 0, arguments.size());
            }

            return new FloatInstance(-this.boundClass.toFloat());
        }
    }


    private static class Subtract extends BuiltinMethod {

        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("subtract", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new FloatType())) {
                return new FloatInstance(this.boundClass.toFloat() - other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new FloatInstance(this.boundClass.toFloat() - other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("-", new FloatType(), other.getType());
        }
    }

    private static class ToString extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return new StringInstance(String.valueOf(this.boundClass.toFloat()));
        }
    }
}
