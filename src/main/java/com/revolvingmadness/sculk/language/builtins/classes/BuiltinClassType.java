package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.types.ObjectClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.TypeClassType;
import com.revolvingmadness.sculk.language.errors.TypeError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.interpreter.Variable;
import com.revolvingmadness.sculk.language.interpreter.VariableScope;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public abstract class BuiltinClassType extends BuiltinClass {
    public final List<TokenType> accessModifiers;
    public final String name;
    public final BuiltinClassType superClass;
    public final VariableScope typeVariableScope;

    public BuiltinClassType(String name) {
        this(List.of(), name, ObjectClassType.TYPE, new VariableScope());
    }

    public BuiltinClassType(BuiltinClassType type, String name) {
        this(type, List.of(), name, ObjectClassType.TYPE, new VariableScope());
    }

    public BuiltinClassType(String name, BuiltinClassType superClass) {
        this(List.of(), name, superClass, new VariableScope());
    }

    public BuiltinClassType(List<TokenType> accessModifiers, String name) {
        this(accessModifiers, name, ObjectClassType.TYPE, new VariableScope());
    }

    public BuiltinClassType(List<TokenType> accessModifiers, String name, BuiltinClassType superClass, VariableScope typeVariableScope) {
        this(TypeClassType.TYPE, accessModifiers, name, superClass, typeVariableScope);
    }

    public BuiltinClassType(BuiltinClassType type, List<TokenType> accessModifiers, String name, BuiltinClassType superClass, VariableScope typeVariableScope) {
        super(type);
        this.accessModifiers = accessModifiers;
        this.name = name;
        this.superClass = superClass;
        this.typeVariableScope = typeVariableScope;

        this.checkIfAllMethodsAreImplemented();

        if (!this.isAbstract() && this.hasAbstractMethods()) {
            throw new TypeError("Cannot declare a non-abstract class with abstract methods");
        }
    }

    public void addMethod(String name, List<BuiltinClassType> argumentTypes) throws ReflectiveOperationException {
        Method method = this.getClass().getMethod(name, Interpreter.class, BuiltinClass.class, BuiltinClass[].class);

        BuiltinMethod methodClass = new BuiltinMethod() {
            @Override
            public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
                this.validateCall(name, arguments, argumentTypes);

                Object result;

                List<Object> methodCallArguments = new ArrayList<>();

                methodCallArguments.add(interpreter);
                methodCallArguments.add(this.boundClass);
                methodCallArguments.add(arguments.toArray(new BuiltinClass[0]));

                try {
                    result = method.invoke(this.boundClass, methodCallArguments.toArray());
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException(e);
                }

                if (!(result instanceof BuiltinClass builtinClass)) {
                    throw new RuntimeException("Invalid method '" + name + "'");
                }

                return builtinClass;
            }
        };

        this.typeVariableScope.declare(List.of(TokenType.CONST), method.getName(), methodClass);
    }

    public void addNoArgMethod(String name, Function<BuiltinClass, BuiltinClass> supplier) {
        BuiltinMethod method = new BuiltinMethod() {
            @Override
            public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
                this.validateCall(name, arguments);

                return supplier.apply(this.boundClass);
            }
        };

        this.typeVariableScope.declare(List.of(TokenType.CONST), name, method);
    }

    public void addStaticMethod(String name, List<BuiltinClassType> argumentTypes) throws ReflectiveOperationException {
        Method method = this.getClass().getMethod(name, Interpreter.class, BuiltinClass.class, BuiltinClass[].class);

        BuiltinMethod methodClass = new BuiltinMethod() {
            @Override
            public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
                this.validateCall(name, arguments, argumentTypes);

                Object result;

                List<Object> methodCallArguments = new ArrayList<>();

                methodCallArguments.add(interpreter);
                methodCallArguments.add(this.boundClass);
                methodCallArguments.add(arguments.toArray(new BuiltinClass[0]));

                try {
                    result = method.invoke(this.boundClass, methodCallArguments.toArray());
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException(e);
                }

                if (!(result instanceof BuiltinClass builtinClass)) {
                    throw new RuntimeException("Invalid method '" + name + "'");
                }

                return builtinClass;
            }
        };

        this.variableScope.declare(List.of(TokenType.CONST), method.getName(), methodClass);
    }

    public boolean canDowncastTo(BuiltinClassType type) {
        if (this.name.equals(type.name)) {
            return true;
        }

        if (this.superClass == null) {
            return false;
        }

        return this.superClass.canDowncastTo(type);
    }

    public void checkIfAllMethodsAreImplemented() {
        if (this.superClass == null) {
            return;
        }

        if (this.isAbstract()) {
            return;
        }

        for (Variable property : this.superClass.getAbstractMethods()) {
            if (!this.typeVariableScope.exists(property.name)) {
                throw new TypeError("Class '" + this.name + "' does not implement method '" + property.name + "'");
            }
        }
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
        BuiltinClassType that = (BuiltinClassType) o;
        return Objects.equals(this.accessModifiers, that.accessModifiers) && Objects.equals(this.name, that.name) && Objects.equals(this.superClass, that.superClass) && Objects.equals(this.typeVariableScope, that.typeVariableScope);
    }

    public List<Variable> getAbstractMethods() {
        List<Variable> variables = new ArrayList<>();

        for (Variable variable : this.typeVariableScope.variables.values()) {
            if (variable.isAbstract()) {
                variables.add(variable);
            }
        }

        if (this.superClass != null) {
            variables.addAll(this.superClass.getAbstractMethods());
        }

        return variables;
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

    public boolean isAbstract() {
        return this.accessModifiers.contains(TokenType.ABSTRACT);
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
