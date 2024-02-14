package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.gamerules.SculkGamerules;
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
    public static final GameRulesType TYPE = new GameRulesType();

    private GameRulesType() {
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
            this.validate("getAnnounceAdvancements", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.ANNOUNCE_ADVANCEMENTS));
        }
    }

    private static class GetBlockExplosionDropDecay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getBlockExplosionDropDecay", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.BLOCK_EXPLOSION_DROP_DECAY));
        }
    }

    private static class GetCommandBlockOutput extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getCommandBlockOutput", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.COMMAND_BLOCK_OUTPUT));
        }
    }

    private static class GetCommandModificationBlockLimit extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getCommandModificationBlockLimit", arguments);

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.COMMAND_MODIFICATION_BLOCK_LIMIT));
        }
    }

    private static class GetDisableElytraMovementCheck extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDisableElytraMovementCheck", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK));
        }
    }

    private static class GetDisableRaids extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDisableRaids", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DISABLE_RAIDS));
        }
    }

    private static class GetDoDaylightCycle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoDaylightCycle", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE));
        }
    }

    private static class GetDoEntityDrops extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoEntityDrops", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_ENTITY_DROPS));
        }
    }

    private static class GetDoFireTick extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoFireTick", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_FIRE_TICK));
        }
    }

    private static class GetDoImmediateRespawn extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoImmediateRespawn", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_IMMEDIATE_RESPAWN));
        }
    }

    private static class GetDoInsomnia extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoInsomnia", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_INSOMNIA));
        }
    }

    private static class GetDoLimitedCrafting extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoLimitedCrafting", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_LIMITED_CRAFTING));
        }
    }

    private static class GetDoMobGriefing extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoMobGriefing", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_MOB_GRIEFING));
        }
    }

    private static class GetDoMobLoot extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoMobLoot", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_MOB_LOOT));
        }
    }

    private static class GetDoMobSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoMobSpawning", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_MOB_SPAWNING));
        }
    }

    private static class GetDoPatrolSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoPatrolSpawning", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_PATROL_SPAWNING));
        }
    }

    private static class GetDoTileDrops extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoTileDrops", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_TILE_DROPS));
        }
    }

    private static class GetDoTraderSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoTraderSpawning", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_TRADER_SPAWNING));
        }
    }

    private static class GetDoVinesSpread extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoVinesSpread", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_VINES_SPREAD));
        }
    }

    private static class GetDoWardenSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoWardenSpawning", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_WARDEN_SPAWNING));
        }
    }

    private static class GetDoWeatherCycle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDoWeatherCycle", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DO_WEATHER_CYCLE));
        }
    }

    private static class GetDrowningDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getDrowningDamage", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.DROWNING_DAMAGE));
        }
    }

    private static class GetEnderPearlsVanishOnDeath extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getEnderPearlsVanishOnDeath", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.ENDER_PEARLS_VANISH_ON_DEATH));
        }
    }

    private static class GetFallDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getFallDamage", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.FALL_DAMAGE));
        }
    }

    private static class GetFireDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getFireDamage", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.FIRE_DAMAGE));
        }
    }

    private static class GetForgiveDeadPlayers extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getForgiveDeadPlayers", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.FORGIVE_DEAD_PLAYERS));
        }
    }

    private static class GetFreezeDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getFreezeDamage", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.FREEZE_DAMAGE));
        }
    }

    private static class GetGlobalSoundEvents extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getGlobalSoundEvents", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.GLOBAL_SOUND_EVENTS));
        }
    }

    private static class GetKeepInventory extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getKeepInventory", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.KEEP_INVENTORY));
        }
    }

    private static class GetLavaSourceConversion extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getLavaSourceConversion", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.LAVA_SOURCE_CONVERSION));
        }
    }

    private static class GetLogAdminCommands extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getLogAdminCommands", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.LOG_ADMIN_COMMANDS));
        }
    }

    private static class GetMaxArgumentCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getMaxArgumentCount", arguments);

            return new IntegerInstance(this.boundClass.toGameRules().getInt(SculkGamerules.MAX_ARGUMENT_COUNT));
        }
    }

    private static class GetMaxCommandChainLength extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getMaxCommandChainLength", arguments);

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.MAX_COMMAND_CHAIN_LENGTH));
        }
    }

    private static class GetMaxEntityCramming extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getMaxEntityCramming", arguments);

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.MAX_ENTITY_CRAMMING));
        }
    }

    private static class GetMaxLoops extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getMaxLoops", arguments);

            return new IntegerInstance(this.boundClass.toGameRules().getInt(SculkGamerules.MAX_LOOPS));
        }
    }

    private static class GetMobExplosionDropDecay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getMobExplosionDropDecay", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.MOB_EXPLOSION_DROP_DECAY));
        }
    }

    private static class GetNaturalRegeneration extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getNaturalRegeneration", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.NATURAL_REGENERATION));
        }
    }

    private static class GetPlayersSleepingPercentage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getPlayersSleepingPercentage", arguments);

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.PLAYERS_SLEEPING_PERCENTAGE));
        }
    }

    private static class GetRandomTickSpeed extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getRandomTickSpeed", arguments);

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.RANDOM_TICK_SPEED));
        }
    }

    private static class GetReducedDebugInfo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getReducedDebugInfo", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.REDUCED_DEBUG_INFO));
        }
    }

    private static class GetSendCommandFeedback extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getSendCommandFeedback", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.SEND_COMMAND_FEEDBACK));
        }
    }

    private static class GetShowDeathMessages extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getShowDeathMessages", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.SHOW_DEATH_MESSAGES));
        }
    }

    private static class GetSnowAccumulationHeight extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getSnowAccumulationHeight", arguments);

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.SNOW_ACCUMULATION_HEIGHT));
        }
    }

    private static class GetSpawnRadius extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getSpawnRadius", arguments);

            return new IntegerInstance(this.boundClass.toGameRules().getInt(GameRules.SPAWN_RADIUS));
        }
    }

    private static class GetSpectatorsGenerateChunks extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getSpectatorsGenerateChunks", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.SPECTATORS_GENERATE_CHUNKS));
        }
    }

    private static class GetTntExplosionDropDecay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getTntExplosionDropDecay", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.TNT_EXPLOSION_DROP_DECAY));
        }
    }

    private static class GetUniversalAnger extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getUniversalAnger", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.UNIVERSAL_ANGER));
        }
    }

    private static class GetWaterSourceConversion extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getWaterSourceConversion", arguments);

            return new BooleanInstance(this.boundClass.toGameRules().getBoolean(GameRules.WATER_SOURCE_CONVERSION));
        }
    }

    private static class SetAnnounceAdvancements extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setAnnounceAdvancements", arguments, List.of(BooleanType.TYPE));

            boolean announceAdvancements = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.ANNOUNCE_ADVANCEMENTS).set(announceAdvancements, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetBlockExplosionDropDecay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setBlockExplosionDropDecay", arguments, List.of(BooleanType.TYPE));

            boolean blockExplosionDropDecay = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.BLOCK_EXPLOSION_DROP_DECAY).set(blockExplosionDropDecay, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetCommandBlockOutput extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setCommandBlockOutput", arguments, List.of(BooleanType.TYPE));

            boolean commandBlockOutput = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.COMMAND_BLOCK_OUTPUT).set(commandBlockOutput, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetCommandModificationBlockLimit extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setCommandModificationBlockLimit", arguments, List.of(IntegerType.TYPE));

            long commandModificationBlockLimit = arguments.get(0).toInteger();

            this.boundClass.toGameRules().get(GameRules.COMMAND_MODIFICATION_BLOCK_LIMIT).set((int) commandModificationBlockLimit, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDisableElytraMovementCheck extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDisableElytraMovementCheck", arguments, List.of(BooleanType.TYPE));

            boolean disableElytraMovementCheck = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK).set(disableElytraMovementCheck, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDisableRaids extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDisableRaids", arguments, List.of(BooleanType.TYPE));

            boolean disableRaids = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DISABLE_RAIDS).set(disableRaids, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoDaylightCycle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoDaylightCycle", arguments, List.of(BooleanType.TYPE));

            boolean doDaylightCycle = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(doDaylightCycle, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoEntityDrops extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoEntityDrops", arguments, List.of(BooleanType.TYPE));

            boolean doEntityDrops = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_ENTITY_DROPS).set(doEntityDrops, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoFireTick extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoFireTick", arguments, List.of(BooleanType.TYPE));

            boolean doFireTick = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_FIRE_TICK).set(doFireTick, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoImmediateRespawn extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoImmediateRespawn", arguments, List.of(BooleanType.TYPE));

            boolean immediateRespawn = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_IMMEDIATE_RESPAWN).set(immediateRespawn, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoInsomnia extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoInsomnia", arguments, List.of(BooleanType.TYPE));

            boolean doInsomnia = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_INSOMNIA).set(doInsomnia, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoLimitedCrafting extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoLimitedCrafting", arguments, List.of(BooleanType.TYPE));

            boolean doLimitedCrafting = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_LIMITED_CRAFTING).set(doLimitedCrafting, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoMobGriefing extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoMobGriefing", arguments, List.of(BooleanType.TYPE));

            boolean doMobGriefing = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_MOB_GRIEFING).set(doMobGriefing, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoMobLoot extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoMobLoot", arguments, List.of(BooleanType.TYPE));

            boolean doMobLoot = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_MOB_LOOT).set(doMobLoot, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoMobSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoMobSpawning", arguments, List.of(BooleanType.TYPE));

            boolean doMobSpawning = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_MOB_SPAWNING).set(doMobSpawning, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoPatrolSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoPatrolSpawning", arguments, List.of(BooleanType.TYPE));

            boolean doPatrolSpawning = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_PATROL_SPAWNING).set(doPatrolSpawning, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoTileDrops extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoTileDrops", arguments, List.of(BooleanType.TYPE));

            boolean doTileDrops = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_TILE_DROPS).set(doTileDrops, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoTraderSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoTraderSpawning", arguments, List.of(BooleanType.TYPE));

            boolean doTraderSpawning = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_TRADER_SPAWNING).set(doTraderSpawning, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoVinesSpread extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoVinesSpread", arguments, List.of(BooleanType.TYPE));

            boolean doVinesSpread = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_VINES_SPREAD).set(doVinesSpread, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoWardenSpawning extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoWardenSpawning", arguments, List.of(BooleanType.TYPE));

            boolean doWardenSpawning = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_WARDEN_SPAWNING).set(doWardenSpawning, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDoWeatherCycle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDoWeatherCycle", arguments, List.of(BooleanType.TYPE));

            boolean doWeatherCycle = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DO_WEATHER_CYCLE).set(doWeatherCycle, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetDrowningDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setDrowningDamage", arguments, List.of(BooleanType.TYPE));

            boolean drowningDamage = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.DROWNING_DAMAGE).set(drowningDamage, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetEnderPearlsVanishOnDeath extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setEnderPearlsVanishOnDeath", arguments, List.of(BooleanType.TYPE));

            boolean enderPearlsVanishOnDeath = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.ENDER_PEARLS_VANISH_ON_DEATH).set(enderPearlsVanishOnDeath, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetFallDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setFallDamage", arguments, List.of(BooleanType.TYPE));

            boolean fallDamage = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.FALL_DAMAGE).set(fallDamage, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetFireDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setFireDamage", arguments, List.of(BooleanType.TYPE));

            boolean fireDamage = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.FIRE_DAMAGE).set(fireDamage, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetForgiveDeadPlayers extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setForgiveDeadPlayers", arguments, List.of(BooleanType.TYPE));

            boolean forgiveDeadPlayers = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.FORGIVE_DEAD_PLAYERS).set(forgiveDeadPlayers, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetFreezeDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setFreezeDamage", arguments, List.of(BooleanType.TYPE));

            boolean freezeDamage = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.FREEZE_DAMAGE).set(freezeDamage, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetGlobalSoundEvents extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setGlobalSoundEvents", arguments, List.of(BooleanType.TYPE));

            boolean globalSoundEvents = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.GLOBAL_SOUND_EVENTS).set(globalSoundEvents, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetKeepInventory extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setKeepInventory", arguments, List.of(BooleanType.TYPE));

            boolean keepInventory = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.KEEP_INVENTORY).set(keepInventory, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetLavaSourceConversion extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setLavaSourceConversion", arguments, List.of(BooleanType.TYPE));

            boolean lavaSourceConversion = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.LAVA_SOURCE_CONVERSION).set(lavaSourceConversion, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetLogAdminCommands extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setLogAdminCommands", arguments, List.of(BooleanType.TYPE));

            boolean logAdminCommands = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.LOG_ADMIN_COMMANDS).set(logAdminCommands, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetMaxArgumentCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setMaxArgumentCount", arguments, List.of(IntegerType.TYPE));

            long maxArgumentCount = arguments.get(0).toInteger();

            this.boundClass.toGameRules().get(SculkGamerules.MAX_ARGUMENT_COUNT).set((int) maxArgumentCount, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetMaxCommandChainLength extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setMaxCommandChainLength", arguments, List.of(IntegerType.TYPE));

            long maxCommandChainLength = arguments.get(0).toInteger();

            this.boundClass.toGameRules().get(GameRules.MAX_COMMAND_CHAIN_LENGTH).set((int) maxCommandChainLength, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetMaxEntityCramming extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setMaxEntityCramming", arguments, List.of(IntegerType.TYPE));

            long maxEntityCramming = arguments.get(0).toInteger();

            this.boundClass.toGameRules().get(GameRules.MAX_ENTITY_CRAMMING).set((int) maxEntityCramming, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetMaxLoops extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setMaxLoops", arguments, List.of(IntegerType.TYPE));

            long maxLoops = arguments.get(0).toInteger();

            this.boundClass.toGameRules().get(SculkGamerules.MAX_LOOPS).set((int) maxLoops, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetMobExplosionDropDecay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setMobExplosionDropDecay", arguments, List.of(BooleanType.TYPE));

            boolean mobExplosionDropDecay = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.MOB_EXPLOSION_DROP_DECAY).set(mobExplosionDropDecay, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetNaturalRegeneration extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setNaturalRegeneration", arguments, List.of(BooleanType.TYPE));

            boolean naturalRegeneration = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.NATURAL_REGENERATION).set(naturalRegeneration, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetPlayersSleepingPercentage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setPlayersSleepingPercentage", arguments, List.of(IntegerType.TYPE));

            long playerSleepingPercentage = arguments.get(0).toInteger();

            this.boundClass.toGameRules().get(GameRules.PLAYERS_SLEEPING_PERCENTAGE).set((int) playerSleepingPercentage, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetRandomTickSpeed extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setRandomTickSpeed", arguments, List.of(IntegerType.TYPE));

            long randomTickSpeed = arguments.get(0).toInteger();

            this.boundClass.toGameRules().get(GameRules.RANDOM_TICK_SPEED).set((int) randomTickSpeed, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetReducedDebugInfo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setReducedDebugInfo", arguments, List.of(BooleanType.TYPE));

            boolean reducedDebugInfo = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.REDUCED_DEBUG_INFO).set(reducedDebugInfo, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetSendCommandFeedback extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setSendCommandFeedback", arguments, List.of(BooleanType.TYPE));

            boolean sendCommandFeedback = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.SEND_COMMAND_FEEDBACK).set(sendCommandFeedback, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetShowDeathMessages extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setShowDeathMessages", arguments, List.of(BooleanType.TYPE));

            boolean showDeathMessages = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.SHOW_DEATH_MESSAGES).set(showDeathMessages, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetSnowAccumulationHeight extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setSnowAccumulationHeight", arguments, List.of(IntegerType.TYPE));

            long snowAccumulationHeight = arguments.get(0).toInteger();

            this.boundClass.toGameRules().get(GameRules.SNOW_ACCUMULATION_HEIGHT).set((int) snowAccumulationHeight, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetSpawnRadius extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setSpawnRadius", arguments, List.of(IntegerType.TYPE));

            long spawnRadius = arguments.get(0).toInteger();

            this.boundClass.toGameRules().get(GameRules.SPAWN_RADIUS).set((int) spawnRadius, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetSpectatorsGenerateChunks extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setSpectatorsGenerateChunks", arguments, List.of(BooleanType.TYPE));

            boolean spectatorsGenerateChunks = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.SPECTATORS_GENERATE_CHUNKS).set(spectatorsGenerateChunks, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetTntExplosionDropDecay extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setTntExplosionDropDecay", arguments, List.of(BooleanType.TYPE));

            boolean tntExplosionDropDecay = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.TNT_EXPLOSION_DROP_DECAY).set(tntExplosionDropDecay, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetUniversalAnger extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setUniversalAnger", arguments, List.of(BooleanType.TYPE));

            boolean universalAnger = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.UNIVERSAL_ANGER).set(universalAnger, Sculk.server);

            return new NullInstance();
        }
    }

    private static class SetWaterSourceConversion extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setWaterSourceConversion", arguments, List.of(BooleanType.TYPE));

            boolean waterSourceConversion = arguments.get(0).toBoolean();

            this.boundClass.toGameRules().get(GameRules.WATER_SOURCE_CONVERSION).set(waterSourceConversion, Sculk.server);

            return new NullInstance();
        }
    }
}
