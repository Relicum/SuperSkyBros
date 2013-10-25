package com.relicum.scb.commands;

import com.relicum.scb.ArenaManager;
import com.relicum.scb.SCB;
import com.relicum.scb.arena.Arena;
import com.relicum.scb.arena.SpawnIO;
import com.relicum.scb.objects.spawns.ArenaGroupSpawn;
import com.relicum.scb.objects.spawns.ArenaSpawn;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class setspawn extends SubBase {


    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    public boolean onCommand(Player player, String[] args) {

        ArenaManager ar = SCB.getInstance().ARM;
        SpawnIO sio = new SpawnIO();
        if (ar.getCurrent() == 0) {
            player.sendMessage(SCB.getMessageManager().getErrorMessage("command.message.NoArenaSet"));
            return true;
        }
        if (ar.checkBlockLocation(player.getLocation().toVector())) {

            ArenaGroupSpawn sg = ar.getArenaById(ar.getCurrent()).getSpawnGroup();

            if (sg == null) {

                System.out.println("The Arena Group is null means this is first spawn and need to create and Save new SpawnGroup First and then add it to the arena object");

                Vector vt = new Vector(Math.round(player.getLocation().getX()) + 0.5, player.getLocation().getY() + 0.5, Math.round(player.getLocation().getZ()) + 0.5);
                ArenaSpawn as = new ArenaSpawn(vt, ar.getCurrent(), player.getWorld().getName());


                if (sio.createNewGroup(as)) {

                    String tmp;
                    player.sendMessage(tmp = SCB.getMessageManager().getAdminMessage("command.message.setSpawnSuccess").replace("%ID%", "1"));

                    return true;
                } else {

                    player.sendMessage(SCB.getMessageManager().getErrorMessage("command.message.setspawnFail"));
                    return true;
                }


            } else {

                System.out.println("Well more than one record try and save it");

                Vector vt = new Vector(Math.round(player.getLocation().getX()), player.getLocation().getY(), Math.round(player.getLocation().getZ()));
                ArenaSpawn as = new ArenaSpawn(vt, ar.getCurrent(), player.getWorld().getName());

                Arena myar = ar.getArenaById(ar.getCurrent());
                as.setGroupId(myar.getSpawnGroup().getTotal());
                Integer t = myar.getSpawnGroup().getTotal() + 1;

                if (sio.saveSpawn(as)) {

                    String tmp;
                    player.sendMessage(tmp = SCB.getMessageManager().getAdminMessage("command.message.setSpawnSuccess").replace("%ID%", t.toString()));
                    return true;

                } else {

                    player.sendMessage(SCB.getMessageManager().getErrorMessage("command.message.setspawnFail"));
                    return true;

                }

            }


        } else {


            player.sendMessage(SCB.getMessageManager().getErrorMessage("command.message.setspawnNotInArena"));


        }
        return true;
    }


    /**
     * Simplify set this function to set the field descNode with the commands description will come from in the
     * messages.yml file You do not need to enter the full node as it will be prefixed for you. Eg is the full node is
     * command.description.createarena you only need to set this to createarena
     */
    @Override
    public void setmDescription() {
        mNode = "setspawn";
    }


    /**
     * Simply set this to return the the number of arguments The command should receive
     *
     * @return Integer
     */
    public Integer setNumArgs() {
        return 0;
    }


    /**
     * Simply set this to return the clist permission
     *
     * @return String
     */
    public String setPermission() {
        return "ssba.admin.setspawn";
    }


    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    public String setUsage() {
        return "/ssba setspawn";
    }


    /**
     * Set this to the label of the command
     *
     * @return String
     */
    public String setLabel() {
        return "ssba setspawn";
    }


    public String setCmd() {
        return "ssba setspawn";
    }
}
