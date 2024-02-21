package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockPosInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class BlockPosType extends BuiltinType {
    public static final BlockPosType TYPE = new BlockPosType();

    private BlockPosType() {
        super("BlockPos");
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(IntegerType.TYPE, IntegerType.TYPE, IntegerType.TYPE));

        long x = arguments.get(0).toInteger();
        long y = arguments.get(1).toInteger();
        long z = arguments.get(2).toInteger();

        return new BlockPosInstance(new BlockPos((int) x, (int) y, (int) z));
    }


}
