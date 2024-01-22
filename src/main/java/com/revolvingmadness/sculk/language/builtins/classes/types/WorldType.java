package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
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

public class WorldType extends BuiltinType {
    public WorldType() {
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
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setBlock", new SetBlock());
    }

    private static class CanSetBlock extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("canSetBlock", 1, arguments.size());
            }

            BuiltinClass blockPosClass = arguments.get(0);

            if (!blockPosClass.instanceOf(new BlockPosType())) {
                throw ErrorHolder.argumentRequiresType(1, "canSetBlock", new BlockPosType(), blockPosClass.getType());
            }

            BlockPos blockPos = blockPosClass.toBlockPos();

            return new BooleanInstance(this.boundClass.toWorld().canSetBlock(blockPos));
        }
    }

    private static class GetPlayers extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getPlayers", 0, arguments.size());
            }

            List<BuiltinClass> list = new ArrayList<>();

            this.boundClass.toWorld().getPlayers().forEach(player -> {
                list.add(new ServerPlayerEntityInstance(player));
            });

            return new ListInstance(list);
        }
    }

    private static class GetSeed extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getSeed", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toWorld().getSeed());
        }
    }

    private static class GetTime extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getTime", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toWorld().getTime());
        }
    }

    private static class GetTimeOfDay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getTimeOfDay", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toWorld().getTimeOfDay());
        }
    }

    private static class HasRain extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("hasRain", 1, arguments.size());
            }

            BuiltinClass blockPosClass = arguments.get(0);

            if (!blockPosClass.instanceOf(new BlockPosType())) {
                throw ErrorHolder.argumentRequiresType(1, "hasRain", new BlockPosType(), blockPosClass.getType());
            }

            BlockPos blockPos = blockPosClass.toBlockPos();

            return new BooleanInstance(this.boundClass.toWorld().hasRain(blockPos));
        }
    }

    private static class IsDay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isDay", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toWorld().isDay());
        }
    }

    private static class IsFlat extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isFlat", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toWorld().isFlat());
        }
    }

    private static class IsNight extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isNight", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toWorld().isNight());
        }
    }

    private static class IsRaining extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isRaining", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toWorld().isRaining());
        }
    }

    private static class IsSleepingEnabled extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isSleepingEnabled", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toWorld().isSleepingEnabled());
        }
    }

    private static class IsThundering extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isThundering", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toWorld().isThundering());
        }
    }

    private static class SetBlock extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 2) {
                throw ErrorHolder.invalidArgumentCount("setBlock", 2, arguments.size());
            }

            BuiltinClass blockPosClass = arguments.get(0);

            if (!blockPosClass.instanceOf(new BlockPosType())) {
                throw ErrorHolder.argumentRequiresType(1, "setBlock", new BlockPosType(), blockPosClass.getType());
            }

            BuiltinClass blockClass = arguments.get(1);

            if (!blockClass.instanceOf(new BlockType())) {
                throw ErrorHolder.argumentRequiresType(2, "setBlock", new BlockType(), blockClass.getType());
            }

            BlockPos blockPos = blockPosClass.toBlockPos();
            Block block = blockClass.toBlock();

            return new BooleanInstance(this.boundClass.toWorld().setBlockState(blockPos, block.getDefaultState()));
        }
    }

    private static class SetSpawnPos extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 2) {
                throw ErrorHolder.invalidArgumentCount("setSpawnPos", 2, arguments.size());
            }

            BuiltinClass blockPosClass = arguments.get(0);

            if (!blockPosClass.instanceOf(new BlockPosType())) {
                throw ErrorHolder.argumentRequiresType(1, "setSpawnPos", new BlockPosType(), blockPosClass.getType());
            }

            BuiltinClass angleClass = arguments.get(1);

            if (!angleClass.instanceOf(new FloatType())) {
                throw ErrorHolder.argumentRequiresType(2, "setSpawnPos", new FloatType(), angleClass.getType());
            }

            BlockPos blockPos = blockPosClass.toBlockPos();
            double angle = angleClass.toFloat();

            this.boundClass.toWorld().setSpawnPos(blockPos, (float) angle);

            return new NullInstance();
        }
    }

    private static class SetTimeOfDay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setTimeOfDay", 1, arguments.size());
            }

            BuiltinClass timeOfDayClass = arguments.get(0);

            if (!timeOfDayClass.instanceOf(new BlockPosType())) {
                throw ErrorHolder.argumentRequiresType(1, "setTimeOfDay", new BlockPosType(), timeOfDayClass.getType());
            }

            long timeOfDay = timeOfDayClass.toInteger();

            this.boundClass.toWorld().setTimeOfDay(timeOfDay);

            return new NullInstance();
        }
    }
}
