package com.revolvingmadness.testing.language;

import java.util.ArrayList;
import java.util.List;

public class EventHolder {
    public static List<Event> onSleepEvents = new ArrayList<>();

    public static void clearEvents() {
        EventHolder.onSleepEvents.clear();
    }
}
