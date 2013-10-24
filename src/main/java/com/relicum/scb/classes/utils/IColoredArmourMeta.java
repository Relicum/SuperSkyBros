package com.relicum.scb.classes.utils;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;

/**
 * SuperSkyBros First Created 20/10/13 Color for leather armour interface Can only be applied to leather armour at this
 * point
 *
 * @author Relicum
 * @version 0.1
 */
public interface IColoredArmourMeta {

    /**
     * Gets armour color.
     *
     * @return the armour color
     */
    Color getArmourColor();

    /**
     * Sets armour color.
     *
     * @param color     the color
     * @param itemStack the item stack to apply the ItemMeta roo
     */
    void setArmourColor(Color color, ItemStack itemStack);

}
