package com.relicum.scb.configs;

import com.relicum.scb.types.SkyApi;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

/**
 * SuperSkyBros First Created 18/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerConfig {

    private File configFile;

    private FileConfiguration fileConfiguration;

    private String fileName;

    public PlayerConfig(String filename) {

        this.fileName = filename;
        this.configFile = new File(SkyApi.getSCB().getDataFolder().toString() + File.separatorChar + "players" + File.separatorChar, fileName);

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
     * Reloads the config file
     */
    public void reloadConfig() {

        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.configFile);

        // Look for defaults in the jar
        InputStream defConfigStream = SkyApi.getSCB().getResource(this.fileName);
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
                SkyApi.getSCB().getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
            }
        }
    }

    /**
     * Saves the default config
     */
    public void saveDefaultConfig() {

        if (!configFile.exists()) {
            SkyApi.getSCB().saveResource(fileName, false);
        }
    }

}
