package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.language.NBTDeserializer;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockSettingsInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.DictionaryInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

import java.util.List;

@SuppressWarnings("unused")
public class BlockSettingsClassType extends NBTBuiltinClassType {
    public static final BlockSettingsClassType TYPE = new BlockSettingsClassType();

    private BlockSettingsClassType() {
        super("BlockSettings");

        try {
            this.addMethod("of", List.of(BlockClassType.TYPE));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments);

        return new BlockSettingsInstance();
    }

    @Override
    public BuiltinClass fromNBTDictionary(DictionaryInstance dictionary) {
        return NBTDeserializer.deserializeBlockSettings(dictionary);
    }

    public BuiltinClass of(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        Block block = arguments[0].toBlock();

        return new BlockSettingsInstance(FabricBlockSettings.copyOf(block));
    }
}
