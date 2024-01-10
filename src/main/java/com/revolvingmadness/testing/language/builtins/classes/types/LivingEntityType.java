package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.lexer.TokenType;

import java.util.List;

public class LivingEntityType extends BuiltinType {
    public LivingEntityType() {
        super("LivingEntity", new EntityType());

        this.typeVariableScope.declare(List.of(TokenType.CONST), "tiltScreen", new TiltScreen());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "wakeUp", new WakeUp());
    }

    private static class TiltScreen extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 2) {
                throw ErrorHolder.invalidArgumentCount("tiltScreen", 2, arguments.size());
            }

            BuiltinClass deltaX = arguments.get(0);

            if (!deltaX.instanceOf(new FloatType())) {
                throw ErrorHolder.argumentRequiresType(1, "tiltScreen", new FloatType(), deltaX.getType());
            }

            BuiltinClass deltaZ = arguments.get(1);

            if (!deltaZ.instanceOf(new FloatType())) {
                throw ErrorHolder.argumentRequiresType(2, "tiltScreen", new FloatType(), deltaZ.getType());
            }

            this.boundClass.toLivingEntity().tiltScreen(deltaX.toFloat(), deltaZ.toFloat());

            return new NullInstance();
        }
    }

    private static class WakeUp extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("wakeUp", 0, arguments.size());
            }

            this.boundClass.toLivingEntity().wakeUp();

            return new NullInstance();
        }
    }

}
