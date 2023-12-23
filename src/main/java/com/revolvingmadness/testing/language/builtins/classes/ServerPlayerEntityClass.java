package com.revolvingmadness.testing.language.builtins.classes;

import com.revolvingmadness.testing.language.builtins.classes.types.BooleanClass;
import com.revolvingmadness.testing.language.builtins.classes.types.IntegerClass;
import com.revolvingmadness.testing.language.builtins.classes.types.NullClass;
import com.revolvingmadness.testing.language.builtins.classes.types.StringClass;
import com.revolvingmadness.testing.language.errors.SyntaxError;
import com.revolvingmadness.testing.language.errors.TypeError;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.interpreter.errors.ValueError;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.IdentifierExpressionNode;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

import java.util.List;
import java.util.Objects;

public class ServerPlayerEntityClass extends BaseClassExpressionNode {
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
        this.variableScope.declare(true, new IdentifierExpressionNode("equalTo"), new EqualTo());
        this.variableScope.declare(true, new IdentifierExpressionNode("notEqualTo"), new NotEqualTo());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        ServerPlayerEntityClass that = (ServerPlayerEntityClass) o;
        return Objects.equals(this.serverPlayerEntity, that.serverPlayerEntity);
    }

    @Override
    public String getType() {
        return "ServerPlayerEntity";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.serverPlayerEntity);
    }

    public class ChangeGameMode extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'changeGameMode' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode gameMode = arguments.get(0);

            if (!gameMode.getType().equals("String")) {
                throw new TypeError("Argument 1 for function 'changeGameMode' requires type 'String' but got '" + gameMode.getType() + "'");
            }

            GameMode gameMode1 = GameMode.byName(((StringClass) gameMode).value, null);

            if (gameMode1 == null) {
                throw new ValueError("Gamemode '" + gameMode + "' does not exist");
            }

            ServerPlayerEntityClass.this.serverPlayerEntity.changeGameMode(gameMode1);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class DropSelectedItem extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'dropSelectedItem' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode entireStack = arguments.get(0);

            if (!entireStack.getType().equals("Boolean")) {
                throw new TypeError("Argument 1 for function 'dropSelectedItem' requires type 'Boolean' but got '" + entireStack.getType() + "'");
            }

            ServerPlayerEntityClass.this.serverPlayerEntity.dropSelectedItem(((BooleanClass) entireStack).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class EqualTo extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'equalTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(ServerPlayerEntityClass.this.equals(o));
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetIp extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getIp' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new StringClass(ServerPlayerEntityClass.this.serverPlayerEntity.getIp());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class GetViewDistance extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 0) {
                throw new SyntaxError("Function 'getViewDistance' takes 0 arguments but got " + arguments.size() + " argument(s)");
            }

            return new IntegerClass(ServerPlayerEntityClass.this.serverPlayerEntity.getViewDistance());
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class NotEqualTo extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'notEqualTo' requires 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode o = arguments.get(0);

            return new BooleanClass(!ServerPlayerEntityClass.this.equals(o));
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetExperienceLevels extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setExperienceLevels' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode experienceLevel = arguments.get(0);

            if (!experienceLevel.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'setExperienceLevels' requires type 'Integer' but got '" + experienceLevel.getType() + "'");
            }

            ServerPlayerEntityClass.this.serverPlayerEntity.setExperienceLevel(((IntegerClass) experienceLevel).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }

    public class SetExperiencePoints extends BaseClassExpressionNode {
        @Override
        public BaseClassExpressionNode call(Interpreter interpreter, List<BaseClassExpressionNode> arguments) {
            if (arguments.size() != 1) {
                throw new SyntaxError("Function 'setExperiencePoints' takes 1 argument but got " + arguments.size() + " argument(s)");
            }

            BaseClassExpressionNode experiencePoints = arguments.get(0);

            if (!experiencePoints.getType().equals("Integer")) {
                throw new TypeError("Argument 1 for function 'setExperiencePoints' requires type 'Integer' but got '" + experiencePoints.getType() + "'");
            }

            ServerPlayerEntityClass.this.serverPlayerEntity.setExperiencePoints(((IntegerClass) experiencePoints).value);

            return new NullClass();
        }

        @Override
        public String getType() {
            return "Function";
        }
    }
}
