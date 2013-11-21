package com.relicum.scb.objects;

import com.relicum.scb.SCB;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class LobbySpawn extends ISpawn {


    public LobbySpawn(Location lo) {
        super(lo);
    }


    /**
     * Set the permission to go to  spawn
     *
     * @param per String
     */
    @Override
    public void setPerm(String per) {
        this.Perm = per;
    }


    /**
     * Get the permission to use the spawn.
     *
     * @return boolean
     */
    @Override
    public String getPerm() {
        return this.Perm;
    }


    /**
     * Teleports the player to the given location
     *
     * @param p Player
     * @param l Location
     * @return boolean
     * @throws IllegalArgumentException
     */
    @Override
    public boolean teleportToLobby(final Player p, final Location l) {
        SCB.getInstance().getServer().getScheduler().runTask(
                SCB.getInstance(), new Runnable() {

            @Override
            public void run() {
                if (!p.teleport(l)) {
                    System.out.println("Error teleporting player to lobby");
                }
            }
        });

        return true;
    }


    /**
     * Method used to save the data to storage
     *
     * @return boolean
     */
    @Override
    public boolean save() {


        return true;
    }


    /**
     * Method used to load the data from storage
     *
     * @return boolean
     */
    @Override
    public boolean load() {

        //new Location(getWorld(), (double) getX(), (double) getY(), (double) getZ(), (float) getYaw(), (float) getPitch());
        return false;
    }


}
