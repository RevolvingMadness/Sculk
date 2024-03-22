package com.revolvingmadness.sculk.language;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.errors.NBTSerializationError;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.nbt.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Map;

public class NBTSerializer {
    public static NbtElement serializeBlock(Block block) {
        return NbtString.of(Registries.BLOCK.getId(block).toString());
    }

    public static NbtElement serializeBlockHitResult(Block block, BlockPos pos, boolean succeeded) {
        NbtCompound compound = new NbtCompound();

        compound.put("block", NBTSerializer.serializeBlock(block));
        compound.put("pos", NBTSerializer.serializeBlockPos(pos));
        compound.put("succeeded", NBTSerializer.serializeBoolean(succeeded));

        return compound;
    }

    public static NbtElement serializeBlockPos(BlockPos pos) {
        NbtCompound compound = new NbtCompound();

        compound.putInt("x", pos.getX());
        compound.putInt("y", pos.getY());
        compound.putInt("z", pos.getZ());

        return compound;
    }

    public static NbtElement serializeBlockSettings(AbstractBlock.Settings settings) {
        NbtCompound compound = new NbtCompound();

        compound.putFloat("hardness", settings.hardness);
        compound.putFloat("resistance", settings.resistance);
        compound.putBoolean("collidable", settings.collidable);
        compound.putInt("luminance", settings.luminance.applyAsInt(Blocks.AIR.getDefaultState()));
        compound.putFloat("slipperiness", settings.slipperiness);
        compound.putBoolean("burnable", settings.burnable);
        compound.put("pistonBehavior", NBTSerializer.serializePistonBehavior(settings.pistonBehavior));
        compound.putBoolean("hasBlockBreakParticles", settings.blockBreakParticles);
        compound.put("instrument", NBTSerializer.serializeInstrument(settings.instrument));
        compound.putBoolean("requiresTool", settings.toolRequired);

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

    public static NbtElement serializeInstrument(Instrument instrument) {
        return switch (instrument) {
            case HARP -> NbtString.of("harp");
            case BASEDRUM -> NbtString.of("basedrum");
            case SNARE -> NbtString.of("snare");
            case HAT -> NbtString.of("hat");
            case BASS -> NbtString.of("bass");
            case FLUTE -> NbtString.of("flute");
            case BELL -> NbtString.of("bell");
            case GUITAR -> NbtString.of("guitar");
            case CHIME -> NbtString.of("chime");
            case XYLOPHONE -> NbtString.of("xylophone");
            case IRON_XYLOPHONE -> NbtString.of("iron_xylophone");
            case COW_BELL -> NbtString.of("cow_bell");
            case DIDGERIDOO -> NbtString.of("didgeridoo");
            case BIT -> NbtString.of("bit");
            case BANJO -> NbtString.of("banjo");
            case PLING -> NbtString.of("pling");
            case ZOMBIE -> NbtString.of("zombie");
            case SKELETON -> NbtString.of("skeleton");
            case CREEPER -> NbtString.of("creeper");
            case DRAGON -> NbtString.of("dragon");
            case WITHER_SKELETON -> NbtString.of("wither_skeleton");
            case PIGLIN -> NbtString.of("piglin");
            case CUSTOM_HEAD -> NbtString.of("custom_head");
        };
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

    public static NbtElement serializePistonBehavior(PistonBehavior pistonBehavior) {
        return switch (pistonBehavior) {
            case NORMAL -> NbtString.of("normal");
            case DESTROY -> NbtString.of("destroy");
            case BLOCK -> NbtString.of("block");
            case IGNORE -> NbtString.of("ignore");
            case PUSH_ONLY -> NbtString.of("push_only");
        };
    }

    public static NbtElement serializeString(String value) {
        return NbtString.of(value);
    }
}
