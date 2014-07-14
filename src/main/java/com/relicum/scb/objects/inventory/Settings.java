package com.relicum.scb.objects.inventory;

import com.relicum.scb.types.SkyApi;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * SuperSkyBros First Created 02/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class Settings {

    private String playerName;

    private double playerHealth;

    private double playerDamage;

    private Integer totalExperience;

    private boolean allowedFlight = false;

    private boolean setFlying = true;

    private Inventory inv;
    private List<String> myl = new ArrayList<>();
    private ItemStack it;

    public Settings() {
    }

    public List<String> getMyl() {
        return myl;
    }

    public void setMyl(String myl) {
        this.myl.add(myl);
    }

    public void setInv() {
        this.inv = SkyApi.getSCB().getServer().createInventory(null, InventoryType.PLAYER);
    }

    public Inventory getInv() {
        return this.inv;
    }

    public ItemStack getIt() {

        return it;
    }

    public void setIt(ItemStack it) {
        this.it = it;
    }

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
}
