package com.relicum.scb;

import org.bukkit.Server;
import org.bukkit.World;

/**
 * BukkitInterface
 *
 * @author Relicum
 * @version 0.1
 */
public class BukkitInterface {

	/**
	 * Static p of BukkitInterface Server
	 */

	static Server server;

	/**
	 * Returns a p of a World by String name
	 *
	 * @param name String
	 * @return World
	 */
	public static World getWorld(String name) {
		return server.getWorld(name);
	}

	/**
	 * Sets the p of Bukkit Server
	 *
	 * @param server Server
	 */
	public static void setServer(Server server) {
		BukkitInterface.server = server;
	}

	public static Server getServer() {

		return server;
	}

}
