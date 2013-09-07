package com.relicum.scb.configs;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class ArenaConfig extends IConfig {

	public Integer totalArenas;
	public Integer lastId;

	public ArenaConfig(String filename) {
		super(filename);
	}

	public void setTotalArenas() {

		totalArenas = this.getConfig().getInt("arena.total");
	}

	public void setLastId() {

		lastId = this.getConfig().getInt("arena.lastId");
	}
}
