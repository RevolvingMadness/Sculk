package com.revolvingmadness.sculk.language.builtins.classes.types.particle;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.ParticleInstance;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;

import java.util.List;

public class ParticlesClassType extends BuiltinClassType {
    public static final ParticlesClassType TYPE = new ParticlesClassType();

    private ParticlesClassType() {
        super("Particles");

        this.addParticle("AMBIENT_ENTITY_EFFECT", ParticleTypes.AMBIENT_ENTITY_EFFECT);
        this.addParticle("ANGRY_VILLAGER", ParticleTypes.ANGRY_VILLAGER);
        this.addParticle("BUBBLE", ParticleTypes.BUBBLE);
        this.addParticle("CLOUD", ParticleTypes.CLOUD);
        this.addParticle("CRIT", ParticleTypes.CRIT);
        this.addParticle("DAMAGE_INDICATOR", ParticleTypes.DAMAGE_INDICATOR);
        this.addParticle("DRAGON_BREATH", ParticleTypes.DRAGON_BREATH);
        this.addParticle("DRIPPING_LAVA", ParticleTypes.DRIPPING_LAVA);
        this.addParticle("FALLING_LAVA", ParticleTypes.FALLING_LAVA);
        this.addParticle("LANDING_LAVA", ParticleTypes.LANDING_LAVA);
        this.addParticle("DRIPPING_WATER", ParticleTypes.DRIPPING_WATER);
        this.addParticle("FALLING_WATER", ParticleTypes.FALLING_WATER);
        this.addParticle("EFFECT", ParticleTypes.EFFECT);
        this.addParticle("ELDER_GUARDIAN", ParticleTypes.ELDER_GUARDIAN);
        this.addParticle("ENCHANTED_HIT", ParticleTypes.ENCHANTED_HIT);
        this.addParticle("ENCHANT", ParticleTypes.ENCHANT);
        this.addParticle("END_ROD", ParticleTypes.END_ROD);
        this.addParticle("ENTITY_EFFECT", ParticleTypes.ENTITY_EFFECT);
        this.addParticle("EXPLOSION_EMITTER", ParticleTypes.EXPLOSION_EMITTER);
        this.addParticle("EXPLOSION", ParticleTypes.EXPLOSION);
        this.addParticle("GUST", ParticleTypes.GUST);
        this.addParticle("GUST_EMITTER", ParticleTypes.GUST_EMITTER);
        this.addParticle("SONIC_BOOM", ParticleTypes.SONIC_BOOM);
        this.addParticle("FIREWORK", ParticleTypes.FIREWORK);
        this.addParticle("FISHING", ParticleTypes.FISHING);
        this.addParticle("FLAME", ParticleTypes.FLAME);
        this.addParticle("CHERRY_LEAVES", ParticleTypes.CHERRY_LEAVES);
        this.addParticle("SCULK_SOUL", ParticleTypes.SCULK_SOUL);
        this.addParticle("SCULK_CHARGE_POP", ParticleTypes.SCULK_CHARGE_POP);
        this.addParticle("SOUL_FIRE_FLAME", ParticleTypes.SOUL_FIRE_FLAME);
        this.addParticle("SOUL", ParticleTypes.SOUL);
        this.addParticle("FLASH", ParticleTypes.FLASH);
        this.addParticle("HAPPY_VILLAGER", ParticleTypes.HAPPY_VILLAGER);
        this.addParticle("COMPOSTER", ParticleTypes.COMPOSTER);
        this.addParticle("HEART", ParticleTypes.HEART);
        this.addParticle("INSTANT_EFFECT", ParticleTypes.INSTANT_EFFECT);
        this.addParticle("ITEM_SLIME", ParticleTypes.ITEM_SLIME);
        this.addParticle("ITEM_SNOWBALL", ParticleTypes.ITEM_SNOWBALL);
        this.addParticle("LARGE_SMOKE", ParticleTypes.LARGE_SMOKE);
        this.addParticle("LAVA", ParticleTypes.LAVA);
        this.addParticle("MYCELIUM", ParticleTypes.MYCELIUM);
        this.addParticle("NOTE", ParticleTypes.NOTE);
        this.addParticle("POOF", ParticleTypes.POOF);
        this.addParticle("PORTAL", ParticleTypes.PORTAL);
        this.addParticle("RAIN", ParticleTypes.RAIN);
        this.addParticle("SMOKE", ParticleTypes.SMOKE);
        this.addParticle("WHITE_SMOKE", ParticleTypes.WHITE_SMOKE);
        this.addParticle("SNEEZE", ParticleTypes.SNEEZE);
        this.addParticle("SPIT", ParticleTypes.SPIT);
        this.addParticle("SQUID_INK", ParticleTypes.SQUID_INK);
        this.addParticle("SWEEP_ATTACK", ParticleTypes.SWEEP_ATTACK);
        this.addParticle("TOTEM_OF_UNDYING", ParticleTypes.TOTEM_OF_UNDYING);
        this.addParticle("UNDERWATER", ParticleTypes.UNDERWATER);
        this.addParticle("SPLASH", ParticleTypes.SPLASH);
        this.addParticle("WITCH", ParticleTypes.WITCH);
        this.addParticle("BUBBLE_POP", ParticleTypes.BUBBLE_POP);
        this.addParticle("CURRENT_DOWN", ParticleTypes.CURRENT_DOWN);
        this.addParticle("BUBBLE_COLUMN_UP", ParticleTypes.BUBBLE_COLUMN_UP);
        this.addParticle("NAUTILUS", ParticleTypes.NAUTILUS);
        this.addParticle("DOLPHIN", ParticleTypes.DOLPHIN);
        this.addParticle("CAMPFIRE_COSY_SMOKE", ParticleTypes.CAMPFIRE_COSY_SMOKE);
        this.addParticle("CAMPFIRE_SIGNAL_SMOKE", ParticleTypes.CAMPFIRE_SIGNAL_SMOKE);
        this.addParticle("DRIPPING_HONEY", ParticleTypes.DRIPPING_HONEY);
        this.addParticle("FALLING_HONEY", ParticleTypes.FALLING_HONEY);
        this.addParticle("LANDING_HONEY", ParticleTypes.LANDING_HONEY);
        this.addParticle("FALLING_NECTAR", ParticleTypes.FALLING_NECTAR);
        this.addParticle("FALLING_SPORE_BLOSSOM", ParticleTypes.FALLING_SPORE_BLOSSOM);
        this.addParticle("ASH", ParticleTypes.ASH);
        this.addParticle("CRIMSON_SPORE", ParticleTypes.CRIMSON_SPORE);
        this.addParticle("WARPED_SPORE", ParticleTypes.WARPED_SPORE);
        this.addParticle("SPORE_BLOSSOM_AIR", ParticleTypes.SPORE_BLOSSOM_AIR);
        this.addParticle("DRIPPING_OBSIDIAN_TEAR", ParticleTypes.DRIPPING_OBSIDIAN_TEAR);
        this.addParticle("FALLING_OBSIDIAN_TEAR", ParticleTypes.FALLING_OBSIDIAN_TEAR);
        this.addParticle("LANDING_OBSIDIAN_TEAR", ParticleTypes.LANDING_OBSIDIAN_TEAR);
        this.addParticle("REVERSE_PORTAL", ParticleTypes.REVERSE_PORTAL);
        this.addParticle("WHITE_ASH", ParticleTypes.WHITE_ASH);
        this.addParticle("SMALL_FLAME", ParticleTypes.SMALL_FLAME);
        this.addParticle("SNOWFLAKE", ParticleTypes.SNOWFLAKE);
        this.addParticle("DRIPPING_DRIPSTONE_LAVA", ParticleTypes.DRIPPING_DRIPSTONE_LAVA);
        this.addParticle("FALLING_DRIPSTONE_LAVA", ParticleTypes.FALLING_DRIPSTONE_LAVA);
        this.addParticle("DRIPPING_DRIPSTONE_WATER", ParticleTypes.DRIPPING_DRIPSTONE_WATER);
        this.addParticle("FALLING_DRIPSTONE_WATER", ParticleTypes.FALLING_DRIPSTONE_WATER);
        this.addParticle("GLOW_SQUID_INK", ParticleTypes.GLOW_SQUID_INK);
        this.addParticle("GLOW", ParticleTypes.GLOW);
        this.addParticle("WAX_ON", ParticleTypes.WAX_ON);
        this.addParticle("WAX_OFF", ParticleTypes.WAX_OFF);
        this.addParticle("ELECTRIC_SPARK", ParticleTypes.ELECTRIC_SPARK);
        this.addParticle("SCRAPE", ParticleTypes.SCRAPE);
        this.addParticle("EGG_CRACK", ParticleTypes.EGG_CRACK);
        this.addParticle("DUST_PLUME", ParticleTypes.DUST_PLUME);
        this.addParticle("GUST_DUST", ParticleTypes.GUST_DUST);
        this.addParticle("TRIAL_SPAWNER_DETECTION", ParticleTypes.TRIAL_SPAWNER_DETECTION);
    }

    private void addParticle(String name, ParticleEffect particleType) {
        this.variableScope.declare(List.of(TokenType.CONST), name, new ParticleInstance(particleType));
    }
}
