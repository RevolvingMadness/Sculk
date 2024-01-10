package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.lexer.TokenType;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Variable implements Serializable {
    public final List<TokenType> accessModifiers;
    public final String name;
    public BuiltinClass value;

    public Variable(List<TokenType> accessModifiers, String name, BuiltinClass value) {
        this.accessModifiers = accessModifiers;
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Variable variable = (Variable) o;
        return Objects.equals(accessModifiers, variable.accessModifiers) && Objects.equals(name, variable.name) && Objects.equals(value, variable.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessModifiers, name, value);
    }

    public boolean isAbstract() {
        return this.accessModifiers.contains(TokenType.ABSTRACT);
    }

    public boolean isConstant() {
        return this.accessModifiers.contains(TokenType.CONST);
    }
}
