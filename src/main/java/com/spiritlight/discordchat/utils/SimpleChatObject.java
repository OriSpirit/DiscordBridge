package com.spiritlight.discordchat.utils;

import com.spiritlight.discordchat.enums.ChatType;
import com.spiritlight.discordchat.enums.Receiver;

import javax.annotation.Nonnull;

public class SimpleChatObject {
    private final String message;
    private final ChatType type;
    private final Receiver receiver;

    /**
     * Constructs a {@link SimpleChatObject}
     * @param message The String literal this object contains
     * @param type The {@link ChatType} of this object
     * @param receiver The annotated {@link Receiver}
     */
    public SimpleChatObject(@Nonnull String message, @Nonnull ChatType type, @Nonnull Receiver receiver) {
        this.message = message;
        this.type = type;
        this.receiver = receiver;
    }

    /**
     * Constructs a {@link SimpleChatObject}
     * @param message The String literal this object contains
     * @param type The {@link ChatType} of this object
     */
    public SimpleChatObject(@Nonnull String message, @Nonnull ChatType type) {
        this.message = message;
        this.type = type;
        this.receiver = Receiver.DISCORD;
    }

    public ChatType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Receiver getReceiver() { return receiver; }

}
