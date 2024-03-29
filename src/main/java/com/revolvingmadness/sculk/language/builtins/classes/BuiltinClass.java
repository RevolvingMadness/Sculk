package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.instances.GUIInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.item.ItemInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockPosClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockSettingsClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.entity.EntityClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.entity.PlayerEntityClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.entity.ServerPlayerEntityClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.item.ItemClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.item.ItemSettingsClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.item.ItemStackClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.item.ToolMaterialClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.particle.ParticleClassType;
import com.revolvingmadness.sculk.language.builtins.enums.AttributesEnumType;
import com.revolvingmadness.sculk.language.builtins.enums.DifficultiesEnumType;
import com.revolvingmadness.sculk.language.builtins.enums.GameModesEnumType;
import com.revolvingmadness.sculk.language.errors.TypeError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.interpreter.Variable;
import com.revolvingmadness.sculk.language.interpreter.VariableScope;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtElement;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameMode;
import net.minecraft.world.GameRules;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public abstract class BuiltinClass extends ExpressionNode implements Serializable {
    public final VariableScope variableScope;
    public BuiltinClassType type;

    public BuiltinClass(BuiltinClassType type) {
        this(type, new VariableScope());
    }

    public BuiltinClass(BuiltinClassType type, VariableScope variableScope) {
        this.type = type;
        this.variableScope = variableScope;
    }

    public BuiltinClass add(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("+", this.type, other.type);
    }

    public BooleanInstance booleanAnd(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("&&", this.type, other.type);
    }

    public BooleanInstance booleanOr(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("||", this.type, other.type);
    }

    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        throw new TypeError("Type '" + this.type.name + "' is not callable");
    }

    public BuiltinClass call(Interpreter interpreter, String methodName, List<BuiltinClass> arguments) {
        BuiltinClass method = this.getProperty(methodName);

        return method.call(interpreter, arguments);
    }

    public BuiltinClass decrement() {
        throw ErrorHolder.cannotApplyUnaryOperatorToType("--", this.type);
    }

    public void deleteIndex(BuiltinClass index) {
        throw ErrorHolder.typeIsNotIndexable(this.type);
    }

    public void deleteProperty(String propertyName) {
        this.variableScope.deleteOrThrow(propertyName);
    }

    public BuiltinClass divide(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("/", this.type, other.type);
    }

    public void downcast(BuiltinClass typeClass) {
        if (!(typeClass instanceof BuiltinClassType type_)) {
            throw new TypeError("Cannot cast a class to an instance");
        }

        if (!type_.canDowncastTo(this.type)) {
            throw new TypeError("Cannot cast type '" + this.type + "' to type '" + type_ + "'");
        }

        this.type = type_;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        BuiltinClass that = (BuiltinClass) o;
        return Objects.equals(this.variableScope, that.variableScope);
    }

    public BuiltinClass exponentiate(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("^", this.type, other.type);
    }

    public BuiltinClass getIndex(BuiltinClass index) {
        throw ErrorHolder.typeIsNotIndexable(this.type);
    }

    public BuiltinClass getProperty(String propertyName) {
        Optional<Variable> optionalProperty = this.variableScope.getOptional(propertyName);

        if (optionalProperty.isPresent()) {
            BuiltinClass property = optionalProperty.get().value;

            if (property instanceof BuiltinMethod method) {
                method.bind(this, this.type.superClass);
            }

            return property;
        }

        BuiltinClass property = this.type.getProperty(propertyName);

        if (property instanceof BuiltinMethod method) {
            method.bind(this, this.type.superClass);
        }

        return property;
    }

    public BooleanInstance greaterThan(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator(">", this.type, other.type);
    }

    public BuiltinClass increment() {
        throw ErrorHolder.cannotApplyUnaryOperatorToType("++", this.type);
    }

    public boolean instanceOf(BuiltinClassType type) {
        BuiltinClassType valueType = this.type;

        while (valueType != null) {
            if (valueType.name.equals(type.name)) {
                return true;
            }

            valueType = valueType.superClass;
        }

        return false;
    }

    public BooleanInstance lessThan(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("<", this.type, other.type);
    }

    public BuiltinClass logicalNot() {
        throw ErrorHolder.cannotApplyUnaryOperatorToType("!", this.type);
    }

    public BuiltinClass mod(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("%", this.type, other.type);
    }

    public BuiltinClass multiply(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("*", this.type, other.type);
    }

    public BuiltinClass negate() {
        throw ErrorHolder.cannotApplyUnaryOperatorToType("-", this.type);
    }

    public void setIndex(BuiltinClass index, BuiltinClass value) {
        throw ErrorHolder.typeIsNotIndexable(this.type);
    }

    public void setProperty(String propertyName, BuiltinClass value) {
        Optional<Variable> optionalVariable = this.variableScope.getOptional(propertyName);

        if (optionalVariable.isPresent()) {
            this.variableScope.assign(propertyName, value);

            return;
        }

        this.type.setProperty(propertyName, value);
    }

    public BuiltinClass subtract(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("-", this.type, other.type);
    }

    public EntityAttribute toAttribute() {
        throw ErrorHolder.cannotConvertType(this.type, AttributesEnumType.TYPE);
    }

    public Block toBlock() {
        throw ErrorHolder.cannotConvertType(this.type, BlockClassType.TYPE);
    }

    public BlockInstance toBlockInstance() {
        throw ErrorHolder.cannotConvertType(this.type, BlockClassType.TYPE);
    }

    public BlockPos toBlockPos() {
        throw ErrorHolder.cannotConvertType(this.type, BlockPosClassType.TYPE);
    }

    public FabricBlockSettings toBlockSettings() {
        throw ErrorHolder.cannotConvertType(this.type, BlockSettingsClassType.TYPE);
    }

    public boolean toBoolean() {
        throw ErrorHolder.cannotConvertType(this.type, BooleanClassType.TYPE);
    }

    public Map<BuiltinClass, BuiltinClass> toDictionary() {
        throw ErrorHolder.cannotConvertType(this.type, DictionaryClassType.TYPE);
    }

    public Difficulty toDifficulty() {
        throw ErrorHolder.cannotConvertType(this.type, DifficultiesEnumType.TYPE);
    }

    public Entity toEntity() {
        throw ErrorHolder.cannotConvertType(this.type, EntityClassType.TYPE);
    }

    public double toFloat() {
        throw ErrorHolder.cannotConvertType(this.type, FloatClassType.TYPE);
    }

    public BuiltinFunction toFunction() {
        throw ErrorHolder.cannotConvertType(this.type, FunctionClassType.TYPE);
    }

    public GUIInstance toGUI() {
        throw ErrorHolder.cannotConvertType(this.type, GUIClassType.TYPE);
    }

    public GameMode toGameMode() {
        throw ErrorHolder.cannotConvertType(this.type, GameModesEnumType.TYPE);
    }

    public GameRules toGameRules() {
        throw ErrorHolder.cannotConvertType(this.type, GameRulesClassType.TYPE);
    }

    public long toInteger() {
        throw ErrorHolder.cannotConvertType(this.type, IntegerClassType.TYPE);
    }

    public Inventory toInventory() {
        throw ErrorHolder.cannotConvertType(this.type, InventoryClassType.TYPE);
    }

    public Item toItem() {
        throw ErrorHolder.cannotConvertType(this.type, ItemClassType.TYPE);
    }

    public ItemInstance toItemInstance() {
        throw ErrorHolder.cannotConvertType(this.type, ItemClassType.TYPE);
    }

    public FabricItemSettings toItemSettings() {
        throw ErrorHolder.cannotConvertType(this.type, ItemSettingsClassType.TYPE);
    }

    public ItemStack toItemStack() {
        throw ErrorHolder.cannotConvertType(this.type, ItemStackClassType.TYPE);
    }

    public List<BuiltinClass> toList() {
        throw ErrorHolder.cannotConvertType(this.type, ListClassType.TYPE);
    }

    public LivingEntity toLivingEntity() {
        throw ErrorHolder.cannotConvertType(this.type, LivingEntityClassType.TYPE);
    }

    public NbtElement toNBT() {
        throw new TypeError("Type '" + this.type.name + "' does not support NBT serialization");
    }

    public ParticleEffect toParticle() {
        throw ErrorHolder.cannotConvertType(this.type, ParticleClassType.TYPE);
    }

    public PlayerEntity toPlayerEntity() {
        throw ErrorHolder.cannotConvertType(this.type, PlayerEntityClassType.TYPE);
    }

    public PlayerManager toPlayerManager() {
        throw ErrorHolder.cannotConvertType(this.type, PlayerManagerClassType.TYPE);
    }

    public String toRepresentation() {
        return this.toString();
    }

    public ServerPlayerEntity toServerPlayerEntity() {
        throw ErrorHolder.cannotConvertType(this.type, ServerPlayerEntityClassType.TYPE);
    }

    @Override
    public String toString() {
        return "<Class '" + this.type.name + "'>";
    }

    public ToolMaterial toToolMaterial() {
        throw ErrorHolder.cannotConvertType(this.type, ToolMaterialClassType.TYPE);
    }

    public ServerWorld toWorld() {
        throw ErrorHolder.cannotConvertType(this.type, WorldClassType.TYPE);
    }

    public void validateCall(String callableName, List<BuiltinClass> arguments, List<BuiltinClassType> argumentTypes) {
        if (arguments.size() != argumentTypes.size()) {
            throw ErrorHolder.invalidArgumentCount(callableName, arguments.size(), argumentTypes.size());
        }

        int argumentNumber = 0;

        for (BuiltinClass argument : arguments) {
            BuiltinClassType argumentType = argumentTypes.get(argumentNumber);

            if (!argument.instanceOf(NullClassType.TYPE)) {
                if (!argument.instanceOf(argumentType)) {
                    throw ErrorHolder.argumentRequiresType(argumentNumber + 1, callableName, argument.type, argumentType);
                }
            }

            argumentNumber++;
        }
    }

    public void validateCall(String callableName, List<BuiltinClass> arguments) {
        this.validateCall(callableName, arguments, List.of());
    }

    public void validateIndex(BuiltinClassType type, BuiltinClass requiredType) {
        if (!requiredType.instanceOf(type)) {
            throw ErrorHolder.cannotIndexTypeByType(this.type, requiredType.type);
        }
    }
}