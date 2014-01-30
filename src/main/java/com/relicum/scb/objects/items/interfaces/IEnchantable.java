/**
 * Name: IEnchantable.java Edited: 30 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.scb.objects.items.interfaces;

import java.util.List;
import org.bukkit.enchantments.Enchantment;

public interface IEnchantable extends IMetable {
    /**
     * Gets enchants.
     * 
     * @return the enchants
     */
    List<Enchantment> getEnchants();
}
