/**
 * Name: CommandSaver.java Edited: 25 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.scb.configs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.relicum.scb.types.SkyApi;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.permissions.Permission;

public class CommandSaver {

    private Map<PluginCommand, Permission> store = new HashMap<>();

    private String fileName;

    public void addToStore(PluginCommand cmd, Permission per) {
        store.put(cmd, per);
    }

    public void saveStoreToFile() {

        if (!SkyApi.getSCB().getConfig().getBoolean("storeCmds")) {
            System.out.println("You need to set storeCmds to true in config.yml");
            System.out.println("This is automatically set to false after each run");
            return;
        }

        StringBuilder ssb = new StringBuilder();
        ssb.append("Sub Commands registered for 'ssb'").append("\n\n");
        StringBuilder ssba = new StringBuilder();
        ssba.append("\n\t\t##############").append("Sub Commands registered for 'ssba'").append("##############").append("\n\n");
        StringBuilder ssbw = new StringBuilder();
        ssbw.append("\n\t\t##############").append("Sub Commands registered for 'ssbw'").append("##############").append("\n\n");
        if (store.size() == 0) {
            System.out.println("No commands registered");
        } else {
            System.out.println("Number of cmd register is " + store.size());
            PluginCommand cmd;
            Permission per;

            for (Map.Entry<PluginCommand, Permission> entry : store.entrySet()) {
                PluginCommand c = entry.getKey();
                Permission p = entry.getValue();
                StringBuilder sb = new StringBuilder();
                String[] sp = c.getLabel().split(" ");

                sb.append("Sub command ").append(c.getLabel()).append("\n");
                sb.append("\tCommand: ").append(c.getName()).append("\n");
                sb.append("\tHas the description: ").append(ChatColor.stripColor(c.getDescription())).append("\n");
                sb.append("\tHas the usage: ").append(c.getUsage()).append("\n\n");
                sb.append("\tHas the permission ").append(p.getName()).append("\n");
                sb.append("\tHas default setting: ").append(p.getDefault().toString()).append("\n");

                switch (sp[0]) {
                    case "ssb":
                        ssb.append("\n\n").append("----------------------").append("\n").append(sb);
                        break;
                    case "ssba":
                        ssba.append("\n\n").append("----------------------").append("\n").append(sb);
                        break;
                    case "ssbw":
                        ssbw.append("\n\n").append("----------------------").append("\n").append(sb);
                        break;
                }

            }

            StringBuilder fi = new StringBuilder();
            fi.append(ssb.toString()).append(ssba.toString()).append(ssbw.toString());
            SkyApi.getCMsg().INFO("Attempting to write all commands to file");
            try {
                org.apache.commons.io.FileUtils.writeStringToFile(new File(SkyApi.getSCB().getDataFolder() + "/" + fileName + ".txt"), fi.toString());
                SkyApi.getCMsg().INFO("File " + fileName + ".txt successfully written to disk.");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        SkyApi.getSCB().getConfig().set("storeCmds", false);

    }

    public Map<PluginCommand, Permission> getStore() {
        return store;
    }

    public CommandSaver(String name) {
        this.fileName = name;
    }

    private void saveAllPermsToFile() {
        String st = "";
    }
}
