package com.relicum.scb;

import java.util.List;
import java.util.Map;

/**
 * Smash Player
 *
 * @author Relicum
 * @version 0.1
 */
public class SmashPlayer {




	private Boolean inWorld;
	private String world;
	private String smasher;
	private int x;
	private int y;
	private int z;
	private List<String> l;
	private Map<String, Object> ms;

	public SmashPlayer(String n) {
		this.smasher = n;
	}


	public boolean isInWorld() {
		return inWorld;
	}

	public String getWorld() {
		return world;
	}


	public String getSmasher() {
		return smasher;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
}
