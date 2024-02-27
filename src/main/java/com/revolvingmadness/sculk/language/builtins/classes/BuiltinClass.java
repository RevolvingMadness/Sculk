package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.instances.GUIInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.nbt.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockPosType;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.entity.EntityType;
import com.revolvingmadness.sculk.language.builtins.classes.types.entity.PlayerEntityType;
import com.revolvingmadness.sculk.language.builtins.classes.types.entity.ServerPlayerEntityType;
import com.revolvingmadness.sculk.language.builtins.enums.AttributesEnumType;
import com.revolvingmadness.sculk.language.builtins.enums.DifficultiesEnumType;
import com.revolvingmadness.sculk.language.builtins.enums.GameModesEnumType;
import com.revolvingmadness.sculk.language.errors.TypeError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.interpreter.Variable;
import com.revolvingmadness.sculk.language.interpreter.VariableScope;
import com.revolvingmadness.sculk.language.parser.nodes.expression_nodes.ExpressionNode;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
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

import java.io.Serializable;
import java.util.*;

public abstract class BuiltinClass extends ExpressionNode implements Serializable {
    public final VariableScope variableScope;
    public BuiltinType type;

    public BuiltinClass(BuiltinType type) {
        this(type, new VariableScope());
    }

    public BuiltinClass(BuiltinType type, VariableScope variableScope) {
        this.type = type;
        this.variableScope = variableScope;
    }

    public static NBTElementInstance fromNbtElement(NbtElement result) {
        if (result == null) {
            return new NBTNullInstance();
        }

        if (result instanceof NbtByteArray nbtByteArray) {
            List<NBTElementInstance> list = new ArrayList<>();

            nbtByteArray.forEach(nbtByte -> list.add(BuiltinClass.fromNbtElement(nbtByte)));

            return new NBTListInstance(list);
        } else if (result instanceof NbtIntArray nbtIntArray) {
            List<NBTElementInstance> list = new ArrayList<>();

            nbtIntArray.forEach(nbtInt -> list.add(BuiltinClass.fromNbtElement(nbtInt)));

            return new NBTListInstance(list);
        } else if (result instanceof NbtList nbtList) {
            List<NBTElementInstance> list = new ArrayList<>();

            nbtList.forEach(nbtElement -> list.add(BuiltinClass.fromNbtElement(nbtElement)));

            return new NBTListInstance(list);
        } else if (result instanceof NbtLongArray nbtLongArray) {
            List<NBTElementInstance> list = new ArrayList<>();

            nbtLongArray.forEach(nbtLong -> list.add(BuiltinClass.fromNbtElement(nbtLong)));

            return new NBTListInstance(list);
        } else if (result instanceof NbtByte nbtByte) {
            return new NBTIntegerInstance(nbtByte.byteValue());
        } else if (result instanceof NbtDouble nbtDouble) {
            return new NBTFloatInstance(nbtDouble.doubleValue());
        } else if (result instanceof NbtFloat nbtFloat) {
            return new NBTFloatInstance(nbtFloat.floatValue());
        } else if (result instanceof NbtInt nbtInt) {
            return new NBTIntegerInstance(nbtInt.intValue());
        } else if (result instanceof NbtLong nbtLong) {
            return new NBTIntegerInstance(nbtLong.longValue());
        } else if (result instanceof NbtShort nbtShort) {
            return new NBTIntegerInstance(nbtShort.shortValue());
        } else if (result instanceof NbtCompound nbtCompound) {
            Map<String, NBTElementInstance> compound = new HashMap<>();

            Set<String> keys = nbtCompound.getKeys();

            keys.forEach(key -> {
                NBTElementInstance value = BuiltinClass.fromNbtElement(nbtCompound.get(key));

                compound.put(key, value);
            });

            return new NBTCompoundInstance(compound);
        } else if (result instanceof NbtString nbtString) {
            return new NBTStringInstance(nbtString.asString());
        }

        throw new TypeError("Cannot convert nbt element '" + result + "' to class");
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

    public void checkIfAllMethodsAreImplemented() {
        this.type.checkIfAllMethodsAreImplemented();
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
        if (!(typeClass instanceof BuiltinType type_)) {
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

    public BuiltinClass fromNBT(NBTElementInstance nbtElement) {
        throw new TypeError("Type '" + this.type.name + "' does not support NBT de-serialization");
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

    public boolean instanceOf(BuiltinType type) {
        BuiltinType valueType = this.type;

        while (valueType != null) {
            if (valueType.name.equals(type.name)) {
                return true;
            }

            valueType = valueType.superClass;
        }

        return false;
    }

    public boolean isAbstract() {
        return this.type.isAbstract();
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
        this.type.setProperty(propertyName, value);
    }

    public BuiltinClass subtract(BuiltinClass other) {
        throw ErrorHolder.unsupportedBinaryOperator("-", this.type, other.type);
    }

    public EntityAttribute toAttribute() {
        throw ErrorHolder.cannotConvertType(this.type, AttributesEnumType.TYPE);
    }

    public Block toBlock() {
        throw ErrorHolder.cannotConvertType(this.type, BlockType.TYPE);
    }

    public BlockPos toBlockPos() {
        throw ErrorHolder.cannotConvertType(this.type, BlockPosType.TYPE);
    }

    public boolean toBoolean() {
        throw ErrorHolder.cannotConvertType(this.type, BooleanType.TYPE);
    }

    public Difficulty toDifficulty() {
        throw ErrorHolder.cannotConvertType(this.type, DifficultiesEnumType.TYPE);
    }

    public Entity toEntity() {
        throw ErrorHolder.cannotConvertType(this.type, EntityType.TYPE);
    }

    public double toFloat() {
        throw ErrorHolder.cannotConvertType(this.type, FloatType.TYPE);
    }

    public BuiltinFunction toFunction() {
        throw ErrorHolder.cannotConvertType(this.type, FunctionType.TYPE);
    }

    public GUIInstance toGUI() {
        throw ErrorHolder.cannotConvertType(this.type, GUIType.TYPE);
    }

    public GameMode toGameMode() {
        throw ErrorHolder.cannotConvertType(this.type, GameModesEnumType.TYPE);
    }

    public GameRules toGameRules() {
        throw ErrorHolder.cannotConvertType(this.type, GameRulesType.TYPE);
    }

    public long toInteger() {
        throw ErrorHolder.cannotConvertType(this.type, IntegerType.TYPE);
    }

    public Inventory toInventory() {
        throw ErrorHolder.cannotConvertType(this.type, InventoryType.TYPE);
    }

    public Item toItem() {
        throw ErrorHolder.cannotConvertType(this.type, ItemType.TYPE);
    }

    public ItemStack toItemStack() {
        throw ErrorHolder.cannotConvertType(this.type, ItemStackType.TYPE);
    }

    public List<BuiltinClass> toList() {
        throw ErrorHolder.cannotConvertType(this.type, ListType.TYPE);
    }

    public LivingEntity toLivingEntity() {
        throw ErrorHolder.cannotConvertType(this.type, LivingEntityType.TYPE);
    }

    public NbtElement toNBT() {
        throw new TypeError("Type '" + this.type.name + "' does not support NBT serialization");
    }

    public PlayerEntity toPlayerEntity() {
        throw ErrorHolder.cannotConvertType(this.type, PlayerEntityType.TYPE);
    }

    public PlayerManager toPlayerManager() {
        throw ErrorHolder.cannotConvertType(this.type, PlayerManagerType.TYPE);
    }

    public String toRepresentation() {
        return this.toString();
    }

    public ServerPlayerEntity toServerPlayerEntity() {
        throw ErrorHolder.cannotConvertType(this.type, ServerPlayerEntityType.TYPE);
    }

    @Override
    public String toString() {
        return "<Class '" + this.type.name + "'>";
    }

    public ServerWorld toWorld() {
        throw ErrorHolder.cannotConvertType(this.type, WorldType.TYPE);
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
                    throw ErrorHolder.argumentRequiresType(argumentNumber + 1, callableName, argument.type, argumentType);
                }
            }

            argumentNumber++;
        }
    }

    public void validateCall(String callableName, List<BuiltinClass> arguments) {
        this.validateCall(callableName, arguments, List.of());
    }

    public void validateIndex(BuiltinType type, BuiltinClass requiredType) {
        if (!requiredType.instanceOf(type)) {
            throw ErrorHolder.cannotIndexTypeByType(this.type, requiredType.type);
        }
    }
}