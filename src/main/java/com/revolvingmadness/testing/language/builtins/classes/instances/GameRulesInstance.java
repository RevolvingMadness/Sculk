package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.GameRulesType;
import net.minecraft.world.GameRules;

public class GameRulesInstance extends BuiltinClass {
    public final GameRules value;

    public GameRulesInstance(GameRules value) {
        this.value = value;
    }

    @Override
    public BuiltinType getType() {
        return new GameRulesType();
    }

    @Override
    public GameRules toGameRules() {
        return this.value;
    }
}
