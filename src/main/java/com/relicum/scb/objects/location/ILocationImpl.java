package com.relicum.scb.objects.location;

import com.relicum.scb.objects.LocationType;
import org.bukkit.Location;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class ILocationImpl implements ILocation {

	protected LocationType LT;
	protected Location Loc;
	protected String name;

	/**
	 * Constructor that takes a Location and LocationType Returns an instance of itself for chaining
	 *
	 * @param Lo Location
	 */

	public ILocationImpl(Location Lo) {
		super();
		setLocation(Lo);
		setLocationType();


	}

	public void setLocationDetails() {

	}

	/**
	 * Set Location
	 */
	@Override
	public void setLocation(Location lo) {

		Loc = lo;
		Loc.setWorld(lo.getWorld());
		Loc.setX(lo.getX());
		Loc.setY(lo.getY());
		Loc.setZ(lo.getZ());
		Loc.setPitch(0);
		Loc.setYaw(getDirection(lo.getYaw()));
	}

	/**
	 * Set LocationType
	 */
	@Override
	public void setLocationType() {

		LT = LocationType.LOBBYSPAWN;
	}

	/**
	 * Get the World the Location is in
	 *
	 * @return String
	 */
	@Override
	public String getWorld() {
		return Loc.getWorld().getName();
	}

	/**
	 * @return Location
	 */
	@Override
	public Location getLocation() {
		return this.Loc;
	}

	public void setName(String st) {

		this.name = st;
	}

	public String getName() {
		return this.name;
	}

	public String getLocationTypeAsString() {

		return this.LT.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[" + getLocationTypeAsString().toUpperCase() + " " + this.getWorld() + " ");
		sb.append(", ");
		sb.append("X-" + Loc.getBlockX());
		sb.append(", ");
		sb.append("Y-" + Loc.getBlockY());
		sb.append(", ");
		sb.append("Z-" + Loc.getBlockZ());
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Returns the direction you are looking
	 *
	 * @param yaw float
	 * @return float
	 */
	public float getDirection(Float yaw) {
		yaw = yaw / 90;
		yaw = (float) Math.round(yaw);

		if (yaw == -4 || yaw == 0 || yaw == 4) {
			return (0.00F);
		}
		if (yaw == -1 || yaw == 3) {
			return -90.00F;
		}
		if (yaw == -2 || yaw == 2) {
			return -179.00F;
		}
		if (yaw == -3 || yaw == 1) {
			return 90.00F;
		}
		return 5.00F;
	}
}
