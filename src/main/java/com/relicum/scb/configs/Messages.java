package com.relicum.scb.configs;

import com.relicum.scb.utils.ConfigAccessor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class Messages extends ConfigAccessor {

    /**
     * Convenient and easy way to deal with other yml files using the same methods as the default
     *
     * @param jp JavaPlugin
     * @param fn String
     */
    public Messages(JavaPlugin jp, String fn) {
        super(jp, fn);
    }


    /**
     * Checks to see if the String path exists in the message file Will return True if it does False if not
     *
     * @param s String
     * @return boolean
     */
    public boolean stringExists(String s) {

        return getConfig().contains(s);
    }
}
