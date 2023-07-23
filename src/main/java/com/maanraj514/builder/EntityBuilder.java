package com.maanraj514.builder;

import com.maanraj514.utils.ColorUtil;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class EntityBuilder {
    // the entityType.
    private final EntityType entityType;
    // the name of the entity.
    private String name;
    // the health of the entity.
    private double health = 10.0;
    // the potionEffects that'll be applied to the entity.
    private final List<PotionEffect> potionEffects;
    // the attributes that'll be applied to the entity.
    private HashMap<Attribute, Double> attributes;
    // if true entity can move, false if not.
    private boolean canMove = true;
    // if true entity can pick up items, false if not.
    private boolean canPickUpItems = false;
    // if true entity is glowing, false if not.
    private boolean isGlowing = false;
    // if true entity has gravity, false if not.
    private boolean hasGravity = true;
    // if true entity is invincible, false if not.
    private boolean isInvincible = false;

    /**
     * Creates a new instance of
     * entityBuilder.
     *
     * @param entityType the entityType of the entity.
     */
    public EntityBuilder(@NotNull EntityType entityType){
        this.entityType = entityType;
        this.potionEffects = new ArrayList<>();
        this.attributes = new HashMap<>();
    }

    /**
     * Spawns the entity.
     *
     * @param location the location where the entity will spawn.
     * @return the entity.
     */
    @Nullable
    public Entity spawn(@NotNull Location location){
        Entity entity;

        entity = location.getWorld().spawnEntity(location, entityType);
        handlePostSpawn(entity);

        return entity;
    }

    /**
     * This handles what happens
     * after the entity spawns.
     *
     * @param entity the entity that spawned.
     */
    private void handlePostSpawn(@NotNull Entity entity){
        entity.setCustomNameVisible(this.name == null);
        entity.setCustomName(this.name == null ? "" : ColorUtil.translate(this.name));

        if(entity instanceof LivingEntity)
            handleLivingEntity(entity);

        entity.setGlowing(this.isGlowing);
        entity.setGravity(this.hasGravity);
        entity.setInvulnerable(this.isInvincible);
    }

    /**
     * This handles if the entity
     * is a livingEntity.
     *
     * @param entity the entity that is a livingEntity.
     */
    private void handleLivingEntity(Entity entity){
        LivingEntity livingEntity = (LivingEntity) entity;

        if (attributes.size() != 0){
            for (Map.Entry<Attribute, Double> map : attributes.entrySet()){
                AttributeInstance attribute1 = livingEntity.getAttribute(map.getKey());
                attribute1.setBaseValue(map.getValue());
            }
        }

        livingEntity.setHealth(this.health);
        livingEntity.setAI(canMove);
        livingEntity.setCanPickupItems(canPickUpItems);
        livingEntity.addPotionEffects(potionEffects);
    }

    /**
     * Adds potion effects if the
     * entity is a livingEntity.
     *
     * @param potionEffects the potionEffects.
     */
    public EntityBuilder addPotionEffects(@NotNull PotionEffect... potionEffects){
        this.potionEffects.addAll(Arrays.asList(potionEffects));
        return this;
    }

    /**
     * Adds an attribute to the
     * entity.
     *
     * @param attribute the attribute to be added.
     * @param baseValue the value of the attribute.
     * @return this.
     */
    public EntityBuilder addAttribute(@NotNull Attribute attribute, @NotNull Double baseValue){
        this.attributes.put(attribute, baseValue);
        return this;
    }

    /**
     * Adds all the attributes
     * from the hashmap to the
     * entity.
     *
     * @param attributes the hashMap for attributes.
     * @return this.
     */
    public EntityBuilder addAttribute(@NotNull HashMap<Attribute, Double> attributes){
        for (Map.Entry<Attribute, Double> map : attributes.entrySet()){
            if (!this.attributes.containsKey(map.getKey())){
                this.attributes.put(map.getKey(), map.getValue());
            }
        }
        return this;
    }

    /**
     * Replaces the current attributes map
     * with the new one.
     *
     * @param attributes the replacement for the current attributes map.
     * @return this.
     */
    public EntityBuilder setAttributes(@NotNull HashMap<Attribute, Double> attributes){
        this.attributes = attributes;
        return this;
    }

    /**
     * Sets the entity to
     * glowing.
     *
     * @param isGlowing if true, entity will glow, false if not.
     * @return this.
     */
    public EntityBuilder setGlowing(boolean isGlowing) {
        this.isGlowing = isGlowing;
        return this;
    }

    /**
     * Sets the entity to
     * have gravity.
     *
     * @param hasGravity if true, entity will have gravity, false if not.
     * @return this.
     */
    public EntityBuilder setGravity(boolean hasGravity) {
        this.hasGravity = hasGravity;
        return this;
    }

    /**
     * Sets the entity to
     * be invincible or not.
     *
     * @param isInvincible if true, entity will be invincible, false if not.
     * @return this.
     */
    public EntityBuilder setInvincible(boolean isInvincible) {
        this.isInvincible = isInvincible;
        return this;
    }

    /**
     * Sets the entity's health.
     *
     * @param health the health of the entity.
     * @return this.
     */
    public EntityBuilder setHealth(double health) {
        this.health = health;
        return this;
    }

    /**
     * Sets if the entity
     * can move or not.
     *
     * @param canMove if true, entity can move, false if not.
     * @return this.
     */
    public EntityBuilder setMove(boolean canMove) {
        this.canMove = canMove;
        return this;
    }

    /**
     * Sets if the entity
     * cna pick up items
     * or not.
     *
     * @param canPickUpItems if true, entity can pick up items, false if not.
     * @return this.
     */
    public EntityBuilder setItemPickup(boolean canPickUpItems) {
        this.canPickUpItems = canPickUpItems;
        return this;
    }

    /**
     * Sets the entity's name.
     *
     * @param name the name of the entity.
     * @return this.
     */
    public EntityBuilder setName(String name) {
        this.name = name;
        return this;
    }
}