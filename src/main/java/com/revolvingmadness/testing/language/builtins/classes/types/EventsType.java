package com.revolvingmadness.testing.language.builtins.classes.types;

import com.revolvingmadness.testing.language.ErrorHolder;
import com.revolvingmadness.testing.language.Event;
import com.revolvingmadness.testing.language.EventHolder;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.testing.language.builtins.classes.BuiltinType;
import com.revolvingmadness.testing.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.testing.language.interpreter.Interpreter;
import com.revolvingmadness.testing.language.lexer.TokenType;

import java.util.List;

public class EventsType extends BuiltinType {
    public EventsType() {
        super("Events");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "onPlayerSleep", new OnPlayerSleep());
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

            EventHolder.onSleepEvents.add(new Event(function));

            return new NullInstance();
        }
    }
}
