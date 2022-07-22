package com.spiritlight.discordchat.events;

import com.spiritlight.discordchat.ChatMessages;
import com.spiritlight.discordchat.Main;
import com.spiritlight.discordchat.Preferences;
import com.spiritlight.discordchat.enums.ChatType;
import com.spiritlight.discordchat.enums.Receiver;
import com.spiritlight.discordchat.utils.EventManager;
import com.spiritlight.discordchat.utils.SimpleChatObject;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.jetbrains.annotations.NotNull;

public class DiscordEvent extends ListenerAdapter implements ChatEvent.Listener {

    // Handle message to send to Minecraft chat
    @Override
    public void onMessage(SimpleChatObject content) {
        // Send message to channel
        MinecraftServer minecraftServer = FMLCommonHandler.instance().getMinecraftServerInstance();
        minecraftServer.sendMessage(new TextComponentString(content.getMessage()));
    }

    // Discord message fired to Minecraft
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(!event.getChannel().getId().equals(Main.getConfig().getChannelId())) return;
        if(event.getAuthor().isBot()) return; // Ignore bot messages to prevent inf-loop
        String sender = event.getAuthor().getName();
        String content = event.getMessage().getContentRaw();
        if(content.length() > 256) {
            event.getMessage().addReaction("\uD83D\uDCAC").queue();
            content = content.substring(0, 253).concat("...");
        }
        String message = ChatMessages.DISCORD_MESSAGE.replace("%author%", sender).replace("%message%", content);
        SimpleChatObject object = new SimpleChatObject(message, ChatType.SERVER_MESSAGE, Receiver.MINECRAFT);
        EventManager.getChatEvent().fire(object);
    }
}
