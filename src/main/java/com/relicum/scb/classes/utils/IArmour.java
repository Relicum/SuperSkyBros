package com.relicum.scb.classes.utils;

import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

/**
 * The interface IArmour.
 */
public interface IArmour {


    /**
     * Gets id.
     *
     * @return the id
     */
    UUID getId();

    /**
     * Gets armour.
     *
     * @return the armour
     */
    public Armour getArmour();

    /**
     * Gets armour.
     *
     * @return the armour
     */
    public Armour getArmour(ArmourMaterial a);

    /**
     * Old stack.
     *
     * @return the item stack
     */
    public ItemStack armourStack();

    /**
     * Gets armour type.
     *
     * @return the armour type
     */
    public ArmourType getArmourType();

    /**
     * Gets armour material.
     *
     * @return the armour material
     */
    public ArmourMaterial getArmourMaterial();

    /**
     * Gets armour meta.
     *
     * @return the armour meta
     */
    public ArmourMeta getArmourMeta();

    /**
     * Ge armour item.
     *
     * @return the ItemStack
     */
    public ItemStack getArmourItem(ArmourItem armourItem);

    /**
     * Gets class name.
     *
     * @return the class name
     */
    public String getClassName();

    /**
     * Gets permission.
     *
     * @return the permission
     */
    public String getPermission();

    /**
     * Is vIP.
     *
     * @return the boolean
     */
    public boolean isVIP();

    /**
     * Is premium.
     *
     * @return the boolean
     */
    public boolean isPremium();

    /**
     * Has permission.
     *
     * @return the boolean
     */
    public boolean hasPermission();

    /**
     * Is oP.
     *
     * @return the boolean
     */
    public boolean isOP();

    /**
     * Gets lore.
     *
     * @return the lore
     */
    public List<String> getLore();

    /**
     * Gets lore list.
     *
     * @param lines the lines
     * @return the lore list
     */
    public boolean getLoreList(List<String> lines);

    /**
     * Sets line ro lore.
     *
     * @param line the line
     * @return the line ro lore
     */
    public boolean setLineOfLore(String line);

    /**
     * Gets item stack.
     *
     * @return the item stack
     */
    public double getItemStack();

    /**
     * Can remove armour.
     *
     * @return the boolean
     */
    public boolean canRemoveArmour();

    /**
     * Unsave enchants allowed.
     *
     * @return the boolean
     */
    public boolean unsaveEnchantsAllowed();

    /**
     * @return an items durability
     */
    public double getItemDurability();

    /**
     * All 4 Old items have the same settings. Makes it very fast to create classes very quickly
     */
    public void setAllSettingTheSame();

    public ItemStack getHelmet();

    public ItemStack getChestPlate();

    public ItemStack getLeggings();

    public ItemStack getBoots();

    public ItemStack[] getAllArmour();
}