package com.spiritlight.discordchat;

import net.minecraft.util.text.TextFormatting;

/**
 * Useful class to store all general consistent chat messages.<br>
 * Placeholders for future notices:<br>
 * %player%: The player invoking this event<br>
 * %message%: The message sent by the player<br>
 * %advancement%: The advancement<br>
 * %author%: The message sender (Discord only)<br>
 * %count%: Player count<br>
 * %players%: Online players
 */
public class ChatMessages {
    public static final String SERVER_STOPPED = "\uD83D\uDED1 Server has stopped.";
    public static final String SERVER_STARTED = "✅ Server has started.";
    public static final String PLAYER_JOINED = "⏩ %player% has joined the server.";
    public static final String PLAYER_LEFT = "⏪ %player% has left the server.";
    public static final String PLAYER_MESSAGE = "%player%: %message%";
    public static final String ADVANCEMENT_GET = "\uD83C\uDF89 %player% has completed the advancement %advancement%!";
    public static final String PLAYER_LIST = "There are currently %count% players online: ```%players%```";
    public static final String PLAYER_LIST_EMPTY = "There are currently no online players.";
    public static final String DISCORD_MESSAGE = "[" + TextFormatting.AQUA + "Discord" + TextFormatting.RESET + "] %author%: %message%";
}
