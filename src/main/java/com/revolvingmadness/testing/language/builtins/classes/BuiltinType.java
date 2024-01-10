package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.instances.StringInstance;
import com.revolvingmadness.testing.language.builtins.classes.types.ObjectType;
import com.revolvingmadness.testing.language.builtins.classes.types.TypeType;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;

import java.util.List;
import java.util.Optional;

public abstract class BuiltinType extends BuiltinClass {
    public final String typeName;
    public final BuiltinType typeSuperClass;
    public final VariableScope typeVariableScope;

    public BuiltinType(String typeName) {
        this(typeName, new VariableScope());
    }

    public BuiltinType(String typeName, VariableScope variableScope) {
        this(typeName, new ObjectType(), variableScope);
    }

    public BuiltinType(String typeName, BuiltinType typeSuperClass) {
        this(typeName, typeSuperClass, new VariableScope());
    }

    public BuiltinType(String typeName, BuiltinType typeSuperClass, VariableScope typeVariableScope) {
        this.typeName = typeName;
        this.typeSuperClass = typeSuperClass;
        this.typeVariableScope = typeVariableScope;

        this.variableScope.declare(true, "toString", new ToString());
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
