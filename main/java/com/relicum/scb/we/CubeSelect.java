package com.relicum.scb.we;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.regions.CuboidRegion;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class CubeSelect extends CuboidSelection {


	/**
	 * Constructor
	 *
	 * @param w World
	 */

	public CubeSelect(World wo, Location pos1, Location pos2) {
		super(wo, pos1, pos2);

	}


	/**
	 * Returns a CuboidRegion based on the selection made;
	 *
	 * @return CuboidRegion
	 * @exception IncompleteRegionException
	 */
	@Override
	public CuboidRegion getRegion() {

		try {
			return (CuboidRegion) this.getRegionSelector().getRegion();
		} catch (IncompleteRegionException e) {
			System.out.println(e.getStackTrace().toString());
		}

		return null;
	}

	/**
	 * Set the region.
	 *
	 * @param cu CuboidRegion
	 * @return boolean
	 */
	public boolean setTheRegion(CuboidRegion cu) {
		try {
			this.setRegion(cu);
			return true;
		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
			return false;
		}
	}
}
