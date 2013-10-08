package com.relicum.scb.objects.inventory;

import com.relicum.scb.SCB;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

/**
 * SuperSkyBros First Created 02/10/13
 *
 * @author Relicum
 * @version 0.1
 */
@XStreamAlias("settings")
public class Settings {


    @XStreamAlias("name")
    private String playerName;

    private double playerHealth;

    private double playerDamage;

    private Integer totalExperience;

    private boolean allowedFlight = false;

    private boolean setFlying = true;

    private Inventory inv;


    public List<String> getMyl() {
        return myl;
    }


    public void setMyl(String myl) {
        this.myl.add(myl);
    }


    public void setInv() {
        this.inv = SCB.getInstance().getServer().createInventory(null, InventoryType.PLAYER);
    }


    public Inventory getInv() {
        return this.inv;
    }


    @XStreamAlias("games")
    private List<String> myl = new ArrayList<>();


    public ItemStack getIt() {

        return it;
    }


    public void setIt(ItemStack it) {
        this.it = it;
    }


    private ItemStack it;


    public String getPlayerName() {
        return playerName;
    }


    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


    public double getPlayerHealth() {
        return playerHealth;
    }


    public void setPlayerHealth(double playerHealth) {
        this.playerHealth = playerHealth;
    }


    public double getPlayerDamage() {
        return playerDamage;
    }


    public void setPlayerDamage(double playerDamage) {
        this.playerDamage = playerDamage;
    }


    public Integer getTotalExperience() {
        return totalExperience;
    }


    public void setTotalExperience(Integer totalExperience) {
        this.totalExperience = totalExperience;
    }


    public boolean isAllowedFlight() {
        return allowedFlight;
    }


    public void setAllowedFlight(boolean aAllowedFlight) {
        this.allowedFlight = aAllowedFlight;
    }


    public boolean isSetFlying() {
        return setFlying;
    }


    public void setSetFlying(boolean setFlying) {
        this.setFlying = setFlying;
    }


    public Settings() {
    }
}
