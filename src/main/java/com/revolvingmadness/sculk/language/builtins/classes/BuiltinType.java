package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.ObjectType;
import com.revolvingmadness.sculk.language.builtins.classes.types.TypeType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.interpreter.Variable;
import com.revolvingmadness.sculk.language.interpreter.VariableScope;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class BuiltinType extends BuiltinClass {
    public final List<TokenType> typeAccessModifiers;
    public final String typeName;
    public final BuiltinType typeSuperClass;
    public final VariableScope typeVariableScope;

    public BuiltinType(String typeName) {
        this(List.of(), typeName, new ObjectType(), new VariableScope());
    }

    public BuiltinType(String typeName, BuiltinType typeSuperClass) {
        this(List.of(), typeName, typeSuperClass, new VariableScope());
    }

    public BuiltinType(List<TokenType> typeAccessModifiers, String typeName) {
        this(typeAccessModifiers, typeName, new ObjectType(), new VariableScope());
    }

    public BuiltinType(List<TokenType> typeAccessModifiers, String typeName, BuiltinType typeSuperClass, VariableScope typeVariableScope) {
        this.typeAccessModifiers = typeAccessModifiers;
        this.typeName = typeName;
        this.typeSuperClass = typeSuperClass;
        this.typeVariableScope = typeVariableScope;

        this.checkIfAllMethodsAreImplemented();

        if (!this.isAbstract() && this.hasAbstractMethods()) {
            throw ErrorHolder.cannotDeclareNonAbstractClassWithAbstractMethods(this.typeName);
        }

        this.variableScope.declare(List.of(TokenType.CONST), "toString", new ToString());
    }

    @Override
    public void checkIfAllMethodsAreImplemented() {
        if (this.typeSuperClass == null) {
            return;
        }

        if (!this.typeSuperClass.isAbstract()) {
            return;
        }

        for (Variable property : this.typeSuperClass.typeVariableScope.variables.values()) {
            if (property.isAbstract()) {
                if (!this.typeVariableScope.exists(property.name)) {
                    throw ErrorHolder.methodNotImplemented(property.name, this.typeName);
                }
            }
        }

        this.typeSuperClass.checkIfAllMethodsAreImplemented();
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
        return Objects.equals(this.typeAccessModifiers, that.typeAccessModifiers) && Objects.equals(this.typeName, that.typeName) && Objects.equals(this.typeSuperClass, that.typeSuperClass) && Objects.equals(this.typeVariableScope, that.typeVariableScope);
    }

    @Override
    public BuiltinClass getProperty(String propertyName) {
        Optional<Variable> optionalProperty = this.typeVariableScope.getOptional(propertyName);

        if (optionalProperty.isPresent()) {
            BuiltinClass property = optionalProperty.get().value;

            if (property instanceof BuiltinMethod method) {
                method.bind(this, this.typeSuperClass);
            }

            return property;
        }

        optionalProperty = this.variableScope.getOptional(propertyName);

        if (optionalProperty.isPresent()) {
            BuiltinClass property = optionalProperty.get().value;

            if (property instanceof BuiltinMethod method) {
                method.bind(this, this.typeSuperClass);
            }

            return property;
        }

        if (this.typeSuperClass == null) {
            throw ErrorHolder.typeHasNoProperty(this, propertyName);
        }

        BuiltinClass superProperty = this.typeSuperClass.getProperty(propertyName);

        if (superProperty instanceof BuiltinMethod superMethod) {
            superMethod.bind(this, this.typeSuperClass);
        }

        return superProperty;
    }

    @Override
    public BuiltinType getType() {
        return new TypeType();
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
        return Objects.hash(this.typeAccessModifiers, this.typeName, this.typeSuperClass, this.typeVariableScope);
    }

    @Override
    public boolean instanceOf(BuiltinType type) {
        boolean isInstanceOf = this.typeName.equals(type.typeName);

        if (isInstanceOf) {
            return true;
        }

        if (this.typeSuperClass == null) {
            return false;
        }

        return this.typeSuperClass.instanceOf(type);
    }

    @Override
    public boolean isAbstract() {
        return this.typeAccessModifiers.contains(TokenType.ABSTRACT);
    }

    @Override
    public boolean isConstant() {
        return this.typeAccessModifiers.contains(TokenType.CONST);
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

        if (this.typeSuperClass == null) {
            throw ErrorHolder.typeHasNoProperty(this, propertyName);
        }

        this.typeSuperClass.setProperty(propertyName, value);
    }

    @Override
    public String toString() {
        return this.typeName;
    }

    private static class ToString extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("toString", 0, arguments.size());
            }

            return new StringInstance("<Class '" + this.boundClass.getType().typeName + "'>");
        }
    }
}
