package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public abstract class EnumValue extends BuiltinClass {
    public final String name;
    public final int position;
    public final BuiltinType type;

    public EnumValue(String name, int position, BuiltinType type) {
        this.name = name;
        this.position = position;
        this.type = type;

        this.variableScope.declare(List.of(TokenType.CONST), "name", new StringInstance(this.name));
        this.variableScope.declare(List.of(TokenType.CONST), "position", new IntegerInstance(this.position));
    }

    @Override
    public BuiltinType getType() {
        return this.type;
    }
}
