package com.maanraj514;

import com.maanraj514.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
/*
* Main class of the entire core.
*/
public abstract class Okmeta extends JavaPlugin {

    /*
    * This gets called when server starts.
    */
    public abstract void enable();

    /*
    * This gets called when server stops
    */
    public abstract void disable();

    @Override
    public void onEnable() {
        enable();

        Bukkit.getConsoleSender().sendMessage(ColorUtil.color("&e[&eOkmeta&e]&e: &eThe Core has started!"));
    }

    @Override
    public void onDisable() {
        disable();

        Bukkit.getConsoleSender().sendMessage(ColorUtil.color("&c[&cOkmeta&c]&c: &cThe Core has stopped!"));
    }

    public void registerListeners(@NotNull Listener... listeners){
        PluginManager pm = Bukkit.getPluginManager();

        for (Listener listener : listeners){
            pm.registerEvents(listener, this);
        }
    }
}