package com.revolvingmadness.sculk.language.builtins.classes.instances.block;

import com.revolvingmadness.sculk.language.NBTSerializer;
import com.revolvingmadness.sculk.language.builtins.classes.NBTBuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.FloatInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockSettingsClassType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.nbt.NbtElement;

import java.util.List;

public class BlockSettingsInstance extends NBTBuiltinClass {
    public final AbstractBlock.Settings settings;

    public BlockSettingsInstance() {
        this(FabricBlockSettings.create());
    }

    public BlockSettingsInstance(AbstractBlock.Settings settings) {
        super(BlockSettingsClassType.TYPE);

        this.settings = settings;

        this.variableScope.declare(List.of(TokenType.NONULL), "hardness", new FloatInstance(settings.hardness));
        this.variableScope.declare(List.of(TokenType.NONULL), "resistance", new FloatInstance(settings.resistance));
        this.variableScope.declare(List.of(TokenType.NONULL), "collidable", new BooleanInstance(settings.collidable));
        this.variableScope.declare(List.of(TokenType.NONULL), "luminance", new IntegerInstance(settings.luminance.applyAsInt(Blocks.AIR.getDefaultState())));
        this.variableScope.declare(List.of(TokenType.NONULL), "slipperiness", new FloatInstance(settings.slipperiness));
        this.variableScope.declare(List.of(TokenType.NONULL), "burnable", new BooleanInstance(settings.burnable));
        String pistonBehavior = switch (settings.pistonBehavior) {
            case NORMAL -> "normal";
            case DESTROY -> "destroy";
            case BLOCK -> "block";
            case IGNORE -> "ignore";
            case PUSH_ONLY -> "push_only";
        };
        this.variableScope.declare(List.of(TokenType.NONULL), "pistonBehavior", new StringInstance(pistonBehavior));
        this.variableScope.declare(List.of(TokenType.NONULL), "hasBlockBreakParticles", new BooleanInstance(settings.blockBreakParticles));
        String instrument = switch (settings.instrument) {
            case HARP -> "harp";
            case BASEDRUM -> "basedrum";
            case SNARE -> "snare";
            case HAT -> "hat";
            case BASS -> "bass";
            case FLUTE -> "flute";
            case BELL -> "bell";
            case GUITAR -> "guitar";
            case CHIME -> "chime";
            case XYLOPHONE -> "xylophone";
            case IRON_XYLOPHONE -> "iron_xylophone";
            case COW_BELL -> "cow_bell";
            case DIDGERIDOO -> "didgeridoo";
            case BIT -> "bit";
            case BANJO -> "banjo";
            case PLING -> "pling";
            case ZOMBIE -> "zombie";
            case SKELETON -> "skeleton";
            case CREEPER -> "creeper";
            case DRAGON -> "dragon";
            case WITHER_SKELETON -> "wither_skeleton";
            case PIGLIN -> "piglin";
            case CUSTOM_HEAD -> "custom_head";
        };
        this.variableScope.declare(List.of(TokenType.NONULL), "instrument", new StringInstance(instrument));
        this.variableScope.declare(List.of(TokenType.NONULL), "requiresTool", new BooleanInstance(settings.toolRequired));
    }

    @Override
    public FabricBlockSettings toBlockSettings() {
        FabricBlockSettings settings = FabricBlockSettings.create();

        settings.hardness((float) this.variableScope.getOrThrow("hardness").value.toFloat());
        settings.resistance((float) this.variableScope.getOrThrow("resistance").value.toFloat());
        settings.collidable(this.variableScope.getOrThrow("collidable").value.toBoolean());
        settings.luminance((int) this.variableScope.getOrThrow("luminance").value.toInteger());
        settings.slipperiness((float) this.variableScope.getOrThrow("slipperiness").value.toFloat());
        boolean burnable = this.variableScope.getOrThrow("burnable").value.toBoolean();
        if (burnable) {
            settings.burnable();
        }
        String pistonBehaviorString = this.variableScope.getOrThrow("pistonBehavior").value.toString();
        PistonBehavior pistonBehavior = switch (pistonBehaviorString) {
            case "destroy", "DESTROY" -> PistonBehavior.DESTROY;
            case "block", "BLOCK" -> PistonBehavior.BLOCK;
            case "ignore", "IGNORE" -> PistonBehavior.IGNORE;
            case "push_only", "PUSH_ONLY" -> PistonBehavior.PUSH_ONLY;
            default -> PistonBehavior.NORMAL;
        };
        settings.pistonBehavior(pistonBehavior);
        boolean hasBlockBreakParticles = this.variableScope.getOrThrow("hasBlockBreakParticles").value.toBoolean();
        if (!hasBlockBreakParticles) {
            settings.noBlockBreakParticles();
        }
        String instrumentString = this.variableScope.getOrThrow("instrument").value.toString();
        Instrument instrument = switch (instrumentString) {
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
            default -> Instrument.HARP;
        };
        settings.instrument(instrument);
        boolean requiresTool = this.variableScope.getOrThrow("requiresTool").value.toBoolean();
        if (requiresTool) {
            settings.requiresTool();
        }

        return settings;
    }

    @Override
    public NbtElement toNBTElement() {
        return NBTSerializer.serializeBlockSettings(this.settings);
    }
}
