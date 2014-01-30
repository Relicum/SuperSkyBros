/**
 * Name: IPermissible.java Edited: 30 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.scb.objects.items.interfaces;

public interface IPermissible {
    /**
     * Has permission. Returns True if the permission has been set
     * 
     * @return the boolean
     */
    boolean hasPermission();

    /**
     * Gets permission to use the item in a string format
     * 
     * @return the permission
     */
    String getPermission();
}
