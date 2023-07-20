package com.maanraj514.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * This class is for handling cooldowns for the players.
 */
public class CoolDownUtil {

    /*
    * The coolDownMap for storing the
    * player's set coolDown.
    */
    private static final HashMap<UUID, Long> coolDownMap = new HashMap<>();

    /**
     * This sets the coolDown for the player
     * and the seconds as the cooldown time.
     *
     * @param player the player with the set coolDown.
     * @param seconds the time the coolDown will be.
     */
    public static void setCoolDown(Player player, int seconds) {
        long delay = (System.currentTimeMillis() + (seconds * 1000L));
        coolDownMap.put(player.getUniqueId(), delay);
    }

    /**
     * This checks if the player still has
     * a cooldown.
     *
     * @param player the player checked.
     */
    public static boolean checkCoolDown(Player player) {
        return !coolDownMap.containsKey(player.getUniqueId()) || coolDownMap.get(player.getUniqueId()) <= System.currentTimeMillis();
    }

    /**
     * This checks the time left
     * on the player's coolDown.
     *
     * @param player the player checked
     */
    public static long timeLeft(Player player) {
        return (coolDownMap.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000;
    }
}