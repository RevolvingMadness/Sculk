package com.revolvingmadness.sculk.language.interpreter;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Variable implements Serializable {
    public final List<TokenType> accessModifiers;
    public final String name;
    public final BuiltinClassType type;
    public BuiltinClass value;

    public Variable(List<TokenType> accessModifiers, BuiltinClassType type, String name, BuiltinClass value) {
        this.accessModifiers = accessModifiers;
        this.type = type;
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Variable variable = (Variable) o;
        return Objects.equals(this.accessModifiers, variable.accessModifiers) && Objects.equals(this.name, variable.name) && Objects.equals(this.type, variable.type) && Objects.equals(this.value, variable.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.accessModifiers, this.name, this.type, this.value);
    }

    public boolean isAbstract() {
        return this.accessModifiers.contains(TokenType.ABSTRACT);
    }

    public boolean isConstant() {
        return this.accessModifiers.contains(TokenType.CONST);
    }

    public boolean isNonNull() {
        return this.accessModifiers.contains(TokenType.NONULL);
    }
}
