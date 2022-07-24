# DiscordChat

### A utility server-sided mod to build a chat bridge.


## Configurations

Configs are generated in config/DiscordChat.yaml, fields:

- botToken: Your discord bot token
- channelId: The channel ID this bot is supposed to send messages in

Check the console logs if the bot does not send messages, might have some useful information. (Does it have access? Is the channel ID correct? etc.)

Config file:
```
# DiscordChat Config
# Defines the bot token. Do not share to anyone else!
botToken: "DISCORDBOT.TOKEN_ABCDEFG42069"

# Defines the channel id the bot sends messages to
channelId: "1234567890161616"
```
