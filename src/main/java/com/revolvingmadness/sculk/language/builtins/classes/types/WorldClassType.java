package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.block.BlockInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.ListInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.entity.ServerPlayerEntityInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.BlockPosClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.BooleanClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.FloatClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.particle.ParticleClassType;
import net.minecraft.block.Block;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"resource", "unused"})
public class WorldClassType extends BuiltinClassType {
    public static final WorldClassType TYPE = new WorldClassType();

    private WorldClassType() {
        super("World");

        try {
            this.addMethod("breakBlock", List.of(BlockPosClassType.TYPE, BooleanClassType.TYPE));
            this.addMethod("canSetBlock", List.of(BlockPosClassType.TYPE));
            this.addMethod("getBlock", List.of(BlockPosClassType.TYPE));
            this.addGetterMethod("getPlayers", builtinClass -> {
                List<ServerPlayerEntity> players = builtinClass.toWorld().getPlayers();
                List<BuiltinClass> playersClasses = new ArrayList<>();

                players.forEach(serverPlayerEntity -> playersClasses.add(new ServerPlayerEntityInstance(serverPlayerEntity)));

                return new ListInstance(playersClasses);
            });
            this.addGetterMethod("getSeed", builtinClass -> new IntegerInstance(builtinClass.toWorld().getSeed()));
            this.addGetterMethod("getTime", builtinClass -> new IntegerInstance(builtinClass.toWorld().getTime()));
            this.addGetterMethod("getTimeOfDay", builtinClass -> new IntegerInstance(builtinClass.toWorld().getTimeOfDay()));
            this.addMethod("hasRain", List.of(BlockPosClassType.TYPE));
            this.addGetterMethod("isDay", builtinClass -> new BooleanInstance(builtinClass.toWorld().isDay()));
            this.addGetterMethod("isFlat", builtinClass -> new BooleanInstance(builtinClass.toWorld().isFlat()));
            this.addGetterMethod("isNight", builtinClass -> new BooleanInstance(builtinClass.toWorld().isNight()));
            this.addGetterMethod("isRaining", builtinClass -> new BooleanInstance(builtinClass.toWorld().isRaining()));
            this.addGetterMethod("isSleepingEnabled", builtinClass -> new BooleanInstance(builtinClass.toWorld().isSleepingEnabled()));
            this.addGetterMethod("isThundering", builtinClass -> new BooleanInstance(builtinClass.toWorld().isThundering()));
            this.addMethod("placeBlock", List.of(BlockPosClassType.TYPE, BlockClassType.TYPE));
            this.addMethod("setSpawnPos", List.of(BlockPosClassType.TYPE, FloatClassType.TYPE));
            this.addMethod("setTimeOfDay", List.of(IntegerClassType.TYPE));
            this.addMethod("spawnParticle", List.of(ParticleClassType.TYPE, FloatClassType.TYPE, FloatClassType.TYPE, FloatClassType.TYPE, IntegerClassType.TYPE, FloatClassType.TYPE, FloatClassType.TYPE, FloatClassType.TYPE, FloatClassType.TYPE));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static BuiltinClass breakBlock(BuiltinClass boundClass, BuiltinClass[] arguments) {
        BlockPos blockPos = arguments[0].toBlockPos();
        boolean dropItems = arguments[1].toBoolean();

        return new BooleanInstance(boundClass.toWorld().breakBlock(blockPos, dropItems));
    }

    public static BuiltinClass canSetBlock(BuiltinClass boundClass, BuiltinClass[] arguments) {
        BlockPos blockPos = arguments[0].toBlockPos();

        return new BooleanInstance(boundClass.toWorld().canSetBlock(blockPos));
    }

    public static BuiltinClass getBlock(BuiltinClass boundClass, BuiltinClass[] arguments) {
        BlockPos blockPos = arguments[0].toBlockPos();

        return new BlockInstance(boundClass.toWorld().getBlockState(blockPos).getBlock());
    }

    public static BuiltinClass hasRain(BuiltinClass boundClass, BuiltinClass[] arguments) {
        BlockPos blockPos = arguments[0].toBlockPos();

        return new BooleanInstance(boundClass.toWorld().hasRain(blockPos));
    }

    public static BuiltinClass placeBlock(BuiltinClass boundClass, BuiltinClass[] arguments) {
        BlockPos blockPos = arguments[0].toBlockPos();
        Block block = arguments[1].toBlock();

        return new BooleanInstance(boundClass.toWorld().setBlockState(blockPos, block.getDefaultState()));
    }

    public static BuiltinClass setSpawnPos(BuiltinClass boundClass, BuiltinClass[] arguments) {
        BlockPos blockPos = arguments[0].toBlockPos();
        double angle = arguments[1].toFloat();

        boundClass.toWorld().setSpawnPos(blockPos, (float) angle);

        return new NullInstance();
    }

    public static BuiltinClass setTimeOfDay(BuiltinClass boundClass, BuiltinClass[] arguments) {
        long timeOfDay = arguments[0].toInteger();

        boundClass.toWorld().setTimeOfDay(timeOfDay);

        return new NullInstance();
    }

    public static BuiltinClass spawnParticle(BuiltinClass boundClass, BuiltinClass[] arguments) {
        ServerWorld world = boundClass.toWorld();

        ParticleEffect particle = arguments[0].toParticle();

        double x = arguments[1].toFloat();
        double y = arguments[2].toFloat();
        double z = arguments[3].toFloat();

        int count = (int) arguments[4].toInteger();

        double deltaX = arguments[5].toFloat();
        double deltaY = arguments[6].toFloat();
        double deltaZ = arguments[7].toFloat();

        double speed = arguments[8].toFloat();

        world.spawnParticles(particle, x, y, z, count, deltaX, deltaY, deltaZ, speed);

        return new NullInstance();
    }
}
