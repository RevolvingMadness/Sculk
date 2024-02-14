package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.types.ObjectType;
import com.revolvingmadness.sculk.language.builtins.classes.types.TypeType;
import com.revolvingmadness.sculk.language.errors.TypeError;
import com.revolvingmadness.sculk.language.interpreter.Variable;
import com.revolvingmadness.sculk.language.interpreter.VariableScope;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class BuiltinType extends BuiltinClass {
    public final List<TokenType> accessModifiers;
    public final String name;
    public final BuiltinType superClass;
    public final VariableScope typeVariableScope;

    public BuiltinType(String name) {
        this(List.of(), name, ObjectType.TYPE, new VariableScope());
    }

    public BuiltinType(String name, BuiltinType superClass) {
        this(List.of(), name, superClass, new VariableScope());
    }

    public BuiltinType(List<TokenType> accessModifiers, String name) {
        this(accessModifiers, name, ObjectType.TYPE, new VariableScope());
    }

    public BuiltinType(List<TokenType> accessModifiers, String name, BuiltinType superClass, VariableScope typeVariableScope) {
        this.accessModifiers = accessModifiers;
        this.name = name;
        this.superClass = superClass;
        this.typeVariableScope = typeVariableScope;

        this.checkIfAllMethodsAreImplemented();

        if (!this.isAbstract() && this.hasAbstractMethods()) {
            throw new TypeError("Cannot declare a non-abstract class with abstract methods");
        }
    }

    @Override
    public void checkIfAllMethodsAreImplemented() {
        if (this.superClass == null) {
            return;
        }

        if (!this.superClass.isAbstract()) {
            return;
        }

        for (Variable property : this.superClass.typeVariableScope.variables.values()) {
            if (property.isAbstract()) {
                if (!this.typeVariableScope.exists(property.name)) {
                    throw new TypeError("Class '" + this.name + "' does not implement method '" + property.name + "'");
                }
            }
        }

        this.superClass.checkIfAllMethodsAreImplemented();
    }

    @Override
    public void deleteProperty(String propertyName) {
        this.typeVariableScope.deleteOrThrow(propertyName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        BuiltinType that = (BuiltinType) o;
        return Objects.equals(this.accessModifiers, that.accessModifiers) && Objects.equals(this.name, that.name) && Objects.equals(this.superClass, that.superClass) && Objects.equals(this.typeVariableScope, that.typeVariableScope);
    }

    @Override
    public BuiltinClass getProperty(String propertyName) {
        Optional<Variable> optionalProperty = this.typeVariableScope.getOptional(propertyName);

        if (optionalProperty.isPresent()) {
            BuiltinClass property = optionalProperty.get().value;

            if (property instanceof BuiltinMethod method) {
                method.bind(this, this.superClass);
            }

            return property;
        }

        optionalProperty = this.variableScope.getOptional(propertyName);

        if (optionalProperty.isPresent()) {
            BuiltinClass property = optionalProperty.get().value;

            if (property instanceof BuiltinMethod method) {
                method.bind(this, this.superClass);
            }

            return property;
        }

        if (this.superClass == null) {
            throw ErrorHolder.typeHasNoProperty(this, propertyName);
        }

        BuiltinClass superProperty = this.superClass.getProperty(propertyName);

        if (superProperty instanceof BuiltinMethod superMethod) {
            superMethod.bind(this, this.superClass);
        }

        return superProperty;
    }

    @Override
    public BuiltinType getType() {
        return TypeType.TYPE;
    }

    @Override
    public boolean hasAbstractMethods() {
        for (Variable variable : this.typeVariableScope.variables.values()) {
            if (variable.isAbstract()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.accessModifiers, this.name, this.superClass, this.typeVariableScope);
    }

    @Override
    public boolean isAbstract() {
        return this.accessModifiers.contains(TokenType.ABSTRACT);
    }

    @Override
    public boolean isConstant() {
        return this.accessModifiers.contains(TokenType.CONST);
    }

    @Override
    public void setProperty(String propertyName, BuiltinClass value) {
        Optional<Variable> optionalVariable = this.variableScope.getOptional(propertyName);

        if (optionalVariable.isPresent()) {
            this.variableScope.assign(propertyName, value);

            return;
        }

        optionalVariable = this.typeVariableScope.getOptional(propertyName);

        if (optionalVariable.isPresent()) {
            this.typeVariableScope.assign(propertyName, value);

            return;
        }

        if (this.superClass == null) {
            throw ErrorHolder.typeHasNoProperty(this, propertyName);
        }

        this.superClass.setProperty(propertyName, value);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
