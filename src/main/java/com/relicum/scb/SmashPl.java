package com.relicum.scb;

import com.relicum.scb.objects.BukkitPlayer;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.PlayerStatus;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachment;

import java.util.UUID;

/**
 * The type Smash player.
 */
public class SmashPl extends BukkitPlayer {

    public PlayerStatus pStatus;

    private String playerLocation;

    private PermissionAttachment permissionAttachment;

    /**
     * The G iD.
     */
    private Integer gID = null;

    public SmashPl instance;


    /**
     * Instantiates a new Smash player.
     *
     * @param p the p
     */
    public SmashPl(Player p) {
        super(p);

    }


    /**
     * Instantiates a new Smash player.
     *
     * @param sender the sender
     */
    public SmashPl(CommandSender sender) {
        super(sender);
    }


    /**
     * Wrap smash player.
     *
     * @param p the p
     * @return the smash player
     */
    public static SmashPl wrap(Player p) {
        return new SmashPl(p);
    }


    /**
     * Create new smash player by player name
     *
     * @param String the String
     * @return the smash player
     */
    public static SmashPl warp(String pname) {
        return new SmashPl(Bukkit.getPlayerExact(pname));
    }


    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(Game g) {

    }


    public PlayerStatus getStatus() {
        return this.pStatus;
    }


    public void setpStatus(PlayerStatus pls) {

        this.pStatus = pls;
    }


    /**
     * Set my current location as a string
     *
     * @param String
     */
    public void setMyLocation(String l) {
        playerLocation = l;
    }


    /**
     * @return the Location I currently am as a Sting
     */
    public String whereAmI() {
        return playerLocation;
    }


    public void stripInventory() {
        System.out.println("Start strip");
        removeArmour();
        SCB.getInstance().getServer().getScheduler().runTask(
                SCB.getInstance(), new Runnable() {

            public void run() {

                updateInventory();
            }
        });

    }


    public SmashPl removeArmour() {
        getPlayer().getInventory().setArmorContents(new ItemStack[4]);
        System.out.println("Old removed");
        return this;
    }


    public SmashPl clearInventory(SmashPl pl) {

        pl.getInventory().clear();
        return this;

    }


    public PermissionAttachment getPermissionAttachment() {
        return permissionAttachment;
    }


    public void setPermissionAttachment(PermissionAttachment pt) {
        this.permissionAttachment = pt;
    }


    public SmashPl closeInventory(SmashPl pl) {
        pl.closeInventory();
        return this;
    }


    @Deprecated
    public SmashPl updateInventory(SmashPl pl) {
        invUpdate(pl);
        return this;
    }


    public SmashPl resetHealth(Float f) {
        getPlayer().setHealth(f);
        return this;
    }


    public SmashPl removeFire() {
        getPlayer().setFireTicks(0);
        return this;
    }


    public void invUpdate(final SmashPl pl) {
        SCB.getInstance().getServer().getScheduler().runTask(
                SCB.getInstance(), new Runnable() {

            public void run() {
                System.out.println("Play is " + p);
                pl.updateInventory();
            }
        });
    }


    /**
     * Teleports the player to the lobby
     *
     * @return boolean
     * @throws IllegalArgumentException
     */
    public boolean teleportToLobby() {

        SCB.getInstance().getServer().getScheduler().runTaskLater(
                SCB.getInstance(), new Runnable() {

            @Override
            public void run() {

                if (!getPlayer().teleport(SkyApi.getLobbyManager().getLobbyRg().getLobbySpawn())) {
                    System.out.println("Error teleporting player to lobby");
                }
                if (!SCB.getInstance().getConfig().getBoolean(SCB.DEDICATED_SSB))
                    getPlayer().sendMessage(SCB.MM.getMessage("command.message.teleportToLobby"));
            }
        }, 1L);

        return true;
    }


    /**
     * Get Returns the players Unique ID
     *
     * @return the uUID
     */
    public UUID getUUID() {
        return getPlayer().getUniqueId();
    }


}
