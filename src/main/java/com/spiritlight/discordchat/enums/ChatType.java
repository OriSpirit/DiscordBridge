package com.spiritlight.discordchat.enums;

/**
 * Useful enums to indicate the handling method for each type<br>
 */
// Note to myself: There really isn't a lot of diff just that server msg and achievements are sent directly,
// while player is sent formatted
public enum ChatType {
    PLAYER, ACHIEVEMENT, SERVER_MESSAGE
}
