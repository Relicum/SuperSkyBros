package com.relicum.scb.classes.utils;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class IClassArmour implements IColoredArmour {

    private ItemEnum itemType = ItemEnum.ARMOR;


    /**
     * @return ItemEnum
     */
    public ItemEnum getItemType() {

        return itemType;
    }


    public abstract void setArmourColor(Color co);

    public abstract void setArmourMaterial(ArmourEnum m);

    public abstract ItemStack[] getAllArmour(ArmourEnum em);

    public abstract ItemStack getHelmet(ArmourEnum em);

    public abstract ItemStack getChestPlate(ArmourEnum em);

    public abstract ItemStack getLeggings(ArmourEnum em);

    public abstract ItemStack getBoots(ArmourEnum em);


}
