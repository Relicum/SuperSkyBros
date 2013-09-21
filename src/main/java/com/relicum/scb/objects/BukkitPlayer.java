package com.relicum.scb.objects;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.List;
import java.util.Set;


/**
 * Bukkit Player Wrapper This Bukkit Player wrapper ws not written by myself and the credit goes to The author below.
 *
 * @author Ultimate-N00b
 */
@SuppressWarnings({"InfiniteRecursion"})
public class BukkitPlayer {

    public String p;


    public BukkitPlayer(Player p) {
        this.p = p.getName();
    }


    public BukkitPlayer(CommandSender sender) {
        this.p = ((Player) sender).getName();
    }


    public static BukkitPlayer wrap(Player p) {
        return new BukkitPlayer(p);
    }


    /*
     * Methods from CraftPlayer
     */
    public boolean isPlayer() {
        return !Bukkit.getPlayerExact(p).equals(null);
    }


    public BukkitPlayer sendMessage(String msg) {
        getPlayer().sendMessage(msg);
        return this;
    }


    public BukkitPlayer sendMessage(String[] msg) {
        getPlayer().sendMessage(msg);
        return this;
    }


    public BukkitPlayer sendColoredMessage(String msg, char c) {
        sendMessage(color(msg, c));
        return this;
    }


    public BukkitPlayer sendColoredMessage(String[] msg, char c) {
        for ( int i = 0; i <= msg.length; i++ )
            msg[i] = color(msg[i], c);
        sendMessage(msg);
        return this;
    }


    public BukkitPlayer sendColoredMessage(String msg) {
        sendMessage(color(msg));
        return this;
    }


    public BukkitPlayer sendColoredMessage(String[] msg) {
        for ( int i = 0; i <= msg.length; i++ )
            msg[i] = color(msg[i]);
        sendMessage(msg);
        return this;
    }


    public boolean isOp() {
        return getPlayer().isOp();
    }


    public BukkitPlayer setOp(boolean op) {
        getPlayer().setOp(op);
        return this;
    }


    public BukkitPlayer setOp() {
        return setOp(true);
    }


    public BukkitPlayer toggleOp() {
        return setOp(!isOp());
    }


    public BukkitPlayer removeOp() {
        return setOp(false);
    }


    public boolean isOnline() {
        return getPlayer().isOnline();
    }


    public InetSocketAddress getAddress() {
        return getPlayer().getAddress();
    }


    public double getEyeHeight() {
        return getEyeHeight(false);
    }


    public double getEyeHeight(boolean ignoreSneaking) {
        return getPlayer().getEyeHeight(ignoreSneaking);
    }


    public BukkitPlayer sendRawMessage(String msg) {
        getPlayer().sendRawMessage(msg);
        return this;
    }


    public String getDisplayName() {
        return getPlayer().getDisplayName();
    }


    public BukkitPlayer setDisplayName(String name) {
        getPlayer().setDisplayName(name);
        return this;
    }


    public BukkitPlayer setColoredDisplayName(String name, char c) {
        return setDisplayName(color(name, c));
    }


    public BukkitPlayer setColoredDisplayName(String name) {
        return setDisplayName(color(name));
    }


    public BukkitPlayer setPlayerListName(String name) {
        getPlayer().setPlayerListName(name);
        return this;
    }


    public BukkitPlayer setColoredPlayerListName(String name, char c) {
        return setPlayerListName(color(name, c));
    }


    public BukkitPlayer setColoredPlayerListName(String name) {
        return setPlayerListName(color(name));
    }


    public BukkitPlayer kickPlayer(String msg) {
        getPlayer().kickPlayer(msg);
        return this;
    }


    public BukkitPlayer kickPlayer() {
        return kickPlayer("You have been kicked from game!");
    }


    public BukkitPlayer colorKickPlayer(String msg, char c) {
        kickPlayer(color(msg, c));
        return this;
    }


    public BukkitPlayer colorKickPlayer(char c) {
        return kickPlayer(color("You have been kicked from game!"));
    }


    public BukkitPlayer colorKickPlayer(String msg) {
        kickPlayer(color(msg));
        return this;
    }


    public BukkitPlayer colorKickPlayer() {
        return kickPlayer(color("You have been kicked from game!"));
    }


    public BukkitPlayer setCompassTarget(Location loc) {
        getPlayer().setCompassTarget(loc);
        return this;
    }


    public BukkitPlayer setCompassTarget(Entity e) {
        return setCompassTarget(e.getLocation());
    }


    public BukkitPlayer setCompassTarget(Block b) {
        return setCompassTarget(b.getLocation());
    }


    public Location getCompassTarget() {
        return getPlayer().getCompassTarget();
    }


    public BukkitPlayer chat(String msg) {
        getPlayer().chat(msg);
        return this;
    }


    public BukkitPlayer colorChat(String msg, char c) {
        return chat(color(msg, c));
    }


    public BukkitPlayer colorChat(String msg) {
        return chat(color(msg));
    }


    public BukkitPlayer performCommand(String cmd) {
        getPlayer().performCommand(cmd);
        return this;
    }


    public BukkitPlayer playNote(Location loc, byte instrument, byte note) {
        getPlayer().playNote(loc, instrument, note);
        return this;
    }


    public BukkitPlayer playNote(Location loc, Instrument instrument, Note note) {
        return playNote(loc, instrument.getType(), note.getId());
    }


    public BukkitPlayer playNote(byte instrument, byte note) {
        getPlayer().playNote(getLocation(), instrument, note);
        return this;
    }


    public BukkitPlayer playNote(Instrument instrument, Note note) {
        return playNote(getLocation(), instrument.getType(), note.getId());
    }


    public BukkitPlayer playSound(Location loc, String sound, float volume, float pitch) {
        getPlayer().playSound(loc, Sound.valueOf(sound), volume, pitch);
        return this;
    }


    public BukkitPlayer playSound(Location loc, Sound sound, float volume, float pitch) {
        return playSound(loc, sound.name(), volume, pitch);
    }


    public BukkitPlayer playSound(String sound, float volume, float pitch) {
        getPlayer().playSound(getLocation(), Sound.valueOf(sound), volume, pitch);
        return this;
    }


    public BukkitPlayer playSound(Sound sound, float volume, float pitch) {
        return playSound(getLocation(), sound.name(), volume, pitch);
    }


    public BukkitPlayer playEffect(Location loc, Effect effect, int data) {
        return playEffect(loc, effect, data);
    }


    public BukkitPlayer playEffect(Location loc, String effect, int data) {
        return playEffect(loc, Effect.valueOf(effect), data);
    }


    public BukkitPlayer playEffect(String effect, int data) {
        return playEffect(getLocation(), Effect.valueOf(effect), data);
    }


    public <T> BukkitPlayer playEffect(Location loc, Effect effect, T data) {
        getPlayer().playEffect(loc, effect, data);
        return this;
    }


    public BukkitPlayer sendBlockChange(Location loc, Material material, byte data) {
        getPlayer().sendBlockChange(loc, material, data);
        return this;
    }


    public BukkitPlayer sendBlockChange(Location loc, int material, byte data) {
        return sendBlockChange(loc, Material.getMaterial(material), data);
    }


    public BukkitPlayer sendBlockChange(Location loc, String material, byte data) {
        return sendBlockChange(loc, Material.getMaterial(material), data);
    }


    public BukkitPlayer sendMap(MapView map) {
        getPlayer().sendMap(map);
        return this;
    }


    public BukkitPlayer teleport(Location loc, PlayerTeleportEvent.TeleportCause cause) {
        getPlayer().teleport(loc, cause);
        return this;
    }


    public BukkitPlayer teleport(Location loc) {
        return teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
    }


    public BukkitPlayer teleport(Entity e, PlayerTeleportEvent.TeleportCause cause) {
        return teleport(e.getLocation(), cause);
    }


    public BukkitPlayer teleport(Entity e) {
        return teleport(e.getLocation());
    }


    public BukkitPlayer teleport(Block e, PlayerTeleportEvent.TeleportCause cause) {
        return teleport(e.getLocation(), cause);
    }


    public BukkitPlayer teleport(Block e) {
        return teleport(e.getLocation());
    }


    public boolean isSneaking() {
        return getPlayer().isSneaking();
    }


    public BukkitPlayer setSneaking(boolean sneak) {
        getPlayer().setSneaking(sneak);
        return this;
    }


    public BukkitPlayer setSneaking() {
        return setSneaking(true);
    }


    public BukkitPlayer removeSneak() {
        return setSneaking(false);
    }


    public BukkitPlayer toggleSneak() {
        return setSneaking(!isSneaking());
    }


    public boolean isSprinting() {
        return getPlayer().isSprinting();
    }


    public BukkitPlayer setSprinting(boolean sprint) {
        getPlayer().setSprinting(sprint);
        return this;
    }


    public BukkitPlayer setSprinting() {
        return setSprinting(true);
    }


    public BukkitPlayer removeSprinting() {
        return setSprinting(false);
    }


    public BukkitPlayer toggleSprinting() {
        return setSprinting(!isSprinting());
    }


    public BukkitPlayer loadData() {
        getPlayer().loadData();
        return this;
    }


    public BukkitPlayer saveData() {
        getPlayer().saveData();
        return this;
    }


    public BukkitPlayer updateInventory() {
        getPlayer().updateInventory();
        return this;
    }


    public BukkitPlayer setSleepingIgnored(boolean ignore) {
        getPlayer().setSleepingIgnored(ignore);
        return this;
    }


    public boolean isSleepingIgnored() {
        return getPlayer().isSleepingIgnored();
    }


    public BukkitPlayer setSleepingIgnored() {
        return setSleepingIgnored(true);
    }


    public BukkitPlayer removeSleepingIgnored() {
        return setSleepingIgnored(false);
    }


    public BukkitPlayer awardAchievement(Achievement ach) {
        getPlayer().awardAchievement(ach);
        return this;
    }


    public BukkitPlayer awardAchievement(String ach) {
        getPlayer().awardAchievement(Achievement.valueOf(ach));
        return this;
    }


    public BukkitPlayer incrementStatistic(Statistic stat, Material mat, int amount) {
        getPlayer().incrementStatistic(stat, mat, amount);
        return this;
    }


    public BukkitPlayer incrementStatistic(Statistic stat, Material mat) {
        return incrementStatistic(stat, mat, 1);
    }


    public BukkitPlayer incrementStatistic(Statistic stat, int mat) {
        return incrementStatistic(stat, Material.getMaterial(mat), 1);
    }


    public BukkitPlayer incrementStatistic(Statistic stat, String mat) {
        return incrementStatistic(stat, Material.getMaterial(mat), 1);
    }


    public BukkitPlayer incrementStatistic(Statistic stat) {
        return incrementStatistic(stat, Material.getMaterial(1));
    }


    public BukkitPlayer setPlayerTime(long time, boolean rel) {
        getPlayer().setPlayerTime(time, rel);
        return this;
    }


    public BukkitPlayer setPlayerTime(long time) {
        return setPlayerTime(time, false);
    }


    public long getPlayerTimeOffset() {
        return getPlayer().getPlayerTimeOffset();
    }


    public long getPlayerTime() {
        return getPlayer().getPlayerTime();
    }


    public boolean isPlayerTimeRelative() {
        return getPlayer().isPlayerTimeRelative();
    }


    public BukkitPlayer resetPlayerTime() {
        return setPlayerTime(0, true);
    }


    public BukkitPlayer setPlayerWeather(WeatherType type) {
        getPlayer().setPlayerWeather(type);
        return this;
    }


    public BukkitPlayer setPlayerWeather(String type) {
        return setPlayerWeather(WeatherType.valueOf(type));
    }


    public BukkitPlayer resetPlayerWeather() {
        getPlayer().resetPlayerWeather();
        return this;
    }


    public boolean isBanned() {
        return getPlayer().isBanned();
    }


    public BukkitPlayer setBanned(boolean ban) {
        getPlayer().setBanned(ban);
        return this;
    }


    public BukkitPlayer setBanned() {
        return setBanned(true);
    }


    public BukkitPlayer removeBan() {
        return setBanned(false);
    }


    public BukkitPlayer toggleBan() {
        return setBanned(!isBanned());
    }


    public boolean isWhitelisted() {
        return getPlayer().isBanned();
    }


    public BukkitPlayer setWhitelisted(boolean w) {
        getPlayer().setBanned(w);
        return this;
    }


    public BukkitPlayer setWhitelisted() {
        return setBanned(true);
    }


    public BukkitPlayer removeWhitelistd() {
        return setBanned(false);
    }


    public BukkitPlayer toggleWhitelistd() {
        return setBanned(!isWhitelisted());
    }


    public BukkitPlayer setGameMode(GameMode mode) {
        getPlayer().setGameMode(mode);
        return this;
    }


    public BukkitPlayer setGameMode(int i) {
        return setGameMode(GameMode.getByValue(i));
    }


    public BukkitPlayer setGameMode(String mode) {
        return setGameMode(GameMode.valueOf(mode));
    }


    public GameMode getGameMode() {
        return getPlayer().getGameMode();
    }


    public BukkitPlayer giveExp(int exp) {
        getPlayer().giveExp(exp);
        return this;
    }


    public BukkitPlayer giveExpLevels(int exp) {
        getPlayer().giveExpLevels(exp);
        return this;
    }


    public float getExp() {
        return getPlayer().getExp();
    }


    public BukkitPlayer setExp(float exp) {
        getPlayer().setExp(exp);
        return this;
    }


    public int getLevel() {
        return getPlayer().getLevel();
    }


    public BukkitPlayer setLevel(int level) {
        getPlayer().setLevel(level);
        return this;
    }


    public int getTotalExperiance() {
        return getPlayer().getTotalExperience();
    }


    public BukkitPlayer setTotalExperiance(int exp) {
        getPlayer().setTotalExperience(exp);
        return this;
    }


    public float getExhaustion() {
        return getPlayer().getExhaustion();
    }


    public BukkitPlayer setExhaustion(float value) {
        getPlayer().setExhaustion(value);
        return this;
    }


    public float getSaturation() {
        return getPlayer().getSaturation();
    }


    public BukkitPlayer setSaturation(float value) {
        getPlayer().setSaturation(value);
        return this;
    }


    public int getFoodLevel() {
        return getPlayer().getFoodLevel();
    }


    public BukkitPlayer setFoodLevel(int value) {
        getPlayer().setFoodLevel(value);
        return this;
    }


    public Location getBedSpawnLocation() {
        return getPlayer().getBedSpawnLocation();
    }


    public BukkitPlayer setBedSpawnLocation(Location loc, boolean override) {
        getPlayer().setBedSpawnLocation(loc, override);
        return this;
    }


    public BukkitPlayer setBedSpawnLocation(Location loc) {
        return setBedSpawnLocation(loc, false);
    }


    public BukkitPlayer setBedSpawnLocation(Block b) {
        return setBedSpawnLocation(b.getLocation());
    }


    public BukkitPlayer setBedSpawnLocation(Entity e) {
        return setBedSpawnLocation(e.getLocation());
    }


    public BukkitPlayer hidePlayer(BukkitPlayer p) {
        getPlayer().hidePlayer(p.getPlayer());
        return this;
    }


    public BukkitPlayer showPlayer(BukkitPlayer p) {
        getPlayer().showPlayer(p.getPlayer());
        return this;
    }


    public boolean canSee(BukkitPlayer p) {
        return getPlayer().canSee(p.getPlayer());
    }


    public BukkitPlayer toggleShowPlayer(BukkitPlayer p) {
        if (canSee(p))
            hidePlayer(p);
        else
            showPlayer(p);
        return this;
    }


    public BukkitPlayer hideFromAll() {
        for ( Player pl : Bukkit.getOnlinePlayers() ) {
            if (new BukkitPlayer(pl).canSee(this))
                new BukkitPlayer(pl).hidePlayer(this);
        }
        return this;
    }


    public BukkitPlayer showToAll() {
        for ( Player pl : Bukkit.getOnlinePlayers() ) {
            if (!new BukkitPlayer(pl).canSee(this))
                new BukkitPlayer(pl).showPlayer(this);
        }
        return this;
    }


    public String toString() {
        return p;
    }


    public int hashCode() {
        return getPlayer().hashCode();
    }


    public long getFirstPlayed() {
        return getPlayer().getFirstPlayed();
    }


    public long getLastPlayed() {
        return getPlayer().getLastPlayed();
    }


    public boolean hasPlayedBefore() {
        return getPlayer().hasPlayedBefore();
    }


    public boolean beginConversation(Conversation convo) {
        return getPlayer().beginConversation(convo);
    }


    public BukkitPlayer abandonConversation(Conversation convo) {
        getPlayer().abandonConversation(convo);
        return this;
    }


    public BukkitPlayer abandonConversation(Conversation convo, ConversationAbandonedEvent details) {
        getPlayer().abandonConversation(convo, details);
        return this;
    }


    public BukkitPlayer acceptConversationInput(String input) {
        getPlayer().acceptConversationInput(input);
        return this;
    }


    public boolean isConversing() {
        return getPlayer().isConversing();
    }


    public BukkitPlayer sendPluginMessage(Plugin pl, String channal, byte[] msg) {
        getPlayer().sendPluginMessage(pl, channal, msg);
        return this;
    }


    public BukkitPlayer setTexturePack(String url) {
        getPlayer().setTexturePack(url);
        return this;
    }


    public Set<String> getListeningPluginChannels() {
        return getPlayer().getListeningPluginChannels();
    }


    public EntityType getType() {
        return EntityType.PLAYER;
    }


    public BukkitPlayer setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        getPlayer().setMetadata(metadataKey, newMetadataValue);
        return this;
    }


    public List<MetadataValue> getMetadata(String metaKey) {
        return getPlayer().getMetadata(metaKey);
    }


    public boolean hasMetadata(String key) {
        return getPlayer().hasMetadata(key);
    }


    public BukkitPlayer removeMetadata(String key, Plugin pl) {
        getPlayer().removeMetadata(key, pl);
        return this;
    }


    public boolean setWindowProperty(Property prop, int value) {
        return getPlayer().setWindowProperty(prop, value);
    }


    public boolean isFlying() {
        return getPlayer().isFlying();
    }


    public BukkitPlayer setFlying(boolean t) {
        getPlayer().setFlying(t);
        return this;
    }


    public BukkitPlayer setFlying() {
        return setFlying(true);
    }


    public BukkitPlayer removeFlying() {
        return setFlying(false);
    }


    public BukkitPlayer toggleFlying() {
        return setFlying(!isFlying());
    }


    public boolean getAllowFlight() {
        return getPlayer().getAllowFlight();
    }


    public BukkitPlayer setAllowFlight(boolean t) {
        getPlayer().setAllowFlight(t);
        return this;
    }


    public BukkitPlayer setAllowFlight() {
        return setAllowFlight(true);
    }


    public BukkitPlayer removeAllowFlight() {
        return setAllowFlight(false);
    }


    public BukkitPlayer toggleAllowFlight() {
        return setAllowFlight(!getAllowFlight());
    }


    public int getNoDamageTicks() {
        return getPlayer().getNoDamageTicks();
    }


    public BukkitPlayer setFlySpeed(float value) {
        getPlayer().setFlySpeed(value);
        return this;
    }


    public BukkitPlayer setWalkSpeed(float value) {
        getPlayer().setWalkSpeed(value);
        return this;
    }


    public float getFlySpeed() {
        return getPlayer().getFlySpeed();
    }


    public float getWalkSpeed() {
        return getPlayer().getWalkSpeed();
    }


    public BukkitPlayer setMaxHealth(double amount) {
        getPlayer().setMaxHealth(amount);
        return this;
    }


    public BukkitPlayer setMaxHealth(int amount) {
        getPlayer().setMaxHealth(amount);
        return this;
    }


    public BukkitPlayer resetMaxHealth() {
        getPlayer().resetMaxHealth();
        return this;
    }


    public Scoreboard getScoreboard() {
        return getPlayer().getScoreboard();
    }


    public BukkitPlayer setScoreboard(Scoreboard sb) {
        getPlayer().setScoreboard(sb);
        return this;
    }

   /*
    * End of craftplayer methods, start humanentity
    */


    public String getName() {
        return getPlayer().getName();
    }


    public PlayerInventory getInventory() {
        return getPlayer().getInventory();
    }


    public EntityEquipment getEquipment() {
        return getPlayer().getEquipment();
    }


    public Inventory getEnderChest() {
        return getPlayer().getEnderChest();
    }


    public ItemStack getItemInHand() {
        return getPlayer().getItemInHand();
    }


    public ItemStack getItemOnCursor() {
        return getPlayer().getItemOnCursor();
    }


    public BukkitPlayer setItemOnCursor(ItemStack item) {
        getPlayer().setItemOnCursor(item);
        return this;
    }


    public boolean isSleeping() {
        return getPlayer().isSleeping();
    }


    public int getSleepTicks() {
        return getPlayer().getSleepTicks();
    }


    public boolean isPermissionSet(String name) {
        return getPlayer().isPermissionSet(name);
    }


    public boolean isPermissionSet(Permission name) {
        return getPlayer().isPermissionSet(name);
    }


    public boolean hasPermission(String name) {
        return getPlayer().hasPermission(name);
    }


    public PermissionAttachment addAttachment(Plugin pl, String name, boolean value, int ticks) {
        return getPlayer().addAttachment(pl, name, value, ticks);
    }


    public PermissionAttachment addAttachment(Plugin pl, String name, boolean value) {
        return getPlayer().addAttachment(pl, name, value);
    }


    public PermissionAttachment addAttachment(Plugin pl) {
        return getPlayer().addAttachment(pl);
    }


    public BukkitPlayer removeAttachment(PermissionAttachment pa) {
        getPlayer().removeAttachment(pa);
        return this;
    }


    public BukkitPlayer recalculatePermissions() {
        getPlayer().recalculatePermissions();
        return this;
    }


    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return getPlayer().getEffectivePermissions();
    }


    public InventoryView getOpenInventory() {
        return getPlayer().getOpenInventory();
    }


    public InventoryView openInventory(Inventory inv) {
        return getPlayer().openInventory(inv);
    }


    public InventoryView openWorkbench(Location loc, boolean force) {
        return getPlayer().openWorkbench(loc, force);
    }


    public InventoryView openEnchanting(Location loc, boolean force) {
        return getPlayer().openEnchanting(loc, force);
    }


    public BukkitPlayer openInventory(InventoryView view) {
        getPlayer().openInventory(view);
        return this;
    }


    public BukkitPlayer closeInventory() {
        getPlayer().closeInventory();
        return this;
    }


    public boolean isBlocking() {
        return getPlayer().isBlocking();
    }


    public int getExpToLevel() {
        return getPlayer().getExpToLevel();
    }

   /*
    * End HumanEntity, start LivingEntity TODO
    */

   /*
    * End LivingEntity, start Entity TODO
    */


    public BukkitPlayer addPotionEffect(PotionEffect effect, boolean force) {
        getPlayer().addPotionEffect(effect, force);
        return this;
    }


    public BukkitPlayer addPotionEffect(PotionEffect effect) {
        return addPotionEffect(effect, false);
    }


    public BukkitPlayer forcePotionEffect(PotionEffect effect) {
        return addPotionEffect(effect, false);
    }


    public BukkitPlayer addPotionEffects(Collection<PotionEffect> effects) {
        for ( PotionEffect effect : effects ) {
            addPotionEffect(effect);
        }
        return this;
    }


    public boolean hasPotionEffect(PotionEffectType type) {
        return getPlayer().hasPotionEffect(type);
    }


    public BukkitPlayer removePotionEffect(PotionEffectType type) {
        getPlayer().removePotionEffect(type);
        return this;
    }


    public Collection<PotionEffect> getActivePotionEffects() {
        return getPlayer().getActivePotionEffects();
    }


    public Location getLocation() {
        return getPlayer().getLocation();
    }


    public World getWorld() {
        return getPlayer().getWorld();
    }


    public LivingEntity getEntity() {
        return getPlayer();
    }


    public double getHealth() {
        return getPlayer().getHealth();
    }


    public double getMaxHealth() {
        return getPlayer().getMaxHealth();
    }


    public Player getPlayer() {
        return Bukkit.getPlayerExact(p);
    }


    private String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }


    private String color(String msg, char c) {
        return ChatColor.translateAlternateColorCodes(c, msg);
    }

}
