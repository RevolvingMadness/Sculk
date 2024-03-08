package com.revolvingmadness.sculk.keybinds;

import com.revolvingmadness.sculk.language.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeybindHelper {
    public static final Map<Integer, List<Event>> EVENTS = new HashMap<>();

    public static void clearEvents() {
        KeybindHelper.EVENTS.clear();
    }

    public static void registerEvent(int code, Event event) {
        if (!KeybindHelper.EVENTS.containsKey(code)) {
            EVENTS.put(code, new ArrayList<>());
        }

        List<Event> currentEvents = KeybindHelper.EVENTS.get(code);

        currentEvents.add(event);
    }
}
