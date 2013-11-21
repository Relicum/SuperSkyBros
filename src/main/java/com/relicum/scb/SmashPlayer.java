package com.relicum.scb;

import com.relicum.scb.objects.BukkitPlayer;
import com.relicum.scb.utils.playerStatus;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachment;

import java.util.UUID;

/**
 * The type Smash player.
 */
public class SmashPlayer extends BukkitPlayer {

    public playerStatus pStatus;

    private String playerLocation;

    private PermissionAttachment permissionAttachment;

    /**
     * The G iD.
     */
    private Integer gID = null;

    public SmashPlayer instance;


    /**
     * Instantiates a new Smash player.
     *
     * @param p the p
     */
    public SmashPlayer(Player p) {
        super(p);

    }


    /**
     * Instantiates a new Smash player.
     *
     * @param sender the sender
     */
    public SmashPlayer(CommandSender sender) {
        super(sender);
    }


    /**
     * Wrap smash player.
     *
     * @param p the p
     * @return the smash player
     */
    public static SmashPlayer wrap(Player p) {
        return new SmashPlayer(p);
    }


    /**
     * Create new smash player by player name
     *
     * @param String the String
     * @return the smash player
     */
    public static SmashPlayer warp(String pname) {
        return new SmashPlayer(Bukkit.getPlayerExact(pname));
    }


    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(Game g) {

    }


    public playerStatus getStatus() {
        return this.pStatus;
    }


    public void setpStatus(playerStatus pls) {

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


    public SmashPlayer removeArmour() {
        getPlayer().getInventory().setArmorContents(new ItemStack[4]);
        System.out.println("Old removed");
        return this;
    }


    public SmashPlayer clearInventory(SmashPlayer pl) {

        pl.getInventory().clear();
        return this;

    }


    public PermissionAttachment getPermissionAttachment() {
        return permissionAttachment;
    }


    public void setPermissionAttachment(PermissionAttachment pt) {
        this.permissionAttachment = pt;
    }


    public SmashPlayer closeInventory(SmashPlayer pl) {
        pl.closeInventory();
        return this;
    }


    @Deprecated
    public SmashPlayer updateInventory(SmashPlayer pl) {
        invUpdate(pl);
        return this;
    }


    public SmashPlayer resetHealth(Float f) {
        getPlayer().setHealth(f);
        return this;
    }


    public SmashPlayer removeFire() {
        getPlayer().setFireTicks(0);
        return this;
    }


    public void invUpdate(final SmashPlayer pl) {
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

                if (!getPlayer().teleport(SCB.getInstance().LBS.getLobbyRegion().getLobbySpawn())) {
                    System.out.println("Error teleporting player to lobby");
                }
                if (!SCB.getInstance().getConfig().getBoolean(SCB.DEDICATED_SSB)) getPlayer().sendMessage(SCB.MM.getMessage("command.message.teleportToLobby"));
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
