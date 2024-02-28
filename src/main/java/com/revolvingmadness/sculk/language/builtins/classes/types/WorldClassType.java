package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
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
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("resource")
public class WorldClassType extends BuiltinClassType {
    public static final WorldClassType TYPE = new WorldClassType();

    private WorldClassType() {
        super("World");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "canSetBlock", new CanSetBlock());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getTime", new GetTime());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getTimeOfDay", new GetTimeOfDay());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "hasRain", new HasRain());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isDay", new IsDay());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isNight", new IsNight());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isRaining", new IsRaining());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isThundering", new IsThundering());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getPlayers", new GetPlayers());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isFlat", new IsFlat());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isSleepingEnabled", new IsSleepingEnabled());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getSeed", new GetSeed());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setSpawnPos", new SetSpawnPos());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setTimeOfDay", new SetTimeOfDay());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "placeBlock", new PlaceBlock());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "breakBlock", new BreakBlock());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getBlock", new GetBlock());
    }

    private static class BreakBlock extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("breakBlock", arguments, List.of(BlockPosClassType.TYPE, BooleanClassType.TYPE));

            BlockPos blockPos = arguments.get(0).toBlockPos();
            boolean dropItems = arguments.get(1).toBoolean();

            return new BooleanInstance(this.boundClass.toWorld().breakBlock(blockPos, dropItems));
        }
    }

    private static class CanSetBlock extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("canSetBlock", arguments, List.of(BlockPosClassType.TYPE));

            BlockPos blockPos = arguments.get(0).toBlockPos();

            return new BooleanInstance(this.boundClass.toWorld().canSetBlock(blockPos));
        }
    }

    private static class GetBlock extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getBlock", arguments, List.of(BlockPosClassType.TYPE));

            BlockPos blockPos = arguments.get(0).toBlockPos();

            return new BlockInstance(this.boundClass.toWorld().getBlockState(blockPos).getBlock());
        }
    }

    private static class GetPlayers extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getPlayers", arguments);

            List<BuiltinClass> list = new ArrayList<>();

            this.boundClass.toWorld().getPlayers().forEach(player -> list.add(new ServerPlayerEntityInstance(player)));

            return new ListInstance(list);
        }
    }

    private static class GetSeed extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getSeed", arguments);

            return new IntegerInstance(this.boundClass.toWorld().getSeed());
        }
    }

    private static class GetTime extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getTime", arguments);

            return new IntegerInstance(this.boundClass.toWorld().getTime());
        }
    }

    private static class GetTimeOfDay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getTimeOfDay", arguments);

            return new IntegerInstance(this.boundClass.toWorld().getTimeOfDay());
        }
    }

    private static class HasRain extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("hasRain", arguments, List.of(BlockPosClassType.TYPE));

            BlockPos blockPos = arguments.get(0).toBlockPos();

            return new BooleanInstance(this.boundClass.toWorld().hasRain(blockPos));
        }
    }

    private static class IsDay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isDay", arguments);

            return new BooleanInstance(this.boundClass.toWorld().isDay());
        }
    }

    private static class IsFlat extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isFlat", arguments);

            return new BooleanInstance(this.boundClass.toWorld().isFlat());
        }
    }

    private static class IsNight extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isNight", arguments);

            return new BooleanInstance(this.boundClass.toWorld().isNight());
        }
    }

    private static class IsRaining extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isRaining", arguments);

            return new BooleanInstance(this.boundClass.toWorld().isRaining());
        }
    }

    private static class IsSleepingEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isSleepingEnabled", arguments);

            return new BooleanInstance(this.boundClass.toWorld().isSleepingEnabled());
        }
    }

    private static class IsThundering extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isThundering", arguments);

            return new BooleanInstance(this.boundClass.toWorld().isThundering());
        }
    }

    private static class PlaceBlock extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("isThundering", arguments, List.of(BlockPosClassType.TYPE, BlockClassType.TYPE));

            BlockPos blockPos = arguments.get(0).toBlockPos();
            Block block = arguments.get(1).toBlock();

            return new BooleanInstance(this.boundClass.toWorld().setBlockState(blockPos, block.getDefaultState()));
        }
    }

    private static class SetSpawnPos extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setSpawnPos", arguments, List.of(BlockPosClassType.TYPE, FloatClassType.TYPE));

            BlockPos blockPos = arguments.get(0).toBlockPos();
            double angle = arguments.get(1).toFloat();

            this.boundClass.toWorld().setSpawnPos(blockPos, (float) angle);

            return new NullInstance();
        }
    }

    private static class SetTimeOfDay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setTimeOfDay", arguments, List.of(IntegerClassType.TYPE));

            long timeOfDay = arguments.get(0).toInteger();

            this.boundClass.toWorld().setTimeOfDay(timeOfDay);

            return new NullInstance();
        }
    }
}
