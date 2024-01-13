package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.Event;
import com.revolvingmadness.testing.language.EventHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.testing.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.lexer.TokenType;

import java.util.List;

public class EventsType extends BuiltinType {
    public EventsType() {
        super("Events");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "onPlaceBlock", new OnPlaceBlock());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "onPlayerAttackEntity", new OnPlayerAttackEntity());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "onPlayerBreakBlock", new OnPlayerBreakBlock());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "onPlayerCraftItem", new OnPlayerCraftItem());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "onPlayerDropItem", new OnPlayerDropItem());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "onPlayerJump", new OnPlayerJump());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "onPlayerPickupItem", new OnPlayerPickupItem());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "onPlayerSleep", new OnPlayerSleep());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "onPlayerUseItem", new OnPlayerUseItem());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "whilePlayerSneak", new WhilePlayerSneak());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "onRingBell", new OnRingBell());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "onSendChatMessage", new OnSendChatMessage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "equalTo", new EqualTo());
    }

    private static class EqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("equalTo", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new EventsType())) {
                return new BooleanInstance(other.toEvents().equals(this.boundClass.toEvents()));
            }

            return new BooleanInstance(false);
        }
    }

    private static class OnPlaceBlock extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("onPlaceBlock", 1, arguments.size());
            }

            BuiltinClass onPlaceBlockFunction = arguments.get(0);

            if (!onPlaceBlockFunction.instanceOf(new FunctionType())) {
                throw ErrorHolder.argumentRequiresType(1, "onPlaceBlock", new FunctionType(), onPlaceBlockFunction.getType());
            }

            BuiltinFunction function = onPlaceBlockFunction.toFunction();

            EventHolder.onPlaceBlock.add(new Event(function));

            return new NullInstance();
        }
    }

    private static class OnPlayerAttackEntity extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("onPlayerAttackEntity", 1, arguments.size());
            }

            BuiltinClass onPlayerAttackEntityFunction = arguments.get(0);

            if (!onPlayerAttackEntityFunction.instanceOf(new FunctionType())) {
                throw ErrorHolder.argumentRequiresType(1, "onPlayerAttackEntity", new FunctionType(), onPlayerAttackEntityFunction.getType());
            }

            BuiltinFunction function = onPlayerAttackEntityFunction.toFunction();

            EventHolder.onPlayerAttackEntity.add(new Event(function));

            return new NullInstance();
        }
    }

    private static class OnPlayerBreakBlock extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("onPlayerBreakBlock", 1, arguments.size());
            }

            BuiltinClass onPlayerBreakBlockFunction = arguments.get(0);

            if (!onPlayerBreakBlockFunction.instanceOf(new FunctionType())) {
                throw ErrorHolder.argumentRequiresType(1, "onPlayerBreakBlock", new FunctionType(), onPlayerBreakBlockFunction.getType());
            }

            BuiltinFunction function = onPlayerBreakBlockFunction.toFunction();

            EventHolder.onPlayerBlockBreak.add(new Event(function));

            return new NullInstance();
        }
    }

    private static class OnPlayerCraftItem extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("onPlayerCraftItem", 1, arguments.size());
            }

            BuiltinClass onPlayerCraftItemFunction = arguments.get(0);

            if (!onPlayerCraftItemFunction.instanceOf(new FunctionType())) {
                throw ErrorHolder.argumentRequiresType(1, "onPlayerCraftItem", new FunctionType(), onPlayerCraftItemFunction.getType());
            }

            BuiltinFunction function = onPlayerCraftItemFunction.toFunction();

            EventHolder.onPlayerCraftItem.add(new Event(function));

            return new NullInstance();
        }
    }

    private static class OnPlayerDropItem extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("onPlayerDropItem", 1, arguments.size());
            }

            BuiltinClass onPlayerDropItemFunction = arguments.get(0);

            if (!onPlayerDropItemFunction.instanceOf(new FunctionType())) {
                throw ErrorHolder.argumentRequiresType(1, "onPlayerDropItem", new FunctionType(), onPlayerDropItemFunction.getType());
            }

            BuiltinFunction function = onPlayerDropItemFunction.toFunction();

            EventHolder.onPlayerDropItem.add(new Event(function));

            return new NullInstance();
        }
    }

    private static class OnPlayerJump extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("onPlayerJump", 1, arguments.size());
            }

            BuiltinClass onPlayerJumpFunction = arguments.get(0);

            if (!onPlayerJumpFunction.instanceOf(new FunctionType())) {
                throw ErrorHolder.argumentRequiresType(1, "onPlayerJump", new FunctionType(), onPlayerJumpFunction.getType());
            }

            BuiltinFunction function = onPlayerJumpFunction.toFunction();

            EventHolder.onPlayerJump.add(new Event(function));

            return new NullInstance();
        }
    }

    private static class OnPlayerPickupItem extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("onPlayerPickupItem", 1, arguments.size());
            }

            BuiltinClass onPlayerPickupItemFunction = arguments.get(0);

            if (!onPlayerPickupItemFunction.instanceOf(new FunctionType())) {
                throw ErrorHolder.argumentRequiresType(1, "onPlayerPickupItem", new FunctionType(), onPlayerPickupItemFunction.getType());
            }

            BuiltinFunction function = onPlayerPickupItemFunction.toFunction();

            EventHolder.onPlayerPickupItem.add(new Event(function));

            return new NullInstance();
        }
    }

    private static class OnPlayerSleep extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("onPlayerSleep", 1, arguments.size());
            }

            BuiltinClass onPlayerSleepFunction = arguments.get(0);

            if (!onPlayerSleepFunction.instanceOf(new FunctionType())) {
                throw ErrorHolder.argumentRequiresType(1, "onPlayerSleep", new FunctionType(), onPlayerSleepFunction.getType());
            }

            BuiltinFunction function = onPlayerSleepFunction.toFunction();

            EventHolder.onPlayerSleep.add(new Event(function));

            return new NullInstance();
        }
    }

    private static class OnPlayerUseItem extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("onPlayerUseItem", 1, arguments.size());
            }

            BuiltinClass onPlayerUseItemFunction = arguments.get(0);

            if (!onPlayerUseItemFunction.instanceOf(new FunctionType())) {
                throw ErrorHolder.argumentRequiresType(1, "onPlayerUseItem", new FunctionType(), onPlayerUseItemFunction.getType());
            }

            BuiltinFunction function = onPlayerUseItemFunction.toFunction();

            EventHolder.onPlayerUseItem.add(new Event(function));

            return new NullInstance();
        }
    }

    private static class OnRingBell extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("onRingBell", 1, arguments.size());
            }

            BuiltinClass onRingBellFunction = arguments.get(0);

            if (!onRingBellFunction.instanceOf(new FunctionType())) {
                throw ErrorHolder.argumentRequiresType(1, "onRingBell", new FunctionType(), onRingBellFunction.getType());
            }

            BuiltinFunction function = onRingBellFunction.toFunction();

            EventHolder.onRingBell.add(new Event(function));

            return new NullInstance();
        }
    }

    private static class OnSendChatMessage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("onSendChatMessage", 1, arguments.size());
            }

            BuiltinClass onSendChatMessageFunction = arguments.get(0);

            if (!onSendChatMessageFunction.instanceOf(new FunctionType())) {
                throw ErrorHolder.argumentRequiresType(1, "onSendChatMessage", new FunctionType(), onSendChatMessageFunction.getType());
            }

            BuiltinFunction function = onSendChatMessageFunction.toFunction();

            EventHolder.onSendChatMessage.add(new Event(function));

            return new NullInstance();
        }
    }

    private static class WhilePlayerSneak extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("whilePlayerSneak", 1, arguments.size());
            }

            BuiltinClass onPlayerSneakFunction = arguments.get(0);

            if (!onPlayerSneakFunction.instanceOf(new FunctionType())) {
                throw ErrorHolder.argumentRequiresType(1, "whilePlayerSneak", new FunctionType(), onPlayerSneakFunction.getType());
            }

            BuiltinFunction function = onPlayerSneakFunction.toFunction();

            EventHolder.whilePlayerSneak.add(new Event(function));

            return new NullInstance();
        }
    }
}
