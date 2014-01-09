package com.relicum.scb.objects.inventory.interfaces;


import org.bukkit.inventory.ItemStack;

/**
 * SuperSkyBros First Created 02/10/13 Top level Interface for Inventor's
 *
 * @author Relicum
 * @version 0.1
 */
public interface IInventory {

    /**
     * Gets inventory as ItemStack[]
     *
     * @return the inventory as an ItemStack
     */
    ItemStack[] getInventory();

}
