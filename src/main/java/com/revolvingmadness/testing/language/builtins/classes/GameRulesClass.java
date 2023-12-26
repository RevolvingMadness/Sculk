package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
import com.revolvingmadness.testing.language.builtins.classes.types.IntegerClass;
import com.revolvingmadness.testing.language.builtins.classes.types.NullClass;
import com.revolvingmadness.testing.language.error_holder.ErrorHolder;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import net.minecraft.world.GameRules;

import java.util.List;
import java.util.Objects;

public class GameRulesClass extends BaseClassExpressionNode {
    public final GameRules gameRules;

    public GameRulesClass() {
        this.gameRules = Testing.server.getGameRules();

        this.variableScope.declare(true, "getDoFireTick", this.new GetDoFireTick());
        this.variableScope.declare(true, "getDoMobGriefing", this.new GetDoMobGriefing());
        this.variableScope.declare(true, "getKeepInventory", this.new GetKeepInventory());
        this.variableScope.declare(true, "getDoMobSpawning", this.new GetDoMobSpawning());
        this.variableScope.declare(true, "getDoMobLoot", this.new GetDoMobLoot());
        this.variableScope.declare(true, "getDoTileDrops", this.new GetDoTileDrops());
        this.variableScope.declare(true, "getDoEntityDrops", this.new GetDoEntityDrops());
        this.variableScope.declare(true, "getCommandBlockOutput", this.new GetCommandBlockOutput());
        this.variableScope.declare(true, "getNaturalRegeneration", this.new GetNaturalRegeneration());
        this.variableScope.declare(true, "getDoDaylightCycle", this.new GetDoDaylightCycle());
        this.variableScope.declare(true, "getLogAdminCommands", this.new GetLogAdminCommands());
        this.variableScope.declare(true, "getShowDeathMessages", this.new GetShowDeathMessages());
        this.variableScope.declare(true, "getRandomTickSpeed", this.new GetRandomTickSpeed());
        this.variableScope.declare(true, "getSendCommandFeedback", this.new GetSendCommandFeedback());
        this.variableScope.declare(true, "getReducedDebugInfo", this.new GetReducedDebugInfo());
        this.variableScope.declare(true, "getSpectatorsGenerateChunks", this.new GetSpectatorsGenerateChunks());
        this.variableScope.declare(true, "getSpawnRadius", this.new GetSpawnRadius());
        this.variableScope.declare(true, "getDisableElytraMovementCheck", this.new GetDisableElytraMovementCheck());
        this.variableScope.declare(true, "getMaxEntityCramming", this.new GetMaxEntityCramming());
        this.variableScope.declare(true, "getDoWeatherCycle", this.new GetDoWeatherCycle());
        this.variableScope.declare(true, "getDoLimitedCrafting", this.new GetDoLimitedCrafting());
        this.variableScope.declare(true, "getMaxCommandChainLength", this.new GetMaxCommandChainLength());
        this.variableScope.declare(true, "getCommandModificationBlockLimit", this.new GetCommandModificationBlockLimit());
        this.variableScope.declare(true, "getAnnounceAdvancements", this.new GetAnnounceAdvancements());
        this.variableScope.declare(true, "getDisableRaids", this.new GetDisableRaids());
        this.variableScope.declare(true, "getDoInsomnia", this.new GetDoInsomnia());
        this.variableScope.declare(true, "getDoImmediateRespawn", this.new GetDoImmediateRespawn());
        this.variableScope.declare(true, "getDrowningDamage", this.new GetDrowningDamage());
        this.variableScope.declare(true, "getFallDamage", this.new GetFallDamage());
        this.variableScope.declare(true, "getFireDamage", this.new GetFireDamage());
        this.variableScope.declare(true, "getFreezeDamage", this.new GetFreezeDamage());
        this.variableScope.declare(true, "getDoPatrolSpawning", this.new GetDoPatrolSpawning());
        this.variableScope.declare(true, "getDoTraderSpawning", this.new GetDoTraderSpawning());
        this.variableScope.declare(true, "getDoWardenSpawning", this.new GetDoWardenSpawning());
        this.variableScope.declare(true, "getForgiveDeadPlayers", this.new GetForgiveDeadPlayers());
        this.variableScope.declare(true, "getUniversalAnger", this.new GetUniversalAnger());
        this.variableScope.declare(true, "getPlayersSleepingPercentage", this.new GetPlayersSleepingPercentage());
        this.variableScope.declare(true, "getBlockExplosionDropDecay", this.new GetBlockExplosionDropDecay());
        this.variableScope.declare(true, "getMobExplosionDropDecay", this.new GetMobExplosionDropDecay());
        this.variableScope.declare(true, "getTntExplosionDropDecay", this.new GetTntExplosionDropDecay());
        this.variableScope.declare(true, "getSnowAccumulationHeight", this.new GetSnowAccumulationHeight());
        this.variableScope.declare(true, "getWaterSourceConversion", this.new GetWaterSourceConversion());
        this.variableScope.declare(true, "getLavaSourceConversion", this.new GetLavaSourceConversion());
        this.variableScope.declare(true, "getGlobalSoundEvents", this.new GetGlobalSoundEvents());
        this.variableScope.declare(true, "getDoVinesSpread", this.new GetDoVinesSpread());
        this.variableScope.declare(true, "getEnderPearlsVanishOnDeath", this.new GetEnderPearlsVanishOnDeath());
        this.variableScope.declare(true, "getMaxArgumentCount", this.new GetMaxArgumentCount());
        this.variableScope.declare(true, "getMaxLoops", this.new GetMaxLoops());
        this.variableScope.declare(true, "getScriptLogsEnabled", this.new GetScriptLogsEnabled());
        this.variableScope.declare(true, "setDoFireTick", this.new SetDoFireTick());
        this.variableScope.declare(true, "setDoMobGriefing", this.new SetDoMobGriefing());
        this.variableScope.declare(true, "setKeepInventory", this.new SetKeepInventory());
        this.variableScope.declare(true, "setDoMobSpawning", this.new SetDoMobSpawning());
        this.variableScope.declare(true, "setDoMobLoot", this.new SetDoMobLoot());
        this.variableScope.declare(true, "setDoTileDrops", this.new SetDoTileDrops());
        this.variableScope.declare(true, "setDoEntityDrops", this.new SetDoEntityDrops());
        this.variableScope.declare(true, "setCommandBlockOutput", this.new SetCommandBlockOutput());
        this.variableScope.declare(true, "setNaturalRegeneration", this.new SetNaturalRegeneration());
        this.variableScope.declare(true, "setDoDaylightCycle", this.new SetDoDaylightCycle());
        this.variableScope.declare(true, "setLogAdminCommands", this.new SetLogAdminCommands());
        this.variableScope.declare(true, "setShowDeathMessages", this.new SetShowDeathMessages());
        this.variableScope.declare(true, "setRandomTickSpeed", this.new SetRandomTickSpeed());
        this.variableScope.declare(true, "setSendCommandFeedback", this.new SetSendCommandFeedback());
        this.variableScope.declare(true, "setReducedDebugInfo", this.new SetReducedDebugInfo());
        this.variableScope.declare(true, "setSpectatorsGenerateChunks", this.new SetSpectatorsGenerateChunks());
        this.variableScope.declare(true, "setSpawnRadius", this.new SetSpawnRadius());
        this.variableScope.declare(true, "setDisableElytraMovementCheck", this.new SetDisableElytraMovementCheck());
        this.variableScope.declare(true, "setMaxEntityCramming", this.new SetMaxEntityCramming());
        this.variableScope.declare(true, "setDoWeatherCycle", this.new SetDoWeatherCycle());
        this.variableScope.declare(true, "setDoLimitedCrafting", this.new SetDoLimitedCrafting());
        this.variableScope.declare(true, "setMaxCommandChainLength", this.new SetMaxCommandChainLength());
        this.variableScope.declare(true, "setCommandModificationBlockLimit", this.new SetCommandModificationBlockLimit());
        this.variableScope.declare(true, "setAnnounceAdvancements", this.new SetAnnounceAdvancements());
        this.variableScope.declare(true, "setDisableRaids", this.new SetDisableRaids());
        this.variableScope.declare(true, "setDoInsomnia", this.new SetDoInsomnia());
        this.variableScope.declare(true, "setDoImmediateRespawn", this.new SetDoImmediateRespawn());
        this.variableScope.declare(true, "setDrowningDamage", this.new SetDrowningDamage());
        this.variableScope.declare(true, "setFallDamage", this.new SetFallDamage());
        this.variableScope.declare(true, "setFireDamage", this.new SetFireDamage());
        this.variableScope.declare(true, "setFreezeDamage", this.new SetFreezeDamage());
        this.variableScope.declare(true, "setDoPatrolSpawning", this.new SetDoPatrolSpawning());
        this.variableScope.declare(true, "setDoTraderSpawning", this.new SetDoTraderSpawning());
        this.variableScope.declare(true, "setDoWardenSpawning", this.new SetDoWardenSpawning());
        this.variableScope.declare(true, "setForgiveDeadPlayers", this.new SetForgiveDeadPlayers());
        this.variableScope.declare(true, "setUniversalAnger", this.new SetUniversalAnger());
        this.variableScope.declare(true, "setPlayersSleepingPercentage", this.new SetPlayersSleepingPercentage());
        this.variableScope.declare(true, "setBlockExplosionDropDecay", this.new SetBlockExplosionDropDecay());
        this.variableScope.declare(true, "setMobExplosionDropDecay", this.new SetMobExplosionDropDecay());
        this.variableScope.declare(true, "setTntExplosionDropDecay", this.new SetTntExplosionDropDecay());
        this.variableScope.declare(true, "setSnowAccumulationHeight", this.new SetSnowAccumulationHeight());
        this.variableScope.declare(true, "setWaterSourceConversion", this.new SetWaterSourceConversion());
        this.variableScope.declare(true, "setLavaSourceConversion", this.new SetLavaSourceConversion());
        this.variableScope.declare(true, "setGlobalSoundEvents", this.new SetGlobalSoundEvents());
        this.variableScope.declare(true, "setDoVinesSpread", this.new SetDoVinesSpread());
        this.variableScope.declare(true, "setEnderPearlsVanishOnDeath", this.new SetEnderPearlsVanishOnDeath());
        this.variableScope.declare(true, "setScriptLogsEnabled", this.new SetScriptLogsEnabled());
        this.variableScope.declare(true, "setMaxArgumentCount", this.new SetMaxArgumentCount());
        this.variableScope.declare(true, "setMaxLoops", this.new SetMaxLoops());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        GameRulesClass that = (GameRulesClass) o;
        return Objects.equals(this.gameRules, that.gameRules);
    }

    @Override
    public String getType() {
        return "GameRules";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.gameRules);
    }

    public class GetAnnounceAdvancements extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getAnnounceAdvancements", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.ANNOUNCE_ADVANCEMENTS));
        }
    }

    public class GetBlockExplosionDropDecay extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getBlockExplosionDropDecay", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.BLOCK_EXPLOSION_DROP_DECAY));
        }
    }

    public class GetCommandBlockOutput extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getCommandBlockOutput", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.COMMAND_BLOCK_OUTPUT));
        }
    }

    public class GetCommandModificationBlockLimit extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getCommandModificationBlockLimit", 0, arguments.size());
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.COMMAND_MODIFICATION_BLOCK_LIMIT));
        }
    }

    public class GetDisableElytraMovementCheck extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDisableElytraMovementCheck", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK));
        }
    }

    public class GetDisableRaids extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDisableRaids", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DISABLE_RAIDS));
        }
    }

    public class GetDoDaylightCycle extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoDaylightCycle", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_DAYLIGHT_CYCLE));
        }
    }

    public class GetDoEntityDrops extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoEntityDrops", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_ENTITY_DROPS));
        }
    }

    public class GetDoFireTick extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoFireTick", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_FIRE_TICK));
        }
    }

    public class GetDoImmediateRespawn extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoImmediateRespawn", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_IMMEDIATE_RESPAWN));
        }
    }

    public class GetDoInsomnia extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoInsomnia", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_INSOMNIA));
        }
    }

    public class GetDoLimitedCrafting extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoLimitedCrafting", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_LIMITED_CRAFTING));
        }
    }

    public class GetDoMobGriefing extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoMobGriefing", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_MOB_GRIEFING));
        }
    }

    public class GetDoMobLoot extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoMobLoot", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_MOB_LOOT));
        }
    }

    public class GetDoMobSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoMobSpawning", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_MOB_SPAWNING));
        }
    }

    public class GetDoPatrolSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoPatrolSpawning", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_PATROL_SPAWNING));
        }
    }

    public class GetDoTileDrops extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoTileDrops", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_TILE_DROPS));
        }
    }

    public class GetDoTraderSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoTraderSpawning", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_TRADER_SPAWNING));
        }
    }

    public class GetDoVinesSpread extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoVinesSpread", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_VINES_SPREAD));
        }
    }

    public class GetDoWardenSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoWardenSpawning", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_WARDEN_SPAWNING));
        }
    }

    public class GetDoWeatherCycle extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDoWeatherCycle", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_WEATHER_CYCLE));
        }
    }

    public class GetDrowningDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDrowningDamage", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DROWNING_DAMAGE));
        }
    }

    public class GetEnderPearlsVanishOnDeath extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getEnderPearlsVanishOnDeath", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.ENDER_PEARLS_VANISH_ON_DEATH));
        }
    }

    public class GetFallDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getFallDamage", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.FALL_DAMAGE));
        }
    }

    public class GetFireDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getFireDamage", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.FIRE_DAMAGE));
        }
    }

    public class GetForgiveDeadPlayers extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getForgiveDeadPlayers", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.FORGIVE_DEAD_PLAYERS));
        }
    }

    public class GetFreezeDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getFreezeDamage", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.FREEZE_DAMAGE));
        }
    }

    public class GetGlobalSoundEvents extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getGlobalSoundEvents", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.GLOBAL_SOUND_EVENTS));
        }
    }

    public class GetKeepInventory extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getKeepInventory", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.KEEP_INVENTORY));
        }
    }

    public class GetLavaSourceConversion extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getLavaSourceConversion", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.LAVA_SOURCE_CONVERSION));
        }
    }

    public class GetLogAdminCommands extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getLogAdminCommands", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.LOG_ADMIN_COMMANDS));
        }
    }

    public class GetMaxArgumentCount extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxArgumentCount", 0, arguments.size());
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(TestingGamerules.MAX_ARGUMENTS));
        }
    }

    public class GetMaxCommandChainLength extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxCommandChainLength", 0, arguments.size());
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.MAX_COMMAND_CHAIN_LENGTH));
        }
    }

    public class GetMaxEntityCramming extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxEntityCramming", 0, arguments.size());
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.MAX_ENTITY_CRAMMING));
        }
    }

    public class GetMaxLoops extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxLoops", 0, arguments.size());
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(TestingGamerules.MAX_LOOPS));
        }
    }

    public class GetMobExplosionDropDecay extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMobExplosionDropDecay", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.MOB_EXPLOSION_DROP_DECAY));
        }
    }

    public class GetNaturalRegeneration extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getNaturalRegeneration", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.NATURAL_REGENERATION));
        }
    }

    public class GetPlayersSleepingPercentage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getPlayersSleepingPercentage", 0, arguments.size());
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.PLAYERS_SLEEPING_PERCENTAGE));
        }
    }

    public class GetRandomTickSpeed extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getRandomTickSpeed", 0, arguments.size());
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.RANDOM_TICK_SPEED));
        }
    }

    public class GetReducedDebugInfo extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getReducedDebugInfo", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.REDUCED_DEBUG_INFO));
        }
    }

    public class GetScriptLogsEnabled extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getScriptLogsEnabled", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(TestingGamerules.SCRIPT_LOGS_ENABLED));
        }
    }

    public class GetSendCommandFeedback extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getSendCommandFeedback", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.SEND_COMMAND_FEEDBACK));
        }
    }

    public class GetShowDeathMessages extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getShowDeathMessages", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.SHOW_DEATH_MESSAGES));
        }
    }

    public class GetSnowAccumulationHeight extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getSnowAccumulationHeight", 0, arguments.size());
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.SNOW_ACCUMULATION_HEIGHT));
        }
    }

    public class GetSpawnRadius extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getSpawnRadius", 0, arguments.size());
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.SPAWN_RADIUS));
        }
    }

    public class GetSpectatorsGenerateChunks extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getSpectatorsGenerateChunks", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.SPECTATORS_GENERATE_CHUNKS));
        }
    }

    public class GetTntExplosionDropDecay extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getTntExplosionDropDecay", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.TNT_EXPLOSION_DROP_DECAY));
        }
    }

    public class GetUniversalAnger extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getUniversalAnger", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.UNIVERSAL_ANGER));
        }
    }

    public class GetWaterSourceConversion extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getWaterSourceConversion", 0, arguments.size());
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.WATER_SOURCE_CONVERSION));
        }
    }

    public class SetAnnounceAdvancements extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setAnnounceAdvancements", 1, arguments.size());
            }

            BaseClassExpressionNode announceAdvancements = arguments.get(0);

            if (!announceAdvancements.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setAnnounceAdvancements", "Boolean", announceAdvancements.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.ANNOUNCE_ADVANCEMENTS).set(((BooleanClass) announceAdvancements).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetBlockExplosionDropDecay extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setBlockExplosionDropDecay", 1, arguments.size());
            }

            BaseClassExpressionNode blockExplosionDropDecay = arguments.get(0);

            if (!blockExplosionDropDecay.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setBlockExplosionDropDecay", "Boolean", blockExplosionDropDecay.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.BLOCK_EXPLOSION_DROP_DECAY).set(((BooleanClass) blockExplosionDropDecay).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetCommandBlockOutput extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setCommandBlockOutput", 1, arguments.size());
            }

            BaseClassExpressionNode commandBlockOutput = arguments.get(0);

            if (!commandBlockOutput.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setCommandBlockOutput", "Boolean", commandBlockOutput.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.COMMAND_BLOCK_OUTPUT).set(((BooleanClass) commandBlockOutput).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetCommandModificationBlockLimit extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setCommandModificationBlockLimit", 1, arguments.size());
            }

            BaseClassExpressionNode commandModificationBlockLimit = arguments.get(0);

            if (!commandModificationBlockLimit.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "setCommandModificationBlockLimit", "Integer", commandModificationBlockLimit.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.COMMAND_MODIFICATION_BLOCK_LIMIT).set(((IntegerClass) commandModificationBlockLimit).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDisableElytraMovementCheck extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDisableElytraMovementCheck", 1, arguments.size());
            }

            BaseClassExpressionNode disableElytraMovementCheck = arguments.get(0);

            if (!disableElytraMovementCheck.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDisableElytraMovementCheck", "Boolean", disableElytraMovementCheck.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK).set(((BooleanClass) disableElytraMovementCheck).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDisableRaids extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDisableRaids", 1, arguments.size());
            }

            BaseClassExpressionNode disableRaids = arguments.get(0);

            if (!disableRaids.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDisableRaids", "Boolean", disableRaids.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DISABLE_RAIDS).set(((BooleanClass) disableRaids).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoDaylightCycle extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoDaylightCycle", 1, arguments.size());
            }

            BaseClassExpressionNode doDaylightCycle = arguments.get(0);

            if (!doDaylightCycle.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoDaylightCycle", "Boolean", doDaylightCycle.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_DAYLIGHT_CYCLE).set(((BooleanClass) doDaylightCycle).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoEntityDrops extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoEntityDrops", 1, arguments.size());
            }

            BaseClassExpressionNode doEntityDrops = arguments.get(0);

            if (!doEntityDrops.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoEntityDrops", "Boolean", doEntityDrops.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_ENTITY_DROPS).set(((BooleanClass) doEntityDrops).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoFireTick extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoFireTick", 1, arguments.size());
            }

            BaseClassExpressionNode doFireTick = arguments.get(0);

            if (!doFireTick.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoFireTick", "Boolean", doFireTick.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_FIRE_TICK).set(((BooleanClass) doFireTick).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoImmediateRespawn extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoImmediateRespawn", 1, arguments.size());
            }

            BaseClassExpressionNode immediateRespawn = arguments.get(0);

            if (!immediateRespawn.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoImmediateRespawn", "Boolean", immediateRespawn.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_IMMEDIATE_RESPAWN).set(((BooleanClass) immediateRespawn).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoInsomnia extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoInsomnia", 1, arguments.size());
            }

            BaseClassExpressionNode doInsomnia = arguments.get(0);

            if (!doInsomnia.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoInsomnia", "Boolean", doInsomnia.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_INSOMNIA).set(((BooleanClass) doInsomnia).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoLimitedCrafting extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoLimitedCrafting", 1, arguments.size());
            }

            BaseClassExpressionNode doLimitedCrafting = arguments.get(0);

            if (!doLimitedCrafting.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoLimitedCrafting", "Boolean", doLimitedCrafting.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_LIMITED_CRAFTING).set(((BooleanClass) doLimitedCrafting).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoMobGriefing extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoMobGriefing", 1, arguments.size());
            }

            BaseClassExpressionNode doMobGriefing = arguments.get(0);

            if (!doMobGriefing.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoMobGriefing", "Boolean", doMobGriefing.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_MOB_GRIEFING).set(((BooleanClass) doMobGriefing).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoMobLoot extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoMobLoot", 1, arguments.size());
            }

            BaseClassExpressionNode doMobLoot = arguments.get(0);

            if (!doMobLoot.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoMobLoot", "Boolean", doMobLoot.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_MOB_LOOT).set(((BooleanClass) doMobLoot).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoMobSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoMobSpawning", 1, arguments.size());
            }

            BaseClassExpressionNode doMobSpawning = arguments.get(0);

            if (!doMobSpawning.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoMobSpawning", "Boolean", doMobSpawning.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_MOB_SPAWNING).set(((BooleanClass) doMobSpawning).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoPatrolSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoPatrolSpawning", 1, arguments.size());
            }

            BaseClassExpressionNode doPatrolSpawning = arguments.get(0);

            if (!doPatrolSpawning.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoPatrolSpawning", "Boolean", doPatrolSpawning.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_PATROL_SPAWNING).set(((BooleanClass) doPatrolSpawning).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoTileDrops extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoTileDrops", 1, arguments.size());
            }

            BaseClassExpressionNode doTileDrops = arguments.get(0);

            if (!doTileDrops.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoTileDrops", "Boolean", doTileDrops.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_TILE_DROPS).set(((BooleanClass) doTileDrops).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoTraderSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoTraderSpawning", 1, arguments.size());
            }

            BaseClassExpressionNode doTraderSpawning = arguments.get(0);

            if (!doTraderSpawning.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoTraderSpawning", "Boolean", doTraderSpawning.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_TRADER_SPAWNING).set(((BooleanClass) doTraderSpawning).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoVinesSpread extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoVinesSpread", 1, arguments.size());
            }

            BaseClassExpressionNode doVinesSpread = arguments.get(0);

            if (!doVinesSpread.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoVinesSpread", "Boolean", doVinesSpread.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_VINES_SPREAD).set(((BooleanClass) doVinesSpread).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoWardenSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoWardenSpawning", 1, arguments.size());
            }

            BaseClassExpressionNode doWardenSpawning = arguments.get(0);

            if (!doWardenSpawning.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoWardenSpawning", "Boolean", doWardenSpawning.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_WARDEN_SPAWNING).set(((BooleanClass) doWardenSpawning).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoWeatherCycle extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDoWeatherCycle", 1, arguments.size());
            }

            BaseClassExpressionNode doWeatherCycle = arguments.get(0);

            if (!doWeatherCycle.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDoWeatherCycle", "Boolean", doWeatherCycle.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_WEATHER_CYCLE).set(((BooleanClass) doWeatherCycle).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDrowningDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDrowningDamage", 1, arguments.size());
            }

            BaseClassExpressionNode drowningDamage = arguments.get(0);

            if (!drowningDamage.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setDrowningDamage", "Boolean", drowningDamage.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.DROWNING_DAMAGE).set(((BooleanClass) drowningDamage).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetEnderPearlsVanishOnDeath extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setEnderPearlsVanishOnDeath", 1, arguments.size());
            }

            BaseClassExpressionNode enderPearlsVanishOnDeath = arguments.get(0);

            if (!enderPearlsVanishOnDeath.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setEnderPearlsVanishOnDeath", "Boolean", enderPearlsVanishOnDeath.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.ENDER_PEARLS_VANISH_ON_DEATH).set(((BooleanClass) enderPearlsVanishOnDeath).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetFallDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setFallDamage", 1, arguments.size());
            }

            BaseClassExpressionNode fallDamage = arguments.get(0);

            if (!fallDamage.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setFallDamage", "Boolean", fallDamage.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.FALL_DAMAGE).set(((BooleanClass) fallDamage).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetFireDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setFireDamage", 1, arguments.size());
            }

            BaseClassExpressionNode fireDamage = arguments.get(0);

            if (!fireDamage.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setFireDamage", "Boolean", fireDamage.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.FIRE_DAMAGE).set(((BooleanClass) fireDamage).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetForgiveDeadPlayers extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setForgiveDeadPlayers", 1, arguments.size());
            }

            BaseClassExpressionNode forgiveDeadPlayers = arguments.get(0);

            if (!forgiveDeadPlayers.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setForgiveDeadPlayers", "Boolean", forgiveDeadPlayers.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.FORGIVE_DEAD_PLAYERS).set(((BooleanClass) forgiveDeadPlayers).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetFreezeDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setFreezeDamage", 1, arguments.size());
            }

            BaseClassExpressionNode freezeDamage = arguments.get(0);

            if (!freezeDamage.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setFreezeDamage", "Boolean", freezeDamage.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.FREEZE_DAMAGE).set(((BooleanClass) freezeDamage).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetGlobalSoundEvents extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setGlobalSoundEvents", 1, arguments.size());
            }

            BaseClassExpressionNode globalSoundEvents = arguments.get(0);

            if (!globalSoundEvents.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setGlobalSoundEvents", "Boolean", globalSoundEvents.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.GLOBAL_SOUND_EVENTS).set(((BooleanClass) globalSoundEvents).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetKeepInventory extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setKeepInventory", 1, arguments.size());
            }

            BaseClassExpressionNode keepInventory = arguments.get(0);

            if (!keepInventory.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setKeepInventory", "Boolean", keepInventory.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.KEEP_INVENTORY).set(((BooleanClass) keepInventory).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetLavaSourceConversion extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setLavaSourceConversion", 1, arguments.size());
            }

            BaseClassExpressionNode lavaSourceConversion = arguments.get(0);

            if (!lavaSourceConversion.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setLavaSourceConversion", "Boolean", lavaSourceConversion.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.LAVA_SOURCE_CONVERSION).set(((BooleanClass) lavaSourceConversion).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetLogAdminCommands extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setLogAdminCommands", 1, arguments.size());
            }

            BaseClassExpressionNode logAdminCommands = arguments.get(0);

            if (!logAdminCommands.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setLogAdminCommands", "Boolean", logAdminCommands.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.LOG_ADMIN_COMMANDS).set(((BooleanClass) logAdminCommands).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetMaxArgumentCount extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setMaxArgumentCount", 1, arguments.size());
            }

            BaseClassExpressionNode maxArgumentCount = arguments.get(0);

            if (!maxArgumentCount.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "setMaxArgumentCount", "Integer", maxArgumentCount.getType());
            }

            GameRulesClass.this.gameRules.get(TestingGamerules.MAX_ARGUMENTS).set(((IntegerClass) maxArgumentCount).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetMaxCommandChainLength extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setMaxCommandChainLength", 1, arguments.size());
            }

            BaseClassExpressionNode maxCommandChainLength = arguments.get(0);

            if (!maxCommandChainLength.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "setMaxCommandChainLength", "Integer", maxCommandChainLength.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.MAX_COMMAND_CHAIN_LENGTH).set(((IntegerClass) maxCommandChainLength).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetMaxEntityCramming extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setMaxEntityCramming", 1, arguments.size());
            }

            BaseClassExpressionNode maxEntityCramming = arguments.get(0);

            if (!maxEntityCramming.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "setMaxEntityCramming", "Integer", maxEntityCramming.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.MAX_ENTITY_CRAMMING).set(((IntegerClass) maxEntityCramming).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetMaxLoops extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setMaxLoops", 1, arguments.size());
            }

            BaseClassExpressionNode maxLoops = arguments.get(0);

            if (!maxLoops.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "setMaxLoops", "Integer", maxLoops.getType());
            }

            GameRulesClass.this.gameRules.get(TestingGamerules.MAX_LOOPS).set(((IntegerClass) maxLoops).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetMobExplosionDropDecay extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setMobExplosionDropDecay", 1, arguments.size());
            }

            BaseClassExpressionNode mobExplosionDropDecay = arguments.get(0);

            if (!mobExplosionDropDecay.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setMobExplosionDropDecay", "Boolean", mobExplosionDropDecay.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.MOB_EXPLOSION_DROP_DECAY).set(((BooleanClass) mobExplosionDropDecay).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetNaturalRegeneration extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setNaturalRegeneration", 1, arguments.size());
            }

            BaseClassExpressionNode naturalRegeneration = arguments.get(0);

            if (!naturalRegeneration.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setNaturalRegeneration", "Boolean", naturalRegeneration.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.NATURAL_REGENERATION).set(((BooleanClass) naturalRegeneration).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetPlayersSleepingPercentage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setPlayersSleepingPercentage", 1, arguments.size());
            }

            BaseClassExpressionNode playerSleepingPercentage = arguments.get(0);

            if (!playerSleepingPercentage.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "setPlayersSleepingPercentage", "Integer", playerSleepingPercentage.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.PLAYERS_SLEEPING_PERCENTAGE).set(((IntegerClass) playerSleepingPercentage).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetRandomTickSpeed extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setRandomTickSpeed", 1, arguments.size());
            }

            BaseClassExpressionNode randomTickSpeed = arguments.get(0);

            if (!randomTickSpeed.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "setRandomTickSpeed", "Integer", randomTickSpeed.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.RANDOM_TICK_SPEED).set(((IntegerClass) randomTickSpeed).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetReducedDebugInfo extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setReducedDebugInfo", 1, arguments.size());
            }

            BaseClassExpressionNode reducedDebugInfo = arguments.get(0);

            if (!reducedDebugInfo.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setReducedDebugInfo", "Boolean", reducedDebugInfo.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.REDUCED_DEBUG_INFO).set(((BooleanClass) reducedDebugInfo).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetScriptLogsEnabled extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setScriptLogsEnabled", 1, arguments.size());
            }

            BaseClassExpressionNode scriptLogsEnabled = arguments.get(0);

            if (!scriptLogsEnabled.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setScriptLogsEnabled", "Boolean", scriptLogsEnabled.getType());
            }

            GameRulesClass.this.gameRules.get(TestingGamerules.SCRIPT_LOGS_ENABLED).set(((BooleanClass) scriptLogsEnabled).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetSendCommandFeedback extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSendCommandFeedback", 1, arguments.size());
            }

            BaseClassExpressionNode sendCommandFeedback = arguments.get(0);

            if (!sendCommandFeedback.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setSendCommandFeedback", "Boolean", sendCommandFeedback.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.SEND_COMMAND_FEEDBACK).set(((BooleanClass) sendCommandFeedback).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetShowDeathMessages extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setShowDeathMessages", 1, arguments.size());
            }

            BaseClassExpressionNode showDeathMessages = arguments.get(0);

            if (!showDeathMessages.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setShowDeathMessages", "Boolean", showDeathMessages.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.SHOW_DEATH_MESSAGES).set(((BooleanClass) showDeathMessages).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetSnowAccumulationHeight extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSnowAccumulationHeight", 1, arguments.size());
            }

            BaseClassExpressionNode snowAccumulationHeight = arguments.get(0);

            if (!snowAccumulationHeight.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "setSnowAccumulationHeight", "Integer", snowAccumulationHeight.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.SNOW_ACCUMULATION_HEIGHT).set(((IntegerClass) snowAccumulationHeight).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetSpawnRadius extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSpawnRadius", 1, arguments.size());
            }

            BaseClassExpressionNode spawnRadius = arguments.get(0);

            if (!spawnRadius.getType().equals("Integer")) {
                throw ErrorHolder.argumentRequiresType(1, "setSpawnRadius", "Integer", spawnRadius.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.SPAWN_RADIUS).set(((IntegerClass) spawnRadius).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetSpectatorsGenerateChunks extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setSpectatorsGenerateChunks", 1, arguments.size());
            }

            BaseClassExpressionNode spectatorsGenerateChunks = arguments.get(0);

            if (!spectatorsGenerateChunks.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setSpectatorsGenerateChunks", "Boolean", spectatorsGenerateChunks.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.SPECTATORS_GENERATE_CHUNKS).set(((BooleanClass) spectatorsGenerateChunks).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetTntExplosionDropDecay extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setTntExplosionDropDecay", 1, arguments.size());
            }

            BaseClassExpressionNode tntExplosionDropDecay = arguments.get(0);

            if (!tntExplosionDropDecay.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setTntExplosionDropDecay", "Boolean", tntExplosionDropDecay.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.TNT_EXPLOSION_DROP_DECAY).set(((BooleanClass) tntExplosionDropDecay).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetUniversalAnger extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setUniversalAnger", 1, arguments.size());
            }

            BaseClassExpressionNode universalAnger = arguments.get(0);

            if (!universalAnger.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setUniversalAnger", "Boolean", universalAnger.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.UNIVERSAL_ANGER).set(((BooleanClass) universalAnger).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetWaterSourceConversion extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setWaterSourceConversion", 1, arguments.size());
            }

            BaseClassExpressionNode waterSourceConversion = arguments.get(0);

            if (!waterSourceConversion.getType().equals("Boolean")) {
                throw ErrorHolder.argumentRequiresType(1, "setWaterSourceConversion", "Boolean", waterSourceConversion.getType());
            }

            GameRulesClass.this.gameRules.get(GameRules.WATER_SOURCE_CONVERSION).set(((BooleanClass) waterSourceConversion).value, Testing.server);

            return new NullClass();
        }
    }
}
