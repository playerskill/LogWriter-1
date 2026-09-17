package com.PlayeR_SkiLL.logwriter;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import com.PlayeR_SkiLL.logwriter.commands.LwCommand;
import com.PlayeR_SkiLL.logwriter.commands.LogWriterCommand;

public class Main extends JavaPlugin {

    private static Main instance;
    private FileConfiguration messages;

    @Override
    public void onEnable() {
        instance = this;

        // Сохраняем messages.yml
        saveResource("messages.yml", false);
        createLogFilesFolder();

        // Регистрация команд
        getCommand("logwrite").setExecutor(new LogWriteCommand());
        getCommand("logwriter").setExecutor(new LogWriterCommand());
        getCommand("lw").setExecutor(new LwCommand());

        // Загрузка сообщений
        loadMessages();
    }

    @Override
    public void onDisable() {
        // ничего не требуется
    }

    public static Main getInstance() {
        return instance;
    }

    public void reloadPlugin() {
        saveResource("messages.yml", false);
        loadMessages();
    }

    public void createLogFilesFolder() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        java.io.File logFolder = new java.io.File(getDataFolder(), "logfiles");
        if (!logFolder.exists()) {
            logFolder.mkdirs();
        }
    }

    private void loadMessages() {
        this.messages = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(new java.io.File(getDataFolder(), "messages.yml"));
    }

    public FileConfiguration getMessages() {
        return messages;
    }
}
