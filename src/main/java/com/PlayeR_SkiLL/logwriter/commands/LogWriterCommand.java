package com.PlayeR_SkiLL.LogWriter.commands;

import com.PlayeR_SkiLL.LogWriter.LogWriter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;

public class LogWriterCommand implements CommandExecutor {

    private final LogWriter plugin;

    public LogWriterCommand(LogWriter plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            // Сообщение, которое всегда отображается
            sender.sendMessage("&f[&aLogWriter&f] &7Простой плагин от &6PlayeR_SkiLL &7для логирования действий.");
            return true;
        }

        String subCommand = args[0];

        if (subCommand.equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage(getMessage("reload_message"));
            return true;
        }

        if (subCommand.equalsIgnoreCase("create")) {
            if (args.length < 2) {
                sender.sendMessage("Использование: /logwriter create <имя файла>");
                return true;
            }
            String filename = args[1] + ".yml";
            File file = new File(plugin.getDataFolder(), "logfiles" + "/" + filename);
            if (file.exists()) {
                sender.sendMessage(getMessage("create_file_exists"));
            } else {
                try {
                    if (file.createNewFile()) {
                        sender.sendMessage(getMessage("create_file_success").replace("%file%", filename));
                    } else {
                        sender.sendMessage("Ошибка при создании файла");
                    }
                } catch (IOException e) {
                    sender.sendMessage(getMessage("create_error").replace("%error%", e.getMessage()));
                }
            }
            return true;
        }

        sender.sendMessage("Неизвестная команда. Используйте /logwriter reload | create <имя>");
        return true;
    }

    private String getMessage(String key) {
        String msg = plugin.getConfig().getString(key, "");
        return msg.replaceAll("&", "§");
    }
}
