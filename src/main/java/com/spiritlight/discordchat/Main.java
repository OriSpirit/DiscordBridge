package com.spiritlight.discordchat;

import com.spiritlight.discordchat.api.DiscordInstance;
import com.spiritlight.discordchat.enums.Receiver;
import com.spiritlight.discordchat.events.DiscordEvent;
import com.spiritlight.discordchat.events.ServerEvent;
import com.spiritlight.discordchat.utils.EventManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class Main
{
    public static final String MODID = "discordchat";
    public static final String NAME = "DiscordChat";
    public static final String VERSION = "1.0";
    private static Logger logger;
    final DiscordEvent discordEvent = new DiscordEvent();
    final ServerEvent serverEvent = new ServerEvent();
    private static Preferences config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        config = Config.load();
        MinecraftForge.EVENT_BUS.register(serverEvent);
        DiscordInstance.start();
        // EventManager should work like our event bus.
        EventManager.getChatEvent().subscribe(discordEvent, Receiver.MINECRAFT);
        EventManager.getChatEvent().subscribe(serverEvent, Receiver.DISCORD);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    public static Preferences getConfig() {
        return config;
    }
}
