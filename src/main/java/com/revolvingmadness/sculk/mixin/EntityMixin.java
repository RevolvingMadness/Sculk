package com.revolvingmadness.sculk.mixin;

import com.revolvingmadness.sculk.accessors.EntityAccessor;
import com.revolvingmadness.sculk.language.errors.KeyError;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityAccessor {
    @Unique
    public NbtCompound customData = new NbtCompound();

    @Inject(at = @At("HEAD"), method = "readNbt")
    public void injectReadNbt(NbtCompound nbt, CallbackInfo ci) {
        try {
            this.customData = nbt.getCompound("custom_data");
        } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.create(throwable, "Loading entity NBT");
            CrashReportSection crashReportSection = crashReport.addElement("Entity being loaded");
            this.populateCrashReport(crashReportSection);
            throw new CrashException(crashReport);
        }
    }

    @Inject(at = @At("HEAD"), method = "writeNbt")
    public void injectWriteNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
        try {
            nbt.put("custom_data", this.customData);
        } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.create(throwable, "Loading entity NBT");
            CrashReportSection crashReportSection = crashReport.addElement("Entity being loaded");
            this.populateCrashReport(crashReportSection);
            throw new CrashException(crashReport);
        }
    }

    @Shadow
    public abstract void populateCrashReport(CrashReportSection section);

    @Override
    public void sculk$deleteCustomData(String key) {
        this.customData.remove(key);
    }

    @Override
    public NbtElement sculk$readCustomData(String key) {
        if (!this.customData.contains(key)) {
            throw new KeyError("Entity has no data '" + key + "'");
        }

        return this.customData.get(key);
    }

    @Override
    public void sculk$writeCustomData(String key, NbtElement value) {
        this.customData.put(key, value);
    }
}
