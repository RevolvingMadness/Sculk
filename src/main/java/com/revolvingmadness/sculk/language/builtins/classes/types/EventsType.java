package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.backend.SculkScript;
import com.revolvingmadness.sculk.backend.SculkScriptManager;
import com.revolvingmadness.sculk.language.Event;
import com.revolvingmadness.sculk.language.EventHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.NullInstance;
import com.revolvingmadness.sculk.language.errors.SyntaxError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.Collection;
import java.util.List;

public class EventsType extends BuiltinType {
    public static final EventsType TYPE = new EventsType();

    private EventsType() {
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
    }

    public void registerEvent(String name, List<Event> events) {
        BuiltinFunction eventFunction = new BuiltinFunction() {
            @Override
            public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
                this.validate(name, arguments, List.of(FunctionType.TYPE));

                BuiltinFunction function = arguments.get(0).toFunction();

                Collection<SculkScript> loadScripts = SculkScriptManager.loader.getScriptsFromTag(SculkScriptManager.LOAD_TAG_ID);

                if (loadScripts == null || !loadScripts.contains(SculkScriptManager.currentScript)) {
                    throw new SyntaxError("Events can only be registered in load scripts");
                }

                events.add(new Event(function));

                return new NullInstance();
            }
        };

        this.variableScope.declare(List.of(TokenType.CONST), name, eventFunction);
    }
}
