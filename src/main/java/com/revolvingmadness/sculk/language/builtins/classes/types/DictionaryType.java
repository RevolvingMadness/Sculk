package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class DictionaryType extends BuiltinType {
    public static final DictionaryType TYPE = new DictionaryType();

    private DictionaryType() {
        super("Dictionary");
    }
}
