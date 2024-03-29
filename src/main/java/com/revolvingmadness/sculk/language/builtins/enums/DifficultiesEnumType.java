package com.revolvingmadness.sculk.language.builtins.enums;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinEnumType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.world.Difficulty;

import java.util.List;

public class DifficultiesEnumType extends BuiltinEnumType {
    public static final DifficultiesEnumType TYPE = new DifficultiesEnumType();

    private DifficultiesEnumType() {
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

        public DifficultiesEnumValue(String name, int position, BuiltinClassType type, Difficulty difficulty) {
            super(name, position, type);

            this.difficulty = difficulty;
        }

        @Override
        public Difficulty toDifficulty() {
            return this.difficulty;
        }
    }
}
