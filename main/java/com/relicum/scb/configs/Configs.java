package com.relicum.scb.configs;

import com.relicum.scb.utils.ConfigAccessor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class Configs extends ConfigAccessor {
	/**
	 * Default Constructor
	 *
	 * @param plugin   JavaPlugin
	 * @param fileName String
	 */
	public Configs(JavaPlugin plugin, String fileName) {
		super(plugin, fileName);
	}
}
