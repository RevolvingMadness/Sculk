package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class DictionaryClassType extends BuiltinClassType {
    public static final DictionaryClassType TYPE = new DictionaryClassType();

    private DictionaryClassType() {
        super("Dictionary");
    }
}
