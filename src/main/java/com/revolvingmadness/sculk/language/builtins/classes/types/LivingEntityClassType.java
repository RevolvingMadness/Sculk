package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.FloatClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.entity.EntityClassType;
import com.revolvingmadness.sculk.language.builtins.enums.AttributesEnumType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.entity.attribute.EntityAttributeInstance;

import java.util.List;

public class LivingEntityClassType extends BuiltinClassType {
    public static final LivingEntityClassType TYPE = new LivingEntityClassType();

    private LivingEntityClassType() {
        super("LivingEntity", EntityClassType.TYPE);

        this.typeVariableScope.declare(List.of(TokenType.CONST), "tiltScreen", new TiltScreen());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "wakeUp", new WakeUp());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setAttribute", new SetAttributes());
    }

    private static class SetAttributes extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getAttributes", arguments, List.of(AttributesEnumType.TYPE, FloatClassType.TYPE));

            EntityAttributeInstance attributeInstance = this.boundClass.toLivingEntity().getAttributeInstance(arguments.get(0).toAttribute());

            if (attributeInstance == null) {
                return new NullInstance();
            }

            attributeInstance.setBaseValue(arguments.get(1).toFloat());

            return new NullInstance();
        }
    }

    private static class TiltScreen extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("tiltScreen", arguments, List.of(FloatClassType.TYPE, FloatClassType.TYPE));

            double deltaX = arguments.get(0).toFloat();
            double deltaZ = arguments.get(1).toFloat();

            this.boundClass.toLivingEntity().tiltScreen(deltaX, deltaZ);

            return new NullInstance();
        }
    }

    private static class WakeUp extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("wakeUp", arguments);

            this.boundClass.toLivingEntity().wakeUp();

            return new NullInstance();
        }
    }

}
