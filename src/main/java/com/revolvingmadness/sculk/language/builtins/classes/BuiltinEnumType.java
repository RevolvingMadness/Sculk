package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public abstract class BuiltinEnumType extends BuiltinClassType {
    public int position;

    public BuiltinEnumType(List<TokenType> accessModifiers, String name) {
        super(accessModifiers, name);

        this.position = 1;
    }
}
