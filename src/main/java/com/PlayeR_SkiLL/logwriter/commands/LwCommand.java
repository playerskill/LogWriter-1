package com.PlayeR_SkiLL.logwriter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.PlayeR_SkiLL.logwriter.Main;

public class LwCommand implements CommandExecutor {

    private Main plugin;

    public LwCommand() {
        plugin = Main.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(plugin.getMessages().getString("usage"));
            return false;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadPlugin();
            sender.sendMessage(plugin.getMessages().getString("reload_success"));
            return true;
        }
        sender.sendMessage(plugin.getMessages().getString("unknown_command"));
        return false;
    }
}
