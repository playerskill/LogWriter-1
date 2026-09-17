package com.Player_SkiLL.logwriter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.Player_SkiLL.logwriter.Main;
import com.Player_SkiLL.logwriter.utils.FileUtils;

public class LogWriteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Этот команду можно использовать только в игре.");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage("Использование: /logwrite <имя файла> <сообщение>");
            return true;
        }

        String filename = args[0];
        String message = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));

        java.io.File logFolder = new java.io.File(Main.getInstance().getDataFolder(), "logfiles");
        java.io.File file = new java.io.File(logFolder, filename);

        try {
            FileUtils.appendLine(file, message);
            sender.sendMessage("Сообщение записано в файл: " + filename);
        } catch (Exception e) {
            sender.sendMessage("Произошла ошибка при записи файла.");
            e.printStackTrace();
        }

        return true;
    }
}
