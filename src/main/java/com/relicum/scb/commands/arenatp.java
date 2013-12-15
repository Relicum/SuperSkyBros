package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import com.relicum.scb.types.SkyApi;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class arenatp extends SubBase {

    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {
        Integer id = null;
        Location tpto = null;
        Location below = null;
        Chunk ch = null;
        Block b = null;
        try {

            Integer s = SkyApi.getArenaManager().total;
            id = Integer.parseInt(args[0]);
            tpto = SkyApi.getArenaManager().getArenaById(id).getAreg().getadminSpawn();
            tpto.add(0.0D, 1.0D, 0.0D);
            below = new Location(tpto.getWorld(), tpto.getX(), tpto.getY(), tpto.getZ());
            below.subtract(0.00D, 1.00D, 0.00D);
            b = below.getBlock();
            b.setType(Material.GLASS);
            ch = tpto.getChunk();
            player.sendMessage(SCB.getMessageManager().getAdminMessage("command.message.arenatpLoading"));
            if (!ch.load()) {
                player.sendMessage(SCB.getMessageManager().getErrorMessage("command.message.arenatpLoadingFail"));
            } else {


                teleportToLobby(player, tpto, args[0]);

            }
        } catch (Exception e) {
            String me = SCB.getMessageManager().getErrorMessage("command.message.arenatpDoesNotExist");
            String tmp = me.replace("%ID%", args[0]);
            player.sendMessage(tmp);
            e.printStackTrace();

        }


        return true;
    }


    /**
     * Teleports the player to the given location
     *
     * @param p Player
     * @param l Location
     * @return boolean
     * @throws IllegalArgumentException
     */
    public boolean teleportToLobby(final Player p, final Location l, final String a) {
        String mess = SCB.getMessageManager().getAdminMessage("command.message.arenatpSuccess");
        final String tmp = mess.replace("%ID%", a);
        SCB.getInstance().getServer().getScheduler().runTaskLater(
                SCB.getInstance(), new Runnable() {

            @Override
            public void run() {
                System.out.println("Location going to is " + l.getX() + " " + l.getY() + " " + l.getZ());
                if (!p.teleport(l)) {
                    System.out.println("Error teleporting player to lobby");
                }
                p.sendMessage(tmp);
            }
        }, 20L);

        return true;
    }


    /**
     * Simplify set this function to set the field mNode with the commands description will come from in the
     * messages.yml file You do not need to enter the full node as it will be prefixed for you. Eg is the full node is
     * command.description.createarena you only need to set this to createarena
     */
    @Override
    public void setmDescription() {
        mNode = "arenatp";
    }


    /**
     * Simply set this to return the the number of arguments The command should receive
     *
     * @return Integer
     */
    @Override
    public Integer setNumArgs() {
        return 1;
    }


    /**
     * Simply set this to return the clist permission
     *
     * @return String
     */
    @Override
    public String setPermission() {
        return "ssba.admin.arenatp";
    }


    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssba arenatp [id]";
    }


    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba arenatp";
    }


    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba arenatp";
    }


    @Override
    public Plugin getPlugin() {
        return SCB.getInstance();
    }
}
