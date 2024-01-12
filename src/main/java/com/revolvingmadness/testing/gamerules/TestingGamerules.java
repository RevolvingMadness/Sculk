package com.revolvingmadness.testing.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class TestingGamerules {
    public static final GameRules.Key<GameRules.IntRule> MAX_ARGUMENTS = GameRuleRegistry.register("maxArguments", GameRules.Category.MISC, GameRuleFactory.createIntRule(255, 0, 32767));
    public static final GameRules.Key<GameRules.IntRule> MAX_LOOPS = GameRuleRegistry.register("maxLoops", GameRules.Category.UPDATES, GameRuleFactory.createIntRule(65535, 0, Integer.MAX_VALUE));
    public static final GameRules.Key<GameRules.BooleanRule> SCRIPT_LOGS_ENABLED = GameRuleRegistry.register("scriptLogsEnabled", GameRules.Category.CHAT, GameRuleFactory.createBooleanRule(false));

    public static void registerGamerules() {
        
    }
}
