package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.BooleanExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.IntegerExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.LiteralExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.NullExpressionNode;
import net.minecraft.world.GameRules;

import java.util.List;

public class GameRulesClass implements LiteralExpressionNode {
    public final GameRules gameRules;
    public final VariableScope variableScope;

    public GameRulesClass() {
        this.variableScope = new VariableScope();
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
        this.variableScope.declare(true, new IdentifierExpressionNode("getTraderSpawning"), this.new GetDoTraderSpawning());
        this.variableScope.declare(true, new IdentifierExpressionNode("getWardenSpawning"), this.new GetDoWardenSpawning());
        this.variableScope.declare(true, new IdentifierExpressionNode("getForgiveDeadPlayers"), this.new GetForgiveDeadPlayers());
        this.variableScope.declare(true, new IdentifierExpressionNode("getUniversalAnger"), this.new GetUniversalAnger());
        this.variableScope.declare(true, new IdentifierExpressionNode("getPlayersSleepingPercentage"), this.new GetPlayerSleepingPercentage());
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
        this.variableScope.declare(true, new IdentifierExpressionNode("setTraderSpawning"), this.new SetDoTraderSpawning());
        this.variableScope.declare(true, new IdentifierExpressionNode("setWardenSpawning"), this.new SetDoWardenSpawning());
        this.variableScope.declare(true, new IdentifierExpressionNode("setForgiveDeadPlayers"), this.new SetForgiveDeadPlayers());
        this.variableScope.declare(true, new IdentifierExpressionNode("setUniversalAnger"), this.new SetUniversalAnger());
        this.variableScope.declare(true, new IdentifierExpressionNode("setPlayersSleepingPercentage"), this.new SetPlayerSleepingPercentage());
        this.variableScope.declare(true, new IdentifierExpressionNode("setBlockExplosionDropDecay"), this.new SetBlockExplosionDropDecay());
        this.variableScope.declare(true, new IdentifierExpressionNode("setMobExplosionDropDecay"), this.new SetMobExplosionDropDecay());
        this.variableScope.declare(true, new IdentifierExpressionNode("setTntExplosionDropDecay"), this.new SetTntExplosionDropDecay());
        this.variableScope.declare(true, new IdentifierExpressionNode("setSnowAccumulationHeight"), this.new SetSnowAccumulationHeight());
        this.variableScope.declare(true, new IdentifierExpressionNode("setWaterSourceConversion"), this.new SetWaterSourceConversion());
        this.variableScope.declare(true, new IdentifierExpressionNode("setLavaSourceConversion"), this.new SetLavaSourceConversion());
        this.variableScope.declare(true, new IdentifierExpressionNode("setGlobalSoundEvents"), this.new SetGlobalSoundEvents());
        this.variableScope.declare(true, new IdentifierExpressionNode("setDoVinesSpread"), this.new SetDoVinesSpread());
        this.variableScope.declare(true, new IdentifierExpressionNode("setEnderPearlsVanishOnDeath"), this.new SetEnderPearlsVanishOnDeath());
        this.variableScope.declare(true, new IdentifierExpressionNode("setMaxArgumentCount"), this.new SetEnderPearlsVanishOnDeath());
        this.variableScope.declare(true, new IdentifierExpressionNode("setMaxLoops"), this.new SetEnderPearlsVanishOnDeath());
        this.variableScope.declare(true, new IdentifierExpressionNode("setScriptsLogsEnabled"), this.new SetEnderPearlsVanishOnDeath());
        this.variableScope.declare(true, new IdentifierExpressionNode("setMaxArgumentCount"), this.new SetMaxArgumentCount());
        this.variableScope.declare(true, new IdentifierExpressionNode("setMaxLoops"), this.new SetMaxLoops());
        this.variableScope.declare(true, new IdentifierExpressionNode("setScriptLogsEnabled"), this.new SetScriptLogsEnabled());
    }

    @Override
    public Variable getProperty(IdentifierExpressionNode propertyName) {
        return this.variableScope.getOrThrow(propertyName);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("GameRules");
    }

    private class GetAnnounceAdvancements implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getAnnounceAdvancements' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.ANNOUNCE_ADVANCEMENTS));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetBlockExplosionDropDecay implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getBlockExplosionDropDecay' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.BLOCK_EXPLOSION_DROP_DECAY));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetCommandBlockOutput implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getCommandBlockOutput' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.COMMAND_BLOCK_OUTPUT));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetCommandModificationBlockLimit implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getCommandModificationBlockLimit' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(GameRulesClass.this.gameRules.getInt(GameRules.COMMAND_MODIFICATION_BLOCK_LIMIT));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDisableElytraMovementCheck implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDisableElytraMovementCheck' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDisableRaids implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDisableRaids' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DISABLE_RAIDS));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoDaylightCycle implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoDaylightCycle' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_DAYLIGHT_CYCLE));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoEntityDrops implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoEntityDrops' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_ENTITY_DROPS));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoFireTick implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoFireTick' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_FIRE_TICK));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoImmediateRespawn implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoImmediateRespawn' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_IMMEDIATE_RESPAWN));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoInsomnia implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoInsomnia' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_INSOMNIA));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoLimitedCrafting implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoLimitedCrafting' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_LIMITED_CRAFTING));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoMobGriefing implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoMobGriefing' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_MOB_GRIEFING));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoMobLoot implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoMobLoot' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_MOB_LOOT));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoMobSpawning implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoMobSpawning' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_MOB_SPAWNING));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoPatrolSpawning implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoPatrolSpawning' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_PATROL_SPAWNING));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoTileDrops implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoTileDrops' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_TILE_DROPS));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoTraderSpawning implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoTraderSpawning' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_TRADER_SPAWNING));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoVinesSpread implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoVinesSpread' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_VINES_SPREAD));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoWardenSpawning implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoWardenSpawning' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_WARDEN_SPAWNING));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDoWeatherCycle implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDoWeatherCycle' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DO_WEATHER_CYCLE));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetDrowningDamage implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getDrowningDamage' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.DROWNING_DAMAGE));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetEnderPearlsVanishOnDeath implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getEnderPearlsVanishOnDeath' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.ENDER_PEARLS_VANISH_ON_DEATH));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetFallDamage implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getFallDamage' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.FALL_DAMAGE));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetFireDamage implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getFireDamage' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.FIRE_DAMAGE));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetForgiveDeadPlayers implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getForgiveDeadPlayers' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.FORGIVE_DEAD_PLAYERS));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetFreezeDamage implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getFreezeDamage' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.FREEZE_DAMAGE));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetGlobalSoundEvents implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getGlobalSoundEvents' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.GLOBAL_SOUND_EVENTS));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetKeepInventory implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getKeepInventory' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.KEEP_INVENTORY));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetLavaSourceConversion implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getLavaSourceConversion' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.LAVA_SOURCE_CONVERSION));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetLogAdminCommands implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getLogAdminCommands' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.LOG_ADMIN_COMMANDS));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetMaxArgumentCount implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getMaxArgumentCount' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(GameRulesClass.this.gameRules.getInt(TestingGamerules.MAX_ARGUMENT_COUNT));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetMaxCommandChainLength implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getMaxCommandChainLength' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(GameRulesClass.this.gameRules.getInt(GameRules.MAX_COMMAND_CHAIN_LENGTH));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetMaxEntityCramming implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getMaxEntityCramming' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(GameRulesClass.this.gameRules.getInt(GameRules.MAX_ENTITY_CRAMMING));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetMaxLoops implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getMaxLoops' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(GameRulesClass.this.gameRules.getInt(TestingGamerules.MAX_LOOPS));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetMobExplosionDropDecay implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getMobExplosionDropDecay' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.MOB_EXPLOSION_DROP_DECAY));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetNaturalRegeneration implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getNaturalRegeneration' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.NATURAL_REGENERATION));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetPlayerSleepingPercentage implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getPlayersSleepingPercentage' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(GameRulesClass.this.gameRules.getInt(GameRules.PLAYERS_SLEEPING_PERCENTAGE));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetRandomTickSpeed implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getRandomTickSpeed' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(GameRulesClass.this.gameRules.getInt(GameRules.RANDOM_TICK_SPEED));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetReducedDebugInfo implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getReducedDebugInfo' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.REDUCED_DEBUG_INFO));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetScriptLogsEnabled implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getScriptLogsEnabled' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(TestingGamerules.SCRIPT_LOGS_ENABLED));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetSendCommandFeedback implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getSendCommandFeedback' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.SEND_COMMAND_FEEDBACK));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetShowDeathMessages implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getShowDeathMessages' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.SHOW_DEATH_MESSAGES));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetSnowAccumulationHeight implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getSnowAccumulationHeight' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(GameRulesClass.this.gameRules.getInt(GameRules.SNOW_ACCUMULATION_HEIGHT));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetSpawnRadius implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getSpawnRadius' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(GameRulesClass.this.gameRules.getInt(GameRules.SPAWN_RADIUS));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetSpectatorsGenerateChunks implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getSpectatorsGenerateChunks' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.SPECTATORS_GENERATE_CHUNKS));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetTntExplosionDropDecay implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getTntExplosionDropDecay' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.TNT_EXPLOSION_DROP_DECAY));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetUniversalAnger implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getUniversalAnger' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.UNIVERSAL_ANGER));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetWaterSourceConversion implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getWaterSourceConversion' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(GameRulesClass.this.gameRules.getBoolean(GameRules.WATER_SOURCE_CONVERSION));
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetAnnounceAdvancements implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setAnnounceAdvancements' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode announceAdvancements = arguments.get(0).interpret(script);

            if (!announceAdvancements.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setAnnounceAdvancements' requires type 'boolean' but got '" + announceAdvancements.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.ANNOUNCE_ADVANCEMENTS).set(((BooleanExpressionNode) announceAdvancements).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetBlockExplosionDropDecay implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setBlockExplosionDropDecay' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode blockExplosionDropDecay = arguments.get(0).interpret(script);

            if (!blockExplosionDropDecay.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setBlockExplosionDropDecay' requires type 'boolean' but got '" + blockExplosionDropDecay.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.BLOCK_EXPLOSION_DROP_DECAY).set(((BooleanExpressionNode) blockExplosionDropDecay).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetCommandBlockOutput implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setCommandBlockOutput' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode commandBlockOutput = arguments.get(0).interpret(script);

            if (!commandBlockOutput.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setCommandBlockOutput' requires type 'boolean' but got '" + commandBlockOutput.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.COMMAND_BLOCK_OUTPUT).set(((BooleanExpressionNode) commandBlockOutput).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetCommandModificationBlockLimit implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setCommandModificationBlockLimit' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode commandModificationBlockLimit = arguments.get(0).interpret(script);

            if (!commandModificationBlockLimit.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setCommandModificationBlockLimit' requires type 'int' but got '" + commandModificationBlockLimit.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.COMMAND_MODIFICATION_BLOCK_LIMIT).set(((IntegerExpressionNode) commandModificationBlockLimit).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDisableElytraMovementCheck implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDisableElytraMovementCheck' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode disableElytraMovementCheck = arguments.get(0).interpret(script);

            if (!disableElytraMovementCheck.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDisableElytraMovementCheck' requires type 'boolean' but got '" + disableElytraMovementCheck.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK).set(((BooleanExpressionNode) disableElytraMovementCheck).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDisableRaids implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDisableRaids' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode disableRaids = arguments.get(0).interpret(script);

            if (!disableRaids.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDisableRaids' requires type 'boolean' but got '" + disableRaids.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DISABLE_RAIDS).set(((BooleanExpressionNode) disableRaids).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoDaylightCycle implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoDaylightCycle' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doDaylightCycle = arguments.get(0).interpret(script);

            if (!doDaylightCycle.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoDaylightCycle' requires type 'boolean' but got '" + doDaylightCycle.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_DAYLIGHT_CYCLE).set(((BooleanExpressionNode) doDaylightCycle).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoEntityDrops implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoEntityDrops' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doEntityDrops = arguments.get(0).interpret(script);

            if (!doEntityDrops.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoEntityDrops' requires type 'boolean' but got '" + doEntityDrops.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_ENTITY_DROPS).set(((BooleanExpressionNode) doEntityDrops).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoFireTick implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoFireTick' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doFireTick = arguments.get(0).interpret(script);

            if (!doFireTick.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoFireTick' requires type 'boolean' but got '" + doFireTick.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_FIRE_TICK).set(((BooleanExpressionNode) doFireTick).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoImmediateRespawn implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoImmediateRespawn' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode immediateRespawn = arguments.get(0).interpret(script);

            if (!immediateRespawn.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoImmediateRespawn' requires type 'boolean' but got '" + immediateRespawn.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_IMMEDIATE_RESPAWN).set(((BooleanExpressionNode) immediateRespawn).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoInsomnia implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoInsomnia' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doInsomnia = arguments.get(0).interpret(script);

            if (!doInsomnia.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoInsomnia' requires type 'boolean' but got '" + doInsomnia.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_INSOMNIA).set(((BooleanExpressionNode) doInsomnia).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoLimitedCrafting implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoLimitedCrafting' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doLimitedCrafting = arguments.get(0).interpret(script);

            if (!doLimitedCrafting.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoLimitedCrafting' requires type 'boolean' but got '" + doLimitedCrafting.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_LIMITED_CRAFTING).set(((BooleanExpressionNode) doLimitedCrafting).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoMobGriefing implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoMobGriefing' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doMobGriefing = arguments.get(0).interpret(script);

            if (!doMobGriefing.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoMobGriefing' requires type 'boolean' but got '" + doMobGriefing.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_MOB_GRIEFING).set(((BooleanExpressionNode) doMobGriefing).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoMobLoot implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoMobLoot' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doMobLoot = arguments.get(0).interpret(script);

            if (!doMobLoot.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoMobLoot' requires type 'boolean' but got '" + doMobLoot.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_MOB_LOOT).set(((BooleanExpressionNode) doMobLoot).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoMobSpawning implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoMobSpawning' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doMobSpawning = arguments.get(0).interpret(script);

            if (!doMobSpawning.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoMobSpawning' requires type 'boolean' but got '" + doMobSpawning.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_MOB_SPAWNING).set(((BooleanExpressionNode) doMobSpawning).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoPatrolSpawning implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoPatrolSpawning' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doPatrolSpawning = arguments.get(0).interpret(script);

            if (!doPatrolSpawning.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoPatrolSpawning' requires type 'boolean' but got '" + doPatrolSpawning.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_PATROL_SPAWNING).set(((BooleanExpressionNode) doPatrolSpawning).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoTileDrops implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoTileDrops' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doTileDrops = arguments.get(0).interpret(script);

            if (!doTileDrops.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoTileDrops' requires type 'boolean' but got '" + doTileDrops.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_TILE_DROPS).set(((BooleanExpressionNode) doTileDrops).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoTraderSpawning implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoTraderSpawning' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doTraderSpawning = arguments.get(0).interpret(script);

            if (!doTraderSpawning.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoTraderSpawning' requires type 'boolean' but got '" + doTraderSpawning.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_TRADER_SPAWNING).set(((BooleanExpressionNode) doTraderSpawning).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoVinesSpread implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoVinesSpread' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doVinesSpread = arguments.get(0).interpret(script);

            if (!doVinesSpread.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoVinesSpread' requires type 'boolean' but got '" + doVinesSpread.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_VINES_SPREAD).set(((BooleanExpressionNode) doVinesSpread).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoWardenSpawning implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoWardenSpawning' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doWardenSpawning = arguments.get(0).interpret(script);

            if (!doWardenSpawning.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoWardenSpawning' requires type 'boolean' but got '" + doWardenSpawning.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_WARDEN_SPAWNING).set(((BooleanExpressionNode) doWardenSpawning).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDoWeatherCycle implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDoWeatherCycle' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode doWeatherCycle = arguments.get(0).interpret(script);

            if (!doWeatherCycle.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDoWeatherCycle' requires type 'boolean' but got '" + doWeatherCycle.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DO_WEATHER_CYCLE).set(((BooleanExpressionNode) doWeatherCycle).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetDrowningDamage implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setDrowningDamage' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode drowningDamage = arguments.get(0).interpret(script);

            if (!drowningDamage.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setDrowningDamage' requires type 'boolean' but got '" + drowningDamage.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.DROWNING_DAMAGE).set(((BooleanExpressionNode) drowningDamage).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetEnderPearlsVanishOnDeath implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setEnderPearlsVanishOnDeath' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode enderPearlsVanishOnDeath = arguments.get(0).interpret(script);

            if (!enderPearlsVanishOnDeath.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setEnderPearlsVanishOnDeath' requires type 'boolean' but got '" + enderPearlsVanishOnDeath.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.ENDER_PEARLS_VANISH_ON_DEATH).set(((BooleanExpressionNode) enderPearlsVanishOnDeath).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetFallDamage implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setFallDamage' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode fallDamage = arguments.get(0).interpret(script);

            if (!fallDamage.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setFallDamage' requires type 'boolean' but got '" + fallDamage.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.FALL_DAMAGE).set(((BooleanExpressionNode) fallDamage).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetFireDamage implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setFireDamage' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode fireDamage = arguments.get(0).interpret(script);

            if (!fireDamage.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setFireDamage' requires type 'boolean' but got '" + fireDamage.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.FIRE_DAMAGE).set(((BooleanExpressionNode) fireDamage).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetForgiveDeadPlayers implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setForgiveDeadPlayers' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode forgiveDeadPlayers = arguments.get(0).interpret(script);

            if (!forgiveDeadPlayers.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setForgiveDeadPlayers' requires type 'boolean' but got '" + forgiveDeadPlayers.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.FORGIVE_DEAD_PLAYERS).set(((BooleanExpressionNode) forgiveDeadPlayers).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetFreezeDamage implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setFreezeDamage' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode freezeDamage = arguments.get(0).interpret(script);

            if (!freezeDamage.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setFreezeDamage' requires type 'boolean' but got '" + freezeDamage.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.FREEZE_DAMAGE).set(((BooleanExpressionNode) freezeDamage).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetGlobalSoundEvents implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setGlobalSoundEvents' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode globalSoundEvents = arguments.get(0).interpret(script);

            if (!globalSoundEvents.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setGlobalSoundEvents' requires type 'boolean' but got '" + globalSoundEvents.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.GLOBAL_SOUND_EVENTS).set(((BooleanExpressionNode) globalSoundEvents).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetKeepInventory implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setKeepInventory' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode keepInventory = arguments.get(0).interpret(script);

            if (!keepInventory.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setKeepInventory' requires type 'boolean' but got '" + keepInventory.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.KEEP_INVENTORY).set(((BooleanExpressionNode) keepInventory).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetLavaSourceConversion implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setLavaSourceConversion' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode lavaSourceConversion = arguments.get(0).interpret(script);

            if (!lavaSourceConversion.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setLavaSourceConversion' requires type 'boolean' but got '" + lavaSourceConversion.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.LAVA_SOURCE_CONVERSION).set(((BooleanExpressionNode) lavaSourceConversion).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetLogAdminCommands implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setLogAdminCommands' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode logAdminCommands = arguments.get(0).interpret(script);

            if (!logAdminCommands.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setLogAdminCommands' requires type 'boolean' but got '" + logAdminCommands.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.LOG_ADMIN_COMMANDS).set(((BooleanExpressionNode) logAdminCommands).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetMaxArgumentCount implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setMaxArgumentCount' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode maxArgumentCount = arguments.get(0).interpret(script);

            if (!maxArgumentCount.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setMaxArgumentCount' requires type 'int' but got '" + maxArgumentCount.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(TestingGamerules.MAX_ARGUMENT_COUNT).set(((IntegerExpressionNode) maxArgumentCount).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetMaxCommandChainLength implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setMaxCommandChainLength' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode maxCommandChainLength = arguments.get(0).interpret(script);

            if (!maxCommandChainLength.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setMaxCommandChainLength' requires type 'int' but got '" + maxCommandChainLength.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.MAX_COMMAND_CHAIN_LENGTH).set(((IntegerExpressionNode) maxCommandChainLength).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetMaxEntityCramming implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setMaxEntityCramming' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode maxEntityCramming = arguments.get(0).interpret(script);

            if (!maxEntityCramming.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setMaxEntityCramming' requires type 'int' but got '" + maxEntityCramming.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.MAX_ENTITY_CRAMMING).set(((IntegerExpressionNode) maxEntityCramming).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetMaxLoops implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setMaxLoops' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode maxLoops = arguments.get(0).interpret(script);

            if (!maxLoops.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setMaxLoops' requires type 'int' but got '" + maxLoops.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(TestingGamerules.MAX_LOOPS).set(((IntegerExpressionNode) maxLoops).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetMobExplosionDropDecay implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setMobExplosionDropDecay' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode mobExplosionDropDecay = arguments.get(0).interpret(script);

            if (!mobExplosionDropDecay.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setMobExplosionDropDecay' requires type 'boolean' but got '" + mobExplosionDropDecay.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.MOB_EXPLOSION_DROP_DECAY).set(((BooleanExpressionNode) mobExplosionDropDecay).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetNaturalRegeneration implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setNaturalRegeneration' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode naturalRegeneration = arguments.get(0).interpret(script);

            if (!naturalRegeneration.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setNaturalRegeneration' requires type 'boolean' but got '" + naturalRegeneration.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.NATURAL_REGENERATION).set(((BooleanExpressionNode) naturalRegeneration).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetPlayerSleepingPercentage implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setPlayersSleepingPercentage' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode playerSleepingPercentage = arguments.get(0).interpret(script);

            if (!playerSleepingPercentage.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setPlayersSleepingPercentage' requires type 'int' but got '" + playerSleepingPercentage.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.PLAYERS_SLEEPING_PERCENTAGE).set(((IntegerExpressionNode) playerSleepingPercentage).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetRandomTickSpeed implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setRandomTickSpeed' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode randomTickSpeed = arguments.get(0).interpret(script);

            if (!randomTickSpeed.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setRandomTickSpeed' requires type 'int' but got '" + randomTickSpeed.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.RANDOM_TICK_SPEED).set(((IntegerExpressionNode) randomTickSpeed).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetReducedDebugInfo implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setReducedDebugInfo' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode reducedDebugInfo = arguments.get(0).interpret(script);

            if (!reducedDebugInfo.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setReducedDebugInfo' requires type 'boolean' but got '" + reducedDebugInfo.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.REDUCED_DEBUG_INFO).set(((BooleanExpressionNode) reducedDebugInfo).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetScriptLogsEnabled implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setScriptLogsEnabled' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode scriptLogsEnabled = arguments.get(0).interpret(script);

            if (!scriptLogsEnabled.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setScriptLogsEnabled' requires type 'boolean' but got '" + scriptLogsEnabled.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(TestingGamerules.SCRIPT_LOGS_ENABLED).set(((BooleanExpressionNode) scriptLogsEnabled).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetSendCommandFeedback implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSendCommandFeedback' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode sendCommandFeedback = arguments.get(0).interpret(script);

            if (!sendCommandFeedback.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setSendCommandFeedback' requires type 'boolean' but got '" + sendCommandFeedback.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.SEND_COMMAND_FEEDBACK).set(((BooleanExpressionNode) sendCommandFeedback).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetShowDeathMessages implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setShowDeathMessages' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode showDeathMessages = arguments.get(0).interpret(script);

            if (!showDeathMessages.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setShowDeathMessages' requires type 'boolean' but got '" + showDeathMessages.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.SHOW_DEATH_MESSAGES).set(((BooleanExpressionNode) showDeathMessages).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetSnowAccumulationHeight implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSnowAccumulationHeight' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode snowAccumulationHeight = arguments.get(0).interpret(script);

            if (!snowAccumulationHeight.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setSnowAccumulationHeight' requires type 'int' but got '" + snowAccumulationHeight.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.SNOW_ACCUMULATION_HEIGHT).set(((IntegerExpressionNode) snowAccumulationHeight).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetSpawnRadius implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSpawnRadius' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode spawnRadius = arguments.get(0).interpret(script);

            if (!spawnRadius.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setSpawnRadius' requires type 'int' but got '" + spawnRadius.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.SPAWN_RADIUS).set(((IntegerExpressionNode) spawnRadius).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetSpectatorsGenerateChunks implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setSpectatorsGenerateChunks' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode spectatorsGenerateChunks = arguments.get(0).interpret(script);

            if (!spectatorsGenerateChunks.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setSpectatorsGenerateChunks' requires type 'boolean' but got '" + spectatorsGenerateChunks.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.SPECTATORS_GENERATE_CHUNKS).set(((BooleanExpressionNode) spectatorsGenerateChunks).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetTntExplosionDropDecay implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setTntExplosionDropDecay' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode tntExplosionDropDecay = arguments.get(0).interpret(script);

            if (!tntExplosionDropDecay.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setTntExplosionDropDecay' requires type 'boolean' but got '" + tntExplosionDropDecay.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.TNT_EXPLOSION_DROP_DECAY).set(((BooleanExpressionNode) tntExplosionDropDecay).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetUniversalAnger implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setUniversalAnger' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode universalAnger = arguments.get(0).interpret(script);

            if (!universalAnger.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setUniversalAnger' requires type 'boolean' but got '" + universalAnger.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.UNIVERSAL_ANGER).set(((BooleanExpressionNode) universalAnger).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetWaterSourceConversion implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setWaterSourceConversion' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode waterSourceConversion = arguments.get(0).interpret(script);

            if (!waterSourceConversion.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'setWaterSourceConversion' requires type 'boolean' but got '" + waterSourceConversion.getType() + "'");
            }

            GameRulesClass.this.gameRules.get(GameRules.WATER_SOURCE_CONVERSION).set(((BooleanExpressionNode) waterSourceConversion).value, Testing.server);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }
}
