/**
 * Name: PermissionSaver.java Edited: 25 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.scb.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import com.relicum.scb.types.SkyApi;
import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;

public class PermissionSaver {

    public static void saveAllPermsToFile() {

        StringBuilder ap = new StringBuilder();
        ap.append("#########     ").append("List of all Permissions registered to SSB").append("     ##########").append("\n");
        ap.append("#########     ").append("This list is in no particular order").append("     ##########").append("\n");
        ap.append("\n").append("\n");
        Set<Permission> per = SkyApi.getSCB().getServer().getPluginManager().getPermissions();

        for (Permission p : per) {
            if (p.getName().startsWith("ssb")) {
                ap.append("\n");
                ap.append("Name: ").append(p.getName()).append("\n");
                ap.append("Description: ");
                if (p.getDescription() != null) {
                    ap.append(ChatColor.stripColor(p.getDescription())).append("\n");
                } else {
                    ap.append("Unknown").append("\n");
                }
                ap.append("-----------------").append("\n");
            }

        }

        ap.append("\n").append("\n").append("############################################").append("\n").append("\n");
        ap.append("This section will now list the 3 main Parent nodes and all it's children").append("\n").append("\n");

        for (Permission permission : per) {
            if (permission.getName().startsWith("ssb")) {
                if (permission.getName().equalsIgnoreCase("ssba.admin.*")) {
                    ap.append("Parent Node: ssba.admin.*").append("\n");
                    ap.append("\tHas the Child Perms: ");
                    Permission ad = SkyApi.getSCB().getServer().getPluginManager().getPermission("ssba.admin.*");
                    Map<String, Boolean> adc = getChild(ad);
                    if (adc.size() > 1) {
                        for (String pn : adc.keySet()) {
                            ap.append(pn).append("\n");
                            Permission np = SkyApi.getSCB().getServer().getPluginManager().getPermission(pn);
                            Map<String, Boolean> npc = getChild(np);
                            ap.append("\t\tHas the children :").append("\n");
                            for (String nk : npc.keySet()) {
                                ap.append("\t\t\t").append(nk).append("\n");
                            }
                        }

                    } else {
                        for (String ak : adc.keySet()) {
                            System.out.println(ak);
                        }
                    }

                    ap.append("\n").append("##############").append("\n");
                }
                if (permission.getName().equalsIgnoreCase("ssb.player.*")) {
                    ap.append("\n").append("Parent Node: ssb.player.*").append("\n");
                    ap.append("\tHas the Child Perms: ").append("\n");
                    Permission ad = SkyApi.getSCB().getServer().getPluginManager().getPermission("ssb.player");
                    Map<String, Boolean> adc = ad.getChildren();
                    for (String ak : adc.keySet()) {
                        ap.append("\t\t").append(ak).append("\n");
                    }

                    ap.append("\n").append("##############").append("\n");
                }
            }

        }
        SkyApi.getCMsg().INFO("Attempting to write all permissions to file");
        try {
            org.apache.commons.io.FileUtils.writeStringToFile(new File(SkyApi.getSCB().getDataFolder() + "/permissions.txt"), ap.toString());
            SkyApi.getCMsg().INFO("File permissions.txt successfully written to disk.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(ap.toString());
    }

    public static Map<String, Boolean> getChild(Permission perm) {

        return perm.getChildren();

    }

}
