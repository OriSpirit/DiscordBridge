package com.spiritlight.discordchat;

import org.apache.logging.log4j.LogManager;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;

public class Config {
    private static boolean antiLoop = false;

    public static void createNew() {
        try {
            FileWriter newcfg = new FileWriter("config/DiscordChat.yaml");
            newcfg.write("# DiscordChat Config\n" +
                    "# Defines the bot token. Do not share to anyone else!\n" +
                    "botToken: \"\"\n\n" +
                    "# Defines the channel id the bot sends messages to\n" +
                    "channelId: \"00000000000000000\""); // Create new config
            newcfg.close();
            System.out.println("A new config has been created.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static Preferences load() {
        try {
            InputStream inputStream = new FileInputStream("config/DiscordChat.yaml");
            Yaml yaml = new Yaml(new Constructor(Preferences.class));
            return yaml.load(inputStream);
        } catch (FileNotFoundException ex) {
            if(antiLoop) {
                LogManager.getLogger(Main.MODID).fatal("Unable to create a yaml file!");
                return new Preferences();
            }
            antiLoop = true;
            createNew();
            load();
        }
        return new Preferences();
    }
}
