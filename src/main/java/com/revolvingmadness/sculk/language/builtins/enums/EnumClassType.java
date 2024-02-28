package com.revolvingmadness.sculk.language.builtins.enums;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class EnumClassType extends BuiltinClassType {
    public static final EnumClassType TYPE = new EnumClassType();

    private EnumClassType() {
        super("Enum");
    }
}
