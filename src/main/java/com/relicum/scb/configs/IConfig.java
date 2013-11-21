package com.relicum.scb.configs;

import com.relicum.scb.SCB;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

/**
 * Custom config to match functionality of default config file
 *
 * @author Relicum
 * @version 0.3
 */
public class IConfig {

    private File configFile;

    private FileConfiguration fileConfiguration;

    private String fileName;


    public IConfig(String filename) {

        this.fileName = filename;
        this.configFile = new File(SCB.getInstance().getDataFolder(), fileName);


    }


    /**
     * Get config as instance of FileConfiguration
     *
     * @return FileConfiguration
     */
    public FileConfiguration getConfig() {

        if (this.fileConfiguration == null) {
            this.reloadConfig();
        }
        return this.fileConfiguration;
    }


    /**
     * Reloads the config fiule
     */
    public void reloadConfig() {

        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.configFile);

        // Look for defaults in the jar
        InputStream defConfigStream = SCB.getInstance().getResource(this.fileName);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            this.fileConfiguration.setDefaults(defConfig);
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
                SCB.getInstance().getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
            }
        }
    }


    /**
     * Saves the default config
     */
    public void saveDefaultConfig() {

        if (!configFile.exists()) {
            SCB.getInstance().saveResource(fileName, false);
        }
    }

}
