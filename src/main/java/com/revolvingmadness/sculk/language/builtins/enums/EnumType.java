package com.revolvingmadness.sculk.language.builtins.enums;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class EnumType extends BuiltinType {
    public static final EnumType TYPE = new EnumType();

    private EnumType() {
        super("Enum");
    }
}
