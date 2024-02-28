package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.backend.SculkScript;
import com.revolvingmadness.sculk.backend.SculkScriptManager;
import com.revolvingmadness.sculk.language.Event;
import com.revolvingmadness.sculk.language.EventHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinFunction;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.FunctionClassType;
import com.revolvingmadness.sculk.language.errors.SyntaxError;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.Collection;
import java.util.List;

public class EventsClassType extends BuiltinClassType {
    public static final EventsClassType TYPE = new EventsClassType();

    private EventsClassType() {
        super("Events");

        this.registerEvent("onPlaceBlock", EventHolder.onPlaceBlock);
        this.registerEvent("onAttackEntity", EventHolder.onAttackEntity);
        this.registerEvent("onBreakBlock", EventHolder.onBreakBlock);
        this.registerEvent("onCraftItem", EventHolder.onCraftItem);
        this.registerEvent("onDropItem", EventHolder.onDropItem);
        this.registerEvent("onJump", EventHolder.onJump);
        this.registerEvent("onPickupItem", EventHolder.onPickupItem);
        this.registerEvent("onEntitySleep", EventHolder.onEntitySleep);
        this.registerEvent("onRightClickItem", EventHolder.onRightClickItem);
        this.registerEvent("onRingBell", EventHolder.onRingBell);
        this.registerEvent("onSendChatMessage", EventHolder.onSendChatMessage);
        this.registerEvent("whileSneaking", EventHolder.whileSneaking);
    }

    public void registerEvent(String name, List<Event> events) {
        BuiltinFunction eventFunction = new BuiltinFunction() {
            @Override
            public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
                this.validateCall(name, arguments, List.of(FunctionClassType.TYPE));

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
