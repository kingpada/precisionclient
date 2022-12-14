package net.minecraft.src;

import net.lax1dude.eaglercraft.sp.EaglercraftRandom;
import net.minecraft.server.MinecraftServer;

import java.util.List;

public class CommandWeather extends CommandBase {
    public String getCommandName() {
        return "weather";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel() {
        return 2;
    }

    public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        if (par2ArrayOfStr.length < 1) {
            throw new WrongUsageException("commands.weather.usage");
        } else {
            boolean clear = "clear".equalsIgnoreCase(par2ArrayOfStr[0]);
            int var3 = (300 + (new EaglercraftRandom()).nextInt(600)) * (clear ? 80 : 20);

            if (par2ArrayOfStr.length >= 2) {
                var3 = parseIntBounded(par1ICommandSender, par2ArrayOfStr[1], 1, 1000000) * 20;
            }

            WorldServer var4 = MinecraftServer.getServer().worldServers[0];
            WorldInfo var5 = var4.getWorldInfo();
            var5.setRainTime(var3);
            var5.setThunderTime(var3);

            if (clear) {
                var5.setRaining(false);
                var5.setThundering(false);
                notifyAdmins(par1ICommandSender, "commands.weather.clear");
            } else if ("rain".equalsIgnoreCase(par2ArrayOfStr[0])) {
                var5.setRaining(true);
                var5.setThundering(false);
                notifyAdmins(par1ICommandSender, "commands.weather.rain");
            } else if ("thunder".equalsIgnoreCase(par2ArrayOfStr[0])) {
                var5.setRaining(true);
                var5.setThundering(true);
                notifyAdmins(par1ICommandSender, "commands.weather.thunder");
            }
        }
    }

    /**
     * Adds the strings available in this command to the given list of tab
     * completion options.
     */
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 1
                ? getListOfStringsMatchingLastWord(par2ArrayOfStr, "clear", "rain", "thunder")
                : null;
    }
}
