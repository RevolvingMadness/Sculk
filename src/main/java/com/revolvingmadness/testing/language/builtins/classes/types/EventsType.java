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

        this.registerEvent("onPlaceBlock", EventHolder.onPlaceBlock);
        this.registerEvent("onPlayerAttackEntity", EventHolder.onPlayerAttackEntity);
        this.registerEvent("onPlayerBreakBlock", EventHolder.onPlayerBreakBlock);
        this.registerEvent("onPlayerCraftItem", EventHolder.onPlayerCraftItem);
        this.registerEvent("onPlayerDropItem", EventHolder.onPlayerDropItem);
        this.registerEvent("onPlayerJump", EventHolder.onPlayerJump);
        this.registerEvent("onPlayerPickupItem", EventHolder.onPlayerPickupItem);
        this.registerEvent("onPlayerSleep", EventHolder.onPlayerSleep);
        this.registerEvent("onPlayerUseItem", EventHolder.onPlayerUseItem);
        this.registerEvent("whilePlayerSneak", EventHolder.whilePlayerSneak);
        this.registerEvent("onRingBell", EventHolder.onRingBell);
        this.registerEvent("onSendChatMessage", EventHolder.onSendChatMessage);
        this.typeVariableScope.declare(List.of(TokenType.CONST), "equalTo", new EqualTo());
    }

    public void registerEvent(String name, List<Event> events) {
        events.add(new Event(new BuiltinFunction() {
            @Override
            public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
                if (arguments.size() != 1) {
                    throw ErrorHolder.invalidArgumentCount(name, 1, arguments.size());
                }

                BuiltinClass functionClass = arguments.get(0);

                if (!functionClass.instanceOf(new FunctionType())) {
                    throw ErrorHolder.argumentRequiresType(1, name, new FunctionType(), functionClass.getType());
                }

                BuiltinFunction function = functionClass.toFunction();

                events.add(new Event(function));

                return new NullInstance();
            }

            @Override
            public BuiltinType getType() {
                return super.getType();
            }
        }));
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
}
