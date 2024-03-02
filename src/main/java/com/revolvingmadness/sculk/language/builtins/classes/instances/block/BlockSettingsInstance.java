package com.revolvingmadness.sculk.language.builtins.classes.instances.block;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.FloatInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockSettingsClassType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;

import java.util.List;

public class BlockSettingsInstance extends BuiltinClass {
    public BlockSettingsInstance() {
        super(BlockSettingsClassType.TYPE);

        this.variableScope.declare(List.of(TokenType.NONULL), "hardness", new FloatInstance(0.5));
        this.variableScope.declare(List.of(TokenType.NONULL), "resistance", new FloatInstance(0.5));
        this.variableScope.declare(List.of(TokenType.NONULL), "collidable", new BooleanInstance(true));
        this.variableScope.declare(List.of(TokenType.NONULL), "luminance", new IntegerInstance(0));
        this.variableScope.declare(List.of(TokenType.NONULL), "slipperiness", new FloatInstance(0.6));
        this.variableScope.declare(List.of(TokenType.NONULL), "burnable", new BooleanInstance(false));
        this.variableScope.declare(List.of(TokenType.NONULL), "pistonBehavior", new StringInstance("normal"));
        this.variableScope.declare(List.of(TokenType.NONULL), "hasBlockBreakParticles", new BooleanInstance(true));
        this.variableScope.declare(List.of(TokenType.NONULL), "instrument", new StringInstance("harp"));
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

        return settings;
    }
}
