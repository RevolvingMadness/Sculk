package com.revolvingmadness.sculk.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class SculkGamerules {
    public static final GameRules.Key<GameRules.IntRule> MAX_ARGUMENT_COUNT = GameRuleRegistry.register("maxArgumentCount", GameRules.Category.MISC, GameRuleFactory.createIntRule(255, 0, 32767));
    public static final GameRules.Key<GameRules.IntRule> MAX_LOOPS = GameRuleRegistry.register("maxLoops", GameRules.Category.UPDATES, GameRuleFactory.createIntRule(65535, 0, Integer.MAX_VALUE));

    public static void registerGamerules() {

    }
}
