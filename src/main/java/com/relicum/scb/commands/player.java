package com.relicum.scb.commands;

import java.io.IOException;
import com.relicum.scb.ArenaManager;
import com.relicum.scb.arena.Arena;
import com.relicum.scb.types.SkyApi;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * SuperSkyBros First Created 17/10/13
 * 
 * @author Relicum
 * @version 0.1
 */
public class player extends SubBase {

    /**
     * @param player Player
     * @param args String[]
     * @return boolean
     */
    @Override
    public boolean onCommand(Player player, String[] args) throws IOException, ClassNotFoundException {
        ArenaManager ar = SkyApi.getArenaManager();
        Arena arena = ar.getArenaById(ar.getCurrent());

        if (ar.getCurrent() == 0) {
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.NoArenaSet"));
            return true;
        }

        Integer minPlayers = arena.getMinPlayers();
        Integer maxPlayers = arena.getMaxPlayers();

        if (!args[0].equalsIgnoreCase("min") && !args[0].equalsIgnoreCase("max")) {
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.playerInvalidArg"));
            return true;
        }
        if (args[0].equalsIgnoreCase("min")) {
            Integer tmin = Integer.valueOf(args[1]);
            if (tmin < 2 || tmin > 8) {
                player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.playerOutMinRange"));
                return true;
            }
            if (tmin > maxPlayers) {
                player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.playerMinGrtMax"));
                return true;
            }

            arena.setMinPlayers(tmin);
            arena.saveArena();
            player.sendMessage(SkyApi.getMessageManager().getAdminMessage("command.message.playerMinSuccess").replace("%MIN%", tmin.toString()));
            return true;

        }
        if (args[0].equalsIgnoreCase("max")) {
            Integer tmax = Integer.valueOf(args[1]);
            if (tmax < 4 || tmax > 8) {
                player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.playerOutMaxRange"));
                return true;
            }
            if (tmax < minPlayers) {
                player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.playerMaxLessMin"));
                return true;
            }

            arena.setMaxPlayers(tmax);
            arena.saveArena();
            player.sendMessage(SkyApi.getMessageManager().getAdminMessage("command.message.playerMaxSuccess").replace("%MAX%", tmax.toString()));
            return true;

        }

        return true;
    }

    /**
     * Simplify set this function to set the field mNode with the commands
     * description will come from in the messages.yml file You do not need to
     * enter the full node as it will be prefixed for you. Eg is the full node
     * is command.description.createarena you only need to set this to
     * createarena
     */
    @Override
    public void setmDescription() {
        mNode = "player";
    }

    /**
     * Simply set this to return the the number of arguments The command should
     * receive
     * 
     * @return Integer
     */
    @Override
    public Integer setNumArgs() {
        return 2;
    }

    /**
     * Simply set this to return the clist permission
     * 
     * @return String
     */
    @Override
    public String setPermission() {
        return "ssba.admin.player";
    }

    /**
     * Simply set this to return the clist Usage
     * 
     * @return String
     */
    @Override
    public String setUsage() {
        return "/ssba player [min|max] [count]";
    }

    /**
     * Set this to the label of the command
     * 
     * @return String
     */
    @Override
    public String setLabel() {
        return "ssba player";
    }

    /**
     * Set com
     * 
     * @return String
     */
    @Override
    public String setCmd() {
        return "ssba player";
    }

    @Override
    public Plugin getPlugin() {
        return SkyApi.getSCB();
    }
}
