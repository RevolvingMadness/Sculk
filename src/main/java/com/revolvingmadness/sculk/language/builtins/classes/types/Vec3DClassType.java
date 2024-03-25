package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.Vec3dInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.FloatClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class Vec3DClassType extends BuiltinClassType {
    public static final Vec3DClassType TYPE = new Vec3DClassType();

    private Vec3DClassType() {
        super("Vec3d");
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(FloatClassType.TYPE, FloatClassType.TYPE, FloatClassType.TYPE));

        double x = arguments.get(0).toFloat();
        double y = arguments.get(1).toFloat();
        double z = arguments.get(2).toFloat();

        return new Vec3dInstance(new Vec3d(x, y, z));
    }
}
