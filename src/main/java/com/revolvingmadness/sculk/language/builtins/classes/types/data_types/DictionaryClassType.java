package com.revolvingmadness.sculk.language.builtins.classes.types.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.DictionaryInstance;

public class DictionaryClassType extends NBTBuiltinClassType {
    public static final DictionaryClassType TYPE = new DictionaryClassType();

    private DictionaryClassType() {
        super("Dictionary");
    }

    @Override
    public BuiltinClass fromNBTDictionary(DictionaryInstance dictionary) {
        return dictionary;
    }
}
