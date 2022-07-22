package com.spiritlight.discordchat.utils;

import com.spiritlight.discordchat.events.ChatEvent;

public class EventManager {
    private static final ChatEvent chatEvent = new ChatEvent();

    private EventManager() { throw new AssertionError("No EventManager instances for you!"); }

    public static ChatEvent getChatEvent() {
        return chatEvent;
    }
}
