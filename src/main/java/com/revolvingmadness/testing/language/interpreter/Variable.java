package com.revolvingmadness.testing.language.interpreter;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.lexer.TokenType;

import java.io.Serializable;
import java.util.List;

public class Variable implements Serializable {
    public final List<TokenType> accessModifiers;
    public final boolean isConstant;
    public final String name;
    public BuiltinClass value;

    public Variable(List<TokenType> accessModifiers, boolean isConstant, String name, BuiltinClass value) {
        this.accessModifiers = accessModifiers;
        this.isConstant = isConstant;
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

        if (!this.name.equals(variable.name))
            return false;
        return this.value.equals(variable.value);
    }

    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + this.value.hashCode();
        return result;
    }
}
