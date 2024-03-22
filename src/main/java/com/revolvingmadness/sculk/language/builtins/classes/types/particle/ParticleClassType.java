package com.revolvingmadness.sculk.language.builtins.classes.types.particle;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class ParticleClassType extends BuiltinClassType {
    public static final ParticleClassType TYPE = new ParticleClassType();

    private ParticleClassType() {
        super("Particle");
    }
}
