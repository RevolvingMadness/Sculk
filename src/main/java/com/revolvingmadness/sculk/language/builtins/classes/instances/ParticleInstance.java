package com.revolvingmadness.sculk.language.builtins.classes.instances;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.particle.ParticleClassType;
import net.minecraft.particle.ParticleEffect;

import java.util.Objects;

public class ParticleInstance extends BuiltinClass {
    public final ParticleEffect particleType;

    public ParticleInstance(ParticleEffect particleType) {
        super(ParticleClassType.TYPE);

        this.particleType = particleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        ParticleInstance that = (ParticleInstance) o;
        return Objects.equals(particleType, that.particleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(particleType);
    }

    @Override
    public ParticleEffect toParticle() {
        return this.particleType;
    }
}
