package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;

public class TypeType extends BuiltinType {
    public TypeType() {
        super("Type");
    }

    @Override
    public BuiltinType getType() {
        return this;
    }
}
