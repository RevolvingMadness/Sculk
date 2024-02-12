package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;

public class BlockHitResultType extends BuiltinType {
    public static final BlockHitResultType TYPE = new BlockHitResultType();

    private BlockHitResultType() {
        super("BlockHitResult");
    }
}
