package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.GameRulesClassType;
import net.minecraft.world.GameRules;

import java.util.Objects;

public class GameRulesInstance extends BuiltinClass {
    public final GameRules value;

    public GameRulesInstance(GameRules value) {
        super(GameRulesClassType.TYPE);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        GameRulesInstance that = (GameRulesInstance) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public GameRules toGameRules() {
        return this.value;
    }
}
