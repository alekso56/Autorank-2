package me.armar.plugins.autorank.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.armar.plugins.autorank.Autorank;
import me.armar.plugins.autorank.commands.manager.AutorankCommand;
import me.armar.plugins.autorank.language.Lang;
import me.armar.plugins.autorank.util.AutorankTools;
import me.armar.plugins.autorank.util.AutorankTools.Time;

/**
 * The command delegator for the '/ar archive' command.
 */
public class ArchiveCommand extends AutorankCommand {

    private final Autorank plugin;

    public ArchiveCommand(final Autorank instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {

        if (!plugin.getCommandsManager().hasPermission("autorank.archive", sender)) {
            return true;
        }

        int rate = -1;

        if (args.length != 2) {

            sender.sendMessage(Lang.INVALID_FORMAT.getConfigValue("/ar archive <minimum>"));
            return true;
        }

        rate = AutorankTools.stringToTime(args[1], Time.MINUTES);

        if (rate <= 0) {
            sender.sendMessage(ChatColor.RED + Lang.INVALID_FORMAT.getConfigValue("/ar archive 10d/10h/10m"));
            return true;
        }

        sender.sendMessage(ChatColor.GREEN + "Removed " + ChatColor.YELLOW + plugin.getFlatFileManager().archive(rate)
                + "" + ChatColor.GREEN + " records below " + ChatColor.YELLOW
                + AutorankTools.timeToString(rate, Time.MINUTES) + ChatColor.GREEN + ".");
        return true;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Archive data with a minimum";
    }

    @Override
    public String getPermission() {
        // TODO Auto-generated method stub
        return "autorank.archive";
    }

    @Override
    public String getUsage() {
        // TODO Auto-generated method stub
        return "/ar archive <minimum>";
    }
}
