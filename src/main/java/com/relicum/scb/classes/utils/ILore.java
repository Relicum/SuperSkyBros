package com.relicum.scb.classes.utils;

import org.bukkit.enchantments.Enchantment;

import java.util.List;

/**
 * The interface ILore.
 */
public interface ILore {

    /**
     * Stores the String list of lores lines. either use addLoreline to add lines one at a time of use add Full Lore
     *
     * @return removes all the lore returns true on success
     */
    boolean removeLore();

    /**
     * Add single lore line. Can be used repeatedly to add multiple.
     *
     * @param line the line
     * @return the boolean  true of success
     */
    boolean addLoreLine(String line);

    /**
     * Add full lore. Past it a List<String> to add multiple lines at once
     *
     * @param List<String> the lines
     * @return the boolean  true on success
     */
    boolean addFullLore(List<String> lines);

    /**
     * Remove custom display name.
     *
     * @return the boolean true on success
     */
    boolean removeDisplayName();

    /**
     * Sets custom display name.  Can use standard MC color formatting
     *
     * @param name the name as a String
     * @return the display name
     */
    boolean setDisplayName(String name);

    /**
     * Removes the specified enchantment from this item meta.
     *
     * @param ench Enchantment to remove
     * @return true if the item meta changed as a result of this call, false otherwise
     */
    boolean removeAllEnchants();

    /**
     * Adds the specified enchantment to this item meta.
     *
     * @param ench                   Enchantment to add
     * @param level                  Level for the enchantment
     * @param ignoreLevelRestriction this indicates the enchantment should be applied, ignoring the level limit
     * @return true if the item meta changed as a result of this call, false otherwise
     */
    boolean addEnchant(Enchantment ench, int level, boolean ignoreLevelRestriction);

}
