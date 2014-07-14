/**
 * Name: IEnchantable.java Edited: 30 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.scb.objects.items.interfaces;

import org.bukkit.enchantments.Enchantment;

import java.util.List;

public interface IEnchantable extends IMetable {
    /**
     * Gets enchants.
     *
     * @return the enchants
     */
    List<Enchantment> getEnchants();
}
