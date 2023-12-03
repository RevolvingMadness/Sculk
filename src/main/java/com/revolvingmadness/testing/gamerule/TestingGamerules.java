package com.revolvingmadness.testing.gamerule;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class TestingGamerules {
    public static final GameRules.Key<GameRules.BooleanRule> SCRIPT_LOGS_ENABLED = GameRuleRegistry.register("scriptLogsEnabled", GameRules.Category.CHAT, GameRuleFactory.createBooleanRule(false));

    public static void registerGamerules() {

    }
}
