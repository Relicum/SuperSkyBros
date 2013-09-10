package com.relicum.scb.utils;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class ColorByIndex {

	/**
	 * Get Color by int
	 *
	 * @param i int
	 * @return Color
	 */
	public static Color getColorByIndex(Integer i) {

		Color color;
		switch (i) {
			case 1:
				return Color.AQUA;
			case 2:
				return Color.BLACK;
			case 3:
				return Color.BLUE;
			case 4:
				return Color.FUCHSIA;

			case 5:
				return Color.GRAY;
			case 6:
				return Color.GREEN;

			case 7:
				return Color.LIME;

			case 8:
				return Color.MAROON;

			case 9:
				return Color.NAVY;

			case 10:
				return Color.OLIVE;

			case 11:
				return Color.ORANGE;

			case 12:
				return Color.PURPLE;

			case 13:
				return Color.RED;

			case 14:
				return Color.SILVER;

			case 15:
				return Color.TEAL;

			case 16:
				return Color.WHITE;

			default:
				return Color.YELLOW;

		}


	}

	/**
	 * Get color by Integer index.
	 *
	 * @param Integer
	 * @return Color
	 */
	public static Color getColor(Integer i) {

		return getColorByIndex(i);
	}

    @Deprecated
	private LeatherArmorMeta getLeatherMeta() {

		LeatherArmorMeta itm = (LeatherArmorMeta) new ItemStack(0).getItemMeta();

		return itm;
	}


}
