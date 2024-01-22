package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.*;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.interpreter.Variable;
import com.revolvingmadness.sculk.language.interpreter.VariableScope;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameMode;
import net.minecraft.world.GameRules;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class BuiltinClass extends ExpressionNode {
    public final VariableScope variableScope;

    public BuiltinClass() {
        this(new VariableScope());
    }

    public BuiltinClass(VariableScope variableScope) {
        this.variableScope = variableScope;
    }

    public BuiltinClass add(BuiltinClass other) {
        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("+", this.getType(), other.getType());
    }

    public Iterator<BuiltinClass> asIterator() {
        throw ErrorHolder.typeIsNotIterable(this.getType());
    }

    public BooleanInstance booleanAnd(BuiltinClass other) {
        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("&&", this.getType(), other.getType());
    }

    public BooleanInstance booleanOr(BuiltinClass other) {
        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("||", this.getType(), other.getType());
    }

    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        throw ErrorHolder.typeIsNotCallable(this.getType());
    }

    public BuiltinClass call(Interpreter interpreter, String methodName, List<BuiltinClass> arguments) {
        BuiltinClass method = this.getProperty(methodName);

        return method.call(interpreter, arguments);
    }

    public void checkIfAllMethodsAreImplemented() {
        this.getType().checkIfAllMethodsAreImplemented();
    }

    public BuiltinClass decrement() {
        throw ErrorHolder.cannotApplyUnaryOperatorToType("--", this.getType());
    }

    public void deleteIndex(BuiltinClass index) {
        throw ErrorHolder.typeIsNotIndexable(this.getType());
    }

    public void deleteProperty(String propertyName) {
        this.variableScope.deleteOrThrow(propertyName);
    }

    public BuiltinClass divide(BuiltinClass other) {
        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("/", this.getType(), other.getType());
    }

    public BooleanInstance equalToMethod(BuiltinClass other) {
        return new BooleanInstance(this.equals(other));
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
        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("^", this.getType(), other.getType());
    }

    public BuiltinClass getIndex(BuiltinClass index) {
        throw ErrorHolder.typeIsNotIndexable(this.getType());
    }

    public BuiltinClass getProperty(String propertyName) {
        Optional<Variable> optionalProperty = this.variableScope.getOptional(propertyName);

        if (optionalProperty.isPresent()) {
            BuiltinClass property = optionalProperty.get().value;

            if (property instanceof BuiltinMethod method) {
                method.bind(this, this.getType().typeSuperClass);
            }

            return property;
        }

        BuiltinClass property = this.getType().getProperty(propertyName);

        if (property instanceof BuiltinMethod method) {
            method.bind(this, this.getType().typeSuperClass);
        }

        return property;
    }

    public abstract BuiltinType getType();

    public BooleanInstance greaterThan(BuiltinClass other) {
        throw ErrorHolder.cannotApplyBinaryOperatorToTypes(">", this.getType(), other.getType());
    }

    public BooleanInstance greaterThanOrEqualTo(BuiltinClass other) {
        return new BooleanInstance(this.greaterThan(other).value || this.equalToMethod(other).value);
    }

    public boolean hasAbstractMethods() {
        for (Variable variable : this.variableScope.variables.values()) {
            if (variable.isAbstract()) {
                return true;
            }
        }

        return false;
    }

    public BuiltinClass increment() {
        throw ErrorHolder.cannotApplyUnaryOperatorToType("++", this.getType());
    }

    public boolean instanceOf(BuiltinType type) {
        if (type.equals(new NullType())) {
            return true;
        }

        return this.getType().instanceOf(type);
    }

    public boolean isAbstract() {
        return this.getType().isAbstract();
    }

    @SuppressWarnings("unused")
    public boolean isConstant() {
        return this.getType().isConstant();
    }

    public BooleanInstance lessThan(BuiltinClass other) {
        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("<", this.getType(), other.getType());
    }

    public BooleanInstance lessThanOrEqualTo(BuiltinClass other) {
        return new BooleanInstance(this.lessThan(other).value || this.equalToMethod(other).value);
    }

    public BuiltinClass logicalNot() {
        throw ErrorHolder.cannotApplyUnaryOperatorToType("!", this.getType());
    }

    public BuiltinClass mod(BuiltinClass other) {
        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("%", this.getType(), other.getType());
    }

    public BuiltinClass multiply(BuiltinClass other) {
        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("*", this.getType(), other.getType());
    }

    public BuiltinClass negate() {
        throw ErrorHolder.cannotApplyUnaryOperatorToType("-", this.getType());
    }

    public BooleanInstance notEqualToMethod(BuiltinClass other) {
        return new BooleanInstance(!this.equalToMethod(other).value);
    }

    public void setIndex(BuiltinClass index, BuiltinClass value) {
        throw ErrorHolder.typeIsNotIndexable(this.getType());
    }

    public void setProperty(String propertyName, BuiltinClass value) {
        this.getType().setProperty(propertyName, value);
    }

    public BuiltinClass subtract(BuiltinClass other) {
        throw ErrorHolder.cannotApplyBinaryOperatorToTypes("-", this.getType(), other.getType());
    }

    public Block toBlock() {
        throw ErrorHolder.cannotConvertType(this.getType(), new BlockType());
    }

    public BlockPos toBlockPos() {
        throw ErrorHolder.cannotConvertType(this.getType(), new BlockPosType());
    }

    public boolean toBoolean() {
        throw ErrorHolder.cannotConvertType(this.getType(), new BooleanType());
    }

    public Difficulty toDifficulty() {
        throw ErrorHolder.cannotConvertType(this.getType(), new DifficultiesEnumType());
    }

    public Entity toEntity() {
        throw ErrorHolder.cannotConvertType(this.getType(), new EntityType());
    }

    public double toFloat() {
        throw ErrorHolder.cannotConvertType(this.getType(), new FloatType());
    }

    public BuiltinFunction toFunction() {
        throw ErrorHolder.cannotConvertType(this.getType(), new FunctionType());
    }

    public GameMode toGameMode() {
        throw ErrorHolder.cannotConvertType(this.getType(), new GameModesEnumType());
    }

    public GameRules toGameRules() {
        throw ErrorHolder.cannotConvertType(this.getType(), new GameRulesType());
    }

    public long toInteger() {
        throw ErrorHolder.cannotConvertType(this.getType(), new IntegerType());
    }

    public Item toItem() {
        throw ErrorHolder.cannotConvertType(this.getType(), new ItemType());
    }

    public ItemStack toItemStack() {
        throw ErrorHolder.cannotConvertType(this.getType(), new ItemStackType());
    }

    public List<BuiltinClass> toList() {
        throw ErrorHolder.cannotConvertType(this.getType(), new ListType());
    }

    public LivingEntity toLivingEntity() {
        throw ErrorHolder.cannotConvertType(this.getType(), new LivingEntityType());
    }

    public PlayerEntity toPlayerEntity() {
        throw ErrorHolder.cannotConvertType(this.getType(), new PlayerEntityType());
    }

    public PlayerManager toPlayerManager() {
        throw ErrorHolder.cannotConvertType(this.getType(), new PlayerManagerType());
    }

    public Identifier toResource() {
        throw ErrorHolder.cannotConvertType(this.getType(), new ResourceType());
    }

    public ServerPlayerEntity toServerPlayerEntity() {
        throw ErrorHolder.cannotConvertType(this.getType(), new ServerPlayerEntityType());
    }

    @Override
    public String toString() {
        return this.toStringType();
    }

    public StringInstance toStringMethod() {
        return new StringInstance("<Class '" + this.getType().typeName + "'>");
    }

    public String toStringType() {
        return "<Class '" + this.getType().typeName + "'>";
    }

    public ServerWorld toWorld() {
        throw ErrorHolder.cannotConvertType(this.getType(), new WorldType());
    }
}