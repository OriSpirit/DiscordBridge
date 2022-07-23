package com.spiritlight.discordchat.utils;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.List;

public class ServerUtils {
    private final MinecraftServer minecraftServer;

    public ServerUtils() {
        this.minecraftServer = FMLCommonHandler.instance().getMinecraftServerInstance();
    }

    public MinecraftServer getServer() {
        return minecraftServer;
    }

    public List<EntityPlayerMP> getPlayers() {
        return getServer().getPlayerList().getPlayers();
    }
}
