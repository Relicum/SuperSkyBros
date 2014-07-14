package com.relicum.scb.objects.inventory;

import com.relicum.scb.objects.inventory.interfaces.IISettings;
import org.bukkit.GameMode;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;


/**
 * The type Store player settings.
 */

public class StorePlayerSettings implements IISettings, ConfigurationSerializable {

    /**
     * The Player time.
     */
    private Long playerTime;
    /**
     * The Exhaustion.
     */
    private float exhaustion;
    /**
     * The Saturation.
     */
    private float saturation;
    /**
     * The Player health.
     */
    private double playerHealth;
    /**
     * The Player damage.
     */
    private float playerDamage;
    /**
     * The Food level.
     */
    private int foodLevel;
    /**
     * The Total experience.
     */
    private int totalExperience;
    /**
     * The Level.
     */
    private int level;
    /**
     * The Xp.
     */
    private float xp;
    /**
     * The Allowed flight.
     */
    private boolean allowedFlight;
    /**
     * The Is flying.
     */
    private boolean isFlying;
    /**
     * The Fly speed.
     */
    private float flySpeed;
    /**
     * The Walk speed.
     */
    private float walkSpeed;
    /**
     * The Player name.
     */
    private String playerName;
    /**
     * The Display name.
     */
    private String displayName;
    /**
     * The Inventory.
     */
    private SerializableInventory inventory;

    /**
     * The Game mode.
     */
    private String gameMode;
    /**
     * The Fire ticks.
     */
    private int fireTicks;

    public StorePlayerSettings(Long playerTime, float exhaustion, float saturation, double playerHealth, float playerDamage, int foodLevel, int totalExperience, int level, float xp, boolean allowedFlight, boolean isFlying, float flySpeed, float walkSpeed, String playerName, String displayName, String gameMode, int fireTicks, SerializableInventory serializableInventory) {
        this.playerTime = playerTime;
        this.exhaustion = exhaustion;
        this.saturation = saturation;
        this.playerHealth = playerHealth;
        this.playerDamage = playerDamage;
        this.foodLevel = foodLevel;
        this.totalExperience = totalExperience;
        this.level = level;
        this.xp = xp;
        this.allowedFlight = allowedFlight;
        this.isFlying = isFlying;
        this.flySpeed = flySpeed;
        this.walkSpeed = walkSpeed;
        this.playerName = playerName;
        this.displayName = displayName;
        this.inventory = serializableInventory;
        this.gameMode = gameMode;
        this.fireTicks = fireTicks;
    }

    public StorePlayerSettings() {

    }

    /**
     * Deserialize store player settings.
     *
     * @param map the map
     * @return the store player settings
     */
    public static StorePlayerSettings deserialize(Map<String, Object> map) {


        Long OTime = (Long) map.get("playerTime");

        float OSaturation = (float) map.get("saturation"),
                OExhaustion = (float) map.get("exhaustion"),
                ODamage = (float) map.get("playerDamage"),
                OXp = (float) map.get("xp"),
                OWalkSpeed = (float) map.get("walkSpeed"),
                OFlySpeed = (float) map.get("flySpeed");

        double OHealth = (double) map.get("playerHealth");

        int OFood = (int) map.get("foodLevel"),
                OLevel = (int) map.get("level"),
                OExperience = (int) map.get("totalExperience"),
                OFireTicks = (int) map.get("fireTicks");

        boolean OIsflying = (boolean) map.get("isFlying"),
                OFlyAllowed = (boolean) map.get("allowedFlight");

        SerializableInventory OInv = (SerializableInventory) map.get("inventory");

        String OName = (String) map.get("playerName"),
                ODisplayName = (String) map.get("displayName"),
                OGameMode = (String) map.get("gameMode");


        return new StorePlayerSettings(OTime, OExhaustion, OSaturation, OHealth, ODamage, OFood, OExperience, OLevel, OXp, OFlyAllowed, OIsflying, OFlySpeed, OWalkSpeed, OName, ODisplayName, OGameMode, OFireTicks, OInv);
    }

    /**
     * Gets walk speed.
     *
     * @return the walk speed
     */
    public float getWalkSpeed() {


        return walkSpeed;
    }

    /**
     * Sets walk speed.
     *
     * @param walkSpeed the walk speed
     */
    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    /**
     * Gets fly speed.
     *
     * @return the fly speed
     */
    public float getFlySpeed() {
        return flySpeed;
    }

    /**
     * Sets fly speed.
     *
     * @param flySpeed the fly speed
     */
    public void setFlySpeed(float flySpeed) {
        this.flySpeed = flySpeed;
    }

    /**
     * Gets display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets display name.
     *
     * @param displayName the display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets game mode.
     *
     * @return the game mode
     */
    public GameMode getGameMode() {
        return GameMode.valueOf(this.gameMode);
    }

    /**
     * Sets game mode.
     *
     * @param gameMode the game mode
     */
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode.name();
    }

    /**
     * Gets food level.
     *
     * @return the food level
     */
    public int getFoodLevel() {
        return foodLevel;
    }

    /**
     * Sets food level.
     *
     * @param foodLevel the food level
     */
    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    /**
     * Gets player health.
     *
     * @return the player health
     */
    @Override
    public double getPlayerHealth() {
        return this.playerHealth;
    }

    /**
     * Sets player health.
     *
     * @param playerHealth the player health
     */
    public void setPlayerHealth(double playerHealth) {
        this.playerHealth = playerHealth;
    }

    /**
     * Gets player damage.
     *
     * @return the player damage
     */
    @Override
    public double getPlayerDamage() {
        return this.playerDamage;
    }

    /**
     * Sets player damage.
     *
     * @param playerDamage the player damage
     */
    public void setPlayerDamage(float playerDamage) {
        this.playerDamage = playerDamage;
    }

    /**
     * Gets total experience.
     *
     * @return the total experience
     */
    @Override
    public int getTotalExperience() {
        return this.totalExperience;
    }

    /**
     * Sets total experience.
     *
     * @param totalExperience the total experience
     */
    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }

    /**
     * Gets allowed flight.
     *
     * @return the allowed flight
     */
    @Override
    public boolean getAllowedFlight() {
        return this.allowedFlight;
    }

    /**
     * Sets allowed flight.
     *
     * @param allowedFlight the allowed flight
     */
    public void setAllowedFlight(boolean allowedFlight) {
        this.allowedFlight = allowedFlight;
    }

    /**
     * Gets is flying.
     *
     * @return the is flying
     */
    @Override
    public boolean getIsFlying() {
        return this.isFlying;
    }

    /**
     * Gets xp.
     *
     * @return the xp
     */
    @Override
    public float getXp() {
        return this.xp;
    }

    /**
     * Sets xp.
     *
     * @param xp the xp
     */
    public void setXp(float xp) {
        this.xp = xp;
    }

    /**
     * Sets flying.
     *
     * @param flying the flying
     */
    public void setFlying(boolean flying) {
        isFlying = flying;
    }

    /**
     * Gets player name.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Sets player name.
     *
     * @param playerName the player name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Gets fire ticks.
     *
     * @return the fire ticks
     */
    public int getFireTicks() {
        return fireTicks;
    }

    /**
     * Sets fire ticks.
     *
     * @param fireTicks the fire ticks
     */
    public void setFireTicks(int fireTicks) {
        this.fireTicks = fireTicks;
    }

    /**
     * Gets player time.
     *
     * @return the player time
     */
    public Long getPlayerTime() {
        return playerTime;
    }

    /**
     * Sets player time.
     *
     * @param playerTime the player time
     */
    public void setPlayerTime(Long playerTime) {
        this.playerTime = playerTime;
    }

    /**
     * Gets inventory.
     *
     * @return the inventory
     */
    public SerializableInventory getInventory() {
        return inventory;
    }

    /**
     * Sets inventory.
     *
     * @param inventory the inventory
     */
    public void setInventory(SerializableInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Gets exhaustion.
     *
     * @return the exhaustion
     */
    public float getExhaustion() {
        return exhaustion;
    }

    /**
     * Sets exhaustion.
     *
     * @param exhaustion the exhaustion
     */
    public void setExhaustion(float exhaustion) {
        this.exhaustion = exhaustion;
    }

    /**
     * Gets saturation.
     *
     * @return the saturation
     */
    public float getSaturation() {
        return saturation;
    }

    /**
     * Sets saturation.
     *
     * @param saturation the saturation
     */
    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("playerTime", this.playerTime);
        map.put("exhaustion", this.exhaustion);
        map.put("saturation", this.saturation);
        map.put("playerHealth", this.playerHealth);
        map.put("playerDamage", this.playerDamage);
        map.put("foodLevel", this.foodLevel);
        map.put("totalExperience", this.totalExperience);
        map.put("level", this.level);
        map.put("xp", this.xp);
        map.put("allowedFlight", this.allowedFlight);
        map.put("isFlying", this.isFlying);
        map.put("flySpeed", this.flySpeed);
        map.put("walkSpeed", this.walkSpeed);
        map.put("playerName", this.playerName);
        map.put("displayName", this.displayName);
        map.put("inventory", this.inventory);
        map.put("gameMode", this.gameMode);
        map.put("fireTicks", this.fireTicks);

        return map;
    }
}



