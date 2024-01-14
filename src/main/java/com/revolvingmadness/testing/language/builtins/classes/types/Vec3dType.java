package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.Vec3dInstance;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.lexer.TokenType;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class Vec3dType extends BuiltinType {
    public Vec3dType() {
        super("Vec3d");
        this.typeVariableScope.declare(List.of(TokenType.CONST), "equalTo", new EqualTo());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 3) {
            throw ErrorHolder.invalidArgumentCount("init", 3, arguments.size());
        }

        BuiltinClass xClass = arguments.get(0);
        BuiltinClass yClass = arguments.get(1);
        BuiltinClass zClass = arguments.get(2);

        if (!xClass.instanceOf(new FloatType())) {
            throw ErrorHolder.argumentRequiresType(1, "init", new FloatType(), xClass.getType());
        }

        if (!yClass.instanceOf(new FloatType())) {
            throw ErrorHolder.argumentRequiresType(2, "init", new FloatType(), yClass.getType());
        }

        if (!zClass.instanceOf(new FloatType())) {
            throw ErrorHolder.argumentRequiresType(3, "init", new FloatType(), zClass.getType());
        }

        double x = xClass.toFloat();
        double y = yClass.toFloat();
        double z = zClass.toFloat();

        return new Vec3dInstance(new Vec3d(x, y, z));
    }

    private static class EqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("equalTo", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new Vec3dType())) {
                return new BooleanInstance(other.toVec3d().equals(this.boundClass.toVec3d()));
            }

            return new BooleanInstance(false);
        }
    }
}
