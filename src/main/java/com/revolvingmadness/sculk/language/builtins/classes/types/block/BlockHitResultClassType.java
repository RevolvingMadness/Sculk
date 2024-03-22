package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.NBTDeserializer;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.DictionaryInstance;

public class BlockHitResultClassType extends NBTBuiltinClassType {
    public static final BlockHitResultClassType TYPE = new BlockHitResultClassType();

    private BlockHitResultClassType() {
        super("BlockHitResult");
    }

    @Override
    public BuiltinClass fromNBTDictionary(DictionaryInstance dictionary) {
        return NBTDeserializer.deserializeBlockHitResult(dictionary);
    }
}
