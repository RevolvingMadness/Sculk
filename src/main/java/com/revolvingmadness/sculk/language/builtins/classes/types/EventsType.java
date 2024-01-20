package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.backend.SculkScript;
import com.revolvingmadness.sculk.backend.SculkScriptManager;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.Event;
import com.revolvingmadness.sculk.language.EventHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.Collection;
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
        this.registerEvent("onEntitySleep", EventHolder.onEntitySleep);
        this.registerEvent("onPlayerUseItem", EventHolder.onPlayerUseItem);
        this.registerEvent("onPlayerRingBell", EventHolder.onPlayerRingBell);
        this.registerEvent("onPlayerSendChatMessage", EventHolder.onPlayerSendChatMessage);
        this.registerEvent("onPlayerSneak", EventHolder.onPlayerSneak);
        this.typeVariableScope.declare(List.of(TokenType.CONST), "equalTo", new EqualTo());
    }

    public void registerEvent(String name, List<Event> events) {
        Collection<SculkScript> loadScripts = SculkScriptManager.currentScript.loader.taggedScripts.get(SculkScriptManager.LOAD_TAG_ID);

        Collection<SculkScript> reloadScripts = SculkScriptManager.currentScript.loader.taggedScripts.get(SculkScriptManager.RELOAD_TAG_ID);

        if (!(loadScripts == null || !loadScripts.contains(SculkScriptManager.currentScript) || reloadScripts == null || !reloadScripts.contains(SculkScriptManager.currentScript))) {
            throw ErrorHolder.eventsCanOnlyBeRegisteredInALoadOrReloadScript();
        }

        BuiltinFunction eventFunction = new BuiltinFunction() {
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
        };

        this.typeVariableScope.declare(List.of(TokenType.CONST), name, eventFunction);
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
