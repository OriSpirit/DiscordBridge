package com.spiritlight.discordchat;

/**
 * Complex data object to store config data.
 */
public class Preferences {
    private String botToken;
    private String channelId;

    public Preferences(String botToken, String channelId) {
        this.botToken = botToken;
        this.channelId = channelId;
    }

    public Preferences() {
        this.botToken = "";
        this.channelId = "";
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
