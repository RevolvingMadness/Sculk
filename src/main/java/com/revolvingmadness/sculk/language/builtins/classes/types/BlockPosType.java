package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BlockPosInstance;
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
        if (arguments.size() != 3) {
            throw ErrorHolder.invalidArgumentCount("init", 3, arguments.size());
        }

        BuiltinClass xClass = arguments.get(0);
        BuiltinClass yClass = arguments.get(1);
        BuiltinClass zClass = arguments.get(2);

        if (!xClass.instanceOf(IntegerType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(1, "init", IntegerType.TYPE, xClass.getType());
        }

        if (!yClass.instanceOf(IntegerType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(2, "init", IntegerType.TYPE, yClass.getType());
        }

        if (!zClass.instanceOf(IntegerType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(3, "init", IntegerType.TYPE, zClass.getType());
        }

        long x = xClass.toInteger();
        long y = yClass.toInteger();
        long z = zClass.toInteger();

        return new BlockPosInstance(new BlockPos((int) x, (int) y, (int) z));
    }


}
