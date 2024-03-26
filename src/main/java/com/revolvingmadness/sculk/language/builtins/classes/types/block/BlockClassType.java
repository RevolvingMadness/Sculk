package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.NBTDeserializer;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.FloatInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.item.ItemInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

import java.util.List;

public class BlockClassType extends NBTBuiltinClassType {
    public static final BlockClassType TYPE = new BlockClassType();

    private BlockClassType() {
        super("Block");

        this.addNoArgMethod("asItem", builtinClass -> new ItemInstance(builtinClass.toBlock().asItem()));
        this.addNoArgMethod("getBlastResistance", builtinClass -> new FloatInstance(builtinClass.toBlock().getBlastResistance()));
        this.addNoArgMethod("getName", builtinClass -> new StringInstance(builtinClass.toBlock().getName().getString()));
        this.addNoArgMethod("getSlipperiness", builtinClass -> new FloatInstance(builtinClass.toBlock().getSlipperiness()));
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(StringClassType.TYPE, BlockSettingsClassType.TYPE));

        return new BlockInstance(new Identifier(interpreter.identifier.getNamespace(), arguments.get(0).toString()), new Block(arguments.get(1).toBlockSettings()));
    }

    @Override
    public BuiltinClass fromNBTString(StringInstance string) {
        return NBTDeserializer.deserializeBlock(string);
    }
}
