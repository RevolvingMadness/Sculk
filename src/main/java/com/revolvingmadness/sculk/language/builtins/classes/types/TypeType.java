package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class TypeType extends BuiltinType {
    public TypeType() {
        super("Type");
    }

    @Override
    public BuiltinType getType() {
        return this;
    }
}
