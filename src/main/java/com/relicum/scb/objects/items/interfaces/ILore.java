/**
 * Name: ILore.java Edited: 30 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.scb.objects.items.interfaces;

import java.util.List;

public interface ILore extends IMetable {
    /**
     * Gets lore.
     *
     * @return the lore
     */
    public List<String> getLore();

    /**
     * Sets the lore for the item as a {@link java.util.List} of
     * {@link java.lang.String}
     *
     * @param lore for the item passed as {@link java.util.List}
     */
    public void setLore(List<String> lore);
}
