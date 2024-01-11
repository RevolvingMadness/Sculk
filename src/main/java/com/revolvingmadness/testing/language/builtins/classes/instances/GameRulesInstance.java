package com.revolvingmadness.testing.language.builtins.classes.instances;

import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.types.GameRulesType;
import net.minecraft.world.GameRules;

import java.util.Objects;

public class GameRulesInstance extends BuiltinClass {
    public final GameRules value;

    public GameRulesInstance(GameRules value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        GameRulesInstance that = (GameRulesInstance) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public BuiltinType getType() {
        return new GameRulesType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public GameRules toGameRules() {
        return this.value;
    }
}
