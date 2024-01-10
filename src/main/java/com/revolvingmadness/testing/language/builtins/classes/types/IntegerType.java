package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.FloatInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.lexer.TokenType;

import java.util.List;

public class IntegerType extends BuiltinType {
    public IntegerType() {
        super("Integer");
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
    }

    private static class Add extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("add", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new FloatType())) {
                return new FloatInstance(this.boundClass.toInteger() + other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new IntegerInstance(this.boundClass.toInteger() + other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("+", new IntegerType(), other.getType());
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
                return new FloatInstance(this.boundClass.toInteger() / other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new IntegerInstance(this.boundClass.toInteger() / other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("/", new IntegerType(), other.getType());
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
                return new FloatInstance(Math.pow(this.boundClass.toInteger(), other.toFloat()));
            } else if (other.instanceOf(new IntegerType())) {
                return new FloatInstance(Math.pow(this.boundClass.toInteger(), other.toInteger()));
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("^", new IntegerType(), other.getType());
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
                return new BooleanInstance(this.boundClass.toInteger() > other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new BooleanInstance(this.boundClass.toInteger() > other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes(">", new IntegerType(), other.getType());
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
                return new BooleanInstance(this.boundClass.toInteger() >= other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new BooleanInstance(this.boundClass.toInteger() >= other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes(">=", new IntegerType(), other.getType());
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
                return new BooleanInstance(this.boundClass.toInteger() < other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new BooleanInstance(this.boundClass.toInteger() < other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("<", new IntegerType(), other.getType());
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
                return new BooleanInstance(this.boundClass.toInteger() <= other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new BooleanInstance(this.boundClass.toInteger() <= other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("<=", new IntegerType(), other.getType());
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
                return new FloatInstance(this.boundClass.toInteger() % other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new IntegerInstance(this.boundClass.toInteger() % other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("%", new IntegerType(), other.getType());
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
                return new FloatInstance(this.boundClass.toInteger() * other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new IntegerInstance(this.boundClass.toInteger() * other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("*", new IntegerType(), other.getType());
        }
    }

    private static class Negate extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("negate", 0, arguments.size());
            }

            return new IntegerInstance(-this.boundClass.toInteger());
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
                return new FloatInstance(this.boundClass.toInteger() - other.toFloat());
            } else if (other.instanceOf(new IntegerType())) {
                return new IntegerInstance(this.boundClass.toInteger() - other.toInteger());
            }

            throw ErrorHolder.cannotApplyBinaryOperatorToTypes("-", new IntegerType(), other.getType());
        }
    }

    private static class ToString extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return new StringInstance(this.boundClass.toInteger().toString());
        }
    }
}
