package com.revolvingmadness.sculk.language.builtins.classes.types;

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
        this.validateCall("init", arguments, List.of(FloatType.TYPE, FloatType.TYPE, FloatType.TYPE));

        double x = arguments.get(0).toFloat();
        double y = arguments.get(1).toFloat();
        double z = arguments.get(2).toFloat();

        return new Vec3dInstance(new Vec3d(x, y, z));
    }

}
