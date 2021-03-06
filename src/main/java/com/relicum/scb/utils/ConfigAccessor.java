package com.relicum.scb.utils;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

/**
 * Easy Access for non default config files
 *
 * @version 0.1
 */
public class ConfigAccessor {

    private String fileName;

    private JavaPlugin plugin;

    private File configFile;

    private FileConfiguration fileConfiguration;


    /**
     * Default Constructor
     *
     * @param plugin   JavaPlugin
     * @param fileName String
     */
    public ConfigAccessor(JavaPlugin plugin, String fileName) {

        if (plugin == null) throw new IllegalArgumentException("plugin cannot be null");

        this.plugin = plugin;
        this.fileName = fileName;
        File dataFolder = plugin.getDataFolder();
        if (dataFolder == null) throw new IllegalStateException();
        this.configFile = new File(plugin.getDataFolder(), fileName);
    }


    /**
     * Get config as instance of FileConfiguration
     *
     * @return FileConfiguration
     */
    public FileConfiguration getConfig() {

        if (fileConfiguration == null) {
            this.reloadConfig();
        }
        return fileConfiguration;
    }


    /**
     * Reloads the config fiule
     */
    public void reloadConfig() {

        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);

        // Look for defaults in the jar
        InputStream defConfigStream = plugin.getResource(fileName);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            fileConfiguration.setDefaults(defConfig);
        }
    }


    /**
     * Saves the config file
     */
    public void saveConfig() {

        if (fileConfiguration == null || configFile == null) {
            return;
        } else {
            try {
                getConfig().save(configFile);
            } catch (IOException ex) {
                plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
            }
        }
    }


    /**
     * Saves the default config
     */
    public void saveDefaultConfig() {

        if (!configFile.exists()) {
            this.plugin.saveResource(fileName, false);
        }
    }
}
