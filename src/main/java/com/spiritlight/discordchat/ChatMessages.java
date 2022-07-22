package com.spiritlight.discordchat;

/**
 * Useful class to store all general consistent chat messages.<br>
 * Placeholders for future notices:<br>
 * %player%: The player invoking this event<br>
 * %message%: The message sent by the player<br>
 * %advancement%: The advancement<br>
 * %author%: The message sender (Discord only)
 */
public class ChatMessages {
    public static final String SERVER_STOPPED = "Server has stopped.";
    public static final String SERVER_STARTED = "Server has started";
    public static final String PLAYER_JOINED = "%player% has joined the server.";
    public static final String PLAYER_LEFT = "%player% has left the server.";
    public static final String PLAYER_MESSAGE = "%player%: %message%";
    public static final String ADVANCEMENT_GET = "%player% has completed the advancement %advancement%!";
    public static final String DISCORD_MESSAGE = "[Discord] %author%: %message%";
}
