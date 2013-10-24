package com.relicum.scb.classes.utils;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;


/**
 * SuperSkyBros First Created 21/10/13 Main Armour class that includes all the functionality to create and mange armour
 * which is just a module part of the main app and a few more lol
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class Armour implements IArmour, IColoredArmourMeta {

    private UUID armourId;

    protected Armour armour;

    protected ItemStack[] itemStack;

    protected ArmourMaterial armourMaterial;

    protected ArmourType armourType;

    protected ArmourMeta armourMeta;

    protected boolean isVip;

    protected boolean isPremium;

    protected boolean isYouTuber;

    protected boolean isPlayer;

    protected boolean isOwner;


    protected Armour() {


    }


    private final void IArmour() {
        if (this.armourId == null)
            this.armourId = UUID.randomUUID();
    }

    //ABSTRACT METHODS START


    /**
     * Gets armour color.
     *
     * @return the armour color
     */
    public abstract Color getArmourColor();


    /**
     * Sets armour color.
     *
     * @param color     the color
     * @param itemStack the item stack to apply the ItemMeta roo
     */
    public abstract void setArmourColor(Color color, ItemStack itemStack);

    //ABSTRACT METHODS END


    /**
     * Gets id.
     *
     * @return the id
     */
    @Override
    public UUID getId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Gets armour.
     *
     * @return the armour
     */
    @Override
    public Armour getArmour() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Gets armour.
     *
     * @return the armour
     */
    @Override
    public Armour getArmour(ArmourMaterial a) {
        return null;

    }


    /**
     * Old stack.
     *
     * @return the item stack
     */
    @Override
    public ItemStack armourStack() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Gets armour type.
     *
     * @return the armour type
     */
    @Override
    public ArmourType getArmourType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Gets armour material.
     *
     * @return the armour material
     */
    @Override
    public ArmourMaterial getArmourMaterial() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Gets armour meta.
     *
     * @return the armour meta
     */
    @Override
    public ArmourMeta getArmourMeta() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Ge armour item.
     *
     * @return the armour item
     */
    @Override
    public ItemStack getArmourItem(ArmourItem armourItem) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Gets class name.
     *
     * @return the class name
     */
    @Override
    public String getClassName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Gets permission.
     *
     * @return the permission
     */
    @Override
    public String getPermission() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Is vIP.
     *
     * @return the boolean
     */
    @Override
    public boolean isVIP() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Is premium.
     *
     * @return the boolean
     */
    @Override
    public boolean isPremium() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Has permission.
     *
     * @return the boolean
     */
    @Override
    public boolean hasPermission() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Is oP.
     *
     * @return the boolean
     */
    @Override
    public boolean isOP() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Gets lore.
     *
     * @return the lore
     */
    @Override
    public List<String> getLore() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Gets lore list.
     *
     * @param lines the lines
     * @return the lore list
     */
    @Override
    public boolean getLoreList(List<String> lines) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Sets line ro lore.
     *
     * @param line the line
     * @return the line ro lore
     */
    @Override
    public boolean setLineOfLore(String line) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Gets item stack.
     *
     * @return the item stack
     */
    @Override
    public double getItemStack() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Can remove armour.
     *
     * @return the boolean
     */
    @Override
    public boolean canRemoveArmour() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Unsave enchants allowed.
     *
     * @return the boolean
     */
    @Override
    public boolean unsaveEnchantsAllowed() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * @return an items durability
     */
    @Override
    public double getItemDurability() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * All 4 Old items have the same settings. Makes it very fast to create classes very quickly
     */
    @Override
    public void setAllSettingTheSame() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public ItemStack getHelmet() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public ItemStack getChestPlate() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public ItemStack getLeggings() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public ItemStack getBoots() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public ItemStack[] getAllArmour() {
        return new ItemStack[0];  //To change body of implemented methods use File | Settings | File Templates.
    }


    protected String armourName(String name) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
