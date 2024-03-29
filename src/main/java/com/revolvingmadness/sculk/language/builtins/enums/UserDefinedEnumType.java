package com.revolvingmadness.sculk.language.builtins.enums;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinEnumType;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class UserDefinedEnumType extends BuiltinEnumType {
    public UserDefinedEnumType(List<TokenType> accessModifiers, String name, List<String> values) {
        super(accessModifiers, name);

        values.forEach(this::addValue);
    }

    public void addValue(String name) {
        this.variableScope.declare(List.of(TokenType.CONST), name, new UserDefinedEnumValue(name, this.position++, this));
    }

    private static class UserDefinedEnumValue extends EnumValue {
        public UserDefinedEnumValue(String name, int position, BuiltinClassType type) {
            super(name, position, type);
        }
    }
}