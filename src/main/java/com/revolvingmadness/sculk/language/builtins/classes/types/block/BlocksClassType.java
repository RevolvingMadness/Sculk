package com.revolvingmadness.sculk.language.builtins.classes.types.block;

import com.revolvingmadness.sculk.dynamicreg.DynamicBlockRegistry;
import com.revolvingmadness.sculk.dynamicreg.DynamicItemRegistry;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.ScriptTag;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.errors.NameError;
import com.revolvingmadness.sculk.language.errors.SyntaxError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class BlocksClassType extends BuiltinClassType {
    public static final BlocksClassType TYPE = new BlocksClassType();

    private BlocksClassType() {
        super("Blocks");

        try {
            this.addMethod("get", List.of(StringClassType.TYPE));
            this.addMethod("register", List.of(BlockClassType.TYPE));
            this.addMethod("registerWithItem", List.of(BlockClassType.TYPE));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public BuiltinClass get(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        String identifierClass = arguments[0].toString();

        Identifier identifier = Identifier.tryParse(identifierClass);

        if (identifier == null) {
            throw ErrorHolder.invalidIdentifier(identifierClass);
        }

        if (Objects.equals(identifier, new Identifier("air"))) {
            return new BlockInstance(Blocks.AIR);
        }

        Block block = Registries.BLOCK.get(identifier);

        if (block == Blocks.AIR) {
            throw new NameError("Block '" + identifier + "' does not exist");
        }

        return new BlockInstance(block);
    }

    public BuiltinClass register(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        if (interpreter.scriptTag != ScriptTag.START) {
            throw new SyntaxError("Blocks can only be registered in the 'start' script tag");
        }

        BlockInstance block = arguments[0].toBlockInstance();

        DynamicBlockRegistry.register(block.id, block.value);

        return block;
    }

    public BuiltinClass registerWithItem(Interpreter interpreter, BuiltinClass boundClass, BuiltinClass[] arguments) {
        if (interpreter.scriptTag != ScriptTag.START) {
            throw new SyntaxError("Blocks can only be registered in the 'start' script tag");
        }

        BlockInstance block = arguments[0].toBlockInstance();

        DynamicBlockRegistry.register(block.id, block.value);
        DynamicItemRegistry.register(block.id, block.value);

        return block;
    }
}
