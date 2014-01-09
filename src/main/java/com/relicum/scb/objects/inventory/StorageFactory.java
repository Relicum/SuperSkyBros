package com.relicum.scb.objects.inventory;

import com.relicum.scb.types.SkyApi;

/**
 * SuperSkyBros
 * First Created 04/01/14
 *
 * @author Relicum
 * @version 0.1
 */
public class StorageFactory {

    public static InventoryStorage getFactory() {

        if (SkyApi.getSm().getStorageType().name().equalsIgnoreCase("flatfile")) {
            return new saveInvToFile();
        } else {

            return new saveInvToDb();
        }
    }

}
