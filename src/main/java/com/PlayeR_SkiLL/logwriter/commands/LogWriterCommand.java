package com.PlayeR_SkiLL.logwriter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.PlayeR_SkiLL.logwriter.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriterCommand implements CommandExecutor {

    private Main plugin;

    public LogWriterCommand() {
        plugin = Main.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Эта команда только для игроков");
            return false;
        }
        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage(plugin.getMessages().getString("usage"));
            return false;
        }

        String filename = args[0];
        String message = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));

        // Создаем папку logfiles, если не существует
        File logFolder = new File(plugin.getDataFolder(), "logfiles");
        if (!logFolder.exists()) {
            logFolder.mkdirs();
        }

        // Создаем файл логов для указанного файла
        File logFile = new File(logFolder, filename + ".txt");
        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            player.sendMessage(plugin.getMessages().getString("error_writing_log"));
            return false;
        }

        player.sendMessage(plugin.getMessages().getString("log_written"));
        return true;
    }
}
