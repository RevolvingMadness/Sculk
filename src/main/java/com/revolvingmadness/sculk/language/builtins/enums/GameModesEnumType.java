package com.revolvingmadness.sculk.language.builtins.enums;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinEnumType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.world.GameMode;

import java.util.List;

public class GameModesEnumType extends BuiltinEnumType {
    public static final GameModesEnumType TYPE = new GameModesEnumType();

    private GameModesEnumType() {
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

        public GameModeEnumValue(String name, int position, BuiltinClassType type, GameMode gameMode) {
            super(name, position, type);

            this.gameMode = gameMode;
        }

        @Override
        public GameMode toGameMode() {
            return this.gameMode;
        }
    }
}
