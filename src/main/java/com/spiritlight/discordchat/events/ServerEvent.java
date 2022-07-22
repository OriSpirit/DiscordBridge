package com.spiritlight.discordchat.events;

import com.spiritlight.discordchat.ChatMessages;
import com.spiritlight.discordchat.api.DiscordInstance;
import com.spiritlight.discordchat.enums.ChatType;
import com.spiritlight.discordchat.utils.EventManager;
import com.spiritlight.discordchat.utils.SimpleChatObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

// When I got sane again I realized a lot of these aren't really needed
public class ServerEvent implements ChatEvent.Listener {
    @Mod.EventHandler
    public void onServerStart(final FMLServerStartedEvent event) {
        SimpleChatObject object = new SimpleChatObject(ChatMessages.SERVER_STARTED, ChatType.SERVER_MESSAGE);
        EventManager.getChatEvent().fire(object);
    }

    @Mod.EventHandler
    public void onServerStop(final FMLServerStoppingEvent event) {
        SimpleChatObject object = new SimpleChatObject(ChatMessages.SERVER_STOPPED, ChatType.SERVER_MESSAGE);
        EventManager.getChatEvent().fire(object);
        DiscordInstance.stop();
    }

    @SubscribeEvent
    public void onMessageReceived(ServerChatEvent event) {
        String message = ChatMessages.PLAYER_MESSAGE.replace("%player%", event.getUsername()).replace("%message%", event.getMessage());
        SimpleChatObject object = new SimpleChatObject(message, ChatType.PLAYER);
        EventManager.getChatEvent().fire(object);
    }

    @SubscribeEvent
    public void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        String message = ChatMessages.PLAYER_LEFT.replace("%player%", event.player.getName());
        SimpleChatObject object = new SimpleChatObject(message, ChatType.SERVER_MESSAGE);
        EventManager.getChatEvent().fire(object);
    }

    @SubscribeEvent
    public void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        String message = ChatMessages.PLAYER_JOINED.replace("%player%", event.player.getName());
        SimpleChatObject object = new SimpleChatObject(message, ChatType.SERVER_MESSAGE);
        EventManager.getChatEvent().fire(object);
    }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent event) {
        if(!(event.getEntity() instanceof EntityPlayer)) return;
        String message = event.getSource().getDeathMessage(event.getEntityLiving()).getUnformattedText();
        SimpleChatObject object = new SimpleChatObject(message, ChatType.SERVER_MESSAGE);
        EventManager.getChatEvent().fire(object);
    }

    @SubscribeEvent
    public void onAdvancement(AdvancementEvent event) {
        String message;
        try {
            message = ChatMessages.ADVANCEMENT_GET.replace("%player%", event.getEntityPlayer().getName()).replace("%advancement%", event.getAdvancement().getDisplay().getTitle().getUnformattedText());
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return;
        }
        SimpleChatObject object = new SimpleChatObject(message, ChatType.ACHIEVEMENT);
        EventManager.getChatEvent().fire(object);
    }

    @Override
    public void onMessage(SimpleChatObject content) {
        if(DiscordInstance.getCurrentState() != DiscordInstance.State.ONLINE) return;
        DiscordInstance.sendMessage(content.getMessage());
    }
}
