package com.revolvingmadness.sculk.language.builtins.classes.instances.item;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.item.ItemSettingsClassType;
import com.revolvingmadness.sculk.language.errors.ValueError;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import java.util.List;

public class ItemSettingsInstance extends BuiltinClass {
    public ItemSettingsInstance() {
        super(ItemSettingsClassType.TYPE);

        this.variableScope.declare(List.of(TokenType.NONULL), "maxCount", new IntegerInstance(64));
        this.variableScope.declare(List.of(TokenType.NONULL), "maxDamage", new IntegerInstance(0));
        this.variableScope.declare(List.of(TokenType.NONULL), "name", new StringInstance("Custom Item"));
        this.variableScope.declare(List.of(TokenType.NONULL), "fireproof", new BooleanInstance(false));
    }

    @Override
    public FabricItemSettings toItemSettings() {
        FabricItemSettings settings = new FabricItemSettings();

        int maxCount = (int) this.variableScope.getOrThrow("maxCount").value.toInteger();
        int maxDamage = (int) this.variableScope.getOrThrow("maxDamage").value.toInteger();

        if (maxCount > 1 && maxDamage > 1) {
            throw new ValueError("Unable to have damage and stack");
        }

        if (maxCount > 1) {
            settings.maxCount(maxCount);
        }

        if (maxDamage > 0) {
            settings.maxDamage(maxDamage);
        }

        boolean isFireproof = this.variableScope.getOrThrow("fireproof").value.toBoolean();
        if (isFireproof) {
            settings.fireproof();
        }

        return settings;
    }
}
