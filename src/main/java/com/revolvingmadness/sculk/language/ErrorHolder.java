package com.revolvingmadness.sculk.language;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.errors.NameError;
import com.revolvingmadness.sculk.language.errors.SyntaxError;
import com.revolvingmadness.sculk.language.errors.TypeError;
import com.revolvingmadness.sculk.language.interpreter.errors.InterpreterError;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.util.Identifier;

public class ErrorHolder {
    public static TypeError argumentRequiresType(int argumentNumber, String functionName, BuiltinType type, BuiltinType requiredType) {
        return new TypeError("Argument " + argumentNumber + " for function '" + functionName + "' requires type '" + type + "' but was passed type '" + requiredType + "'");
    }

    public static TypeError cannotApplyUnaryOperatorToType(String operator, BuiltinType type) {
        return new TypeError("Unsupported unary operator '" + operator + "' for type '" + type + "'");
    }

    public static TypeError cannotChangeValueOfVariableBecauseItIsAConstant(String name) {
        return new TypeError("Cannot change the value of '" + name + "' because it is a constant");
    }

    public static InterpreterError cannotConvertType(BuiltinType type, BuiltinType requiredType) {
        return new InterpreterError("Cannot convert type '" + type + "' to '" + requiredType + "'");
    }

    public static TypeError cannotIndexTypeByType(BuiltinType type, BuiltinType indexType) {
        return new TypeError("Invalid index type '" + type + "' by type '" + indexType + "'");
    }

    public static TypeError functionRequiresReturnType(String functionName, BuiltinClass type, BuiltinType requiredType) {
        return new TypeError("Incompatible return type for function '" + functionName + "' (got '" + type + "', expected '" + requiredType + "')");
    }

    public static SyntaxError invalidArgumentCount(String functionName, int argumentCount, int requiredArgumentCount) {
        return new SyntaxError("Function '" + functionName + "' requires " + requiredArgumentCount + " argument(s) but was passed " + argumentCount + " argument(s)");
    }

    public static SyntaxError invalidIdentifier(String identifier) {
        return new SyntaxError("Invalid identifier '" + identifier + "'");
    }

    public static NameError scriptDoesNotExist(Identifier scriptIdentifier) {
        return new NameError("Script '" + scriptIdentifier + "' does not exist");
    }

    public static NameError typeHasNoProperty(BuiltinType type, String propertyName) {
        return new NameError("'" + type + "' has no attribute '" + propertyName + "'");
    }

    public static TypeError typeIsNotIndexable(BuiltinType type) {
        return new TypeError("'" + type + "' is not indexable");
    }

    public static TypeError unsupportedBinaryOperator(String operator, BuiltinType left, BuiltinType right) {
        return new TypeError("Unsupported binary operator '" + operator + "' for types '" + left + "' and '" + right + "'");
    }

    public static InterpreterError unsupportedPostfixOperator(TokenType operator) {
        return new InterpreterError("Unsupported postfix operator '" + operator + "'");
    }

    public static NameError variableHasNotBeenDeclared(String name) {
        return new NameError("'" + name + "' has not been declared");
    }
}
