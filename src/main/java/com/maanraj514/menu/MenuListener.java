package com.maanraj514.menu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Registers the events from the menus.
 */
public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent event){
        Inventory inventory = event.getClickedInventory();
        if (inventory == null){
            return;
        }
        InventoryHolder holder = event.getClickedInventory().getHolder();

        if (holder instanceof Menu){
            if (event.getCurrentItem() == null){
                return;
            }

            Menu menu = (Menu) holder;

            if (menu.cancelAllClicks()){
                event.setCancelled(true);
            }

            menu.handleMenu(event);
        }
    }
}