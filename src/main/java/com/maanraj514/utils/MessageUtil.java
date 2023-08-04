package com.maanraj514.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Used for sending certain messages such as sendTitle() and sendActionBar().
 */
public class MessageUtil {
    public static void sendTitle(String title, String subTitle, long fadeIn, long stay, long fadeOut, Player... players) {
        Title.Times times = Title.Times.times(Duration.ofMillis(fadeIn), Duration.ofMillis(stay), Duration.ofMillis(fadeOut));
        Title title1 = Title.title(Component.text(title), Component.text(subTitle), times);

        for (Player player : players) {
            player.showTitle(title1);
        }
    }

    public static void sendActionbar(String message, Player... players) {
        for (Player player : players) {
            player.sendActionBar(Component.text(message));
        }
    }

    public static void sendActionbar(String message, Player player) {
        player.sendActionBar(Component.text(message));
    }

    public static void sendMessageWithLines(String message, Player player) {
        player.sendMessage("--------------------");
        player.sendMessage(ColorUtil.translate(message));
        player.sendMessage("--------------------");
    }

    public static void sendMessageWithLines(String message, Player player, String line1, String line2) {
        player.sendMessage(ColorUtil.translate(line1));
        player.sendMessage(ColorUtil.translate(message));
        player.sendMessage(ColorUtil.translate(line2));
    }

    public static void broadcast(String message) {
        Bukkit.broadcastMessage(ColorUtil.translate(message));
    }

    public static void broadcast(String message, Player... players) {
        for (Player player : players) {
            player.sendMessage(ColorUtil.translate(message));
        }
    }

    public static void broadcast(String message, List<UUID> players) {
        for (UUID player : players) {
            Player onlinePlayer = Bukkit.getPlayer(player);
            if (onlinePlayer != null) {
                onlinePlayer.sendMessage(ColorUtil.translate(message));
            }
        }
    }

    public static String rainbow(String message){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            sb.append(getRandomColor()).append(c);
        }

        return sb.toString();
    }

    public static ChatColor getRandomColor(){
        ChatColor[] chatColors = new ChatColor[]{
                ChatColor.AQUA,
                ChatColor.BLUE,
                ChatColor.DARK_AQUA,
                ChatColor.DARK_BLUE,
                ChatColor.DARK_GRAY,
                ChatColor.DARK_GREEN,
                ChatColor.DARK_PURPLE,
                ChatColor.DARK_RED,
                ChatColor.GOLD,
                ChatColor.GRAY,
                ChatColor.GREEN,
                ChatColor.LIGHT_PURPLE,
                ChatColor.RED,
                ChatColor.WHITE,
                ChatColor.YELLOW,
        };

        int randomChatColor = ThreadLocalRandom.current().nextInt(chatColors.length);
        return chatColors[randomChatColor];
    }
}