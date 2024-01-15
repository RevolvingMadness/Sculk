package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.builtins.classes.types.EnumType;
import com.revolvingmadness.sculk.language.builtins.classes.types.EnumValue;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public abstract class BuiltinEnum extends BuiltinType {
    public final List<String> values;

    public BuiltinEnum(List<TokenType> accessModifiers, String name, List<String> values) {
        super(accessModifiers, name);
        this.values = values;

        for (int i = 0; i < this.values.size(); i++) {
            this.addValue(this.values.get(i), i + 1);
        }
    }

    public void addValue(String name, int position) {
        this.variableScope.declare(List.of(TokenType.CONST, TokenType.STATIC), name, new EnumValue(name, position, this) {

        });
    }

    @Override
    public BuiltinType getType() {
        return new EnumType();
    }
}
