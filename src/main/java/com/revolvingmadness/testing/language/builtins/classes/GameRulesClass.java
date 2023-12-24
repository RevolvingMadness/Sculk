package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
import com.revolvingmadness.testing.language.builtins.classes.types.IntegerClass;
import com.revolvingmadness.testing.language.builtins.classes.types.NullClass;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import net.minecraft.world.GameRules;

import java.util.List;
import java.util.Objects;

public class GameRulesClass extends BaseClassExpressionNode {
    public final GameRules gameRules;

    public GameRulesClass() {
        this.gameRules = Testing.server.getGameRules();

        this.variableScope.declare(true, new IdentifierExpressionNode("getDoFireTick"), this.new GetDoFireTick());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoMobGriefing"), this.new GetDoMobGriefing());
        this.variableScope.declare(true, new IdentifierExpressionNode("getKeepInventory"), this.new GetKeepInventory());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoMobSpawning"), this.new GetDoMobSpawning());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoMobLoot"), this.new GetDoMobLoot());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoTileDrops"), this.new GetDoTileDrops());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoEntityDrops"), this.new GetDoEntityDrops());
        this.variableScope.declare(true, new IdentifierExpressionNode("getCommandBlockOutput"), this.new GetCommandBlockOutput());
        this.variableScope.declare(true, new IdentifierExpressionNode("getNaturalRegeneration"), this.new GetNaturalRegeneration());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoDaylightCycle"), this.new GetDoDaylightCycle());
        this.variableScope.declare(true, new IdentifierExpressionNode("getLogAdminCommands"), this.new GetLogAdminCommands());
        this.variableScope.declare(true, new IdentifierExpressionNode("getShowDeathMessages"), this.new GetShowDeathMessages());
        this.variableScope.declare(true, new IdentifierExpressionNode("getRandomTickSpeed"), this.new GetRandomTickSpeed());
        this.variableScope.declare(true, new IdentifierExpressionNode("getSendCommandFeedback"), this.new GetSendCommandFeedback());
        this.variableScope.declare(true, new IdentifierExpressionNode("getReducedDebugInfo"), this.new GetReducedDebugInfo());
        this.variableScope.declare(true, new IdentifierExpressionNode("getSpectatorsGenerateChunks"), this.new GetSpectatorsGenerateChunks());
        this.variableScope.declare(true, new IdentifierExpressionNode("getSpawnRadius"), this.new GetSpawnRadius());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDisableElytraMovementCheck"), this.new GetDisableElytraMovementCheck());
        this.variableScope.declare(true, new IdentifierExpressionNode("getMaxEntityCramming"), this.new GetMaxEntityCramming());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoWeatherCycle"), this.new GetDoWeatherCycle());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoLimitedCrafting"), this.new GetDoLimitedCrafting());
        this.variableScope.declare(true, new IdentifierExpressionNode("getMaxCommandChainLength"), this.new GetMaxCommandChainLength());
        this.variableScope.declare(true, new IdentifierExpressionNode("getCommandModificationBlockLimit"), this.new GetCommandModificationBlockLimit());
        this.variableScope.declare(true, new IdentifierExpressionNode("getAnnounceAdvancements"), this.new GetAnnounceAdvancements());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDisableRaids"), this.new GetDisableRaids());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoInsomnia"), this.new GetDoInsomnia());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoImmediateRespawn"), this.new GetDoImmediateRespawn());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDrowningDamage"), this.new GetDrowningDamage());
        this.variableScope.declare(true, new IdentifierExpressionNode("getFallDamage"), this.new GetFallDamage());
        this.variableScope.declare(true, new IdentifierExpressionNode("getFireDamage"), this.new GetFireDamage());
        this.variableScope.declare(true, new IdentifierExpressionNode("getFreezeDamage"), this.new GetFreezeDamage());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoPatrolSpawning"), this.new GetDoPatrolSpawning());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoTraderSpawning"), this.new GetDoTraderSpawning());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoWardenSpawning"), this.new GetDoWardenSpawning());
        this.variableScope.declare(true, new IdentifierExpressionNode("getForgiveDeadPlayers"), this.new GetForgiveDeadPlayers());
        this.variableScope.declare(true, new IdentifierExpressionNode("getUniversalAnger"), this.new GetUniversalAnger());
        this.variableScope.declare(true, new IdentifierExpressionNode("getPlayersSleepingPercentage"), this.new GetPlayersSleepingPercentage());
        this.variableScope.declare(true, new IdentifierExpressionNode("getBlockExplosionDropDecay"), this.new GetBlockExplosionDropDecay());
        this.variableScope.declare(true, new IdentifierExpressionNode("getMobExplosionDropDecay"), this.new GetMobExplosionDropDecay());
        this.variableScope.declare(true, new IdentifierExpressionNode("getTntExplosionDropDecay"), this.new GetTntExplosionDropDecay());
        this.variableScope.declare(true, new IdentifierExpressionNode("getSnowAccumulationHeight"), this.new GetSnowAccumulationHeight());
        this.variableScope.declare(true, new IdentifierExpressionNode("getWaterSourceConversion"), this.new GetWaterSourceConversion());
        this.variableScope.declare(true, new IdentifierExpressionNode("getLavaSourceConversion"), this.new GetLavaSourceConversion());
        this.variableScope.declare(true, new IdentifierExpressionNode("getGlobalSoundEvents"), this.new GetGlobalSoundEvents());
        this.variableScope.declare(true, new IdentifierExpressionNode("getDoVinesSpread"), this.new GetDoVinesSpread());
        this.variableScope.declare(true, new IdentifierExpressionNode("getEnderPearlsVanishOnDeath"), this.new GetEnderPearlsVanishOnDeath());
        this.variableScope.declare(true, new IdentifierExpressionNode("getMaxArgumentCount"), this.new GetMaxArgumentCount());
        this.variableScope.declare(true, new IdentifierExpressionNode("getMaxLoops"), this.new GetMaxLoops());
        this.variableScope.declare(true, new IdentifierExpressionNode("getScriptLogsEnabled"), this.new GetScriptLogsEnabled());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoFireTick"), this.new SetDoFireTick());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoMobGriefing"), this.new SetDoMobGriefing());
        this.variableScope.declare(true, new IdentifierExpressionNode("setKeepInventory"), this.new SetKeepInventory());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoMobSpawning"), this.new SetDoMobSpawning());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoMobLoot"), this.new SetDoMobLoot());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoTileDrops"), this.new SetDoTileDrops());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoEntityDrops"), this.new SetDoEntityDrops());
        this.variableScope.declare(true, new IdentifierExpressionNode("setCommandBlockOutput"), this.new SetCommandBlockOutput());
        this.variableScope.declare(true, new IdentifierExpressionNode("setNaturalRegeneration"), this.new SetNaturalRegeneration());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoDaylightCycle"), this.new SetDoDaylightCycle());
        this.variableScope.declare(true, new IdentifierExpressionNode("setLogAdminCommands"), this.new SetLogAdminCommands());
        this.variableScope.declare(true, new IdentifierExpressionNode("setShowDeathMessages"), this.new SetShowDeathMessages());
        this.variableScope.declare(true, new IdentifierExpressionNode("setRandomTickSpeed"), this.new SetRandomTickSpeed());
        this.variableScope.declare(true, new IdentifierExpressionNode("setSendCommandFeedback"), this.new SetSendCommandFeedback());
        this.variableScope.declare(true, new IdentifierExpressionNode("setReducedDebugInfo"), this.new SetReducedDebugInfo());
        this.variableScope.declare(true, new IdentifierExpressionNode("setSpectatorsGenerateChunks"), this.new SetSpectatorsGenerateChunks());
        this.variableScope.declare(true, new IdentifierExpressionNode("setSpawnRadius"), this.new SetSpawnRadius());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDisableElytraMovementCheck"), this.new SetDisableElytraMovementCheck());
        this.variableScope.declare(true, new IdentifierExpressionNode("setMaxEntityCramming"), this.new SetMaxEntityCramming());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoWeatherCycle"), this.new SetDoWeatherCycle());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoLimitedCrafting"), this.new SetDoLimitedCrafting());
        this.variableScope.declare(true, new IdentifierExpressionNode("setMaxCommandChainLength"), this.new SetMaxCommandChainLength());
        this.variableScope.declare(true, new IdentifierExpressionNode("setCommandModificationBlockLimit"), this.new SetCommandModificationBlockLimit());
        this.variableScope.declare(true, new IdentifierExpressionNode("setAnnounceAdvancements"), this.new SetAnnounceAdvancements());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDisableRaids"), this.new SetDisableRaids());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoInsomnia"), this.new SetDoInsomnia());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoImmediateRespawn"), this.new SetDoImmediateRespawn());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDrowningDamage"), this.new SetDrowningDamage());
        this.variableScope.declare(true, new IdentifierExpressionNode("setFallDamage"), this.new SetFallDamage());
        this.variableScope.declare(true, new IdentifierExpressionNode("setFireDamage"), this.new SetFireDamage());
        this.variableScope.declare(true, new IdentifierExpressionNode("setFreezeDamage"), this.new SetFreezeDamage());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoPatrolSpawning"), this.new SetDoPatrolSpawning());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoTraderSpawning"), this.new SetDoTraderSpawning());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoWardenSpawning"), this.new SetDoWardenSpawning());
        this.variableScope.declare(true, new IdentifierExpressionNode("setForgiveDeadPlayers"), this.new SetForgiveDeadPlayers());
        this.variableScope.declare(true, new IdentifierExpressionNode("setUniversalAnger"), this.new SetUniversalAnger());
        this.variableScope.declare(true, new IdentifierExpressionNode("setPlayersSleepingPercentage"), this.new SetPlayersSleepingPercentage());
        this.variableScope.declare(true, new IdentifierExpressionNode("setBlockExplosionDropDecay"), this.new SetBlockExplosionDropDecay());
        this.variableScope.declare(true, new IdentifierExpressionNode("setMobExplosionDropDecay"), this.new SetMobExplosionDropDecay());
        this.variableScope.declare(true, new IdentifierExpressionNode("setTntExplosionDropDecay"), this.new SetTntExplosionDropDecay());
        this.variableScope.declare(true, new IdentifierExpressionNode("setSnowAccumulationHeight"), this.new SetSnowAccumulationHeight());
        this.variableScope.declare(true, new IdentifierExpressionNode("setWaterSourceConversion"), this.new SetWaterSourceConversion());
        this.variableScope.declare(true, new IdentifierExpressionNode("setLavaSourceConversion"), this.new SetLavaSourceConversion());
        this.variableScope.declare(true, new IdentifierExpressionNode("setGlobalSoundEvents"), this.new SetGlobalSoundEvents());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoVinesSpread"), this.new SetDoVinesSpread());
        this.variableScope.declare(true, new IdentifierExpressionNode("setEnderPearlsVanishOnDeath"), this.new SetEnderPearlsVanishOnDeath());
        this.variableScope.declare(true, new IdentifierExpressionNode("setScriptLogsEnabled"), this.new SetScriptLogsEnabled());
        this.variableScope.declare(true, new IdentifierExpressionNode("setMaxArgumentCount"), this.new SetMaxArgumentCount());
        this.variableScope.declare(true, new IdentifierExpressionNode("setMaxLoops"), this.new SetMaxLoops());
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
                throw new SyntaxError("Function 'getAnnounceAdvancements' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.ANNOUNCE_ADVANCEMENTS));
        }
    }

    public class GetBlockExplosionDropDecay extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getBlockExplosionDropDecay' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.BLOCK_EXPLOSION_DROP_DECAY));
        }
    }

    public class GetCommandBlockOutput extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getCommandBlockOutput' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.COMMAND_BLOCK_OUTPUT));
        }
    }

    public class GetCommandModificationBlockLimit extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getCommandModificationBlockLimit' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.COMMAND_MODIFICATION_BLOCK_LIMIT));
        }
    }

    public class GetDisableElytraMovementCheck extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDisableElytraMovementCheck' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK));
        }
    }

    public class GetDisableRaids extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDisableRaids' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DISABLE_RAIDS));
        }
    }

    public class GetDoDaylightCycle extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoDaylightCycle' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_DAYLIGHT_CYCLE));
        }
    }

    public class GetDoEntityDrops extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoEntityDrops' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_ENTITY_DROPS));
        }
    }

    public class GetDoFireTick extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoFireTick' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_FIRE_TICK));
        }
    }

    public class GetDoImmediateRespawn extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoImmediateRespawn' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_IMMEDIATE_RESPAWN));
        }
    }

    public class GetDoInsomnia extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoInsomnia' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_INSOMNIA));
        }
    }

    public class GetDoLimitedCrafting extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoLimitedCrafting' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_LIMITED_CRAFTING));
        }
    }

    public class GetDoMobGriefing extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoMobGriefing' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_MOB_GRIEFING));
        }
    }

    public class GetDoMobLoot extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoMobLoot' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_MOB_LOOT));
        }
    }

    public class GetDoMobSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoMobSpawning' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_MOB_SPAWNING));
        }
    }

    public class GetDoPatrolSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoPatrolSpawning' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_PATROL_SPAWNING));
        }
    }

    public class GetDoTileDrops extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoTileDrops' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_TILE_DROPS));
        }
    }

    public class GetDoTraderSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoTraderSpawning' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_TRADER_SPAWNING));
        }
    }

    public class GetDoVinesSpread extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoVinesSpread' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_VINES_SPREAD));
        }
    }

    public class GetDoWardenSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoWardenSpawning' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_WARDEN_SPAWNING));
        }
    }

    public class GetDoWeatherCycle extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoWeatherCycle' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_WEATHER_CYCLE));
        }
    }

    public class GetDrowningDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDrowningDamage' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.DROWNING_DAMAGE));
        }
    }

    public class GetEnderPearlsVanishOnDeath extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getEnderPearlsVanishOnDeath' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.ENDER_PEARLS_VANISH_ON_DEATH));
        }
    }

    public class GetFallDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getFallDamage' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.FALL_DAMAGE));
        }
    }

    public class GetFireDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getFireDamage' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.FIRE_DAMAGE));
        }
    }

    public class GetForgiveDeadPlayers extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getForgiveDeadPlayers' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.FORGIVE_DEAD_PLAYERS));
        }
    }

    public class GetFreezeDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getFreezeDamage' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.FREEZE_DAMAGE));
        }
    }

    public class GetGlobalSoundEvents extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getGlobalSoundEvents' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.GLOBAL_SOUND_EVENTS));
        }
    }

    public class GetKeepInventory extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getKeepInventory' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.KEEP_INVENTORY));
        }
    }

    public class GetLavaSourceConversion extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getLavaSourceConversion' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.LAVA_SOURCE_CONVERSION));
        }
    }

    public class GetLogAdminCommands extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getLogAdminCommands' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.LOG_ADMIN_COMMANDS));
        }
    }

    public class GetMaxArgumentCount extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getMaxArgumentCount' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(TestingGamerules.MAX_ARGUMENTS));
        }
    }

    public class GetMaxCommandChainLength extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getMaxCommandChainLength' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.MAX_COMMAND_CHAIN_LENGTH));
        }
    }

    public class GetMaxEntityCramming extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getMaxEntityCramming' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.MAX_ENTITY_CRAMMING));
        }
    }

    public class GetMaxLoops extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getMaxLoops' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(TestingGamerules.MAX_LOOPS));
        }
    }

    public class GetMobExplosionDropDecay extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getMobExplosionDropDecay' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.MOB_EXPLOSION_DROP_DECAY));
        }
    }

    public class GetNaturalRegeneration extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getNaturalRegeneration' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.NATURAL_REGENERATION));
        }
    }

    public class GetPlayersSleepingPercentage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getPlayersSleepingPercentage' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.PLAYERS_SLEEPING_PERCENTAGE));
        }
    }

    public class GetRandomTickSpeed extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getRandomTickSpeed' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.RANDOM_TICK_SPEED));
        }
    }

    public class GetReducedDebugInfo extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getReducedDebugInfo' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.REDUCED_DEBUG_INFO));
        }
    }

    public class GetScriptLogsEnabled extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getScriptLogsEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(TestingGamerules.SCRIPT_LOGS_ENABLED));
        }
    }

    public class GetSendCommandFeedback extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getSendCommandFeedback' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.SEND_COMMAND_FEEDBACK));
        }
    }

    public class GetShowDeathMessages extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getShowDeathMessages' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.SHOW_DEATH_MESSAGES));
        }
    }

    public class GetSnowAccumulationHeight extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getSnowAccumulationHeight' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.SNOW_ACCUMULATION_HEIGHT));
        }
    }

    public class GetSpawnRadius extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getSpawnRadius' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(GameRulesClass.this.gameRules.getInt(GameRules.SPAWN_RADIUS));
        }
    }

    public class GetSpectatorsGenerateChunks extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getSpectatorsGenerateChunks' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.SPECTATORS_GENERATE_CHUNKS));
        }
    }

    public class GetTntExplosionDropDecay extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getTntExplosionDropDecay' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.TNT_EXPLOSION_DROP_DECAY));
        }
    }

    public class GetUniversalAnger extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getUniversalAnger' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.UNIVERSAL_ANGER));
        }
    }

    public class GetWaterSourceConversion extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getWaterSourceConversion' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanClass(GameRulesClass.this.gameRules.getBoolean(GameRules.WATER_SOURCE_CONVERSION));
        }
    }

    public class SetAnnounceAdvancements extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setAnnounceAdvancements' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode announceAdvancements = arguments.get(0);

            if (!announceAdvancements.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setAnnounceAdvancements' requires type 'Boolean' but got '" + announceAdvancements.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.ANNOUNCE_ADVANCEMENTS).set(((BooleanClass) announceAdvancements).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetBlockExplosionDropDecay extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setBlockExplosionDropDecay' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode blockExplosionDropDecay = arguments.get(0);

            if (!blockExplosionDropDecay.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setBlockExplosionDropDecay' requires type 'Boolean' but got '" + blockExplosionDropDecay.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.BLOCK_EXPLOSION_DROP_DECAY).set(((BooleanClass) blockExplosionDropDecay).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetCommandBlockOutput extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setCommandBlockOutput' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode commandBlockOutput = arguments.get(0);

            if (!commandBlockOutput.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setCommandBlockOutput' requires type 'Boolean' but got '" + commandBlockOutput.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.COMMAND_BLOCK_OUTPUT).set(((BooleanClass) commandBlockOutput).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetCommandModificationBlockLimit extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setCommandModificationBlockLimit' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode commandModificationBlockLimit = arguments.get(0);

            if (!commandModificationBlockLimit.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'setCommandModificationBlockLimit' requires type 'Integer' but got '" + commandModificationBlockLimit.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.COMMAND_MODIFICATION_BLOCK_LIMIT).set(((IntegerClass) commandModificationBlockLimit).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDisableElytraMovementCheck extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDisableElytraMovementCheck' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode disableElytraMovementCheck = arguments.get(0);

            if (!disableElytraMovementCheck.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDisableElytraMovementCheck' requires type 'Boolean' but got '" + disableElytraMovementCheck.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK).set(((BooleanClass) disableElytraMovementCheck).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDisableRaids extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDisableRaids' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode disableRaids = arguments.get(0);

            if (!disableRaids.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDisableRaids' requires type 'Boolean' but got '" + disableRaids.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DISABLE_RAIDS).set(((BooleanClass) disableRaids).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoDaylightCycle extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoDaylightCycle' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doDaylightCycle = arguments.get(0);

            if (!doDaylightCycle.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoDaylightCycle' requires type 'Boolean' but got '" + doDaylightCycle.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_DAYLIGHT_CYCLE).set(((BooleanClass) doDaylightCycle).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoEntityDrops extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoEntityDrops' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doEntityDrops = arguments.get(0);

            if (!doEntityDrops.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoEntityDrops' requires type 'Boolean' but got '" + doEntityDrops.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_ENTITY_DROPS).set(((BooleanClass) doEntityDrops).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoFireTick extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoFireTick' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doFireTick = arguments.get(0);

            if (!doFireTick.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoFireTick' requires type 'Boolean' but got '" + doFireTick.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_FIRE_TICK).set(((BooleanClass) doFireTick).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoImmediateRespawn extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoImmediateRespawn' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode immediateRespawn = arguments.get(0);

            if (!immediateRespawn.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoImmediateRespawn' requires type 'Boolean' but got '" + immediateRespawn.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_IMMEDIATE_RESPAWN).set(((BooleanClass) immediateRespawn).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoInsomnia extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoInsomnia' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doInsomnia = arguments.get(0);

            if (!doInsomnia.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoInsomnia' requires type 'Boolean' but got '" + doInsomnia.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_INSOMNIA).set(((BooleanClass) doInsomnia).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoLimitedCrafting extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoLimitedCrafting' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doLimitedCrafting = arguments.get(0);

            if (!doLimitedCrafting.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoLimitedCrafting' requires type 'Boolean' but got '" + doLimitedCrafting.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_LIMITED_CRAFTING).set(((BooleanClass) doLimitedCrafting).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoMobGriefing extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoMobGriefing' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doMobGriefing = arguments.get(0);

            if (!doMobGriefing.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoMobGriefing' requires type 'Boolean' but got '" + doMobGriefing.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_MOB_GRIEFING).set(((BooleanClass) doMobGriefing).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoMobLoot extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoMobLoot' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doMobLoot = arguments.get(0);

            if (!doMobLoot.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoMobLoot' requires type 'Boolean' but got '" + doMobLoot.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_MOB_LOOT).set(((BooleanClass) doMobLoot).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoMobSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoMobSpawning' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doMobSpawning = arguments.get(0);

            if (!doMobSpawning.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoMobSpawning' requires type 'Boolean' but got '" + doMobSpawning.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_MOB_SPAWNING).set(((BooleanClass) doMobSpawning).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoPatrolSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoPatrolSpawning' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doPatrolSpawning = arguments.get(0);

            if (!doPatrolSpawning.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoPatrolSpawning' requires type 'Boolean' but got '" + doPatrolSpawning.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_PATROL_SPAWNING).set(((BooleanClass) doPatrolSpawning).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoTileDrops extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoTileDrops' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doTileDrops = arguments.get(0);

            if (!doTileDrops.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoTileDrops' requires type 'Boolean' but got '" + doTileDrops.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_TILE_DROPS).set(((BooleanClass) doTileDrops).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoTraderSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoTraderSpawning' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doTraderSpawning = arguments.get(0);

            if (!doTraderSpawning.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoTraderSpawning' requires type 'Boolean' but got '" + doTraderSpawning.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_TRADER_SPAWNING).set(((BooleanClass) doTraderSpawning).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoVinesSpread extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoVinesSpread' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doVinesSpread = arguments.get(0);

            if (!doVinesSpread.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoVinesSpread' requires type 'Boolean' but got '" + doVinesSpread.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_VINES_SPREAD).set(((BooleanClass) doVinesSpread).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoWardenSpawning extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoWardenSpawning' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doWardenSpawning = arguments.get(0);

            if (!doWardenSpawning.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoWardenSpawning' requires type 'Boolean' but got '" + doWardenSpawning.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_WARDEN_SPAWNING).set(((BooleanClass) doWardenSpawning).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDoWeatherCycle extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoWeatherCycle' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode doWeatherCycle = arguments.get(0);

            if (!doWeatherCycle.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDoWeatherCycle' requires type 'Boolean' but got '" + doWeatherCycle.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_WEATHER_CYCLE).set(((BooleanClass) doWeatherCycle).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetDrowningDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDrowningDamage' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode drowningDamage = arguments.get(0);

            if (!drowningDamage.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setDrowningDamage' requires type 'Boolean' but got '" + drowningDamage.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DROWNING_DAMAGE).set(((BooleanClass) drowningDamage).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetEnderPearlsVanishOnDeath extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setEnderPearlsVanishOnDeath' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode enderPearlsVanishOnDeath = arguments.get(0);

            if (!enderPearlsVanishOnDeath.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setEnderPearlsVanishOnDeath' requires type 'Boolean' but got '" + enderPearlsVanishOnDeath.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.ENDER_PEARLS_VANISH_ON_DEATH).set(((BooleanClass) enderPearlsVanishOnDeath).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetFallDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setFallDamage' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode fallDamage = arguments.get(0);

            if (!fallDamage.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setFallDamage' requires type 'Boolean' but got '" + fallDamage.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.FALL_DAMAGE).set(((BooleanClass) fallDamage).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetFireDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setFireDamage' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode fireDamage = arguments.get(0);

            if (!fireDamage.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setFireDamage' requires type 'Boolean' but got '" + fireDamage.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.FIRE_DAMAGE).set(((BooleanClass) fireDamage).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetForgiveDeadPlayers extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setForgiveDeadPlayers' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode forgiveDeadPlayers = arguments.get(0);

            if (!forgiveDeadPlayers.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setForgiveDeadPlayers' requires type 'Boolean' but got '" + forgiveDeadPlayers.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.FORGIVE_DEAD_PLAYERS).set(((BooleanClass) forgiveDeadPlayers).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetFreezeDamage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setFreezeDamage' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode freezeDamage = arguments.get(0);

            if (!freezeDamage.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setFreezeDamage' requires type 'Boolean' but got '" + freezeDamage.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.FREEZE_DAMAGE).set(((BooleanClass) freezeDamage).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetGlobalSoundEvents extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setGlobalSoundEvents' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode globalSoundEvents = arguments.get(0);

            if (!globalSoundEvents.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setGlobalSoundEvents' requires type 'Boolean' but got '" + globalSoundEvents.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.GLOBAL_SOUND_EVENTS).set(((BooleanClass) globalSoundEvents).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetKeepInventory extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setKeepInventory' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode keepInventory = arguments.get(0);

            if (!keepInventory.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setKeepInventory' requires type 'Boolean' but got '" + keepInventory.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.KEEP_INVENTORY).set(((BooleanClass) keepInventory).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetLavaSourceConversion extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setLavaSourceConversion' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode lavaSourceConversion = arguments.get(0);

            if (!lavaSourceConversion.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setLavaSourceConversion' requires type 'Boolean' but got '" + lavaSourceConversion.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.LAVA_SOURCE_CONVERSION).set(((BooleanClass) lavaSourceConversion).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetLogAdminCommands extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setLogAdminCommands' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode logAdminCommands = arguments.get(0);

            if (!logAdminCommands.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setLogAdminCommands' requires type 'Boolean' but got '" + logAdminCommands.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.LOG_ADMIN_COMMANDS).set(((BooleanClass) logAdminCommands).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetMaxArgumentCount extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setMaxArgumentCount' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode maxArgumentCount = arguments.get(0);

            if (!maxArgumentCount.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'setMaxArgumentCount' requires type 'Integer' but got '" + maxArgumentCount.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(TestingGamerules.MAX_ARGUMENTS).set(((IntegerClass) maxArgumentCount).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetMaxCommandChainLength extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setMaxCommandChainLength' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode maxCommandChainLength = arguments.get(0);

            if (!maxCommandChainLength.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'setMaxCommandChainLength' requires type 'Integer' but got '" + maxCommandChainLength.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.MAX_COMMAND_CHAIN_LENGTH).set(((IntegerClass) maxCommandChainLength).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetMaxEntityCramming extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setMaxEntityCramming' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode maxEntityCramming = arguments.get(0);

            if (!maxEntityCramming.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'setMaxEntityCramming' requires type 'Integer' but got '" + maxEntityCramming.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.MAX_ENTITY_CRAMMING).set(((IntegerClass) maxEntityCramming).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetMaxLoops extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setMaxLoops' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode maxLoops = arguments.get(0);

            if (!maxLoops.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'setMaxLoops' requires type 'Integer' but got '" + maxLoops.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(TestingGamerules.MAX_LOOPS).set(((IntegerClass) maxLoops).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetMobExplosionDropDecay extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setMobExplosionDropDecay' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode mobExplosionDropDecay = arguments.get(0);

            if (!mobExplosionDropDecay.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setMobExplosionDropDecay' requires type 'Boolean' but got '" + mobExplosionDropDecay.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.MOB_EXPLOSION_DROP_DECAY).set(((BooleanClass) mobExplosionDropDecay).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetNaturalRegeneration extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setNaturalRegeneration' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode naturalRegeneration = arguments.get(0);

            if (!naturalRegeneration.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setNaturalRegeneration' requires type 'Boolean' but got '" + naturalRegeneration.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.NATURAL_REGENERATION).set(((BooleanClass) naturalRegeneration).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetPlayersSleepingPercentage extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setPlayersSleepingPercentage' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode playerSleepingPercentage = arguments.get(0);

            if (!playerSleepingPercentage.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'setPlayersSleepingPercentage' requires type 'Integer' but got '" + playerSleepingPercentage.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.PLAYERS_SLEEPING_PERCENTAGE).set(((IntegerClass) playerSleepingPercentage).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetRandomTickSpeed extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setRandomTickSpeed' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode randomTickSpeed = arguments.get(0);

            if (!randomTickSpeed.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'setRandomTickSpeed' requires type 'Integer' but got '" + randomTickSpeed.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.RANDOM_TICK_SPEED).set(((IntegerClass) randomTickSpeed).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetReducedDebugInfo extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setReducedDebugInfo' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode reducedDebugInfo = arguments.get(0);

            if (!reducedDebugInfo.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setReducedDebugInfo' requires type 'Boolean' but got '" + reducedDebugInfo.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.REDUCED_DEBUG_INFO).set(((BooleanClass) reducedDebugInfo).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetScriptLogsEnabled extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setScriptLogsEnabled' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode scriptLogsEnabled = arguments.get(0);

            if (!scriptLogsEnabled.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setScriptLogsEnabled' requires type 'Boolean' but got '" + scriptLogsEnabled.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(TestingGamerules.SCRIPT_LOGS_ENABLED).set(((BooleanClass) scriptLogsEnabled).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetSendCommandFeedback extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSendCommandFeedback' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode sendCommandFeedback = arguments.get(0);

            if (!sendCommandFeedback.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setSendCommandFeedback' requires type 'Boolean' but got '" + sendCommandFeedback.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.SEND_COMMAND_FEEDBACK).set(((BooleanClass) sendCommandFeedback).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetShowDeathMessages extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setShowDeathMessages' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode showDeathMessages = arguments.get(0);

            if (!showDeathMessages.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setShowDeathMessages' requires type 'Boolean' but got '" + showDeathMessages.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.SHOW_DEATH_MESSAGES).set(((BooleanClass) showDeathMessages).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetSnowAccumulationHeight extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSnowAccumulationHeight' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode snowAccumulationHeight = arguments.get(0);

            if (!snowAccumulationHeight.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'setSnowAccumulationHeight' requires type 'Integer' but got '" + snowAccumulationHeight.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.SNOW_ACCUMULATION_HEIGHT).set(((IntegerClass) snowAccumulationHeight).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetSpawnRadius extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSpawnRadius' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode spawnRadius = arguments.get(0);

            if (!spawnRadius.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'setSpawnRadius' requires type 'Integer' but got '" + spawnRadius.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.SPAWN_RADIUS).set(((IntegerClass) spawnRadius).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetSpectatorsGenerateChunks extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSpectatorsGenerateChunks' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode spectatorsGenerateChunks = arguments.get(0);

            if (!spectatorsGenerateChunks.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setSpectatorsGenerateChunks' requires type 'Boolean' but got '" + spectatorsGenerateChunks.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.SPECTATORS_GENERATE_CHUNKS).set(((BooleanClass) spectatorsGenerateChunks).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetTntExplosionDropDecay extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setTntExplosionDropDecay' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode tntExplosionDropDecay = arguments.get(0);

            if (!tntExplosionDropDecay.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setTntExplosionDropDecay' requires type 'Boolean' but got '" + tntExplosionDropDecay.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.TNT_EXPLOSION_DROP_DECAY).set(((BooleanClass) tntExplosionDropDecay).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetUniversalAnger extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setUniversalAnger' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode universalAnger = arguments.get(0);

            if (!universalAnger.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setUniversalAnger' requires type 'Boolean' but got '" + universalAnger.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.UNIVERSAL_ANGER).set(((BooleanClass) universalAnger).value, Testing.server);

            return new NullClass();
        }
    }

    public class SetWaterSourceConversion extends BaseFunctionExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setWaterSourceConversion' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode waterSourceConversion = arguments.get(0);

            if (!waterSourceConversion.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'setWaterSourceConversion' requires type 'Boolean' but got '" + waterSourceConversion.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.WATER_SOURCE_CONVERSION).set(((BooleanClass) waterSourceConversion).value, Testing.server);

            return new NullClass();
        }
    }
}
