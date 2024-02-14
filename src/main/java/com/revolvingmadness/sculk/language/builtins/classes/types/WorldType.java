package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.*;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("resource")
public class WorldType extends BuiltinType {
    public static final WorldType TYPE = new WorldType();

    private WorldType() {
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
            this.validate("breakBlock", arguments, List.of(BlockPosType.TYPE, BooleanType.TYPE));

            BlockPos blockPos = arguments.get(0).toBlockPos();
            boolean dropItems = arguments.get(1).toBoolean();

            return new BooleanInstance(this.boundClass.toWorld().breakBlock(blockPos, dropItems));
        }
    }

    private static class CanSetBlock extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("canSetBlock", arguments, List.of(BlockPosType.TYPE));

            BlockPos blockPos = arguments.get(0).toBlockPos();

            return new BooleanInstance(this.boundClass.toWorld().canSetBlock(blockPos));
        }
    }

    private static class GetBlock extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getBlock", arguments, List.of(BlockPosType.TYPE));

            BlockPos blockPos = arguments.get(0).toBlockPos();

            return new BlockInstance(this.boundClass.toWorld().getBlockState(blockPos).getBlock());
        }
    }

    private static class GetPlayers extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getPlayers", arguments);

            List<BuiltinClass> list = new ArrayList<>();

            this.boundClass.toWorld().getPlayers().forEach(player -> list.add(new ServerPlayerEntityInstance(player)));

            return new ListInstance(list);
        }
    }

    private static class GetSeed extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getSeed", arguments);

            return new IntegerInstance(this.boundClass.toWorld().getSeed());
        }
    }

    private static class GetTime extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getTime", arguments);

            return new IntegerInstance(this.boundClass.toWorld().getTime());
        }
    }

    private static class GetTimeOfDay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getTimeOfDay", arguments);

            return new IntegerInstance(this.boundClass.toWorld().getTimeOfDay());
        }
    }

    private static class HasRain extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("hasRain", arguments, List.of(BlockPosType.TYPE));

            BlockPos blockPos = arguments.get(0).toBlockPos();

            return new BooleanInstance(this.boundClass.toWorld().hasRain(blockPos));
        }
    }

    private static class IsDay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isDay", arguments);

            return new BooleanInstance(this.boundClass.toWorld().isDay());
        }
    }

    private static class IsFlat extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isFlat", arguments);

            return new BooleanInstance(this.boundClass.toWorld().isFlat());
        }
    }

    private static class IsNight extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isNight", arguments);

            return new BooleanInstance(this.boundClass.toWorld().isNight());
        }
    }

    private static class IsRaining extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isRaining", arguments);

            return new BooleanInstance(this.boundClass.toWorld().isRaining());
        }
    }

    private static class IsSleepingEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isSleepingEnabled", arguments);

            return new BooleanInstance(this.boundClass.toWorld().isSleepingEnabled());
        }
    }

    private static class IsThundering extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isThundering", arguments);

            return new BooleanInstance(this.boundClass.toWorld().isThundering());
        }
    }

    private static class PlaceBlock extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("isThundering", arguments, List.of(BlockPosType.TYPE, BlockType.TYPE));

            BlockPos blockPos = arguments.get(0).toBlockPos();
            Block block = arguments.get(1).toBlock();

            return new BooleanInstance(this.boundClass.toWorld().setBlockState(blockPos, block.getDefaultState()));
        }
    }

    private static class SetSpawnPos extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setSpawnPos", arguments, List.of(BlockPosType.TYPE, FloatType.TYPE));

            BlockPos blockPos = arguments.get(0).toBlockPos();
            double angle = arguments.get(1).toFloat();

            this.boundClass.toWorld().setSpawnPos(blockPos, (float) angle);

            return new NullInstance();
        }
    }

    private static class SetTimeOfDay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setTimeOfDay", arguments, List.of(IntegerType.TYPE));

            long timeOfDay = arguments.get(0).toInteger();

            this.boundClass.toWorld().setTimeOfDay(timeOfDay);

            return new NullInstance();
        }
    }
}
