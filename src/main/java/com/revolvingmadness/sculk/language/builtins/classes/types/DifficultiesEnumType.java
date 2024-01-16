package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinEnum;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.world.Difficulty;

import java.util.List;

public class DifficultiesEnumType extends BuiltinEnum {
    public DifficultiesEnumType() {
        super(List.of(TokenType.CONST), "Difficulties");

        this.addValue("EASY", Difficulty.EASY);
        this.addValue("NORMAL", Difficulty.NORMAL);
        this.addValue("HARD", Difficulty.HARD);
        this.addValue("PEACEFUL", Difficulty.PEACEFUL);
    }

    public void addValue(String name, Difficulty difficulty) {
        this.variableScope.declare(List.of(TokenType.CONST, TokenType.STATIC), name, new DifficultiesEnumValue(name, this.position++, this, difficulty));
    }

    private static class DifficultiesEnumValue extends EnumValue {
        public final Difficulty difficulty;

        public DifficultiesEnumValue(String name, int position, BuiltinType type, Difficulty difficulty) {
            super(name, position, type);

            this.difficulty = difficulty;
        }

        @Override
        public Difficulty toDifficulty() {
            return this.difficulty;
        }
    }
}
