package com.maanraj514.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

/**
 * Used for sending certain messages such as sendTitle() and sendActionBar().
 */
public class MessageUtil {

    /**
     * Send a title to the player.
     *
     * @param title title to send
     * @param subTitle subtitle to send
     * @param fadeIn how long to fade in
     * @param stay how long it stays
     * @param fadeOut how long it fades out
     * @param players the players to send it to
     */
    public static void sendTitle(@NotNull String title, @NotNull String subTitle, long fadeIn, long stay, long fadeOut, @NotNull Player... players){
        Title.Times times = Title.Times.times(Duration.ofMillis(fadeIn), Duration.ofMillis(stay), Duration.ofMillis(fadeOut));
        Title title1 = Title.title(Component.text(title), Component.text(subTitle), times);

        for (Player player : players){
            player.showTitle(title1);
        }
    }

    /**
     * Send an action bar to the player.
     *
     * @param message the message in the action bar
     * @param players the players to send it to
     */
    public static void sendActionbar(@NotNull String message, @NotNull Player... players){
        for (Player player : players){
            player.sendActionBar(Component.text(message));
        }
    }
}