package com.relicum.scb.classes.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Interface for all Armours Items to implement
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class IArmour {

	/**
	 * Stores the Material of the Armours
	 */
	public ArmourEnum em;
	/**
	 * The ItemMeta
	 */
	public ItemMeta itm;
	/**
	 * ItemStack
	 */
	public ItemStack it;

	/**
	 * Instantiates a new I armour impl.
	 */
	public void IArmour(Integer i) {


	}

	/**
	 * Gets armour as ItemStack
	 *
	 * @return the ItemStack
	 */

	public ItemStack getArmour() {
		return it;
	}

	/**
	 * Sets armour by Material
	 *
	 * @param mat Material
	 */
	public void setArmour(Material mat) {
		this.it = new ItemStack(mat);

	}


	/**
	 * Gets colored armour item meta.
	 *
	 * @param i Integer
	 * @return ItemMeta armour item meta
	 */
	public ItemMeta getArmourItemMeta(Integer i) {
		itm = it.getItemMeta();
		return itm;
	}


	/**
	 * Save item meta.
	 */
	public void saveItemMeta() {

		it.setItemMeta(itm);

	}

	/**
	 * Sets armour material.
	 *
	 * @param emm ArmourEnum
	 */
	public void setArmourMaterial(ArmourEnum emm) {

		this.em = ArmourEnum.valueOf(emm.toString().toUpperCase());
	}

}
