package com.maanraj514;

import com.maanraj514.menu.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/*
* Main class of the entire core.
*/
public abstract class Okmeta extends JavaPlugin {

    private static Okmeta okmeta;

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
        okmeta = this;
        registerListeners(new MenuListener());
        enable();
    }

    @Override
    public void onDisable() {
        disable();
    }

    public void registerListeners(@NotNull Listener... listeners){
        PluginManager pm = Bukkit.getPluginManager();

        for (Listener listener : listeners){
            pm.registerEvents(listener, this);
        }
    }

    public static Okmeta getOkmeta() {
        return okmeta;
    }
}