/**
 * Name: ImprovedOfflinePlayer.java Edited: 31 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.scb.utils;

/**
 * ImprovedOfflinePlayer, a library for Bukkit.
 * Copyright (C) 2014 one4me@github.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.server.v1_7_R1.*;
import com.google.common.io.Files;
import org.bukkit.*;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_7_R1.inventory.CraftInventoryPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

/**
 * The type Improved offline player.
 */
public class ImprovedOfflinePlayer {
    /**
     * The Player.
     */
    private String player;
    /**
     * The File.
     */
    private File file;
    /**
     * The Compound.
     */
    private NBTTagCompound compound;
    /**
     * The Exists.
     */
    private boolean exists = false;
    /**
     * The Autosave.
     */
    private boolean autosave = true;

    /**
     * Instantiates a new Improved offline player.
     * 
     * @param playername the playername
     */
    public ImprovedOfflinePlayer(String playername) {
        this.exists = loadPlayerData(playername);
    }

    /**
     * Instantiates a new Improved offline player.
     * 
     * @param offlineplayer the offlineplayer
     */
    public ImprovedOfflinePlayer(OfflinePlayer offlineplayer) {
        this.exists = loadPlayerData(offlineplayer.getName());
    }

    /**
     * Load player data.
     * 
     * @param name the name
     * @return the boolean
     */
    private boolean loadPlayerData(String name) {
        try {
            this.player = name;
            for (World w : Bukkit.getWorlds()) {
                this.file = new File(w.getWorldFolder(), "players" + File.separator + this.player + ".dat");
                if (this.file.exists()) {
                    this.compound = NBTCompressedStreamTools.a(new FileInputStream(this.file));
                    this.player = this.file.getCanonicalFile().getName().replace(".dat", "");
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Save player data.
     */
    public void savePlayerData() {
        if (this.exists) {
            try {
                NBTCompressedStreamTools.a(this.compound, new FileOutputStream(this.file));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Exists boolean.
     * 
     * @return the boolean
     */
    public boolean exists() {
        return this.exists;
    }

    /**
     * Gets auto save.
     * 
     * @return the auto save
     */
    public boolean getAutoSave() {
        return this.autosave;
    }

    /**
     * Sets auto save.
     * 
     * @param autosave the autosave
     */
    public void setAutoSave(boolean autosave) {
        this.autosave = autosave;
    }

    /**
     * Copy data to.
     * 
     * @param playername the playername
     */
    public void copyDataTo(String playername) {
        try {
            if (!playername.equalsIgnoreCase(this.player)) {
                Player to = Bukkit.getPlayerExact(playername);
                Player from = Bukkit.getPlayerExact(this.player);
                if (from != null) {
                    from.saveData();
                }
                Files.copy(this.file, new File(this.file.getParentFile(), playername + ".dat"));
                if (to != null) {
                    to.teleport(from == null ? getLocation() : from.getLocation());
                    to.loadData();
                }
            } else {
                Player player = Bukkit.getPlayerExact(this.player);
                if (player != null) {
                    player.saveData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets abilities.
     * 
     * @return the abilities
     */
    public PlayerAbilities getAbilities() {
        PlayerAbilities pa = new PlayerAbilities();
        pa.a(this.compound);
        return pa;
    }

    /**
     * Sets abilities.
     * 
     * @param abilities the abilities
     */
    public void setAbilities(PlayerAbilities abilities) {
        abilities.a(this.compound);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets absorption amount.
     * 
     * @return the absorption amount
     */
    public float getAbsorptionAmount() {
        return this.compound.getFloat("AbsorptionAmount");
    }

    /**
     * Sets absorption amount.
     * 
     * @param input the input
     */
    public void setAbsorptionAmount(float input) {
        this.compound.setFloat("AbsorptionAmount", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets attributes.
     * 
     * @return the attributes
     */
    public AttributeMapBase getAttributes() {
        AttributeMapBase amb = (AttributeMapBase) new AttributeMapServer();
        GenericAttributes.a(amb, this.compound.getList("Attributes", 10));
        return amb;
    }

    /**
     * Sets attributes.
     * 
     * @param attributes the attributes
     */
    public void setAttributes(AttributeMapBase attributes) {
        this.compound.set("Attributes", GenericAttributes.a(attributes));
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets bed spawn location.
     * 
     * @return the bed spawn location
     */
    public Location getBedSpawnLocation() {
        return new Location(Bukkit.getWorld(this.compound.getString("SpawnWorld")), this.compound.getInt("SpawnX"), this.compound.getInt("SpawnY"), this.compound.getInt("SpawnZ"));
    }

    /**
     * Sets bed spawn location.
     * 
     * @param location the location
     * @param override the override
     */
    public void setBedSpawnLocation(Location location, Boolean override) {
        this.compound.setInt("SpawnX", (int) location.getX());
        this.compound.setInt("SpawnY", (int) location.getY());
        this.compound.setInt("SpawnZ", (int) location.getZ());
        this.compound.setString("SpawnWorld", location.getWorld().getName());
        this.compound.setBoolean("SpawnForced", override == null ? false : override);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets ender chest.
     * 
     * @return the ender chest
     */
    public Inventory getEnderChest() {
        InventoryEnderChest endchest = new InventoryEnderChest();
        if (this.compound.hasKeyOfType("EnderItems", 9)) {
            endchest.a(this.compound.getList("EnderItems", 10));
        }
        return new CraftInventory(endchest);
    }

    /**
     * Sets ender chest.
     * 
     * @param inventory the inventory
     */
    public void setEnderChest(Inventory inventory) {
        this.compound.set("EnderItems", ((InventoryEnderChest) ((CraftInventory) inventory).getInventory()).h());
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets exhaustion.
     * 
     * @return the exhaustion
     */
    public float getExhaustion() {
        return this.compound.getFloat("foodExhaustionLevel");
    }

    /**
     * Sets exhaustion.
     * 
     * @param input the input
     */
    public void setExhaustion(float input) {
        this.compound.setFloat("foodExhaustionLevel", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets exp.
     * 
     * @return the exp
     */
    public float getExp() {
        return this.compound.getFloat("XpP");
    }

    /**
     * Sets exp.
     * 
     * @param input the input
     */
    public void setExp(float input) {
        this.compound.setFloat("XpP", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets fall distance.
     * 
     * @return the fall distance
     */
    public float getFallDistance() {
        return this.compound.getFloat("FallDistance");
    }

    /**
     * Sets fall distance.
     * 
     * @param input the input
     */
    public void setFallDistance(float input) {
        this.compound.setFloat("FallDistance", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets fire ticks.
     * 
     * @return the fire ticks
     */
    public int getFireTicks() {
        return this.compound.getShort("Fire");
    }

    /**
     * Sets fire ticks.
     * 
     * @param input the input
     */
    public void setFireTicks(int input) {
        this.compound.setShort("Fire", (short) input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets fly speed.
     * 
     * @return the fly speed
     */
    public float getFlySpeed() {
        return this.compound.getCompound("abilities").getFloat("flySpeed");
    }

    /**
     * Sets fly speed.
     * 
     * @param speed the speed
     */
    public void setFlySpeed(float speed) {
        this.compound.getCompound("abilities").setFloat("flySpeed", speed);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets food level.
     * 
     * @return the food level
     */
    public int getFoodLevel() {
        return this.compound.getInt("foodLevel");
    }

    /**
     * Sets food level.
     * 
     * @param input the input
     */
    public void setFoodLevel(int input) {
        this.compound.setInt("foodLevel", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets food tick timer.
     * 
     * @return the food tick timer
     */
    public int getFoodTickTimer() {
        return this.compound.getInt("foodTickTimer");
    }

    /**
     * Sets food tick timer.
     * 
     * @param input the input
     */
    public void setFoodTickTimer(int input) {
        this.compound.setInt("foodTickTimer", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets game mode.
     * 
     * @return the game mode
     */
    public GameMode getGameMode() {
        return GameMode.values()[this.compound.getInt("playerGameType")];
    }

    /**
     * Sets game mode.
     * 
     * @param input the input
     */
    @SuppressWarnings("deprecation")
    // Untested in 1.7
    public void setGameMode(GameMode input) {
        this.compound.setInt("playerGameType", input.getValue());
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets health float.
     * 
     * @return the health float
     */
    public float getHealthFloat() {
        return this.compound.getFloat("HealF");
    }

    /**
     * Sets health float.
     * 
     * @param input the input
     */
    public void setHealthFloat(float input) {
        this.compound.setFloat("HealF", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets health int.
     * 
     * @return the health int
     */
    public int getHealthInt() {
        return this.compound.getShort("Health");
    }

    /**
     * Sets health int.
     * 
     * @param input the input
     */
    public void setHealthInt(int input) {
        this.compound.setShort("Health", (short) input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets inventory.
     * 
     * @return the inventory
     */
    public org.bukkit.inventory.PlayerInventory getInventory() {
        PlayerInventory inventory = new PlayerInventory(null);
        inventory.b(this.compound.getList("Inventory", 10));
        return new CraftInventoryPlayer(inventory);
    }

    /**
     * Sets inventory.
     * 
     * @param inventory the inventory
     */
    public void setInventory(org.bukkit.inventory.PlayerInventory inventory) {
        this.compound.set("Inventory", ((CraftInventoryPlayer) inventory).getInventory().a(new NBTTagList()));
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets is invulnerable.
     * 
     * @return the is invulnerable
     */
    public boolean getIsInvulnerable() {
        return compound.getBoolean("Invulnerable");
    }

    /**
     * Sets is invulnerable.
     * 
     * @param input the input
     */
    public void setIsInvulnerable(boolean input) {
        this.compound.setBoolean("Invulnerable", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets is on ground.
     * 
     * @return the is on ground
     */
    public boolean getIsOnGround() {
        return compound.getBoolean("OnGround");
    }

    /**
     * Sets is on ground.
     * 
     * @param input the input
     */
    public void setIsOnGround(boolean input) {
        this.compound.setBoolean("OnGround", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets is sleeping.
     * 
     * @return the is sleeping
     */
    public boolean getIsSleeping() {
        return this.compound.getBoolean("Sleeping");
    }

    /**
     * Sets is sleeping.
     * 
     * @param input the input
     */
    public void setIsSleeping(boolean input) {
        this.compound.setBoolean("Sleeping", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets item in hand.
     * 
     * @return the item in hand
     */
    public int getItemInHand() {
        return this.compound.getInt("SelectedItemSlot");
    }

    /**
     * Sets item in hand.
     * 
     * @param input the input
     */
    public void setItemInHand(int input) {
        this.compound.setInt("SelectedItemSlot", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets level.
     * 
     * @return the level
     */
    public int getLevel() {
        return this.compound.getInt("XpLevel");
    }

    /**
     * Sets level.
     * 
     * @param input the input
     */
    public void setLevel(int input) {
        this.compound.setInt("XpLevel", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets location.
     * 
     * @return the location
     */
    public Location getLocation() {
        NBTTagList position = this.compound.getList("Pos", 10);
        NBTTagList rotation = this.compound.getList("Rotation", 10);
        return new Location(Bukkit.getWorld(new UUID(this.compound.getLong("WorldUUIDMost"), this.compound.getLong("WorldUUIDLeast"))), position.d(0), position.d(1),
                            position.d(2), rotation.e(0), rotation.e(1));
    }

    /**
     * Sets location.
     * 
     * @param location the location
     */
    public void setLocation(Location location) {
        World w = location.getWorld();
        UUID uuid = w.getUID();
        this.compound.setLong("WorldUUIDMost", uuid.getMostSignificantBits());
        this.compound.setLong("WorldUUIDLeast", uuid.getLeastSignificantBits());
        this.compound.setInt("Dimension", w.getEnvironment().ordinal());
        NBTTagList position = new NBTTagList();
        position.add(new NBTTagDouble(location.getX()));
        position.add(new NBTTagDouble(location.getY()));
        position.add(new NBTTagDouble(location.getZ()));
        this.compound.set("Pos", position);
        NBTTagList rotation = new NBTTagList();
        rotation.add(new NBTTagFloat(location.getYaw()));
        rotation.add(new NBTTagFloat(location.getPitch()));
        this.compound.set("Rotation", rotation);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets name.
     * 
     * @return the name
     */
    public String getName() {
        return this.player;
    }

    /**
     * Gets portal cooldown.
     * 
     * @return the portal cooldown
     */
    public int getPortalCooldown() {
        return this.compound.getInt("PortalCooldown");
    }

    /**
     * Sets portal cooldown.
     * 
     * @param input the input
     */
    public void setPortalCooldown(int input) {
        this.compound.setInt("PortalCooldown", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets potion effects.
     * 
     * @return the potion effects
     */
    @SuppressWarnings("deprecation")
    // Untested in 1.7
    public ArrayList<PotionEffect> getPotionEffects() {
        ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
        if (this.compound.hasKey("ActiveEffects")) {
            NBTTagList list = this.compound.getList("ActiveEffects", 10);
            for (int i = 0; i < list.size(); i++) {
                NBTTagCompound effect = (NBTTagCompound) list.get(i);
                byte amp = effect.getByte("Amplifier");
                byte id = effect.getByte("Id");
                int time = effect.getInt("Duration");
                effects.add(new PotionEffect(PotionEffectType.getById(id), time, amp));
            }
        }
        return effects;
    }

    /**
     * Sets potion effects.
     * 
     * @param effects the effects
     */
    @SuppressWarnings("deprecation")
    // Untested in 1.7
    public void setPotionEffects(ArrayList<PotionEffect> effects) {
        if (effects.isEmpty()) {
            this.compound.remove("ActiveEffects");
            if (this.autosave)
                savePlayerData();
            return;
        }
        NBTTagList activeEffects = new NBTTagList();
        for (PotionEffect pe : effects) {
            NBTTagCompound eCompound = new NBTTagCompound();
            eCompound.setByte("Amplifier", (byte) (pe.getAmplifier()));
            eCompound.setByte("Id", (byte) (pe.getType().getId()));
            eCompound.setInt("Duration", (int) (pe.getDuration()));
            activeEffects.add(eCompound);
        }
        this.compound.set("ActiveEffects", activeEffects);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets remaining air.
     * 
     * @return the remaining air
     */
    public int getRemainingAir() {
        return this.compound.getShort("Air");
    }

    /**
     * Sets remaining air.
     * 
     * @param input the input
     */
    public void setRemainingAir(int input) {
        this.compound.setShort("Air", (short) input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets saturation.
     * 
     * @return the saturation
     */
    public float getSaturation() {
        return this.compound.getFloat("foodSaturationLevel");
    }

    /**
     * Sets saturation.
     * 
     * @param input the input
     */
    public void setSaturation(float input) {
        this.compound.setFloat("foodSaturationLevel", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets score.
     * 
     * @return the score
     */
    public float getScore() {
        return this.compound.getFloat("foodSaturationLevel");
    }

    /**
     * Sets score.
     * 
     * @param input the input
     */
    public void setScore(int input) {
        this.compound.setInt("Score", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets time attack.
     * 
     * @return the time attack
     */
    public short getTimeAttack() {
        return this.compound.getShort("AttackTime");
    }

    /**
     * Sets time attack.
     * 
     * @param input the input
     */
    public void setTimeAttack(short input) {
        this.compound.setShort("AttackTime", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets time death.
     * 
     * @return the time death
     */
    public short getTimeDeath() {
        return this.compound.getShort("DeathTime");
    }

    /**
     * Sets time death.
     * 
     * @param input the input
     */
    public void setTimeDeath(short input) {
        this.compound.setShort("DeathTime", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets time hurt.
     * 
     * @return the time hurt
     */
    public short getTimeHurt() {
        return this.compound.getShort("HurtTime");
    }

    /**
     * Sets time hurt.
     * 
     * @param input the input
     */
    public void setTimeHurt(short input) {
        this.compound.setShort("HurtTime", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets time sleep.
     * 
     * @return the time sleep
     */
    public short getTimeSleep() {
        return this.compound.getShort("SleepTimer");
    }

    /**
     * Sets time sleep.
     * 
     * @param input the input
     */
    public void setTimeSleep(short input) {
        this.compound.setShort("SleepTimer", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets total experience.
     * 
     * @return the total experience
     */
    public int getTotalExperience() {
        return this.compound.getInt("XpTotal");
    }

    /**
     * Sets total experience.
     * 
     * @param input the input
     */
    public void setTotalExperience(int input) {
        this.compound.setInt("XpTotal", input);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets velocity.
     * 
     * @return the velocity
     */
    public Vector getVelocity() {
        NBTTagList list = this.compound.getList("Motion", 10);
        return new Vector(list.d(0), list.d(1), list.d(2));
    }

    /**
     * Sets velocity.
     * 
     * @param vector the vector
     */
    public void setVelocity(Vector vector) {
        NBTTagList motion = new NBTTagList();
        motion.add(new NBTTagDouble(vector.getX()));
        motion.add(new NBTTagDouble(vector.getY()));
        motion.add(new NBTTagDouble(vector.getZ()));
        this.compound.set("Motion", motion);
        if (this.autosave)
            savePlayerData();
    }

    /**
     * Gets walk speed.
     * 
     * @return the walk speed
     */
    public float getWalkSpeed() {
        return this.compound.getCompound("abilities").getFloat("walkSpeed");
    }

    /**
     * Sets walk speed.
     * 
     * @param speed the speed
     */
    public void setWalkSpeed(float speed) {
        this.compound.getCompound("abilities").setFloat("walkSpeed", speed);
        if (this.autosave)
            savePlayerData();
    }
}
/*
 * Copyright (C) 2014 one4me@github.com
 */
