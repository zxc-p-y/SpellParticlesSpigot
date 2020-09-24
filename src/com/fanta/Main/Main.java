package com.fanta.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new EventsListener(), this);

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin enabled!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin disabled");
    }
}
