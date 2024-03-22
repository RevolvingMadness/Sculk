package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.NBTDeserializer;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockPosInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.DictionaryInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class BlockPosClassType extends NBTBuiltinClassType {
    public static final BlockPosClassType TYPE = new BlockPosClassType();

    private BlockPosClassType() {
        super("BlockPos");
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(IntegerClassType.TYPE, IntegerClassType.TYPE, IntegerClassType.TYPE));

        long x = arguments.get(0).toInteger();
        long y = arguments.get(1).toInteger();
        long z = arguments.get(2).toInteger();

        return new BlockPosInstance(new BlockPos((int) x, (int) y, (int) z));
    }

    @Override
    public BuiltinClass fromNBTDictionary(DictionaryInstance dictionary) {
        return NBTDeserializer.deserializeBlockPos(dictionary);
    }
}
