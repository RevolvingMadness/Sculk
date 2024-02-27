package com.revolvingmadness.sculk.language.builtins.enums;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinEnum;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;

import java.util.List;

public class AttributesEnumType extends BuiltinEnum {
    public static final AttributesEnumType TYPE = new AttributesEnumType();

    private AttributesEnumType() {
        super(List.of(TokenType.CONST), "Attributes");

        this.addValue("GENERIC_ARMOR", EntityAttributes.GENERIC_ARMOR);
        this.addValue("GENERIC_ARMOR_TOUGHNESS", EntityAttributes.GENERIC_ARMOR_TOUGHNESS);
        this.addValue("GENERIC_ATTACK_DAMAGE", EntityAttributes.GENERIC_ATTACK_DAMAGE);
        this.addValue("GENERIC_ATTACK_KNOCKBACK", EntityAttributes.GENERIC_ATTACK_KNOCKBACK);
        this.addValue("GENERIC_ATTACK_SPEED", EntityAttributes.GENERIC_ATTACK_SPEED);
        this.addValue("GENERIC_FLYING_SPEED", EntityAttributes.GENERIC_FLYING_SPEED);
        this.addValue("GENERIC_FOLLOW_RANGE", EntityAttributes.GENERIC_FOLLOW_RANGE);
        this.addValue("HORSE_JUMP_STRENGTH", EntityAttributes.HORSE_JUMP_STRENGTH);
        this.addValue("GENERIC_KNOCKBACK_RESISTANCE", EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
        this.addValue("GENERIC_LUCK", EntityAttributes.GENERIC_LUCK);
        this.addValue("GENERIC_MAX_ABSORPTION", EntityAttributes.GENERIC_MAX_ABSORPTION);
        this.addValue("GENERIC_MAX_HEALTH", EntityAttributes.GENERIC_MAX_HEALTH);
        this.addValue("GENERIC_MOVEMENT_SPEED", EntityAttributes.GENERIC_MOVEMENT_SPEED);
        this.addValue("ZOMBIE_SPAWN_REINFORCEMENTS", EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    public void addValue(String name, EntityAttribute attribute) {
        this.variableScope.declare(List.of(TokenType.CONST, TokenType.STATIC), name, new EntityAttributesEnumValue(name, this.position++, this, attribute));
    }

    private static class EntityAttributesEnumValue extends EnumValue {
        public final EntityAttribute attribute;

        public EntityAttributesEnumValue(String name, int position, BuiltinType type, EntityAttribute attribute) {
            super(name, position, type);

            this.attribute = attribute;
        }

        @Override
        public EntityAttribute toAttribute() {
            return this.attribute;
        }
    }
}
