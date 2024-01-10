package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.types.*;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.lexer.TokenType;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class BuiltinClass extends ExpressionNode {
    public final List<TokenType> accessModifiers;
    public final VariableScope variableScope;

    public BuiltinClass() {
        this(List.of(), new VariableScope());
    }

    public BuiltinClass(List<TokenType> accessModifiers) {
        this(accessModifiers, new VariableScope());
    }

    public BuiltinClass(VariableScope variableScope) {
        this(List.of(), variableScope);
    }

    public BuiltinClass(List<TokenType> accessModifiers, VariableScope variableScope) {
        this.accessModifiers = accessModifiers;
        this.variableScope = variableScope;
    }

    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        throw ErrorHolder.typeIsNotCallable(this.getType());
    }

    public BuiltinClass call(Interpreter interpreter, String methodName, List<BuiltinClass> arguments) {
        BuiltinClass method = this.getProperty(methodName);

        return method.call(interpreter, arguments);
    }

    public void checkIfAllMethodsAreImplemented() {
        if (!this.getType().typeSuperClass.isAbstract()) {
            return;
        }

        VariableScope methodsToImplement = this.getType().typeSuperClass.typeVariableScope;

        for (Variable property : methodsToImplement.variables) {
            if (property.isAbstract()) {
                if (!this.getType().typeVariableScope.exists(property.name)) {
                    throw ErrorHolder.methodNotImplemented(property.name, this.getType().typeName);
                }
            }
        }

        this.getType().typeSuperClass.checkIfAllMethodsAreImplemented();
    }

    public void deleteIndex(BuiltinClass index) {
        throw ErrorHolder.typeIsNotIndexable(this.getType());
    }

    public void deleteProperty(String propertyName) {
        this.variableScope.deleteOrThrow(propertyName);
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

    public BuiltinClass getIndex(BuiltinClass index) {
        throw ErrorHolder.typeIsNotIndexable(this.getType());
    }

    public BuiltinClass getProperty(String propertyName) {
        BuiltinClass property = this.getType().getProperty(propertyName);

        if (property instanceof BuiltinMethod method) {
            method.bind(this, this.getType().typeSuperClass);
        }

        return property;
    }

    public abstract BuiltinType getType();

    public boolean hasAbstractMethods() {
        for (Variable variable : this.variableScope.variables) {
            if (variable.isAbstract()) {
                return true;
            }
        }

        return false;
    }

    public boolean instanceOf(BuiltinType type) {
        return this.getType().instanceOf(type);
    }

    public boolean isAbstract() {
        return this.accessModifiers.contains(TokenType.ABSTRACT);
    }

    public void setIndex(BuiltinClass index, BuiltinClass value) {
        throw ErrorHolder.typeIsNotIndexable(this.getType());
    }

    public void setProperty(String propertyName, BuiltinClass value) {
        this.getType().setProperty(propertyName, value);
    }

    public Boolean toBoolean() {
        throw ErrorHolder.cannotConvertType(this.getType(), new BooleanType());
    }

    @SuppressWarnings("unused")
    public Map<BuiltinClass, BuiltinClass> toDictionary() {
        throw ErrorHolder.cannotConvertType(this.getType(), new DictionaryType());
    }

    public Entity toEntity() {
        throw ErrorHolder.cannotConvertType(this.getType(), new EntityType());
    }

    public Double toFloat() {
        throw ErrorHolder.cannotConvertType(this.getType(), new FloatType());
    }

    public BuiltinFunction toFunction() {
        throw ErrorHolder.cannotConvertType(this.getType(), new FunctionType());
    }

    public GameRules toGameRules() {
        throw ErrorHolder.cannotConvertType(this.getType(), new GameRulesType());
    }

    public Integer toInteger() {
        throw ErrorHolder.cannotConvertType(this.getType(), new IntegerType());
    }

    @SuppressWarnings("unused")
    public List<BuiltinClass> toList() {
        throw ErrorHolder.cannotConvertType(this.getType(), new ListType());
    }

    public LivingEntity toLivingEntity() {
        throw ErrorHolder.cannotConvertType(this.getType(), new LivingEntityType());
    }

    @SuppressWarnings("unused")
    public MinecraftServer toMinecraftServer() {
        throw ErrorHolder.cannotConvertType(this.getType(), new MinecraftServerType());
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

    public String toStringType() {
        return "<Class '" + this.getType().typeName + "'>";
    }

    @SuppressWarnings("unused")
    public Vec3d toVec3d() {
        throw ErrorHolder.cannotConvertType(this.getType(), new Vec3dType());
    }
}