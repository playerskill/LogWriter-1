package com.PlayeR_SkiLL.LogWriter.commands;

import com.PlayeR_SkiLL.LogWriter.LogWriter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class LogWriteCommand implements CommandExecutor {

    private final LogWriter plugin;

    public LogWriteCommand(LogWriter plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Проверка прав
        if (!sender.hasPermission("logwrite.use")) {
            sender.sendMessage("У вас нет прав для использования этой команды.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("Использование: /logwrite <имя_файла.yml> <сообщение>");
            return true;
        }

        String filename = args[0];
        String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        File logFile = new File(plugin.getDataFolder(), "logfiles" + "/" + filename);

        // Проверка существования файла
        if (!logFile.exists()) {
            sender.sendMessage("Файл не существует: " + filename);
            return true;
        }

        // Запись сообщения в файл
        try (FileWriter fw = new FileWriter(logFile, true)) {
            fw.write(message + "\n");
        } catch (IOException e) {
            sender.sendMessage("Ошибка записи файла: " + e.getMessage());
        }

        sender.sendMessage("Сообщение добавлено в " + filename);
        return true;
    }
}
