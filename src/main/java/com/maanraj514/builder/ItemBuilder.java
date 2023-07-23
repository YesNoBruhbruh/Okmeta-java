package com.maanraj514.builder;

import com.google.common.collect.Lists;
import com.maanraj514.utils.ColorUtil;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.profile.PlayerProfile;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * This class is used for building and editing
 * items easier.
 */
public class ItemBuilder {

    /*
    * This is the item.
    */
    private final ItemStack item;
    /*
    * This is the item meta.
    */
    private ItemMeta meta;

    /**
     * Creates a new instance of
     * Itembuilder with ItemStack.
     *
     * @param item the item.
     */
    public ItemBuilder(ItemStack item){
        this.item = item;
        if (item.getItemMeta() == null){
            return;
        }
        this.meta = item.getItemMeta();
    }

    /**
     * Creates a new instance of
     * ItemBuilder with Material
     *
     * @param material the material of the item.
     */
    public ItemBuilder(Material material){
        this(new ItemStack(material));
    }

    /**
     * Copies the instance of other
     * ItemBuilder and pastes it into
     * this one.
     *
     * @param itemBuilder the itembuilder replacing this one.
     */
    public ItemBuilder(ItemBuilder itemBuilder){
        this.item = itemBuilder.item;
        this.meta = itemBuilder.meta;
    }

    /**
     * Sets the name of the item.
     *
     * @param name the name of the item.
     * @return this.
     */
    @NotNull
    public ItemBuilder setName(@NotNull String name){
        this.meta.setDisplayName(ColorUtil.translate(name));
        return this;
    }

    /**
     * Sets lore.
     * List<String>.
     *
     * @param lines the lore.
     * @return this.
     */
    @NotNull
    public ItemBuilder setLore(@NotNull List<String> lines){
        List<String> parsed = new ArrayList<>();

        for (String line : lines){
            parsed.add(ColorUtil.translate(line));
        }
        this.meta.setLore(parsed);
        return this;
    }

    /**
     * Sets lore.
     * String[].
     *
     * @param lines the lore.
     * @return this.
     */
    @NotNull
    public ItemBuilder setLore(@NotNull String... lines){
        List<String> parsed = new ArrayList<>();

        for (String line : lines){
            parsed.add(ColorUtil.translate(line));
        }
        this.meta.setLore(parsed);
        return this;
    }

    /**
     * Adds a single line
     * of lore.
     *
     * @param line the lore line.
     * @return this.
     */
    @NotNull
    public ItemBuilder addLoreLine(@NotNull String line){
        List<String> lore;
        if (this.meta.getLore() == null){
            lore = new ArrayList<>();
        }else{
            lore = this.meta.getLore();
        }

        lore.add(ColorUtil.translate(line));
        this.meta.setLore(lore);

        return this;
    }

    /**
     * Adds multiple lines
     * of lore.
     * String[]
     *
     * @param lines lore lines.
     * @return this.
     */
    @NotNull
    public ItemBuilder addLoreLines(@NotNull String... lines){
        List<String> lore;
        if (this.meta.getLore() == null){
            lore = new ArrayList<>();
        }else{
            lore = this.meta.getLore();
        }

        for (String line : lines){
            lore.add(ColorUtil.translate(line));
        }

        this.meta.setLore(lore);

        return this;
    }

    /**
     * Adds multiple lines
     * of lore.
     * List<String>
     *
     * @param lines lore lines.
     * @return this.
     */
    @NotNull
    public ItemBuilder addLoreLines(@NotNull List<String> lines){
        List<String> lore;
        if (this.meta.getLore() == null){
            lore = new ArrayList<>();
        }else{
            lore = this.meta.getLore();
        }

        for (String line : lines){
            lore.add(ColorUtil.translate(line));
        }

        this.meta.setLore(lore);

        return this;
    }

    /**
     * Replaces the lore.
     * It requires a function.
     *
     * @param function replace the lore.
     * @return this.
     */
    @NotNull
    public ItemBuilder replaceLore(@NotNull UnaryOperator<String> function) {
        List<String> lore = this.meta.getLore();
        if (lore == null) lore = Lists.newArrayList();

        lore.replaceAll(function);

        this.meta.setLore(lore);
        return this;
    }

    /**
     * Uses the other replaceLore().
     *
     * @param placeholder the placeholder.
     * @param replacement the replacement.
     * @return this.
     */
    @NotNull
    public ItemBuilder replaceLore(@NotNull String placeholder, @NotNull String replacement) {
        return this.replaceLore(line -> line.replace(placeholder, replacement));
    }

    /**
     * Adds the specified enchant.
     *
     * @param enchantment enchantment.
     * @param level level.
     * @param ignoreMinecraftLimit if it ignore's minecraft's enchantment limit.
     * @return this.
     */
    @NotNull
    public ItemBuilder addEnchant(@NotNull Enchantment enchantment, int level, boolean ignoreMinecraftLimit){
        this.meta.addEnchant(enchantment, level, ignoreMinecraftLimit);
        return this;
    }

    /**
     * Removes the specified enchant.
     *
     * @param enchantment enchantment.
     * @return this.
     */
    @NotNull
    public ItemBuilder removeEnchant(@NotNull Enchantment enchantment){
        this.meta.removeEnchant(enchantment);
        return this;
    }

    /**
     * Adds the itemflags to the item.
     *
     * @param itemFlags itemflags.
     * @return this.
     */
    @NotNull
    public ItemBuilder addItemFlags(@NotNull ItemFlag... itemFlags){
        this.meta.addItemFlags(itemFlags);
        return this;
    }

    /**
     * Removes the itemflags to the item.
     *
     * @param itemFlags itemflags.
     * @return this.
     */
    @NotNull
    public ItemBuilder removeItemFlags(@NotNull ItemFlag... itemFlags){
        this.meta.removeItemFlags(itemFlags);
        return this;
    }

    /**
     * Sets the skull owner.
     * OfflinePlayer.
     *
     * @param offlinePlayer the offlinePlayer.
     * @return this.
     */
    @NotNull
    public ItemBuilder setSkullOwner(@NotNull OfflinePlayer offlinePlayer){
        SkullMeta skullMeta = (SkullMeta) meta;
        skullMeta.setOwningPlayer(offlinePlayer);
        return this;
    }

    /**
     * Sets the skull owner.
     * PlayerProfile.
     *
     * @param playerProfile the playerProfile.
     * @return this.
     */
    @NotNull
    public ItemBuilder setSkullOwner(@NotNull PlayerProfile playerProfile){
        SkullMeta skullMeta = (SkullMeta) meta;
        skullMeta.setOwnerProfile(playerProfile);
        return this;
    }

    /**
     * Sets customModelData on the item.
     *
     * @param modelData modelData.
     * @return this.
     */
    @NotNull
    public ItemBuilder setCustomModelData(int modelData){
        this.meta.setCustomModelData(modelData);
        return this;
    }

    /**
     * Applies persistentData on the item.
     *
     * @param function applyPersistentData.
     * @return this.
     */
    @NotNull
    public ItemBuilder applyPersistentData(@NotNull Consumer<PersistentDataContainer> function) {
        function.accept(this.meta.getPersistentDataContainer());
        return this;
    }

    /**
     * Add an attribute modifier to the item.
     *
     * @param attribute The attribute to add the modifier to.
     * @param modifier The modifier to add.
     * @return this.
     */
    @NotNull
    public ItemBuilder addAttributeModifier(@NotNull Attribute attribute, @NotNull AttributeModifier modifier) {
        this.meta.addAttributeModifier(attribute, modifier);
        return this;
    }

    /**
     * Remove an attribute modifier from the item.
     *
     * @param attribute The attribute to remove the modifier from.
     * @return this.
     */
    @NotNull
    public ItemBuilder removeAttributeModifier(@NotNull Attribute attribute) {
        this.meta.removeAttributeModifier(attribute);
        return this;
    }

    /**
     * Remove an attribute modifier from the item.
     *
     * @param attribute The attribute to remove the modifier from.
     * @param modifier The modifier to remove.
     * @return this.
     */
    @NotNull
    public ItemBuilder removeAttributeModifier(@NotNull Attribute attribute, @NotNull AttributeModifier modifier) {
        this.meta.removeAttributeModifier(attribute, modifier);
        return this;
    }

    /**
     * Sets the color on
     * leather armor.
     *
     * @param color color.
     * @return this
     */
    @NotNull
    public ItemBuilder setLeatherColor(@NotNull Color color){
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) meta;
        leatherArmorMeta.setColor(color);
        return this;
    }

    /**
     * Sets if the item would be
     * unbreakable or not.
     *
     * @param unbreakable if true, unbreakable. If false, not unbreakable.
     * @return this
     */
    @NotNull
    public ItemBuilder setUnbreakable(boolean unbreakable){
        this.meta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Makes the item glowing.
     *
     * @param glowing true if it should glow, false if not.
     * @return this
     */
    @NotNull
    public ItemBuilder setGlowing(boolean glowing){
        if (glowing){
            addEnchant(Enchantment.LUCK, 1,  false);
            addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }else{
            if (this.item.hasItemFlag(ItemFlag.HIDE_ENCHANTS)){
                this.item.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
            if (this.item.getEnchantments().containsKey(Enchantment.LUCK)){
                this.item.removeEnchantment(Enchantment.LUCK);
            }
        }
        return this;
    }

    /**
     * This returns an itemstack
     * with all the modifications.
     *
     * @return item
     */
    @NotNull
    public ItemStack build(){
        this.item.setItemMeta(meta);
        return this.item;
    }
}