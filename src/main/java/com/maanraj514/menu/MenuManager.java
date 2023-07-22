package com.maanraj514.menu;

import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * This class handles opening menus and playerMenuUtility.
 */
public class MenuManager {

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();;

    public static void openMenu(Class<? extends Menu> menuClass, Player player) {
        try{
            menuClass.getConstructor(PlayerMenuUtility.class).newInstance(getPlayerMenuUtility(player)).open();
        }  catch (InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player player){
        if (playerMenuUtilityMap.containsKey(player)){
            return playerMenuUtilityMap.get(player);
        }else{
            PlayerMenuUtility playerMenuUtility = new PlayerMenuUtility(player);
            playerMenuUtilityMap.put(player, playerMenuUtility);

            return playerMenuUtility;
        }
    }
}