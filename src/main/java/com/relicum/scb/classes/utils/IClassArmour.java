package com.relicum.scb.classes.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public interface IClassArmour {

    ItemEnum itemType = ItemEnum.ARMOR;


    /**
     * @return ItemEnum
     */
    public ItemEnum getItemType();

    public Color getArmourColor();

    public void setArmourColor(Color co);

    public ArmourMaterial getArmourMaterial();

    public void setArmourMaterial(Material mat);

    public ItemStack[] getAllArmour(ArmourMaterial em);

    public ItemStack getHelmet(ArmourMaterial em);

    public ItemStack getChestPlate(ArmourMaterial em);

    public ItemStack getLeggings(ArmourMaterial em);

    public ItemStack getBoots(ArmourMaterial em);

    public ItemMeta getArmourMeta(Material mat);

}
