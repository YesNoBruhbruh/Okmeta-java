package com.maanraj514.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

public class PlayerUtil {

    /**
     * Resets the player to default.
     *
     * @param player the player reset.
     */
    public void reset(@NotNull Player player) {
        for (PotionEffect effect : player.getActivePotionEffects())
            player.removePotionEffect(effect.getType());
        player.setWalkSpeed(0.2F);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.getOpenInventory().close();
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setMaxHealth(20);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setExp(0);
        player.setLevel(0);
        player.setFireTicks(0);
        player.setGameMode(GameMode.SURVIVAL);
        if (player.getVehicle() != null)
            player.leaveVehicle();
        if (player.getPassenger() != null)
            player.getPassenger().leaveVehicle();

    }

    /**
     * Checks if a player's inventory
     * is full.
     *
     * @param player the player checked.
     * @return true if inventory is full, false if not.
     */
    public boolean isInventoryFull(@NotNull Player player){
        return player.getInventory().firstEmpty() == -1;
    }
}