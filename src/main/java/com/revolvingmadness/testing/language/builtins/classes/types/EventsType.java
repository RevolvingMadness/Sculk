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

        this.typeVariableScope.declare(List.of(TokenType.CONST), "onPlayerSleep", new OnPlayerSleep());
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

            EventHolder.onSleep.add(new Event(function));

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
}
