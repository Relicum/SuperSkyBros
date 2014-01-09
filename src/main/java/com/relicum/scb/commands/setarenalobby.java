package com.relicum.scb.commands;

import com.relicum.scb.ArenaManager;
import com.relicum.scb.SCB;
import com.relicum.scb.arena.ALobbyIO;
import com.relicum.scb.configs.ServerStatus;
import com.relicum.scb.objects.ArenaLobby;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.we.WEManager;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.io.IOException;

/**
 * SuperSkyBros First Created 24/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class setarenalobby extends SubBase {

    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {
        ArenaManager ar = SkyApi.getArenaManager();

        if (ar.getCurrent() == 0) {
            player.sendMessage(SCB.getMessageManager().getErrorMessage("command.message.NoArenaSet"));
            return true;
        }

        WorldEditPlugin wm = new WEManager().getWEP();
        Selection cr = wm.getSelection(player);
        Vector rmin;
        try {
            rmin = new Vector(cr.getMinimumPoint().getBlockX(), cr.getMinimumPoint().getBlockY(), cr.getMinimumPoint().getBlockZ());

        } catch (Exception e) {
            player.sendMessage(SCB.MM.getErrorMessage("command.message.setlobbyNoSel"));
            SCB.getInstance().getLogger().severe("Error setting lobby WorldEdit selection not set correctly");
            e.printStackTrace();
            return true;
        }


        Vector rmax;

        rmax = new Vector(cr.getMaximumPoint().getBlockX(), cr.getMaximumPoint().getBlockY(), cr.getMaximumPoint().getBlockZ());

        Vector lobbySpawn = new Vector(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
        ArenaLobby al = new ArenaLobby(rmax, rmin, player.getWorld().getName(), lobbySpawn, ar.getCurrent());

        ALobbyIO lobbyIO = new ALobbyIO(al);

        try {
            lobbyIO.saveLobby();
        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(ChatColor.RED + "Error occurred while trying to save lobby check the logs");
            return true;
        }
        SkyApi.getCMsg().INFO("Arena Lobby for arena " + ar.getCurrent() + " has been saved successfully");
        player.sendMessage(SCB.getMessageManager().getAdminMessage("command.message.setarenalobbySuccess").replace("%ID%", ar.getCurrent().toString()));
        wm.getWorldEdit().clearSessions();
        if (SkyApi.getSCB().getConfig().getString("serverStatus").equalsIgnoreCase(ServerStatus.SETAREALOBBY.name())) {
            SkyApi.getSCB().getConfig().set("serverStatus", ServerStatus.SETENABLE.name());
            SkyApi.getSCB().saveConfig();

        }
        wm = null;
        cr = null;
        return true;
    }


    /**
     * Simplify set this function to set the field mNode with the commands description will come from in the
     * messages.yml file You do not need to enter the full node as it will be prefixed for you. Eg is the full node is
     * command.description.createarena you only need to set this to createarena
     */
    @Override
    public void setmDescription() {
        mNode = "setarenalobby";
    }


    /**
     * Simply set this to return the the number of arguments The command should receive
     *
     * @return Integer
     */
    @Override
    public Integer setNumArgs() {
        return 0;
    }


    /**
     * Simply set this to return the clist permission
     *
     * @return String
     */
    @Override
    public String setPermission() {
        return "ssba.admin.setarenalobby";
    }


    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssba setarenalobby";
    }


    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba setarenalobby";
    }


    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba setarenalobby";
    }


    @Override
    public Plugin getPlugin() {
        return SCB.getInstance();
    }
}
