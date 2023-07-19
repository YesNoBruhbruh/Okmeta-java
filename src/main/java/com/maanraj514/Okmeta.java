package com.maanraj514;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Okmeta extends JavaPlugin {

    @Override
    public void onEnable() {
        sendOnEnableMessage();
    }

    @Override
    public void onDisable() {
        sendOnDisableMessage();
    }

    private void sendOnEnableMessage(){
        TextComponent startMessage = Component.text(
                "[Okmeta]: The Core has started!").color(NamedTextColor.GOLD);

        Bukkit.getConsoleSender().sendMessage(startMessage);
    }

    private void sendOnDisableMessage(){
        TextComponent stopMessage = Component.text(
                "[Okmeta]: The Core has stopped! ").color(NamedTextColor.DARK_RED);

        Bukkit.getConsoleSender().sendMessage(stopMessage);
    }
}