package com.PlayeR_SkiLL.LogWriter;

import org.bukkit.plugin.java.JavaPlugin;

public class LogWriter extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("LogWriter включен!");
        // Инициализация команд и т.п.
    }

    @Override
    public void onDisable() {
        getLogger().info("LogWriter выключен!");
    }
}
