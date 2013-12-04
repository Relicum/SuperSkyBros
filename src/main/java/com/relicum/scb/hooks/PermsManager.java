package com.relicum.scb.hooks;

import com.relicum.scb.types.SkyApi;
import net.milkbowl.vault.permission.Permission;

/**
 * SuperSkyBros First Created 19/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PermsManager {


    public PermsManager() {

    }

    public static Permission getPerms() {
        SkyApi.getVaultManager();
        return VaultManager.getPerms();
    }
}
