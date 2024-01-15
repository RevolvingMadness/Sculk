package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinEnum;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class UserDefinedEnumType extends BuiltinEnum {
    public UserDefinedEnumType(List<TokenType> accessModifiers, String name, List<String> values) {
        super(accessModifiers, name, values);
    }
}