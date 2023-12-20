package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Variable;
import com.revolvingmadness.testing.language.interpreter.VariableScope;
import com.revolvingmadness.testing.language.interpreter.errors.ValueError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.l_value_expression_nodes.IdentifierExpressionNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.literal_expression_nodes.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

import java.util.List;

public class ServerPlayerEntityClass extends BuiltinClass {
    public final ServerPlayerEntity serverPlayerEntity;

    public ServerPlayerEntityClass(ServerPlayerEntity serverPlayerEntity) {
        super(new PlayerEntityClass(serverPlayerEntity));

        this.serverPlayerEntity = serverPlayerEntity;

        this.variableScope.declare(true, new IdentifierExpressionNode("changeGameMode"), new ChangeGameMode());
        this.variableScope.declare(true, new IdentifierExpressionNode("dropSelectedItem"), new DropSelectedItem());
        this.variableScope.declare(true, new IdentifierExpressionNode("getIp"), new GetIp());
        this.variableScope.declare(true, new IdentifierExpressionNode("getViewDistance"), new GetViewDistance());
        this.variableScope.declare(true, new IdentifierExpressionNode("setExperienceLevels"), new SetExperienceLevels());
        this.variableScope.declare(true, new IdentifierExpressionNode("setExperiencePoints"), new SetExperiencePoints());
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("ServerPlayerEntity");
    }

    public class ChangeGameMode implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'changeGameMode' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode gameMode = arguments.get(0).interpret(script);

            if (!gameMode.getType().equals(new IdentifierExpressionNode("string"))) {
                throw new TypeError("Argument 1 for function 'changeGameMode' requires type 'string' but got '" + gameMode.getType() + "'");
            }

            GameMode gameMode1 = GameMode.byName(((StringExpressionNode) gameMode).value, null);

            if (gameMode1 == null) {
                throw new ValueError("Gamemode '" + gameMode + "' does not exist");
            }

            ServerPlayerEntityClass.this.serverPlayerEntity.changeGameMode(gameMode1);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class DropSelectedItem implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'dropSelectedItem' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode entireStack = arguments.get(0).interpret(script);

            if (!entireStack.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'dropSelectedItem' requires type 'boolean' but got '" + entireStack.getType() + "'");
            }

            ServerPlayerEntityClass.this.serverPlayerEntity.dropSelectedItem(((BooleanExpressionNode) entireStack).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class GetIp implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getIp' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringExpressionNode(ServerPlayerEntityClass.this.serverPlayerEntity.getIp());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class GetViewDistance implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getViewDistance' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(ServerPlayerEntityClass.this.serverPlayerEntity.getViewDistance());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SetExperienceLevels implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setExperienceLevels' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode experienceLevel = arguments.get(0).interpret(script);

            if (!experienceLevel.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setExperienceLevels' requires type 'int' but got '" + experienceLevel.getType() + "'");
            }

            ServerPlayerEntityClass.this.serverPlayerEntity.setExperienceLevel(((IntegerExpressionNode) experienceLevel).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    public class SetExperiencePoints implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setExperiencePoints' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode experiencePoints = arguments.get(0).interpret(script);

            if (!experiencePoints.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setExperiencePoints' requires type 'int' but got '" + experiencePoints.getType() + "'");
            }

            ServerPlayerEntityClass.this.serverPlayerEntity.setExperiencePoints(((IntegerExpressionNode) experiencePoints).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }
}
