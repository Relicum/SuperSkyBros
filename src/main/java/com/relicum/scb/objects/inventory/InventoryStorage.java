package com.relicum.scb.objects.inventory;

/**
 * Interface for Saving Inventories
 * First Created 04/01/14
 *
 * @author Relicum
 * @version 0.1
 */
public interface InventoryStorage {

    String getStorageType();

    boolean save(StorePlayerSettings value);

    boolean restore(String name);
}
