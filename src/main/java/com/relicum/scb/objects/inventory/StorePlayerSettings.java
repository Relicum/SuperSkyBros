package com.relicum.scb.objects.inventory;

import com.relicum.scb.objects.inventory.interfaces.IISettings;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.bukkit.GameMode;
import org.bukkit.inventory.ItemStack;


/**
 * SuperSkyBros First Created 03/10/13
 *
 * @author Relicum
 * @version 0.1
 */

public class StorePlayerSettings implements IISettings {

    private double playerHealth;

    private float playerDamage;

    private int totalExperience;

    private boolean allowedFlight;

    private boolean isFlying;

    private String playerName;

    private ItemStack[] inv;

    private ItemStack[] armour = new ItemStack[4];

    private int foodLevel;

    private GameMode gameMode;

    private int level;

    private float xp;

    private String displayName;

    private float flySpeed;

    private float walkSpeed;


    public float getWalkSpeed() {


        return walkSpeed;
    }


    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }


    public float getFlySpeed() {
        return flySpeed;
    }


    public void setFlySpeed(float flySpeed) {
        this.flySpeed = flySpeed;
    }


    public String getDisplayName() {
        return displayName;
    }


    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    public int getLevel() {
        return level;
    }


    public void setLevel(int level) {
        this.level = level;
    }


    public void setXp(float xp) {
        this.xp = xp;
    }


    public ItemStack[] getInv() {
        return this.inv;
    }


    public ItemStack[] getArmour() {
        return this.armour;
    }


    public GameMode getGameMode() {
        return gameMode;
    }


    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }


    public int getFoodLevel() {
        return foodLevel;
    }


    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }


    public void setInventory(ItemStack[] pi) {
        this.inv = pi;

    }


    public void setArmour(ItemStack[] it) {
        this.armour = it;
    }


    @Override
    public double getPlayerHealth() {
        return this.playerHealth;
    }


    @Override
    public double getPlayerDamage() {
        return this.playerDamage;
    }


    @Override
    public int getTotalExperience() {
        return this.totalExperience;
    }


    @Override
    public boolean getAllowedFlight() {
        return this.allowedFlight;
    }


    @Override
    public boolean getIsFlying() {
        return this.isFlying;
    }


    @Override
    public float getXp() {
        return this.xp;
    }


    @Override
    public String getName() {
        return this.getName();
    }


    public void setPlayerHealth(double playerHealth) {
        this.playerHealth = playerHealth;
    }


    public void setPlayerDamage(float playerDamage) {
        this.playerDamage = playerDamage;
    }


    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }


    public void setAllowedFlight(boolean allowedFlight) {
        this.allowedFlight = allowedFlight;
    }


    public void setFlying(boolean flying) {
        isFlying = flying;
    }


    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
