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
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;

import java.util.List;

public class PlayerClass implements LiteralExpressionNode {
    public final VariableScope variableScope;
    private final ServerPlayerEntity serverPlayerEntity;

    public PlayerClass(ServerPlayerEntity serverPlayerEntity) {
        this.serverPlayerEntity = serverPlayerEntity;
        this.variableScope = new VariableScope();
        this.variableScope.declare(true, new IdentifierExpressionNode("addExperiencePoints"), new AddExperiencePoints());
        this.variableScope.declare(true, new IdentifierExpressionNode("addExperienceLevels"), new AddExperienceLevels());
        this.variableScope.declare(true, new IdentifierExpressionNode("changeGameMode"), new ChangeGameMode());
        this.variableScope.declare(true, new IdentifierExpressionNode("dropSelectedItem"), new DropSelectedItem());
        this.variableScope.declare(true, new IdentifierExpressionNode("getIp"), new GetIp());
        this.variableScope.declare(true, new IdentifierExpressionNode("getViewDistance"), new GetViewDistance());
        this.variableScope.declare(true, new IdentifierExpressionNode("isCreative"), new IsCreative());
        this.variableScope.declare(true, new IdentifierExpressionNode("isSpectator"), new IsSpectator());
        this.variableScope.declare(true, new IdentifierExpressionNode("sendMessage"), new SendMessage());
        this.variableScope.declare(true, new IdentifierExpressionNode("setExperienceLevels"), new SetExperienceLevels());
        this.variableScope.declare(true, new IdentifierExpressionNode("setExperiencePoints"), new SetExperiencePoints());
        this.variableScope.declare(true, new IdentifierExpressionNode("tiltScreen"), new TiltScreen());
        this.variableScope.declare(true, new IdentifierExpressionNode("wakeUp"), new WakeUp());
    }

    @Override
    public Variable getProperty(IdentifierExpressionNode propertyName) {
        return this.variableScope.getOrThrow(propertyName);
    }

    @Override
    public IdentifierExpressionNode getType() {
        return new IdentifierExpressionNode("Player");
    }

    private class AddExperienceLevels implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'addExperienceLevels' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode experienceLevels = arguments.get(0).interpret(script);

            if (!experienceLevels.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'addExperienceLevels' requires type 'int' but got '" + experienceLevels.getType() + "'");
            }

            PlayerClass.this.serverPlayerEntity.addExperienceLevels(((IntegerExpressionNode) experienceLevels).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class AddExperiencePoints implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'addExperiencePoints' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode experience = arguments.get(0).interpret(script);

            if (!experience.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'addExperiencePoints' requires type 'int' but got '" + experience.getType() + "'");
            }

            PlayerClass.this.serverPlayerEntity.addExperience(((IntegerExpressionNode) experience).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class ChangeGameMode implements LiteralExpressionNode {
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
                throw new ValueError("Unknown gamemode '" + gameMode + "'");
            }

            PlayerClass.this.serverPlayerEntity.changeGameMode(gameMode1);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class DropSelectedItem implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'dropSelectedItem' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode entireStack = arguments.get(0).interpret(script);

            if (!entireStack.getType().equals(new IdentifierExpressionNode("boolean"))) {
                throw new TypeError("Argument 1 for function 'dropSelectedItem' requires type 'boolean' but got '" + entireStack.getType() + "'");
            }

            PlayerClass.this.serverPlayerEntity.dropSelectedItem(((BooleanExpressionNode) entireStack).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetIp implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getIp' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringExpressionNode(PlayerClass.this.serverPlayerEntity.getIp());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class GetViewDistance implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getViewDistance' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerExpressionNode(PlayerClass.this.serverPlayerEntity.getViewDistance());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class IsCreative implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isCreative' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(PlayerClass.this.serverPlayerEntity.isCreative());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class IsSpectator implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'isSpectator' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new BooleanExpressionNode(PlayerClass.this.serverPlayerEntity.isSpectator());
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SendMessage implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'sendMessage' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode message = arguments.get(0).interpret(script);

            if (!message.getType().equals(new IdentifierExpressionNode("string"))) {
                throw new TypeError("Argument 1 for function 'sendMessage' requires type 'string' but got '" + message.getType() + "'");
            }

            PlayerClass.this.serverPlayerEntity.sendMessage(Text.literal(((StringExpressionNode) message).value));

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetExperienceLevels implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setExperienceLevels' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode experienceLevel = arguments.get(0).interpret(script);

            if (!experienceLevel.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setExperienceLevels' requires type 'int' but got '" + experienceLevel.getType() + "'");
            }

            PlayerClass.this.serverPlayerEntity.setExperienceLevel(((IntegerExpressionNode) experienceLevel).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class SetExperiencePoints implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setExperiencePoints' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode experiencePoints = arguments.get(0).interpret(script);

            if (!experiencePoints.getType().equals(new IdentifierExpressionNode("int"))) {
                throw new TypeError("Argument 1 for function 'setExperiencePoints' requires type 'int' but got '" + experiencePoints.getType() + "'");
            }

            PlayerClass.this.serverPlayerEntity.setExperiencePoints(((IntegerExpressionNode) experiencePoints).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class TiltScreen implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 2) {
                throw new SyntaxError("Function 'tiltScreen' takes 2 arguments but got " + arguments.size() + " argument(s)");
            }

            LiteralExpressionNode deltaX = arguments.get(0).interpret(script);

            if (!deltaX.getType().equals(new IdentifierExpressionNode("float"))) {
                throw new TypeError("Argument 1 for function 'tiltScreen' requires type 'float' but got '" + deltaX.getType() + "'");
            }

            LiteralExpressionNode deltaZ = arguments.get(1).interpret(script);

            if (!deltaZ.getType().equals(new IdentifierExpressionNode("float"))) {
                throw new TypeError("Argument 2 for function 'tiltScreen' requires type 'float' but got '" + deltaZ.getType() + "'");
            }

            PlayerClass.this.serverPlayerEntity.tiltScreen(((FloatExpressionNode) deltaZ).value, ((FloatExpressionNode) deltaZ).value);

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }

    private class WakeUp implements LiteralExpressionNode {
        @Override
        public LiteralExpressionNode call(ScriptNode script, List<ExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'wakeUp' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            PlayerClass.this.serverPlayerEntity.wakeUp();

            return new NullExpressionNode();
        }

        @Override
        public IdentifierExpressionNode getType() {
            return new IdentifierExpressionNode("function");
        }
    }
}
