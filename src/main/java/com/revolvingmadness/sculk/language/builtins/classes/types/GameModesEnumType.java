package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinEnum;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class GameModesEnumType extends BuiltinEnum {
    public GameModesEnumType() {
        super(List.of(TokenType.CONST), "GameModes", List.of("SURVIVAL", "CREATIVE", "ADVENTURE", "SPECTATOR"));
    }
}
