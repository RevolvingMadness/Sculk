package com.revolvingmadness.sculk.language;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.errors.NBTSerializationError;
import net.minecraft.block.Block;
import net.minecraft.nbt.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Map;

public class NBTSerializer {
    public static NbtElement serializeBlock(Block block) {
        return NbtString.of(Registries.BLOCK.getId(block).toString());
    }

    public static NbtElement serializeBlockPos(BlockPos pos) {
        NbtCompound compound = new NbtCompound();

        compound.putInt("x", pos.getX());
        compound.putInt("y", pos.getY());
        compound.putInt("z", pos.getZ());

        return compound;
    }

    public static NbtElement serializeBoolean(boolean value) {
        return NbtByte.of(value);
    }

    public static NbtElement serializeDictionary(Map<BuiltinClass, BuiltinClass> value) {
        NbtCompound compound = new NbtCompound();

        value.forEach((key, value_) -> {
            if (!key.instanceOf(StringClassType.TYPE)) {
                throw new NBTSerializationError("Dictionary keys have to be strings when de-serializing");
            }

            if (!(value_ instanceof NBTBuiltinClass nbtBuiltinClass)) {
                throw new NBTSerializationError(value_.type);
            }

            compound.put(value_.toString(), nbtBuiltinClass.toNBTElement());
        });

        return compound;
    }

    public static NbtElement serializeFloat(double value) {
        return NbtDouble.of(value);
    }

    public static NbtElement serializeInteger(long value) {
        return NbtLong.of(value);
    }

    public static NbtElement serializeList(List<BuiltinClass> list) {
        NbtList nbtList = new NbtList();

        list.forEach(value -> {
            if (!(value instanceof NBTBuiltinClass nbtBuiltinClass)) {
                throw new NBTSerializationError(value.type);
            }

            nbtList.add(nbtBuiltinClass.toNBTElement());
        });

        return nbtList;
    }

    public static NbtElement serializeString(String value) {
        return NbtString.of(value);
    }
}
