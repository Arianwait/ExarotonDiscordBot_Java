# ExarotonDiscordBot

A Discord bot that starts, stops, and checks the status of a Minecraft server hosted on
[Exaroton](https://exaroton.com), all from Discord chat commands. Built on JDA for the
Discord side and the Exaroton API for server control.

## Commands

| Command   | Action                          |
| --------- | ------------------------------- |
| `!start`  | Start the Minecraft server      |
| `!end`    | Stop the Minecraft server       |
| `!status` | Report the current server state |

Any other message gets an "unknown command" reply.

## Tech stack

Java · JDA (Discord API) · Exaroton API · Maven

## Getting started

**Requirements:** JDK 8+, Maven, a Discord bot token, and an Exaroton account with an
API token and a server ID.

1. Provide your credentials. They are currently placeholders in the source:
   - Discord bot token in `App.java` (`token`)
   - Exaroton API token and server ID in `ExarotonServer.java` (`apiToken`, `serverId`)
2. Build:
   ```bash
   mvn clean compile
   ```
3. Run `App` to bring the bot online, then invite it to your server and use the commands above.

## ⚠️ Security

The tokens are placeholders today, which is good — but before adding real ones, **don't
commit them**. Read them from environment variables (e.g. `System.getenv("DISCORD_TOKEN")`)
or a config file added to `.gitignore`. A leaked Discord or Exaroton token gives full
control of your bot and server.

## Possible improvements

- Externalize tokens to env vars / config instead of source constants.
- Restrict commands to specific roles or channels.
- Reuse a single `ExarotonServer` instance instead of creating one per message.
