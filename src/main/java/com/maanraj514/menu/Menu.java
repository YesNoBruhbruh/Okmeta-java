package com.maanraj514.menu;

import com.maanraj514.Okmeta;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * This is the menu class
 * subclasses extend from.
 */
public abstract class Menu implements InventoryHolder {
    protected Okmeta plugin;

    // Protected values that can be accessed in the menus
    // The playerMenuUtility in the menu.
    protected PlayerMenuUtility playerMenuUtility;
    // Player from playerMenuUtility.
    protected Player player;
    // The Inventory used in the menu.
    protected Inventory inventory;

    public Menu(PlayerMenuUtility playerMenuUtility, Okmeta plugin) {
        this.plugin = plugin;
        this.playerMenuUtility = playerMenuUtility;
        this.player = playerMenuUtility.getOwner();
    }

    /**
     * @return Menu name.
     */
    public abstract String getMenuName();

    /**
     * @return Inventory slots.
     */
    public abstract int getSlots();

    /**
     * @return True or false for cancelling clicks.
     */
    public abstract boolean cancelAllClicks();

    /**
     * Handles the inventory click event.
     *
     * @param event The event for InventoryClicking.
     */
    public abstract void handleMenu(InventoryClickEvent event);

    /**
     * Set the menu items.
     */
    public abstract void setMenuItems();

    /**
     * Creates the inventory and sets
     * the items, sets the player
     * to the playerMenuUtility, and
     * opens the inventory.
     */
    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

        //grab all the items specified to be used for this menu and add to inventory
        this.setMenuItems();

        //open the inventory for the player
        playerMenuUtility.getOwner().openInventory(inventory);
        playerMenuUtility.pushMenu(this);
    }

    /**
     * Opens the previous menu the player was on.
     */
    public void back() {
        MenuManager.openMenu(playerMenuUtility.lastMenu().getClass(), playerMenuUtility.getOwner());
    }

    /**
     * Reloads the items.
     */
    protected void reloadItems() {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, null);
        }
        setMenuItems();
    }

    /**
     * Reloads the inventory.
     */
    protected void reload() {
        player.closeInventory();
        MenuManager.openMenu(this.getClass(), player);
    }

    //Overridden method from the InventoryHolder interface
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    /**
     * Sets the default filler item
     * (GRAY_STAINED_GLASS_PANE).
     */
    public void setFillerItem() {
        ItemStack fillerItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        for (int i = 0; i < getSlots(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, fillerItem);
            }
        }
    }

    /**
     * Sets the filler item.
     *
     * @param itemStack the item for the filler item.
     */
    public void setFillerItem(ItemStack itemStack) {
        for (int i = 0; i < getSlots(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, itemStack);
            }
        }
    }
}