package com.revolvingmadness.sculk.language.builtins.classes.instances.entity;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.entity.EntityTypeType;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;
import java.util.Objects;

public class EntityTypeInstance extends BuiltinClass {
    public final boolean isFireImmune;

    public EntityTypeInstance(boolean isFireImmune) {
        this.isFireImmune = isFireImmune;

        this.variableScope.declare(List.of(TokenType.CONST), "isFireImmune", new BooleanInstance(isFireImmune));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        EntityTypeInstance that = (EntityTypeInstance) o;
        return this.isFireImmune == that.isFireImmune;
    }

    @Override
    public BuiltinType getType() {
        return EntityTypeType.TYPE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.isFireImmune);
    }
}
