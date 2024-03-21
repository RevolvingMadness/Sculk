package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClassType;

public class BlockHitResultClassType extends NBTBuiltinClassType {
    public static final BlockHitResultClassType TYPE = new BlockHitResultClassType();

    private BlockHitResultClassType() {
        super("BlockHitResult");
    }
}
