package com.relicum.scb;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.util.permissions.BroadcastPermissions;

/**
 * SuperSkyBros First Created 04/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class BroadcastManager {


    private static Permission lobby;


    private static void BroadcastManager() {

        createLobbyPermission();


    }


    public static void setup() {

        BroadcastManager();
    }


    private static void createLobbyPermission() {


        Permission per = new Permission("ssblobby");
        per.setDescription("Broadcast Messages to Players Only In the Lobby");
        per.setDefault(PermissionDefault.TRUE);

        lobby = BroadcastPermissions.registerPermissions(per);
    }


    /**
     * Get lobby Broadcast Permission
     *
     * @return the permission for Lobby Broadcast
     */
    public static Permission getLobby() {

        return lobby;

    }

}
