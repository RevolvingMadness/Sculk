package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
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
            if (arguments.size() != 2) {
                throw ErrorHolder.invalidArgumentCount("tiltScreen", 2, arguments.size());
            }

            BuiltinClass deltaX = arguments.get(0);

            if (!deltaX.instanceOf(FloatType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "tiltScreen", FloatType.TYPE, deltaX.getType());
            }

            BuiltinClass deltaZ = arguments.get(1);

            if (!deltaZ.instanceOf(FloatType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(2, "tiltScreen", FloatType.TYPE, deltaZ.getType());
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
