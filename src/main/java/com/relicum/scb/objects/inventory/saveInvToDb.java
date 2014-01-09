package com.relicum.scb.objects.inventory;

/**
 * Used to Store a Players Inventory and Settings to Mysql when the server is in mixed Mode
 * First Created 04/01/14
 *
 * @author Relicum
 * @version 0.1
 */
public class saveInvToDb implements InventoryStorage {

    private String playerName;
    private StorageType type = StorageType.MYSQL;

    public saveInvToDb() {
    }

    @Override
    public String getStorageType() {
        return type.name();
    }

    @Override
    public boolean save(StorePlayerSettings value) {
        return false;
    }

    @Override
    public boolean restore(String name) {
        return false;
    }


}
