package com.relicum.scb.classes.utils;

import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Simple class to use old Enchantment String NAMES Eg falling feather is now PROTECTION_FALL
 *
 * @author Relicum
 * @version 0.1
 */
public class EnchantUtil {

	/**
	 * The constant enchants.
	 */
	private static HashMap<String, Enchantment> enchants = new HashMap<String, Enchantment>();

	static {
		enchants.put("SHARPNESS", Enchantment.DAMAGE_ALL);
		enchants.put("POWER", Enchantment.ARROW_DAMAGE);
		enchants.put("FIRE_PROTECTION", Enchantment.PROTECTION_FIRE);
		enchants.put("FEATHER_FALLING", Enchantment.PROTECTION_FALL);
		enchants.put("PROTECTION", Enchantment.PROTECTION_ENVIRONMENTAL);
		enchants.put("BLAST_PROTECTION", Enchantment.PROTECTION_EXPLOSIONS);
		enchants.put("PROJECTILE_PROTECTION", Enchantment.PROTECTION_PROJECTILE);
		enchants.put("RESPIRATION", Enchantment.OXYGEN);
		enchants.put("INFINITY", Enchantment.ARROW_INFINITE);
		enchants.put("AQUA_AFFINITY", Enchantment.WATER_WORKER);
		enchants.put("UNBREAKING", Enchantment.DURABILITY);
		enchants.put("SMITE", Enchantment.DAMAGE_UNDEAD);
		enchants.put("BANE_OF_ANTHROPODS", Enchantment.DAMAGE_ARTHROPODS);
		enchants.put("EFFICIENCY", Enchantment.DIG_SPEED);
		enchants.put("FIRE_ASPECT", Enchantment.FIRE_ASPECT);
		enchants.put("SILK_TOUCH", Enchantment.SILK_TOUCH);
		enchants.put("FORTUNE", Enchantment.LOOT_BONUS_BLOCKS);
		enchants.put("LOOTING", Enchantment.LOOT_BONUS_MOBS);
		enchants.put("PUNCH", Enchantment.ARROW_KNOCKBACK);
		enchants.put("FLAME", Enchantment.ARROW_FIRE);
	}

	/**
	 * Gets enchantment.
	 *
	 * @param ench the ench
	 * @return the enchantment
	 */
	public static Enchantment getEnchantment(String ench) {
		ench = ench.toUpperCase().replace('-', '_');
		Enchantment e = Enchantment.getByName(ench);
		if (e == null) if (enchants.containsKey(ench)) {
			e = enchants.get(ench);
		}
		return e;
	}

	/**
	 * Gets enchantments.
	 *
	 * @return the enchantments
	 */
	public static List<String> getEnchantments() {
		List<String> list = new ArrayList<String>();
		for (String key : enchants.keySet()) {
			list.add(key.toLowerCase().replace('_', '-'));
		}
		return list;
	}

}
