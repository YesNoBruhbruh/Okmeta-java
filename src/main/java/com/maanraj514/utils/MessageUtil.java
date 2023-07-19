package com.maanraj514.utils;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

import java.time.Duration;

/**
 * Used for sending certain messages such as sendTitle() and sendActionBar().
 */
public class MessageUtil {

    /**
     * send a title.
     * @param title title to send
     * @param subTitle subtitle to send
     * @param fadeIn how long to fade in
     * @param stay how long it stays
     * @param fadeOut how long it fades out
     * @param players the players to send it to
     */
    public static void sendTitle(TextComponent title, TextComponent subTitle, long fadeIn, long stay, long fadeOut, Player... players){
        Title.Times times = Title.Times.times(Duration.ofMillis(fadeIn), Duration.ofMillis(stay), Duration.ofMillis(fadeOut));
        Title title1 = Title.title(title, subTitle, times);

        for (Player player : players){
            player.showTitle(title1);
        }
    }

    /**
     * send an action bar
     * @param message the message in the action bar
     * @param players the players to send it to
     */
    public static void sendActionbar(TextComponent message, Player... players){
        for (Player player : players){
            player.sendActionBar(message);
        }
    }
}