package com.spiritlight.discordchat.api;

import com.spiritlight.discordchat.Main;
import com.spiritlight.discordchat.events.DiscordEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.AllowedMentions;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class DiscordInstance {
    private static JDA instance;
    private static final Logger logger = LogManager.getLogger(Main.MODID);
    private static State currentState = State.OFFLINE;
    private static final EnumSet<Message.MentionType> deny = EnumSet.of(Message.MentionType.EVERYONE, Message.MentionType.HERE, Message.MentionType.ROLE);

    public static void start() {
        if(currentState != State.OFFLINE) {
            logger.log(Level.WARN, "Called start() when the instance is already started!");
            return;
        }
        currentState = State.STARTING;
        final JDABuilder builder = JDABuilder.createDefault(Main.getConfig().getBotToken());
        try {
            builder.disableIntents(GatewayIntent.DIRECT_MESSAGES);
            builder.setAutoReconnect(true);
            builder.addEventListeners(new DiscordEvent());
            instance = builder.build();
            instance.awaitReady();
            AllowedMentions.setDefaultMentions(EnumSet.complementOf(deny));
            currentState = State.ONLINE;
        } catch (LoginException e) {
            logger.log(Level.ERROR, "Unable to login to the discord instance!");
        } catch (InterruptedException ignored) {
        }
    }

    public static boolean sendMessage(String message) {
        if(currentState != State.ONLINE) return false;
        TextChannel textChannel = instance.getTextChannelById(Main.getConfig().getChannelId());
        if(textChannel == null || !textChannel.canTalk()) {
            logger.log(Level.FATAL, "Invalid channel ID " + Main.getConfig().getChannelId() + "! (Can the bot access it?)");
            return false;
        }
        textChannel.sendMessage(message).queue();
        return true;
    }

    public static boolean stop() {
        if(currentState == State.OFFLINE) return false;
        instance.shutdown();
        return true;
    }

    public enum State {
        OFFLINE, STARTING, ONLINE
    }

    public static State getCurrentState() {
        return currentState;
    }
}
