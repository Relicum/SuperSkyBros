package com.relicum.scb.objects.inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * This is used to save and restore a players inventory if the server is in mixed mode
 * First Created 04/01/14
 *
 * @author Relicum
 * @version 0.1
 */
public class MixedInventoryManager {

    private Map<String, StorePlayerSettings> store;

    public MixedInventoryManager() {
        this.store = new HashMap<>();
    }

    /**
     * Check if Player settings are in hashMap
     *
     * @param name the name
     * @return the boolean
     */
    public boolean playerIsStored(String name) {
        return store.containsKey(name);
    }

    /**
     * Checks if Player is stored in persistent storage.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean playerIsStoredInStorage(String name) {
        return true;
    }

    /**
     * Put player settings into hashMap.
     *
     * @param settings the settings
     */
    public void putPlayerInStore(StorePlayerSettings settings) {
        this.store.put(settings.getPlayerName(), settings);
    }

    /**
     * Get player settings if stored in hashMap.
     *
     * @param name the name
     * @return the store player settings
     */
    public StorePlayerSettings getPlayerInStore(String name) {
        return store.get(name);
    }

    /**
     * Store inventory to persistent storage.
     *
     * @param settings the settings
     * @return the boolean
     */
    public boolean storeInventory(StorePlayerSettings settings) {

        InventoryStorage save = StorageFactory.getFactory();
        return save.save(settings);

    }

    /**
     * Restore inventory from persistent storage.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean restoreInventory(String name) {
        InventoryStorage storage = StorageFactory.getFactory();
        return storage.restore(name);
    }
}
