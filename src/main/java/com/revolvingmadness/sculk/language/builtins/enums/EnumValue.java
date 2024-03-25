package com.revolvingmadness.sculk.language.builtins.enums;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public abstract class EnumValue extends BuiltinClass {
    public final String name;
    public final int position;

    public EnumValue(String name, int position, BuiltinClassType type) {
        super(type);
        this.name = name;
        this.position = position;

        this.variableScope.declare(List.of(TokenType.CONST), "name", new StringInstance(this.name));
        this.variableScope.declare(List.of(TokenType.CONST), "position", new IntegerInstance(this.position));
    }
}
