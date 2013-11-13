package com.relicum.scb.commands;

import com.relicum.scb.SCB;
import com.relicum.scb.configs.LobbyConfig;
import com.relicum.scb.objects.LobbyRegion;
import com.relicum.scb.we.WEManager;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class setlobby extends SubBase {

    /**
     * @param player Player
     * @param args   String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) {
        String perm = "ssb.player.join";
        WorldEditPlugin wm = new WEManager().getWEP();
        Selection cr = wm.getSelection(player);
        Vector rmin;
        try {
            rmin = new Vector(cr.getMinimumPoint().getBlockX(), cr.getMinimumPoint().getBlockY(), cr.getMinimumPoint().getBlockZ());

        }
        catch ( Exception e ) {
            player.sendMessage(SCB.MM.getErrorMessage("command.message.setlobbyNoSel"));
            SCB.getInstance().getLogger().severe("Error setting lobby WorldEdit selection not set correctly");
            e.printStackTrace();
            return true;
        }


        Vector rmax;

        rmax = new Vector(cr.getMaximumPoint().getBlockX(), cr.getMaximumPoint().getBlockY(), cr.getMaximumPoint().getBlockZ());

        Vector lobbySpawn = new Vector(player.getLocation().getBlockX() + 0.5, player.getLocation().getBlockY() + 0.5, player.getLocation().getBlockZ() + 0.5);
        Float dir = SCB.getInstance().LBS.getDirection(player.getLocation().getYaw());

        LobbyRegion region = new LobbyRegion(rmin, rmax, lobbySpawn, player.getWorld().getName(), perm, player.getLocation().getYaw());

        LobbyConfig LC = SCB.getInstance().LBS.getLobbySaveObject();


        try {
            if (!LC.getConfig().contains("LOBBY")) {
                LC.getConfig().createSection("LOBBY");
            }
            LC.getConfig().set("LOBBY.REGION.MIN", region.getMinVector());
            LC.getConfig().set("LOBBY.REGION.MAX", region.getMaxVector());
            LC.getConfig().set("LOBBY.REGION.SPAWN", region.getLobbySpawnVector());
            LC.getConfig().set("LOBBY.REGION.YAW", region.getYaw());
            LC.getConfig().set("LOBBY.REGION.WORLD", region.getWorld().getName());
            LC.getConfig().set("LOBBY.REGION.PERM", "ssb.player.join");
            if (!LC.getConfig().contains("LOBBYSET")) {
                LC.getConfig().createSection("LOBBYSET");
            }
            LC.getConfig().set("LOBBYSET", true);
            LC.saveConfig();
            LC.reloadConfig();
        }
        catch ( Exception e ) {

            SCB.getInstance().getLogger().severe("Error: saving Lobby Region");
            System.out.println(e.getStackTrace().toString());
            player.sendMessage(SCB.MM.getErrorMessage("command.message.setlobbyFail"));

        }

        SCB.getInstance().LBS.setLobbyRegion(region);

        SCB.getInstance().getConfig().set("enableLobbyProtection", true);
        SCB.getInstance().saveConfig();
        SCB.getInstance().reloadConfig();
        SCB.getInstance().loadLobbyEvents();
        player.sendMessage(SCB.MM.getAdminMessage("command.message.setlobbySuccess"));
        SCB.getInstance().getLogger().info("Lobby Region and Spawn Point have Been Successfully Set");

        return true;
    }


    /**
     * Simplify set this function to set the field mNode with the commands description will come from in the
     * messages.yml file You do not need to enter the full node as it will be prefixed for you. Eg is the full node is
     * command.description.createarena you only need to set this to createarena
     */
    @Override
    public void setmDescription() {
        mNode = "setlobby";
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
        return "ssba.admin.setlobby";
    }


    /**
     * Simply set this to return the clist Usage
     *
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssba setlobby";
    }


    /**
     * Set this to the label of the command
     *
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba setlobby";
    }


    /**
     * Set com
     *
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba setlobby";
    }


    @Override
    public Plugin getPlugin() {
        return SCB.getInstance();
    }
}
