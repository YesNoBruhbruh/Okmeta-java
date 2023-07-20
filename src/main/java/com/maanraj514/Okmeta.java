package com.maanraj514;

import com.maanraj514.object.Message;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Okmeta extends JavaPlugin {

    @Override
    public void onEnable() {
        Message.of("&e[&eOkmeta&e]&e: &eThe Core has started!").send(Bukkit.getConsoleSender());
    }

    @Override
    public void onDisable() {
        Message.of("&c[&cOkmeta&c]&c: &cThe Core has stopped!").send(Bukkit.getConsoleSender());
    }
}