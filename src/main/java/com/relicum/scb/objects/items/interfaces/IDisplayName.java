/**
 * Name: IDisplayName.java Edited: 30 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.scb.objects.items.interfaces;

public interface IDisplayName extends IMetable {
    /**
     * Gets display name.
     *
     * @return the display name of the item
     */
    public String getDisplayName();

    /**
     * Sets display name.
     *
     * @param name the display name to set for the item
     */
    public void setDisplayName(String name);
}
