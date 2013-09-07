package com.relicum.scb.classes;

import com.relicum.scb.classes.utils.Armour;
import com.relicum.scb.classes.utils.IColoredArmour;
import com.relicum.scb.classes.utils.ItemEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Base Class that all other game Class extend from
 *
 * @author Relicum
 * @version 0.1
 */
public class BaseCreateClass {

	/**
	 *
	 */
	private String perm;

	/**
	 * Public Readable name for the class
	 */
	private String name;

	/**
	 * Stores the Armours class
	 *
	 * @see com.relicum.ssb.classes.utils.Armour
	 */
	public Armour armour;

	/**
	 * The outer map holding all the Class Elements
	 */
	protected Map<String, Object> outer = new HashMap<>();

	public IColoredArmour leatherMeta;

	/**
	 * Instantiates a new Create class. The name of the player class will be the name of the java class so think carefully
	 * about the names. The Class permission is also auto built based on the prefix scb.class. folled by the class name in
	 * lower case.
	 */
	public BaseCreateClass() {

		this.name = getClass().getSimpleName().toString();
		this.armour = new Armour();
		this.perm = "ssb.class." + name.toLowerCase();


	}


	/**
	 * Get armour. All settings are applied direct to this object
	 *
	 * @return the armour
	 */
	public Armour getArmour() {

		return this.armour;
	}

	/**
	 * Get the class name. This is auto built from the name of the class itself. Eg if the class was called Creeper name
	 * would equal Creeper.
	 *
	 * @return String string
	 */
	public String getName() {

		return this.name;
	}

	/**
	 * Save to file.
	 *
	 * @param file the file
	 * @return the boolean
	 */
	public boolean saveToFile(String file) {

		return true;
	}

	/**
	 * Load from file. Pass name of File Returns true of success
	 *
	 * @param file String
	 * @return the boolean
	 */
	public boolean loadFromFile(String file) {
		//TODO "This method probably doesn't below here"
		return true;
	}

	private void createHashSave() {

		outer.put(ItemEnum.ARMOR.name(), this.armour);

	}

	private void deasembleHashFromSave() {
		//todo Look to abstract this out to an interface

		this.armour = (Armour) outer.get(ItemEnum.ARMOR.name());
	}

	/**
	 * Gets the permission for the class. This is auto build. The prefix is scb.player. followed by the name of the class
	 * in lower case. So if class was called Creeper the permission would be scb.player.creeper.
	 *
	 * @return the String
	 */
	public String getPerm() {
		return perm;
	}

}
