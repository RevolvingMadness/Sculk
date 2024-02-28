package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class BlockHitResultClassType extends BuiltinClassType {
    public static final BlockHitResultClassType TYPE = new BlockHitResultClassType();

    private BlockHitResultClassType() {
        super("BlockHitResult");
    }
}
