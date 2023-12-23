package com.revolvingmadness.testing.language.user_defined;

import com.revolvingmadness.testing.language.builtins.classes.BaseClassExpressionNode;
import com.revolvingmadness.testing.language.builtins.classes.types.FunctionClass;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import org.apache.commons.lang3.SerializationUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDefinedClassInstance extends BaseClassExpressionNode {
    public final IdentifierExpressionNode name;

    public UserDefinedClassInstance(UserDefinedClass clazz, Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
        super(SerializationUtils.clone(clazz.superClass), SerializationUtils.clone(clazz.variableScope));
        this.name = SerializationUtils.clone(clazz.name);

        Optional<Variable> optionalConstructor = this.variableScope.getOptional(new IdentifierExpressionNode("init"));

        if (optionalConstructor.isPresent()) {
            BaseClassExpressionNode constructor = optionalConstructor.get().value;

            if (!(constructor instanceof FunctionClass method)) {
                throw new SyntaxError("Constructor cannot be type '" + constructor.getType() + "'");
            }

            method.bind(this, this.superClass);

            method.call(interpreter, arguments);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        UserDefinedClassInstance that = (UserDefinedClassInstance) o;
        return Objects.equals(this.name, that.name) && Objects.equals(this.superClass, that.superClass) && Objects.equals(this.variableScope, that.variableScope);
    }

    @Override
    public String getType() {
        return this.name.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.name, this.superClass, this.variableScope);
    }
}
