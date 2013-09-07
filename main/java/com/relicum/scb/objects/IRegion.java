package com.relicum.scb.objects;

import com.relicum.scb.BukkitInterface;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

/**
 * Interface for a IRegion. This should be a Lobby, an Arena, Death IRegion
 *
 * @author Relicum
 * @version 0.1
 */
public class IRegion {

	private Integer regionId;
	private Vector adminSpawn = null;
	private Vector max = null;
	private Vector min = null;
	private String world;
	private String umname;
	private String mn;


	public IRegion(Vector min, Vector max, Vector ad, Integer i, String wo, String mn) {
		this.world = wo;
		this.adminSpawn = ad;
		this.max = max;
		this.min = min;
		this.setMn(mn);
		this.setRegionID(i);
		this.setUmname();
	}


	/**
	 * Set the Region ID must be same as Arena
	 *
	 * @param id Integer
	 */
	public void setRegionID(Integer id) {
		this.regionId = id;
	}

	/**
	 * Get the Regions ID
	 *
	 * @return Integer
	 */
	public Integer getRegionID() {
		return this.regionId;
	}

	/**
	 * Set the Admin Spawn Location for the Region This is the center block, max Y+2
	 *
	 * @return Location
	 */
	public Location getadminSpawn() {

		return adminSpawn.toLocation(getWorld());
	}

	/**
	 * set The Max Location
	 *
	 * @return Location
	 */
	public Location getMaxLocation() {


		return max.toLocation(getWorld());
	}

	/**
	 * Set the min Location
	 *
	 * @return Location
	 */
	public Location setMinLocation() {

		return min.toLocation(getWorld());
	}

	/**
	 * Returns the Max vector
	 *
	 * @return Vector
	 */
	public Vector getMaxVector() {
		return this.max;
	}

	/**
	 * Returns the Min Vector
	 *
	 * @return Vector
	 */
	public Vector getMinVector() {
		return this.min;
	}

	/**
	 * Returns the AdminSpawn as Vector
	 *
	 * @return Vector
	 */
	public Vector getAdminSpawnVector() {
		return this.adminSpawn;
	}

	/**
	 * Returns bukkit world from string
	 *
	 * @return World
	 */
	public World getWorld() {

		return BukkitInterface.getServer().getWorld(this.world);
	}

	/**
	 * Checks if a location is in a Arena Region
	 *
	 * @param Vector
	 * @return boolean
	 */
	public boolean isAABB(Vector Vec) {

		return Vec.isInAABB(this.getMinVector(), this.getMaxVector());
	}

	/**
	 * Gets the map name
	 *
	 * @return String
	 */
	public String getMn() {
		return mn;
	}

	/**
	 * Sets map name
	 *
	 * @param String
	 */
	public void setMn(String mn) {
		this.mn = mn;
	}

	/**
	 * Gets Unique Map Name
	 *
	 * @return String
	 */
	public String getUmname() {
		return umname;
	}

	/**
	 * Sets Unique Map Name Made up of the map name and Arena ID to allow using the same map more than once
	 */
	public void setUmname() {
		this.umname = getMn().toUpperCase() + getRegionID();
	}
}
