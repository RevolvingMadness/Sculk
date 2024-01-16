package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinEnum;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.world.GameMode;

import java.util.List;

public class GameModesEnumType extends BuiltinEnum {
    public GameModesEnumType() {
        super(List.of(TokenType.CONST), "GameModes");

        this.addValue("SURVIVAL", GameMode.SURVIVAL);
        this.addValue("CREATIVE", GameMode.CREATIVE);
        this.addValue("ADVENTURE", GameMode.ADVENTURE);
        this.addValue("SPECTATOR", GameMode.SPECTATOR);
    }

    public void addValue(String name, GameMode gameMode) {
        this.variableScope.declare(List.of(TokenType.CONST, TokenType.STATIC), name, new GameModeEnumValue(name, this.position++, this, gameMode));
    }

    private static class GameModeEnumValue extends EnumValue {
        public final GameMode gameMode;

        public GameModeEnumValue(String name, int position, BuiltinType type, GameMode gameMode) {
            super(name, position, type);

            this.gameMode = gameMode;
        }

        @Override
        public GameMode toGameMode() {
            return this.gameMode;
        }
    }
}
