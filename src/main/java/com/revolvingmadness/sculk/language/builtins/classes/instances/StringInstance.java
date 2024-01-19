package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.types.StringType;

import java.util.Objects;

public class StringInstance extends BuiltinClass {
    public final String value;

    public StringInstance(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        StringInstance that = (StringInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new StringType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toStringType() {
        return this.value;
    }
}
