package com.spiritlight.discordchat;

import com.spiritlight.discordchat.api.DiscordInstance;
import com.spiritlight.discordchat.enums.ChatType;
import com.spiritlight.discordchat.enums.Receiver;
import com.spiritlight.discordchat.events.DiscordEvent;
import com.spiritlight.discordchat.events.ServerEvent;
import com.spiritlight.discordchat.utils.EventManager;
import com.spiritlight.discordchat.utils.SimpleChatObject;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class Main
{
    public static final String MODID = "discordchat";
    public static final String NAME = "DiscordChat";
    public static final String VERSION = "1.0";
    final DiscordEvent discordEvent = new DiscordEvent();
    final ServerEvent serverEvent = new ServerEvent();
    private static Preferences config;

    @Mod.EventHandler
    public void onServerStart(final FMLServerStartedEvent event) {
        DiscordInstance.start();
        // EventManager should work like our event bus.
        EventManager.getChatEvent().subscribe(discordEvent, Receiver.MINECRAFT);
        EventManager.getChatEvent().subscribe(serverEvent, Receiver.DISCORD);
        SimpleChatObject object = new SimpleChatObject(ChatMessages.SERVER_STARTED, ChatType.SERVER_MESSAGE);
        EventManager.getChatEvent().fire(object);
    }

    @Mod.EventHandler
    public void onServerStop(final FMLServerStoppingEvent event) {
        SimpleChatObject object = new SimpleChatObject(ChatMessages.SERVER_STOPPED, ChatType.SERVER_MESSAGE);
        EventManager.getChatEvent().fire(object);
        try {
            DiscordInstance.stop();
        } catch (NoClassDefFoundError ignored) {
            // no-op: Caused by excluding an unnecessary library.
        }
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        config = Config.load();
        MinecraftForge.EVENT_BUS.register(serverEvent);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    public static Preferences getConfig() {
        return config;
    }
}
