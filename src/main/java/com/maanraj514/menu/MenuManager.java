package com.maanraj514.menu;

import com.maanraj514.Okmeta;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * This class handles opening menus and playerMenuUtility.
 */
public class MenuManager {

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    public static void openMenu(Class<? extends Menu> menuClass, Player player) {
        try {
            Menu menu = menuClass.getDeclaredConstructor(PlayerMenuUtility.class, Okmeta.class).newInstance(getPlayerMenuUtility(player), Okmeta.getOkmeta());
            menu.open();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            Bukkit.getLogger().severe("FAILED TO OPEN MENU");
        }
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {

        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) { //See if the player has a pmu "saved" for them

            //Construct PMU
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p); //Return the object by using the provided player
        }
    }
}