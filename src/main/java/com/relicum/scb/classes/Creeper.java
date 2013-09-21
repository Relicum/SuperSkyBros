package com.relicum.scb.classes;

import com.relicum.scb.classes.utils.Armour;
import com.relicum.scb.classes.utils.ArmourEnum;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;

/**
 * Creates a Creeper Class
 *
 * @author Relicum
 * @version 0.1
 */
public class Creeper extends BaseCreateClass {

    private Armour arm;

    public ItemStack[] sta = new ItemStack[4];


    /**
     * Instantiates a new Create class. The name of the player class will be the name of the java class so think
     * carefully about the names. The Class permission is also auto built based on the prefix scb.player folled by the
     * class name in lower case.
     */
    public Creeper() {
        super();
        this.arm = new Armour();
    }


    /**
     * Sets up armour.
     */
    public void setUpArmour() {

        arm.setArmourColor(Color.LIME);
        this.sta = arm.getAllArmour(ArmourEnum.LEATHER);
/*		ItemStack[] its = new ItemStack[4];
        its[0]=arm.getArmourItem(ArmourEnum.LEATHER, ArmourType.HELMET);
		its[1]=arm.getArmourItem(ArmourEnum.LEATHER, ArmourType.CHESTPLATE);
		its[2]=arm.getArmourItem(ArmourEnum.LEATHER, ArmourType.LEGGINGS);
		its[3]=arm.getArmourItem(ArmourEnum.LEATHER, ArmourType.BOOTS);
		System.out.println("There are currently " + its.length);*/


    }


}
