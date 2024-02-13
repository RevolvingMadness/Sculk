package com.revolvingmadness.sculk.language;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.errors.DivisionByZeroError;
import com.revolvingmadness.sculk.language.errors.NameError;
import com.revolvingmadness.sculk.language.errors.SyntaxError;
import com.revolvingmadness.sculk.language.errors.TypeError;
import com.revolvingmadness.sculk.language.interpreter.errors.InterpreterError;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.Identifier;

public class ErrorHolder {
    public static SyntaxError aSwitchStatementCanOnlyHave1DefaultCase() {
        return new SyntaxError(I18n.translate("error.a_switch_statement_can_only_have_1_default_case"));
    }

    public static TypeError argumentRequiresType(int argumentNumber, String functionName, BuiltinType requiredType, BuiltinType type) {
        return new TypeError(I18n.translate("error.argument_requires_type", argumentNumber, functionName, requiredType.toString(), type.toString()));
    }

    public static TypeError cannotApplyBinaryOperatorToTypes(String operator, BuiltinType left, BuiltinType right) {
        return new TypeError(I18n.translate("error.cannot_apply_binary_operator", operator, left.toString(), right.toString()));
    }

    public static TypeError cannotApplyUnaryOperatorToType(String operator, BuiltinType type) {
        return new TypeError(I18n.translate("error.cannot_apply_unary_operator", operator, type.toString()));
    }

    public static TypeError cannotAssignValueToVariableBecauseItIsAConstant(String name) {
        return new TypeError(I18n.translate("error.cannot_assign_value_to_variable_because_it_is_a_constant", name));
    }

    public static InterpreterError cannotConvertType(BuiltinType type, BuiltinType requiredType) {
        return new InterpreterError(I18n.translate("error.cannot_convert_type", type.toString(), requiredType.toString()));
    }

    public static DivisionByZeroError cannotDivideByZero() {
        return new DivisionByZeroError();
    }

    public static NameError cannotFindScript(Identifier scriptIdentifier) {
        return new NameError(I18n.translate("error.cannot_find_script", scriptIdentifier.toString()));
    }

    public static TypeError cannotIndexTypeByType(BuiltinType requiredType, BuiltinType type) {
        return new TypeError(I18n.translate("error.cannot_index_type_by_type", requiredType.toString(), type.toString()));
    }

    public static NameError dictionaryHasNoKey(String key) {
        return new NameError(I18n.translate("error.dictionary_has_no_key", key));
    }

    public static TypeError functionRequiresReturnType(String functionName, BuiltinClass type, BuiltinType requiredType) {
        return new TypeError(I18n.translate("error.function_requires_return_type", functionName, type.toString(), requiredType.toString()));
    }

    public static SyntaxError invalidArgumentCount(String functionName, int requiredArgumentCount, int argumentCount) {
        return new SyntaxError(I18n.translate("error.invalid_argument_count", functionName, requiredArgumentCount, argumentCount));
    }

    public static SyntaxError invalidIdentifier(String identifier) {
        return new SyntaxError("Invalid identifier '" + identifier + "'");
    }

    public static NameError typeHasNoProperty(BuiltinType type, String propertyName) {
        return new NameError(I18n.translate("error.type_has_no_property", type.toString(), propertyName));
    }

    public static TypeError typeIsNotIndexable(BuiltinType type) {
        return new TypeError(I18n.translate("error.type_is_not_indexable", type.toString()));
    }

    public static InterpreterError unsupportedPostfixOperator(TokenType postfixOperator) {
        return new InterpreterError(I18n.translate("error.unsupported_postfix_operator", postfixOperator.toString()));
    }

    public static NameError variableHasNotBeenDeclared(String name) {
        return new NameError(I18n.translate("error.variable_has_not_been_declared", name));
    }
}
