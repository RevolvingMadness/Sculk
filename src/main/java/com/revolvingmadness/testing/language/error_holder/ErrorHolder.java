package com.revolvingmadness.testing.language.error_holder;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.errors.NameError;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.errors.InterpreterError;
import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.statement_nodes.StatementNode;
import net.minecraft.client.resource.language.I18n;

public class ErrorHolder {
    public static TypeError argumentRequiresType(int argumentNumber, String functionName, BuiltinType requiredType, BuiltinType type) {
        return new TypeError(I18n.translate("error.argument_requires_type", argumentNumber, functionName, requiredType, type));
    }

    public static TypeError canOnlyCheckInstanceOfTypes() {
        return new TypeError(I18n.translate("error.can_only_check_instance_of_types"));
    }

    public static TypeError cannotApplyBinaryOperatorToTypes(String operator, BuiltinType left, BuiltinType right) {
        return new TypeError(I18n.translate("error.cannot_apply_binary_operator", operator, left, right));
    }

    public static TypeError cannotApplyUnaryOperatorToType(String operator, BuiltinType type) {
        return new TypeError(I18n.translate("error.cannot_apply_unary_operator", operator, type));
    }

    public static TypeError cannotAssignValueToVariableBecauseItIsAConstant(String name) {
        return new TypeError(I18n.translate("error.cannot_assign_value_to_variable_because_it_is_a_constant", name));
    }

    public static InterpreterError cannotConvertType(BuiltinType type, BuiltinType requiredType) {
        return new InterpreterError(I18n.translate("error.cannot_convert_type", type, requiredType));
    }

    public static TypeError cannotExtendFromNonType(BuiltinClass clazz) {
        return new TypeError(I18n.translate("error.cannot_extend_from_type", clazz.getType()));
    }

    public static TypeError cannotIndexListByType(BuiltinType type) {
        return new TypeError(I18n.translate("error.cannot_index_list_by_type", type));
    }

    public static NameError dictionaryHasNoKey(String key) {
        return new NameError(I18n.translate("error.dictionary_has_no_key", key));
    }

    public static TypeError difficultyDoesNotExist(String difficulty) {
        return new TypeError(I18n.translate("error.difficulty_does_not_exist", difficulty));
    }

    public static TypeError gamemodeDoesNotExist(String gamemode) {
        return new TypeError(I18n.translate("error.gamemode_does_not_exist", gamemode));
    }

    public static TypeError ifStatementConditionRequiresType(BuiltinType requiredType, BuiltinType type) {
        return new TypeError(I18n.translate("error.invalid_if_statement_condition_type", requiredType, type));
    }

    public static SyntaxError invalidArgumentCount(String functionName, int requiredArgumentCount, int argumentCount) {
        return new SyntaxError(I18n.translate("error.invalid_argument_count", functionName, requiredArgumentCount, argumentCount));
    }

    public static TypeError invalidForLoopUpdateType(BuiltinType requiredType, BuiltinType type) {
        return new TypeError(I18n.translate("error.invalid_for_loop_update_type", requiredType, type));
    }

    public static TypeError invalidWhileLoopConditionType(BuiltinType requiredType, BuiltinType type) {
        return new TypeError(I18n.translate("error.invalid_while_loop_condition_type", requiredType, type));
    }

    public static NameError thereIsNoPlayerNamed(String playerName) {
        return new NameError(I18n.translate("error.there_is_no_player_named", playerName));
    }

    public static NameError typeHasNoProperty(BuiltinType type, String propertyName) {
        return new NameError(I18n.translate("error.type_has_no_property", type, propertyName));
    }

    public static TypeError typeIsNotCallable(BuiltinType type) {
        return new TypeError(I18n.translate("error.type_is_not_callable", type));
    }

    public static TypeError typeIsNotIndexable(BuiltinType type) {
        return new TypeError(I18n.translate("error.type_is_not_indexable", type));
    }

    public static InterpreterError unsupportedBinaryOperator(TokenType binaryOperator) {
        return new InterpreterError(I18n.translate("error.unsupported_binary_operator", binaryOperator));
    }

    public static InterpreterError unsupportedExpressionNodeToInterpret(ExpressionNode expressionNode) {
        return new InterpreterError(I18n.translate("error.unsupported_expression_node_to_interpret", expressionNode));
    }

    public static InterpreterError unsupportedLiteralExpressionNodeToInterpret(LiteralExpressionNode literalExpression) {
        return new InterpreterError(I18n.translate("error.unsupported_literal_expression_node_to_interpret", literalExpression));
    }

    public static InterpreterError unsupportedPostfixOperator(TokenType postfixOperator) {
        return new InterpreterError(I18n.translate("error.unsupported_postfix_operator", postfixOperator));
    }

    public static InterpreterError unsupportedStatementNodeToInterpret(StatementNode statementNode) {
        return new InterpreterError(I18n.translate("error.unsupported_statement_node_to_interpret", statementNode));
    }

    public static InterpreterError unsupportedUnaryOperator(TokenType unaryOperator) {
        return new InterpreterError(I18n.translate("error.unsupported_unary_operator", unaryOperator));
    }

    public static NameError variableHasAlreadyBeenDeclared(String name) {
        return new NameError(I18n.translate("error.variable_has_already_been_declared", name));
    }

    public static NameError variableHasNotBeenDeclared(String name) {
        return new NameError(I18n.translate("error.variable_has_not_been_declared", name));
    }
}
