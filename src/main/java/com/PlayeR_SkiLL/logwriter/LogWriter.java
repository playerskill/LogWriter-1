package com.PlayeR_SkiLL.LogWriter;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class LogWriter extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("LogWriter включен!");

        // Создаем папки при запуске
        createLogFolder();

        // Регистрация команд и других инициализаций
        // например: getCommand("logwriter").setExecutor(new LogWriterCommand(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("LogWriter выключен!");
    }

    private void createLogFolder() {
        // Получаем папку плагина
        File pluginFolder = getDataFolder();

        // Создаем папку logfiles внутри папки плагина
        File logFolder = new File(pluginFolder, "logfiles");
        if (!logFolder.exists()) {
            boolean success = logFolder.mkdirs();
            if (success) {
                getLogger().info("[LogWriter] Папка logfiles успешно создана");
            } else {
                getLogger().warning("[LogWriter] Не удалось создать папку logfiles");
            }
        } else {
            getLogger().info("[LogWriter] Папка logfiles уже существует");
        }
    }
}
