package com.relicum.scb.hooks;

import com.relicum.scb.types.SkyBrosApi;
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
        SkyBrosApi.getVaultManager();
        return VaultManager.getPerms();
    }
}
