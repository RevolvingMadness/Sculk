package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.instances.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.*;
import com.revolvingmadness.sculk.language.errors.TypeError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.interpreter.Variable;
import com.revolvingmadness.sculk.language.interpreter.VariableScope;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameMode;
import net.minecraft.world.GameRules;

import java.util.*;

public abstract class BuiltinClass extends ExpressionNode {
    public final VariableScope variableScope;

    public BuiltinClass() {
        this(new VariableScope());
    }

    public BuiltinClass(VariableScope variableScope) {
        this.variableScope = variableScope;
    }

    public static BuiltinClass fromNbtElement(NbtElement result) {
        if (result == null) {
            return new NullInstance();
        }

        if (result instanceof NbtByteArray nbtByteArray) {
            List<BuiltinClass> list = new ArrayList<>();

            nbtByteArray.forEach(nbtByte -> list.add(BuiltinClass.fromNbtElement(nbtByte)));

            return new ListInstance(list);
        } else if (result instanceof NbtIntArray nbtIntArray) {
            List<BuiltinClass> list = new ArrayList<>();

            nbtIntArray.forEach(nbtInt -> list.add(BuiltinClass.fromNbtElement(nbtInt)));

            return new ListInstance(list);
        } else if (result instanceof NbtList nbtList) {
            List<BuiltinClass> list = new ArrayList<>();

            nbtList.forEach(nbtElement -> list.add(BuiltinClass.fromNbtElement(nbtElement)));

            return new ListInstance(list);
        } else if (result instanceof NbtLongArray nbtLongArray) {
            List<BuiltinClass> list = new ArrayList<>();

            nbtLongArray.forEach(nbtLong -> list.add(BuiltinClass.fromNbtElement(nbtLong)));

            return new ListInstance(list);
        } else if (result instanceof NbtByte nbtByte) {
            return new IntegerInstance(nbtByte.byteValue());
        } else if (result instanceof NbtDouble nbtDouble) {
            return new FloatInstance(nbtDouble.doubleValue());
        } else if (result instanceof NbtFloat nbtFloat) {
            return new FloatInstance(nbtFloat.floatValue());
        } else if (result instanceof NbtInt nbtInt) {
            return new IntegerInstance(nbtInt.intValue());
        } else if (result instanceof NbtLong nbtLong) {
            return new IntegerInstance(nbtLong.longValue());
        } else if (result instanceof NbtShort nbtShort) {
            return new IntegerInstance(nbtShort.shortValue());
        } else if (result instanceof NbtCompound nbtCompound) {
            Map<BuiltinClass, BuiltinClass> dictionary = new HashMap<>();

            Set<String> keys = nbtCompound.getKeys();

            keys.forEach(key -> {
                BuiltinClass value = BuiltinClass.fromNbtElement(nbtCompound.get(key));

                dictionary.put(new StringInstance(key), value);
            });

            return new DictionaryInstance(dictionary);
        } else if (result instanceof NbtString nbtString) {
            return new StringInstance(nbtString.asString());
        }

        throw new TypeError("Cannot convert nbt element '" + result + "' to class");
    }

    public BuiltinClass add(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("+", this.getType(), other.getType());
    }

    public BooleanInstance booleanAnd(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("&&", this.getType(), other.getType());
    }

    public BooleanInstance booleanOr(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("||", this.getType(), other.getType());
    }

    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        throw new TypeError("Type '" + this.getType().name + "' is not callable");
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
        throw ErrorHolder.unsupportedBinaryOperator("/", this.getType(), other.getType());
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
        throw ErrorHolder.unsupportedBinaryOperator("^", this.getType(), other.getType());
    }

    public BuiltinClass fromNBT(BuiltinClass nbtElement) {
        throw new TypeError("Type '" + this.getType().name + "' does not support NBT de-serialization");
    }

    public BuiltinClass getIndex(BuiltinClass index) {
        throw ErrorHolder.typeIsNotIndexable(this.getType());
    }

    public BuiltinClass getProperty(String propertyName) {
        Optional<Variable> optionalProperty = this.variableScope.getOptional(propertyName);

        if (optionalProperty.isPresent()) {
            BuiltinClass property = optionalProperty.get().value;

            if (property instanceof BuiltinMethod method) {
                method.bind(this, this.getType().superClass);
            }

            return property;
        }

        BuiltinClass property = this.getType().getProperty(propertyName);

        if (property instanceof BuiltinMethod method) {
            method.bind(this, this.getType().superClass);
        }

        return property;
    }

    public abstract BuiltinType getType();

    public BooleanInstance greaterThan(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator(">", this.getType(), other.getType());
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
        BuiltinType valueType = this.getType();

        while (valueType != null) {
            if (valueType.name.equals(type.name)) {
                return true;
            }

            valueType = valueType.superClass;
        }

        return false;
    }

    public boolean isAbstract() {
        return this.getType().isAbstract();
    }

    @SuppressWarnings("unused")
    public boolean isConstant() {
        return this.getType().isConstant();
    }

    public BooleanInstance lessThan(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("<", this.getType(), other.getType());
    }

    public BuiltinClass logicalNot() {
        throw ErrorHolder.cannotApplyUnaryOperatorToType("!", this.getType());
    }

    public BuiltinClass mod(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("%", this.getType(), other.getType());
    }

    public BuiltinClass multiply(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("*", this.getType(), other.getType());
    }

    public BuiltinClass negate() {
        throw ErrorHolder.cannotApplyUnaryOperatorToType("-", this.getType());
    }

    public void setIndex(BuiltinClass index, BuiltinClass value) {
        throw ErrorHolder.typeIsNotIndexable(this.getType());
    }

    public void setProperty(String propertyName, BuiltinClass value) {
        this.getType().setProperty(propertyName, value);
    }

    public BuiltinClass subtract(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("-", this.getType(), other.getType());
    }

    public Block toBlock() {
        throw ErrorHolder.cannotConvertType(this.getType(), BlockType.TYPE);
    }

    public BlockPos toBlockPos() {
        throw ErrorHolder.cannotConvertType(this.getType(), BlockPosType.TYPE);
    }

    public boolean toBoolean() {
        throw ErrorHolder.cannotConvertType(this.getType(), BooleanType.TYPE);
    }

    public Map<BuiltinClass, BuiltinClass> toDictionary() {
        throw ErrorHolder.cannotConvertType(this.getType(), DictionaryType.TYPE);
    }

    public Difficulty toDifficulty() {
        throw ErrorHolder.cannotConvertType(this.getType(), DifficultiesEnumType.TYPE);
    }

    public Entity toEntity() {
        throw ErrorHolder.cannotConvertType(this.getType(), EntityType.TYPE);
    }

    public double toFloat() {
        throw ErrorHolder.cannotConvertType(this.getType(), FloatType.TYPE);
    }

    public BuiltinFunction toFunction() {
        throw ErrorHolder.cannotConvertType(this.getType(), FunctionType.TYPE);
    }

    public GUIInstance toGUI() {
        throw ErrorHolder.cannotConvertType(this.getType(), GUIType.TYPE);
    }

    public GameMode toGameMode() {
        throw ErrorHolder.cannotConvertType(this.getType(), GameModesEnumType.TYPE);
    }

    public GameRules toGameRules() {
        throw ErrorHolder.cannotConvertType(this.getType(), GameRulesType.TYPE);
    }

    public long toInteger() {
        throw ErrorHolder.cannotConvertType(this.getType(), IntegerType.TYPE);
    }

    public Inventory toInventory() {
        throw ErrorHolder.cannotConvertType(this.getType(), InventoryType.TYPE);
    }

    public Item toItem() {
        throw ErrorHolder.cannotConvertType(this.getType(), ItemType.TYPE);
    }

    public ItemStack toItemStack() {
        throw ErrorHolder.cannotConvertType(this.getType(), ItemStackType.TYPE);
    }

    public List<BuiltinClass> toList() {
        throw ErrorHolder.cannotConvertType(this.getType(), ListType.TYPE);
    }

    public LivingEntity toLivingEntity() {
        throw ErrorHolder.cannotConvertType(this.getType(), LivingEntityType.TYPE);
    }

    public NbtElement toNBT() {
        throw new TypeError("Type '" + this.getType().name + "' does not support NBT serialization");
    }

    public PlayerEntity toPlayerEntity() {
        throw ErrorHolder.cannotConvertType(this.getType(), PlayerEntityType.TYPE);
    }

    public PlayerManager toPlayerManager() {
        throw ErrorHolder.cannotConvertType(this.getType(), PlayerManagerType.TYPE);
    }

    public String toRepresentation() {
        return this.toString();
    }

    public ServerPlayerEntity toServerPlayerEntity() {
        throw ErrorHolder.cannotConvertType(this.getType(), ServerPlayerEntityType.TYPE);
    }

    @Override
    public String toString() {
        return "<Class '" + this.getType().name + "'>";
    }

    public ServerWorld toWorld() {
        throw ErrorHolder.cannotConvertType(this.getType(), WorldType.TYPE);
    }

    public void validateCall(String callableName, List<BuiltinClass> arguments, List<BuiltinType> argumentTypes) {
        if (arguments.size() != argumentTypes.size()) {
            throw ErrorHolder.invalidArgumentCount(callableName, arguments.size(), argumentTypes.size());
        }

        int argumentNumber = 0;

        for (BuiltinClass argument : arguments) {
            BuiltinType argumentType = argumentTypes.get(argumentNumber);

            if (!argument.instanceOf(NullType.TYPE)) {
                if (!argument.instanceOf(argumentType)) {
                    throw ErrorHolder.argumentRequiresType(argumentNumber + 1, callableName, argument.getType(), argumentType);
                }
            }

            argumentNumber++;
        }
    }

    public void validateCall(String callableName, List<BuiltinClass> arguments) {
        this.validateCall(callableName, arguments, List.of());
    }
}