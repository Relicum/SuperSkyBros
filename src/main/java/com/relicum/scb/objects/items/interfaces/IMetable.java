/**
 * Name: IMetable.java Edited: 30 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.scb.objects.items.interfaces;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * The interface IMetable The base interface for all items or item interfaces to
 * inherit off
 */
public interface IMetable {

    /**
     * Gets the items {@link org.bukkit.inventory.meta.ItemMeta} connected to it
     * 
     * @return the items {@link org.bukkit.inventory.meta.ItemMeta}
     */
    ItemMeta getItemMeta();

    /**
     * Gets material. The {@link org.bukkit.Material} the item is from
     * 
     * @return the material {@link org.bukkit.Material} of the item
     */
    Material getMaterial();

    /**
     * Set the Items {@link org.bukkit.Material}
     * 
     * @param material the material of the item
     */
    void setMaterial(Material material);

    /**
     * Sets the items {@link org.bukkit.inventory.meta.ItemMeta}
     * 
     * @param material which is used to set the
     *        {@link org.bukkit.inventory.meta.ItemMeta}
     */
    void setItemMeta(Material material);
}
