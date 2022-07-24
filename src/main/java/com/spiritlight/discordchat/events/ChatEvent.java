package com.spiritlight.discordchat.events;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.spiritlight.discordchat.enums.Receiver;
import com.spiritlight.discordchat.utils.SimpleChatObject;

@SuppressWarnings("unused")
public class ChatEvent {
    interface Listener {
        void onMessage(SimpleChatObject content);
    }


    @SuppressWarnings("UnstableApiUsage")
    private final Multimap<Receiver, Listener> listeners = MultimapBuilder.hashKeys().arrayListValues().build();

    /**
     * Subscribes to this event<br>
     * One entity may subscribe to multiple types. However, devs should identify the {@code type} you are receiving if that's the case.
     * @param entity The {@link Listener} to subscribe to
     * @param type The targeted {@link Receiver} of this entity
     * @return {@code true} if operation succeeded, {@code false} if already subscribed
     */
    public boolean subscribe(Listener entity, Receiver type) {
        if(listeners.get(type).contains(entity)) return false;
        listeners.get(type).add(entity);
        return true;
    }

    /**
     * Unsubscribes from this event
     * @param entity The {@link Listener} to unsubscribe from
     * @param type The {@link Receiver} type to unsubscribe from
     * @return {@code true} if operation succeeded, {@code false} if not subscribed
     */
    public boolean unsubscribe(Listener entity, Receiver type) {
        if(listeners.get(type).contains(entity)) {
            listeners.get(type).remove(entity);
            return true;
        }
        return false;
    }

    public void fire(SimpleChatObject object) {
        for (Listener l : listeners.get(object.getReceiver())) {
            l.onMessage(object);
        }
    }
}
