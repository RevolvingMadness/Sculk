package com.revolvingmadness.sculk.language;

import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockHitResultInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockPosInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockSettingsInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.DictionaryInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.errors.TypeError;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class NBTDeserializer {
    public static BlockInstance deserializeBlock(StringInstance stringInstance) {
        return new BlockInstance(Registries.BLOCK.get(Identifier.tryParse(stringInstance.toString())));
    }

    public static BlockHitResultInstance deserializeBlockHitResult(DictionaryInstance dictionaryInstance) {
        Block block = NBTDeserializer.deserializeBlock(new StringInstance(dictionaryInstance.get("block").toString())).value;
        BlockPos pos = NBTDeserializer.deserializeBlockPos(new DictionaryInstance(dictionaryInstance.get("pos").toDictionary())).value;
        boolean succeeded = dictionaryInstance.get("succeeded").toBoolean();

        return new BlockHitResultInstance(pos, block, succeeded);
    }

    public static BlockPosInstance deserializeBlockPos(DictionaryInstance dictionaryInstance) {
        int x = (int) dictionaryInstance.get("x").toInteger();
        int y = (int) dictionaryInstance.get("y").toInteger();
        int z = (int) dictionaryInstance.get("z").toInteger();

        return new BlockPosInstance(new BlockPos(x, y, z));
    }

    public static BlockSettingsInstance deserializeBlockSettings(DictionaryInstance dictionaryInstance) {
        FabricBlockSettings fabricBlockSettings = FabricBlockSettings.create();

        fabricBlockSettings.hardness((float) dictionaryInstance.get("hardness").toFloat());
        fabricBlockSettings.resistance((float) dictionaryInstance.get("resistance").toFloat());
        fabricBlockSettings.collidable(dictionaryInstance.get("collidable").toBoolean());
        fabricBlockSettings.luminance(value -> (int) dictionaryInstance.get("value").toInteger());
        fabricBlockSettings.slipperiness((float) dictionaryInstance.get("slipperiness").toFloat());
        if (dictionaryInstance.get("burnable").toBoolean()) {
            fabricBlockSettings.burnable();
        }
        fabricBlockSettings.pistonBehavior(NBTDeserializer.deserializePistonBehavior(dictionaryInstance.get("pistonBehavior").toString()));
        if (!dictionaryInstance.get("hasBlockBreakParticles").toBoolean()) {
            fabricBlockSettings.noBlockBreakParticles();
        }
        fabricBlockSettings.instrument(NBTDeserializer.deserializeInstrument(dictionaryInstance.get("instrument").toString()));
        if (dictionaryInstance.get("requiresTool").toBoolean()) {
            fabricBlockSettings.requiresTool();
        }

        return new BlockSettingsInstance(fabricBlockSettings);
    }

    public static Instrument deserializeInstrument(String instrument) {
        return switch (instrument) {
            case "harp", "HARP" -> Instrument.HARP;
            case "basedrum", "BASEDRUM" -> Instrument.BASEDRUM;
            case "snare", "SNARE" -> Instrument.SNARE;
            case "hat", "HAT" -> Instrument.HAT;
            case "bass", "BASS" -> Instrument.BASS;
            case "flute", "FLUTE" -> Instrument.FLUTE;
            case "bell", "BELL" -> Instrument.BELL;
            case "guitar", "GUITAR" -> Instrument.GUITAR;
            case "chime", "CHIME" -> Instrument.CHIME;
            case "xylophone", "XYLOPHONE" -> Instrument.XYLOPHONE;
            case "iron_xylophone", "IRON_XYLOPHONE" -> Instrument.IRON_XYLOPHONE;
            case "cow_bell", "COW_BELL" -> Instrument.COW_BELL;
            case "didgeridoo", "DIDGERIDOO" -> Instrument.DIDGERIDOO;
            case "bit", "BIT" -> Instrument.BIT;
            case "banjo", "BANJO" -> Instrument.BANJO;
            case "pling", "PLING" -> Instrument.PLING;
            case "zombie", "ZOMBIE" -> Instrument.ZOMBIE;
            case "skeleton", "SKELETON" -> Instrument.SKELETON;
            case "creeper", "CREEPER" -> Instrument.CREEPER;
            case "dragon", "DRAGON" -> Instrument.DRAGON;
            case "wither_skeleton", "WITHER_SKELETON" -> Instrument.WITHER_SKELETON;
            case "piglin", "PIGLIN" -> Instrument.PIGLIN;
            case "custom_head", "CUSTOM_HEAD" -> Instrument.CUSTOM_HEAD;
            default -> throw new TypeError("Invalid instrument '" + instrument + "'");
        };
    }

    public static PistonBehavior deserializePistonBehavior(String string) {
        return switch (string) {
            case "normal", "NORMAL" -> PistonBehavior.NORMAL;
            case "destroy", "DESTROY" -> PistonBehavior.DESTROY;
            case "block", "BLOCK" -> PistonBehavior.BLOCK;
            case "ignore", "IGNORE" -> PistonBehavior.IGNORE;
            case "push_only", "PUSH_ONLY" -> PistonBehavior.PUSH_ONLY;
            default -> throw new TypeError("Invalid pistonBehavior '" + string + "'");
        };
    }
}
