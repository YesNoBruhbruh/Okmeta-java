package com.maanraj514.menu;

import com.maanraj514.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * This class extends Menu and adds more features
 * to make it a paginatedMenu.
 */
public abstract class PaginatedMenu extends Menu {

    //The items being paginated
    protected List<Object> data;
    //Keep track of what page the menu is on
    protected int page = 0;
    //28 is max items because with the border set below,
    //28 empty slots are remaining.
    protected int maxItemsPerPage = 28;
    //the index represents the index of the slot
    //that the loop is on
    protected int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    /**
     * @return A list of the data being paginated. usually this is a list of items but it can be anything
     */
    public abstract List<?> getData();

    /**
     * @param object A single element of the data list that you do something with. It is recommended that you turn this into an item if it is not already and then put it into the inventory as you would with a normal Menu. You can execute any other logic in here as well.
     */
    public abstract void loopCode(Object object);

    /**
     * Adds the default menu border to the menu.
     */
    protected void addMenuBorder(){
        inventory.setItem(48, new ItemBuilder(Material.DARK_OAK_BUTTON).setName("&aLeft").build());

        inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("&cClose").build());

        inventory.setItem(50, new ItemBuilder(Material.DARK_OAK_BUTTON).setName("&aRight").build());

        ItemBuilder fillerGlassBuilder = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE);
        fillerGlassBuilder.setName(" ");
        ItemStack fillerGlassItem = fillerGlassBuilder.build();

        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, fillerGlassItem);
            }
        }

        inventory.setItem(17, fillerGlassItem);
        inventory.setItem(18, fillerGlassItem);
        inventory.setItem(26, fillerGlassItem);
        inventory.setItem(27, fillerGlassItem);
        inventory.setItem(35, fillerGlassItem);
        inventory.setItem(36, fillerGlassItem);

        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, fillerGlassItem);
            }
        }
    }

    /**
     * Adds a menu border with specified
     * items.
     *
     * @param backItem the back item to go back a page.
     * @param closeItem the close item to close the menu.
     * @param nextItem the next item to go to the next page.
     * @param fillerItem the filler item for the border of the menu.
     */
    protected void addMenuBorder(ItemStack backItem, ItemStack closeItem, ItemStack nextItem, ItemStack fillerItem){
        inventory.setItem(48, backItem);

        inventory.setItem(49, closeItem);

        inventory.setItem(50, nextItem);

        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, fillerItem);
            }
        }

        inventory.setItem(17, fillerItem);
        inventory.setItem(18, fillerItem);
        inventory.setItem(26, fillerItem);
        inventory.setItem(27, fillerItem);
        inventory.setItem(35, fillerItem);
        inventory.setItem(36, fillerItem);

        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, fillerItem);
            }
        }
    }

    /**
     * Override version of the setMenuItems()
     * from Menu class, modified to work with
     * pages.
     */
    @Override
    public void setMenuItems() {

        addMenuBorder();

        List<Object> data = (List<Object>) getData();

        if (data != null && !data.isEmpty()) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                System.out.println(index);
                if (index >= data.size()) break;
                if (data.get(index) != null) {
                    loopCode(data.get(index)); //run the code defined by the user
                }
            }
        }
    }

    /**
     * @return true if successful, false if already on the first page
     */
    public boolean prevPage() {
        if (page == 0) {
            return false;
        } else {
            page = page - 1;
            reloadItems();
            return true;
        }
    }

    /**
     * @return true if successful, false if already on the last page
     */
    public boolean nextPage() {
        if (!((index + 1) >= getData().size())) {
            page = page + 1;
            reloadItems();
            return true;
        } else {
            return false;
        }
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }
}