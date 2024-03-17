package com.revolvingmadness.sculk.language.builtins.classes.types.item;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.item.ToolMaterialInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.FloatClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.List;

public class ToolMaterialClassType extends BuiltinClassType {
    public static final ToolMaterialClassType TYPE = new ToolMaterialClassType();

    private ToolMaterialClassType() {
        super("Item", ItemStackClassType.TYPE);
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(IntegerClassType.TYPE, IntegerClassType.TYPE, FloatClassType.TYPE, FloatClassType.TYPE, IntegerClassType.TYPE, ItemClassType.TYPE));

        int miningLevel = (int) arguments.get(0).toInteger();
        int durability = (int) arguments.get(1).toInteger();
        float miningSpeed = (float) arguments.get(2).toFloat();
        float attackDamage = (float) arguments.get(3).toFloat();
        int enchantability = (int) arguments.get(4).toFloat();
        Item repairItem = arguments.get(5).toItem();

        return new ToolMaterialInstance(new ToolMaterial() {
            @Override
            public float getAttackDamage() {
                return attackDamage;
            }

            @Override
            public int getDurability() {
                return durability;
            }

            @Override
            public int getEnchantability() {
                return enchantability;
            }

            @Override
            public int getMiningLevel() {
                return miningLevel;
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return miningSpeed;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(repairItem);
            }
        });
    }
}
