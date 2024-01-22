package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.gamerules.SculkGamerules;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.IntegerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.world.GameRules;

import java.util.List;

public class GameRulesType extends BuiltinType {
    public GameRulesType() {
        super("GameRules");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoFireTick", new GetDoFireTick());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoMobGriefing", new GetDoMobGriefing());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getKeepInventory", new GetKeepInventory());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoMobSpawning", new GetDoMobSpawning());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoMobLoot", new GetDoMobLoot());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoTileDrops", new GetDoTileDrops());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoEntityDrops", new GetDoEntityDrops());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getCommandBlockOutput", new GetCommandBlockOutput());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getNaturalRegeneration", new GetNaturalRegeneration());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoDaylightCycle", new GetDoDaylightCycle());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getLogAdminCommands", new GetLogAdminCommands());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getShowDeathMessages", new GetShowDeathMessages());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getRandomTickSpeed", new GetRandomTickSpeed());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getSendCommandFeedback", new GetSendCommandFeedback());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getReducedDebugInfo", new GetReducedDebugInfo());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getSpectatorsGenerateChunks", new GetSpectatorsGenerateChunks());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getSpawnRadius", new GetSpawnRadius());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDisableElytraMovementCheck", new GetDisableElytraMovementCheck());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMaxEntityCramming", new GetMaxEntityCramming());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoWeatherCycle", new GetDoWeatherCycle());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoLimitedCrafting", new GetDoLimitedCrafting());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMaxCommandChainLength", new GetMaxCommandChainLength());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getCommandModificationBlockLimit", new GetCommandModificationBlockLimit());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getAnnounceAdvancements", new GetAnnounceAdvancements());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDisableRaids", new GetDisableRaids());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoInsomnia", new GetDoInsomnia());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoImmediateRespawn", new GetDoImmediateRespawn());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDrowningDamage", new GetDrowningDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getFallDamage", new GetFallDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getFireDamage", new GetFireDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getFreezeDamage", new GetFreezeDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoPatrolSpawning", new GetDoPatrolSpawning());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoTraderSpawning", new GetDoTraderSpawning());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoWardenSpawning", new GetDoWardenSpawning());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getForgiveDeadPlayers", new GetForgiveDeadPlayers());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getUniversalAnger", new GetUniversalAnger());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getPlayersSleepingPercentage", new GetPlayersSleepingPercentage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getBlockExplosionDropDecay", new GetBlockExplosionDropDecay());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMobExplosionDropDecay", new GetMobExplosionDropDecay());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getTntExplosionDropDecay", new GetTntExplosionDropDecay());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getSnowAccumulationHeight", new GetSnowAccumulationHeight());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getWaterSourceConversion", new GetWaterSourceConversion());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getLavaSourceConversion", new GetLavaSourceConversion());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getGlobalSoundEvents", new GetGlobalSoundEvents());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDoVinesSpread", new GetDoVinesSpread());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getEnderPearlsVanishOnDeath", new GetEnderPearlsVanishOnDeath());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMaxArgumentCount", new GetMaxArgumentCount());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMaxLoops", new GetMaxLoops());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoFireTick", new SetDoFireTick());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoMobGriefing", new SetDoMobGriefing());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setKeepInventory", new SetKeepInventory());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoMobSpawning", new SetDoMobSpawning());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoMobLoot", new SetDoMobLoot());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoTileDrops", new SetDoTileDrops());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoEntityDrops", new SetDoEntityDrops());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setCommandBlockOutput", new SetCommandBlockOutput());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setNaturalRegeneration", new SetNaturalRegeneration());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoDaylightCycle", new SetDoDaylightCycle());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setLogAdminCommands", new SetLogAdminCommands());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setShowDeathMessages", new SetShowDeathMessages());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setRandomTickSpeed", new SetRandomTickSpeed());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setSendCommandFeedback", new SetSendCommandFeedback());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setReducedDebugInfo", new SetReducedDebugInfo());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setSpectatorsGenerateChunks", new SetSpectatorsGenerateChunks());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setSpawnRadius", new SetSpawnRadius());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDisableElytraMovementCheck", new SetDisableElytraMovementCheck());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setMaxEntityCramming", new SetMaxEntityCramming());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoWeatherCycle", new SetDoWeatherCycle());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoLimitedCrafting", new SetDoLimitedCrafting());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setMaxCommandChainLength", new SetMaxCommandChainLength());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setCommandModificationBlockLimit", new SetCommandModificationBlockLimit());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setAnnounceAdvancements", new SetAnnounceAdvancements());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDisableRaids", new SetDisableRaids());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoInsomnia", new SetDoInsomnia());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoImmediateRespawn", new SetDoImmediateRespawn());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDrowningDamage", new SetDrowningDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setFallDamage", new SetFallDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setFireDamage", new SetFireDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setFreezeDamage", new SetFreezeDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoPatrolSpawning", new SetDoPatrolSpawning());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoTraderSpawning", new SetDoTraderSpawning());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoWardenSpawning", new SetDoWardenSpawning());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setForgiveDeadPlayers", new SetForgiveDeadPlayers());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setUniversalAnger", new SetUniversalAnger());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setPlayersSleepingPercentage", new SetPlayersSleepingPercentage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setBlockExplosionDropDecay", new SetBlockExplosionDropDecay());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setMobExplosionDropDecay", new SetMobExplosionDropDecay());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setTntExplosionDropDecay", new SetTntExplosionDropDecay());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setSnowAccumulationHeight", new SetSnowAccumulationHeight());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setWaterSourceConversion", new SetWaterSourceConversion());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setLavaSourceConversion", new SetLavaSourceConversion());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setGlobalSoundEvents", new SetGlobalSoundEvents());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDoVinesSpread", new SetDoVinesSpread());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setEnderPearlsVanishOnDeath", new SetEnderPearlsVanishOnDeath());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setMaxArgumentCount", new SetMaxArgumentCount());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setMaxLoops", new SetMaxLoops());
    }

    private static class GetAnnounceAdvancements extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getAnnounceAdvancements", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.ANNOUNCE_ADVANCEMENTS));
        }
    }

    private static class GetBlockExplosionDropDecay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getBlockExplosionDropDecay", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.BLOCK_EXPLOSION_DROP_DECAY));
        }
    }

    private static class GetCommandBlockOutput extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getCommandBlockOutput", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.COMMAND_BLOCK_OUTPUT));
        }
    }

    private static class GetCommandModificationBlockLimit extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getCommandModificationBlockLimit", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.COMMAND_MODIFICATION_BLOCK_LIMIT));
        }
    }

    private static class GetDisableElytraMovementCheck extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDisableElytraMovementCheck", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK));
        }
    }

    private static class GetDisableRaids extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDisableRaids", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DISABLE_RAIDS));
        }
    }

    private static class GetDoDaylightCycle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoDaylightCycle", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE));
        }
    }

    private static class GetDoEntityDrops extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoEntityDrops", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_ENTITY_DROPS));
        }
    }

    private static class GetDoFireTick extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoFireTick", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_FIRE_TICK));
        }
    }

    private static class GetDoImmediateRespawn extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoImmediateRespawn", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_IMMEDIATE_RESPAWN));
        }
    }

    private static class GetDoInsomnia extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoInsomnia", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_INSOMNIA));
        }
    }

    private static class GetDoLimitedCrafting extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoLimitedCrafting", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_LIMITED_CRAFTING));
        }
    }

    private static class GetDoMobGriefing extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoMobGriefing", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_MOB_GRIEFING));
        }
    }

    private static class GetDoMobLoot extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoMobLoot", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_MOB_LOOT));
        }
    }

    private static class GetDoMobSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoMobSpawning", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_MOB_SPAWNING));
        }
    }

    private static class GetDoPatrolSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoPatrolSpawning", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_PATROL_SPAWNING));
        }
    }

    private static class GetDoTileDrops extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoTileDrops", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_TILE_DROPS));
        }
    }

    private static class GetDoTraderSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoTraderSpawning", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_TRADER_SPAWNING));
        }
    }

    private static class GetDoVinesSpread extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoVinesSpread", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_VINES_SPREAD));
        }
    }

    private static class GetDoWardenSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoWardenSpawning", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_WARDEN_SPAWNING));
        }
    }

    private static class GetDoWeatherCycle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoWeatherCycle", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_WEATHER_CYCLE));
        }
    }

    private static class GetDrowningDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDrowningDamage", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DROWNING_DAMAGE));
        }
    }

    private static class GetEnderPearlsVanishOnDeath extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getEnderPearlsVanishOnDeath", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.ENDER_PEARLS_VANISH_ON_DEATH));
        }
    }

    private static class GetFallDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getFallDamage", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.FALL_DAMAGE));
        }
    }

    private static class GetFireDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getFireDamage", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.FIRE_DAMAGE));
        }
    }

    private static class GetForgiveDeadPlayers extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getForgiveDeadPlayers", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.FORGIVE_DEAD_PLAYERS));
        }
    }

    private static class GetFreezeDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getFreezeDamage", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.FREEZE_DAMAGE));
        }
    }

    private static class GetGlobalSoundEvents extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getGlobalSoundEvents", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.GLOBAL_SOUND_EVENTS));
        }
    }

    private static class GetKeepInventory extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getKeepInventory", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.KEEP_INVENTORY));
        }
    }

    private static class GetLavaSourceConversion extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getLavaSourceConversion", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.LAVA_SOURCE_CONVERSION));
        }
    }

    private static class GetLogAdminCommands extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getLogAdminCommands", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.LOG_ADMIN_COMMANDS));
        }
    }

    private static class GetMaxArgumentCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxArgumentCount", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toGameRules().getInt(SculkGamerules.MAX_ARGUMENTS));
        }
    }

    private static class GetMaxCommandChainLength extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxCommandChainLength", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.MAX_COMMAND_CHAIN_LENGTH));
        }
    }

    private static class GetMaxEntityCramming extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxEntityCramming", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.MAX_ENTITY_CRAMMING));
        }
    }

    private static class GetMaxLoops extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxLoops", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toGameRules().getInt(SculkGamerules.MAX_LOOPS));
        }
    }

    private static class GetMobExplosionDropDecay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMobExplosionDropDecay", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.MOB_EXPLOSION_DROP_DECAY));
        }
    }

    private static class GetNaturalRegeneration extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getNaturalRegeneration", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.NATURAL_REGENERATION));
        }
    }

    private static class GetPlayersSleepingPercentage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getPlayersSleepingPercentage", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.PLAYERS_SLEEPING_PERCENTAGE));
        }
    }

    private static class GetRandomTickSpeed extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getRandomTickSpeed", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.RANDOM_TICK_SPEED));
        }
    }

    private static class GetReducedDebugInfo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getReducedDebugInfo", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.REDUCED_DEBUG_INFO));
        }
    }

    private static class GetSendCommandFeedback extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getSendCommandFeedback", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.SEND_COMMAND_FEEDBACK));
        }
    }

    private static class GetShowDeathMessages extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getShowDeathMessages", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.SHOW_DEATH_MESSAGES));
        }
    }

    private static class GetSnowAccumulationHeight extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getSnowAccumulationHeight", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.SNOW_ACCUMULATION_HEIGHT));
        }
    }

    private static class GetSpawnRadius extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getSpawnRadius", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.SPAWN_RADIUS));
        }
    }

    private static class GetSpectatorsGenerateChunks extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getSpectatorsGenerateChunks", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.SPECTATORS_GENERATE_CHUNKS));
        }
    }

    private static class GetTntExplosionDropDecay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getTntExplosionDropDecay", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.TNT_EXPLOSION_DROP_DECAY));
        }
    }

    private static class GetUniversalAnger extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getUniversalAnger", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.UNIVERSAL_ANGER));
        }
    }

    private static class GetWaterSourceConversion extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getWaterSourceConversion", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.WATER_SOURCE_CONVERSION));
        }
    }

    private static class SetAnnounceAdvancements extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setAnnounceAdvancements", 1, arguments.size());
            }

            BuiltinClass announceAdvancements = arguments.get(0);

            if (!announceAdvancements.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setAnnounceAdvancements", new BooleanType(), announceAdvancements.getType());
            }

            this.boundClass.toGameRules().get(GameRules.ANNOUNCE_ADVANCEMENTS).set(announceAdvancements.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetBlockExplosionDropDecay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setBlockExplosionDropDecay", 1, arguments.size());
            }

            BuiltinClass blockExplosionDropDecay = arguments.get(0);

            if (!blockExplosionDropDecay.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setBlockExplosionDropDecay", new BooleanType(), blockExplosionDropDecay.getType());
            }

            this.boundClass.toGameRules().get(GameRules.BLOCK_EXPLOSION_DROP_DECAY).set(blockExplosionDropDecay.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetCommandBlockOutput extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setCommandBlockOutput", 1, arguments.size());
            }

            BuiltinClass commandBlockOutput = arguments.get(0);

            if (!commandBlockOutput.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setCommandBlockOutput", new BooleanType(), commandBlockOutput.getType());
            }

            this.boundClass.toGameRules().get(GameRules.COMMAND_BLOCK_OUTPUT).set(commandBlockOutput.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetCommandModificationBlockLimit extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setCommandModificationBlockLimit", 1, arguments.size());
            }

            BuiltinClass commandModificationBlockLimit = arguments.get(0);

            if (!commandModificationBlockLimit.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "setCommandModificationBlockLimit", new IntegerType(), commandModificationBlockLimit.getType());
            }

            this.boundClass.toGameRules().get(GameRules.COMMAND_MODIFICATION_BLOCK_LIMIT).set((int) commandModificationBlockLimit.toInteger(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDisableElytraMovementCheck extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDisableElytraMovementCheck", 1, arguments.size());
            }

            BuiltinClass disableElytraMovementCheck = arguments.get(0);

            if (!disableElytraMovementCheck.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDisableElytraMovementCheck", new BooleanType(), disableElytraMovementCheck.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK).set(disableElytraMovementCheck.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDisableRaids extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDisableRaids", 1, arguments.size());
            }

            BuiltinClass disableRaids = arguments.get(0);

            if (!disableRaids.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDisableRaids", new BooleanType(), disableRaids.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DISABLE_RAIDS).set(disableRaids.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoDaylightCycle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoDaylightCycle", 1, arguments.size());
            }

            BuiltinClass doDaylightCycle = arguments.get(0);

            if (!doDaylightCycle.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoDaylightCycle", new BooleanType(), doDaylightCycle.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(doDaylightCycle.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoEntityDrops extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoEntityDrops", 1, arguments.size());
            }

            BuiltinClass doEntityDrops = arguments.get(0);

            if (!doEntityDrops.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoEntityDrops", new BooleanType(), doEntityDrops.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_ENTITY_DROPS).set(doEntityDrops.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoFireTick extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoFireTick", 1, arguments.size());
            }

            BuiltinClass doFireTick = arguments.get(0);

            if (!doFireTick.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoFireTick", new BooleanType(), doFireTick.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_FIRE_TICK).set(doFireTick.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoImmediateRespawn extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoImmediateRespawn", 1, arguments.size());
            }

            BuiltinClass immediateRespawn = arguments.get(0);

            if (!immediateRespawn.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoImmediateRespawn", new BooleanType(), immediateRespawn.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_IMMEDIATE_RESPAWN).set(immediateRespawn.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoInsomnia extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoInsomnia", 1, arguments.size());
            }

            BuiltinClass doInsomnia = arguments.get(0);

            if (!doInsomnia.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoInsomnia", new BooleanType(), doInsomnia.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_INSOMNIA).set(doInsomnia.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoLimitedCrafting extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoLimitedCrafting", 1, arguments.size());
            }

            BuiltinClass doLimitedCrafting = arguments.get(0);

            if (!doLimitedCrafting.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoLimitedCrafting", new BooleanType(), doLimitedCrafting.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_LIMITED_CRAFTING).set(doLimitedCrafting.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoMobGriefing extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoMobGriefing", 1, arguments.size());
            }

            BuiltinClass doMobGriefing = arguments.get(0);

            if (!doMobGriefing.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoMobGriefing", new BooleanType(), doMobGriefing.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_MOB_GRIEFING).set(doMobGriefing.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoMobLoot extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoMobLoot", 1, arguments.size());
            }

            BuiltinClass doMobLoot = arguments.get(0);

            if (!doMobLoot.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoMobLoot", new BooleanType(), doMobLoot.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_MOB_LOOT).set(doMobLoot.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoMobSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoMobSpawning", 1, arguments.size());
            }

            BuiltinClass doMobSpawning = arguments.get(0);

            if (!doMobSpawning.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoMobSpawning", new BooleanType(), doMobSpawning.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_MOB_SPAWNING).set(doMobSpawning.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoPatrolSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoPatrolSpawning", 1, arguments.size());
            }

            BuiltinClass doPatrolSpawning = arguments.get(0);

            if (!doPatrolSpawning.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoPatrolSpawning", new BooleanType(), doPatrolSpawning.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_PATROL_SPAWNING).set(doPatrolSpawning.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoTileDrops extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoTileDrops", 1, arguments.size());
            }

            BuiltinClass doTileDrops = arguments.get(0);

            if (!doTileDrops.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoTileDrops", new BooleanType(), doTileDrops.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_TILE_DROPS).set(doTileDrops.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoTraderSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoTraderSpawning", 1, arguments.size());
            }

            BuiltinClass doTraderSpawning = arguments.get(0);

            if (!doTraderSpawning.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoTraderSpawning", new BooleanType(), doTraderSpawning.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_TRADER_SPAWNING).set(doTraderSpawning.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoVinesSpread extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoVinesSpread", 1, arguments.size());
            }

            BuiltinClass doVinesSpread = arguments.get(0);

            if (!doVinesSpread.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoVinesSpread", new BooleanType(), doVinesSpread.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_VINES_SPREAD).set(doVinesSpread.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoWardenSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoWardenSpawning", 1, arguments.size());
            }

            BuiltinClass doWardenSpawning = arguments.get(0);

            if (!doWardenSpawning.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoWardenSpawning", new BooleanType(), doWardenSpawning.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_WARDEN_SPAWNING).set(doWardenSpawning.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoWeatherCycle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoWeatherCycle", 1, arguments.size());
            }

            BuiltinClass doWeatherCycle = arguments.get(0);

            if (!doWeatherCycle.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDoWeatherCycle", new BooleanType(), doWeatherCycle.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DO_WEATHER_CYCLE).set(doWeatherCycle.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDrowningDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDrowningDamage", 1, arguments.size());
            }

            BuiltinClass drowningDamage = arguments.get(0);

            if (!drowningDamage.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setDrowningDamage", new BooleanType(), drowningDamage.getType());
            }

            this.boundClass.toGameRules().get(GameRules.DROWNING_DAMAGE).set(drowningDamage.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetEnderPearlsVanishOnDeath extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setEnderPearlsVanishOnDeath", 1, arguments.size());
            }

            BuiltinClass enderPearlsVanishOnDeath = arguments.get(0);

            if (!enderPearlsVanishOnDeath.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setEnderPearlsVanishOnDeath", new BooleanType(), enderPearlsVanishOnDeath.getType());
            }

            this.boundClass.toGameRules().get(GameRules.ENDER_PEARLS_VANISH_ON_DEATH).set(enderPearlsVanishOnDeath.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetFallDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setFallDamage", 1, arguments.size());
            }

            BuiltinClass fallDamage = arguments.get(0);

            if (!fallDamage.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setFallDamage", new BooleanType(), fallDamage.getType());
            }

            this.boundClass.toGameRules().get(GameRules.FALL_DAMAGE).set(fallDamage.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetFireDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setFireDamage", 1, arguments.size());
            }

            BuiltinClass fireDamage = arguments.get(0);

            if (!fireDamage.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setFireDamage", new BooleanType(), fireDamage.getType());
            }

            this.boundClass.toGameRules().get(GameRules.FIRE_DAMAGE).set(fireDamage.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetForgiveDeadPlayers extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setForgiveDeadPlayers", 1, arguments.size());
            }

            BuiltinClass forgiveDeadPlayers = arguments.get(0);

            if (!forgiveDeadPlayers.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setForgiveDeadPlayers", new BooleanType(), forgiveDeadPlayers.getType());
            }

            this.boundClass.toGameRules().get(GameRules.FORGIVE_DEAD_PLAYERS).set(forgiveDeadPlayers.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetFreezeDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setFreezeDamage", 1, arguments.size());
            }

            BuiltinClass freezeDamage = arguments.get(0);

            if (!freezeDamage.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setFreezeDamage", new BooleanType(), freezeDamage.getType());
            }

            this.boundClass.toGameRules().get(GameRules.FREEZE_DAMAGE).set(freezeDamage.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetGlobalSoundEvents extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setGlobalSoundEvents", 1, arguments.size());
            }

            BuiltinClass globalSoundEvents = arguments.get(0);

            if (!globalSoundEvents.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setGlobalSoundEvents", new BooleanType(), globalSoundEvents.getType());
            }

            this.boundClass.toGameRules().get(GameRules.GLOBAL_SOUND_EVENTS).set(globalSoundEvents.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetKeepInventory extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setKeepInventory", 1, arguments.size());
            }

            BuiltinClass keepInventory = arguments.get(0);

            if (!keepInventory.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setKeepInventory", new BooleanType(), keepInventory.getType());
            }

            this.boundClass.toGameRules().get(GameRules.KEEP_INVENTORY).set(keepInventory.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetLavaSourceConversion extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setLavaSourceConversion", 1, arguments.size());
            }

            BuiltinClass lavaSourceConversion = arguments.get(0);

            if (!lavaSourceConversion.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setLavaSourceConversion", new BooleanType(), lavaSourceConversion.getType());
            }

            this.boundClass.toGameRules().get(GameRules.LAVA_SOURCE_CONVERSION).set(lavaSourceConversion.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetLogAdminCommands extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setLogAdminCommands", 1, arguments.size());
            }

            BuiltinClass logAdminCommands = arguments.get(0);

            if (!logAdminCommands.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setLogAdminCommands", new BooleanType(), logAdminCommands.getType());
            }

            this.boundClass.toGameRules().get(GameRules.LOG_ADMIN_COMMANDS).set(logAdminCommands.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetMaxArgumentCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setMaxArgumentCount", 1, arguments.size());
            }

            BuiltinClass maxArgumentCount = arguments.get(0);

            if (!maxArgumentCount.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "setMaxArgumentCount", new IntegerType(), maxArgumentCount.getType());
            }

            this.boundClass.toGameRules().get(SculkGamerules.MAX_ARGUMENTS).set((int) maxArgumentCount.toInteger(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetMaxCommandChainLength extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setMaxCommandChainLength", 1, arguments.size());
            }

            BuiltinClass maxCommandChainLength = arguments.get(0);

            if (!maxCommandChainLength.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "setMaxCommandChainLength", new IntegerType(), maxCommandChainLength.getType());
            }

            this.boundClass.toGameRules().get(GameRules.MAX_COMMAND_CHAIN_LENGTH).set((int) maxCommandChainLength.toInteger(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetMaxEntityCramming extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setMaxEntityCramming", 1, arguments.size());
            }

            BuiltinClass maxEntityCramming = arguments.get(0);

            if (!maxEntityCramming.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "setMaxEntityCramming", new IntegerType(), maxEntityCramming.getType());
            }

            this.boundClass.toGameRules().get(GameRules.MAX_ENTITY_CRAMMING).set((int) maxEntityCramming.toInteger(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetMaxLoops extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setMaxLoops", 1, arguments.size());
            }

            BuiltinClass maxLoops = arguments.get(0);

            if (!maxLoops.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "setMaxLoops", new IntegerType(), maxLoops.getType());
            }

            this.boundClass.toGameRules().get(SculkGamerules.MAX_LOOPS).set((int) maxLoops.toInteger(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetMobExplosionDropDecay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setMobExplosionDropDecay", 1, arguments.size());
            }

            BuiltinClass mobExplosionDropDecay = arguments.get(0);

            if (!mobExplosionDropDecay.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setMobExplosionDropDecay", new BooleanType(), mobExplosionDropDecay.getType());
            }

            this.boundClass.toGameRules().get(GameRules.MOB_EXPLOSION_DROP_DECAY).set(mobExplosionDropDecay.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetNaturalRegeneration extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setNaturalRegeneration", 1, arguments.size());
            }

            BuiltinClass naturalRegeneration = arguments.get(0);

            if (!naturalRegeneration.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setNaturalRegeneration", new BooleanType(), naturalRegeneration.getType());
            }

            this.boundClass.toGameRules().get(GameRules.NATURAL_REGENERATION).set(naturalRegeneration.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetPlayersSleepingPercentage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setPlayersSleepingPercentage", 1, arguments.size());
            }

            BuiltinClass playerSleepingPercentage = arguments.get(0);

            if (!playerSleepingPercentage.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "setPlayersSleepingPercentage", new IntegerType(), playerSleepingPercentage.getType());
            }

            this.boundClass.toGameRules().get(GameRules.PLAYERS_SLEEPING_PERCENTAGE).set((int) playerSleepingPercentage.toInteger(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetRandomTickSpeed extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setRandomTickSpeed", 1, arguments.size());
            }

            BuiltinClass randomTickSpeed = arguments.get(0);

            if (!randomTickSpeed.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "setRandomTickSpeed", new IntegerType(), randomTickSpeed.getType());
            }

            this.boundClass.toGameRules().get(GameRules.RANDOM_TICK_SPEED).set((int) randomTickSpeed.toInteger(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetReducedDebugInfo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setReducedDebugInfo", 1, arguments.size());
            }

            BuiltinClass reducedDebugInfo = arguments.get(0);

            if (!reducedDebugInfo.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setReducedDebugInfo", new BooleanType(), reducedDebugInfo.getType());
            }

            this.boundClass.toGameRules().get(GameRules.REDUCED_DEBUG_INFO).set(reducedDebugInfo.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetSendCommandFeedback extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSendCommandFeedback", 1, arguments.size());
            }

            BuiltinClass sendCommandFeedback = arguments.get(0);

            if (!sendCommandFeedback.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setSendCommandFeedback", new BooleanType(), sendCommandFeedback.getType());
            }

            this.boundClass.toGameRules().get(GameRules.SEND_COMMAND_FEEDBACK).set(sendCommandFeedback.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetShowDeathMessages extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setShowDeathMessages", 1, arguments.size());
            }

            BuiltinClass showDeathMessages = arguments.get(0);

            if (!showDeathMessages.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setShowDeathMessages", new BooleanType(), showDeathMessages.getType());
            }

            this.boundClass.toGameRules().get(GameRules.SHOW_DEATH_MESSAGES).set(showDeathMessages.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetSnowAccumulationHeight extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSnowAccumulationHeight", 1, arguments.size());
            }

            BuiltinClass snowAccumulationHeight = arguments.get(0);

            if (!snowAccumulationHeight.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "setSnowAccumulationHeight", new IntegerType(), snowAccumulationHeight.getType());
            }

            this.boundClass.toGameRules().get(GameRules.SNOW_ACCUMULATION_HEIGHT).set((int) snowAccumulationHeight.toInteger(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetSpawnRadius extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSpawnRadius", 1, arguments.size());
            }

            BuiltinClass spawnRadius = arguments.get(0);

            if (!spawnRadius.instanceOf(new IntegerType())) {
                throw ErrorHolder.argumentRequiresType(1, "setSpawnRadius", new IntegerType(), spawnRadius.getType());
            }

            this.boundClass.toGameRules().get(GameRules.SPAWN_RADIUS).set((int) spawnRadius.toInteger(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetSpectatorsGenerateChunks extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSpectatorsGenerateChunks", 1, arguments.size());
            }

            BuiltinClass spectatorsGenerateChunks = arguments.get(0);

            if (!spectatorsGenerateChunks.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setSpectatorsGenerateChunks", new BooleanType(), spectatorsGenerateChunks.getType());
            }

            this.boundClass.toGameRules().get(GameRules.SPECTATORS_GENERATE_CHUNKS).set(spectatorsGenerateChunks.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetTntExplosionDropDecay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setTntExplosionDropDecay", 1, arguments.size());
            }

            BuiltinClass tntExplosionDropDecay = arguments.get(0);

            if (!tntExplosionDropDecay.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setTntExplosionDropDecay", new BooleanType(), tntExplosionDropDecay.getType());
            }

            this.boundClass.toGameRules().get(GameRules.TNT_EXPLOSION_DROP_DECAY).set(tntExplosionDropDecay.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetUniversalAnger extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setUniversalAnger", 1, arguments.size());
            }

            BuiltinClass universalAnger = arguments.get(0);

            if (!universalAnger.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setUniversalAnger", new BooleanType(), universalAnger.getType());
            }

            this.boundClass.toGameRules().get(GameRules.UNIVERSAL_ANGER).set(universalAnger.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetWaterSourceConversion extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setWaterSourceConversion", 1, arguments.size());
            }

            BuiltinClass waterSourceConversion = arguments.get(0);

            if (!waterSourceConversion.instanceOf(new BooleanType())) {
                throw ErrorHolder.argumentRequiresType(1, "setWaterSourceConversion", new BooleanType(), waterSourceConversion.getType());
            }

            this.boundClass.toGameRules().get(GameRules.WATER_SOURCE_CONVERSION).set(waterSourceConversion.toBoolean(), Sculk.server);

            return new NullInstance();
        }
    }
}
