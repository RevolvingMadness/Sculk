package com.revolvingmadness.testing.language;

import java.util.ArrayList;
import java.util.List;

public class EventHolder {
    public static List<Event> onSendChatMessage = new ArrayList<>();
    public static List<Event> onSleep = new ArrayList<>();

    public static void clearEvents() {
        EventHolder.onSendChatMessage.clear();
        EventHolder.onSleep.clear();
    }
}
