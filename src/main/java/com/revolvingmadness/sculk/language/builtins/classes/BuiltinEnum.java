package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.builtins.classes.types.EnumType;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public abstract class BuiltinEnum extends BuiltinType {
    public int position;

    public BuiltinEnum(List<TokenType> accessModifiers, String name) {
        super(accessModifiers, name);

        this.position = 1;
    }

    @Override
    public BuiltinType getType() {
        return EnumType.TYPE;
    }
}
