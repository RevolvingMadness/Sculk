package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.Vec3dInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class Vec3dType extends BuiltinType {
    public static final Vec3dType TYPE = new Vec3dType();

    private Vec3dType() {
        super("Vec3d");
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 3) {
            throw ErrorHolder.invalidArgumentCount("init", 3, arguments.size());
        }

        BuiltinClass xClass = arguments.get(0);
        BuiltinClass yClass = arguments.get(1);
        BuiltinClass zClass = arguments.get(2);

        if (!xClass.instanceOf(FloatType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(1, "init", FloatType.TYPE, xClass.getType());
        }

        if (!yClass.instanceOf(FloatType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(2, "init", FloatType.TYPE, yClass.getType());
        }

        if (!zClass.instanceOf(FloatType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(3, "init", FloatType.TYPE, zClass.getType());
        }

        double x = xClass.toFloat();
        double y = yClass.toFloat();
        double z = zClass.toFloat();

        return new Vec3dInstance(new Vec3d(x, y, z));
    }

}
