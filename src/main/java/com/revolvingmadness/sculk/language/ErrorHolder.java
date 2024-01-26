package com.revolvingmadness.sculk.language;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.BooleanType;
import com.revolvingmadness.sculk.language.errors.*;
import com.revolvingmadness.sculk.language.interpreter.errors.InterpreterError;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;
import com.revolvingmadness.sculk.language.parser.nodes.statement_nodes.StatementNode;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.Identifier;

public class ErrorHolder {
    public static SyntaxError aSwitchStatementCanOnlyHave1DefaultCase() {
        return new SyntaxError(I18n.translate("error.a_switch_statement_can_only_have_1_default_case"));
    }

    public static SyntaxError abstractMethodCannotHaveABody(String name) {
        throw new SyntaxError(I18n.translate("error.abstract_method_cannot_have_a_body", name));
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

    public static SyntaxError cannotDeclareNonAbstractClassWithAbstractMethods(String className) {
        return new SyntaxError(I18n.translate("error.cannot_declare_non_abstract_class_with_abstract_methods", className));
    }

    public static DivisionByZeroError cannotDivideByZero() {
        return new DivisionByZeroError();
    }

    public static TypeError cannotExtendFromNonType(BuiltinType type) {
        return new TypeError(I18n.translate("error.cannot_extend_from_type", type.toString()));
    }

    public static NameError cannotFindScript(Identifier scriptIdentifier) {
        return new NameError(I18n.translate("error.cannot_find_script", scriptIdentifier.toString()));
    }

    public static TypeError cannotGetAbsoluteValueOfType(BuiltinType type) {
        return new TypeError(I18n.translate("error.cannot_get_absolute_value_of_type", type.toString()));
    }

    public static TypeError cannotIndexTypeByType(BuiltinType requiredType, BuiltinType type) {
        return new TypeError(I18n.translate("error.cannot_index_type_by_type", requiredType.toString(), type.toString()));
    }

    public static SyntaxError cannotInstantiateAbstractClass(String className) {
        return new SyntaxError(I18n.translate("error.cannot_instantiate_abstract_class", className));
    }

    public static NameError dictionaryHasNoKey(String key) {
        return new NameError(I18n.translate("error.dictionary_has_no_key", key));
    }

    public static TypeError elseIfStatementConditionRequiresType(BuiltinType requiredType, BuiltinType type) {
        return new TypeError(I18n.translate("error.invalid_else_if_statement_condition_type", requiredType.toString(), type.toString()));
    }

    public static FileError eventsCanOnlyBeRegisteredInALoadScript() {
        return new FileError(I18n.translate("error.events_can_only_be_registered_in_a_load_script"));
    }

    public static TypeError functionRequiresReturnType(String functionName, BooleanType type, BuiltinType requiredType) {
        return new TypeError(I18n.translate("error.function_requires_return_type", functionName, type.toString(), requiredType.toString()));
    }

    public static TypeError ifStatementConditionRequiresType(BuiltinType requiredType, BuiltinType type) {
        return new TypeError(I18n.translate("error.invalid_if_statement_condition_type", requiredType.toString(), type.toString()));
    }

    public static IndexOutOfBoundsError indexOutOfBounds(int index, int length) {
        return new IndexOutOfBoundsError(I18n.translate("error.index_out_of_bounds", index, length));
    }

    public static SyntaxError invalidArgumentCount(String functionName, int requiredArgumentCount, int argumentCount) {
        return new SyntaxError(I18n.translate("error.invalid_argument_count", functionName, requiredArgumentCount, argumentCount));
    }

    public static SyntaxError invalidClassAccessModifier(TokenType accessModifier) {
        return new SyntaxError(I18n.translate("error.invalid_class_access_modifier", accessModifier.toString()));
    }

    public static SyntaxError invalidEnumAccessModifier(TokenType accessModifier) {
        return new SyntaxError(I18n.translate("error.invalid_enum_access_modifier", accessModifier.toString()));
    }

    public static SyntaxError invalidFieldAccessModifier(TokenType accessModifier) {
        return new SyntaxError(I18n.translate("error.invalid_field_access_modifier", accessModifier.toString()));
    }

    public static TypeError invalidForLoopUpdateType(BuiltinType requiredType, BuiltinType type) {
        return new TypeError(I18n.translate("error.invalid_for_loop_update_type", requiredType.toString(), type.toString()));
    }

    public static SyntaxError invalidFunctionAccessModifier(TokenType accessModifier) {
        return new SyntaxError(I18n.translate("error.invalid_function_access_modifier", accessModifier.toString()));
    }

    public static SyntaxError invalidMethodAccessModifier(TokenType accessModifier) {
        return new SyntaxError(I18n.translate("error.invalid_method_access_modifier", accessModifier.toString()));
    }

    public static SyntaxError invalidVariableAccessModifier(TokenType accessModifier) {
        return new SyntaxError(I18n.translate("error.invalid_variable_access_modifier", accessModifier.toString()));
    }

    public static TypeError invalidWhileLoopConditionType(BuiltinType requiredType, BuiltinType type) {
        return new TypeError(I18n.translate("error.invalid_while_loop_condition_type", requiredType.toString(), type.toString()));
    }

    public static MethodNotImplementedError methodNotImplemented(String name, String className) {
        return new MethodNotImplementedError(I18n.translate("error.method_not_implemented", name, className));
    }

    public static SyntaxError switchCaseDoesntYieldAValue(BuiltinClass caseClass) {
        return new SyntaxError(I18n.translate("error.switch_case_doesnt_yield_a_value", caseClass.toString()));
    }

    public static NameError thereIsNoPlayerNamed(String playerName) {
        return new NameError(I18n.translate("error.there_is_no_player_named", playerName));
    }

    public static NameError typeHasNoProperty(BuiltinType type, String propertyName) {
        return new NameError(I18n.translate("error.type_has_no_property", type.toString(), propertyName));
    }

    public static TypeError typeIsNotCallable(BuiltinType type) {
        return new TypeError(I18n.translate("error.type_is_not_callable", type.toString()));
    }

    public static TypeError typeIsNotIndexable(BuiltinType type) {
        return new TypeError(I18n.translate("error.type_is_not_indexable", type.toString()));
    }

    public static InterpreterError unsupportedBinaryOperator(TokenType binaryOperator) {
        return new InterpreterError(I18n.translate("error.unsupported_binary_operator", binaryOperator.toString()));
    }

    public static InterpreterError unsupportedExpressionNodeToInterpret(ExpressionNode expressionNode) {
        return new InterpreterError(I18n.translate("error.unsupported_expression_node_to_interpret", expressionNode.toString()));
    }

    public static InterpreterError unsupportedLiteralExpressionNodeToInterpret(LiteralExpressionNode literalExpression) {
        return new InterpreterError(I18n.translate("error.unsupported_literal_expression_node_to_interpret", literalExpression.toString()));
    }

    public static InterpreterError unsupportedPostfixOperator(TokenType postfixOperator) {
        return new InterpreterError(I18n.translate("error.unsupported_postfix_operator", postfixOperator.toString()));
    }

    public static InterpreterError unsupportedStatementNodeToInterpret(StatementNode statementNode) {
        return new InterpreterError(I18n.translate("error.unsupported_statement_node_to_interpret", statementNode.toString()));
    }

    public static InterpreterError unsupportedUnaryOperator(TokenType unaryOperator) {
        return new InterpreterError(I18n.translate("error.unsupported_unary_operator", unaryOperator.toString()));
    }

    public static NameError variableHasAlreadyBeenDeclared(String name) {
        return new NameError(I18n.translate("error.variable_has_already_been_declared", name));
    }

    public static NameError variableHasNotBeenDeclared(String name) {
        return new NameError(I18n.translate("error.variable_has_not_been_declared", name));
    }
}
