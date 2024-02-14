package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class LivingEntityType extends BuiltinType {
    public static final LivingEntityType TYPE = new LivingEntityType();

    private LivingEntityType() {
        super("LivingEntity", EntityType.TYPE);

        this.typeVariableScope.declare(List.of(TokenType.CONST), "tiltScreen", new TiltScreen());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "wakeUp", new WakeUp());
    }

    private static class TiltScreen extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("tiltScreen", arguments, List.of(FloatType.TYPE, FloatType.TYPE));

            double deltaX = arguments.get(0).toFloat();
            double deltaZ = arguments.get(1).toFloat();

            this.boundClass.toLivingEntity().tiltScreen(deltaX, deltaZ);

            return new NullInstance();
        }
    }

    private static class WakeUp extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("wakeUp", arguments);

            this.boundClass.toLivingEntity().wakeUp();

            return new NullInstance();
        }
    }

}
