package com.relicum.scb.commands;

import com.relicum.scb.ArenaManager;
import com.relicum.scb.SCB;
import com.relicum.scb.arena.Arena;
import com.relicum.scb.arena.ArenaStatus;
import com.relicum.scb.types.SkyApi;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Set;

/**
 * SuperSkyBros First Created 11/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class disable extends SubBase {

    @Override
    public boolean onCommand(Player player, String[] args) {

        String tmp;
        ArenaManager ar = SkyApi.getArenaManager();
        if (ar.getCurrent() != Integer.parseInt(args[0])) {
            player.sendMessage(
                    tmp = SCB.getMessageManager().getErrorMessage("command.message.enableMisMatch").replace("%ID%", args[0]).replace("%MID%", ar.getCurrent().toString()));
            return true;
        }

        if (ar.getCurrent() == 0) {
            player.sendMessage(SCB.getMessageManager().getErrorMessage("command.message.NoArenaSet"));
            return true;
        }

        Set<String> aids = ar.getArenaIds();
        if (aids.size() == 0 || !aids.contains(args[0])) {

            player.sendMessage(tmp = SCB.getMessageManager().getErrorMessage("command.message.enableNoIdFound").replace("%ID%", args[0]));
            return true;
        }

        Arena arena = ar.getArenaById(Integer.parseInt(args[0]));
        ArenaStatus pre = arena.getArenaStatus();
        arena.setEnable(false);
        arena.setStatus("DISABLED");


        try {

            // ArenaStatusChangeEvents disablearena = new ArenaStatusChangeEvents(pre, arena);
            // Bukkit.getServer().getPluginManager().callEvent(disablearena);

        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(tmp = SCB.getMessageManager().getErrorMessage("command.message.enableFailedToSave").replace("%ID%", args[0]));
            return true;

        }
        player.sendMessage(tmp = SCB.getMessageManager().getAdminMessage("command.message.disabledSuccess").replace("%ID%", ar.getCurrent().toString()));

        return true;
    }


    @Override
    public void setmDescription() {
        mNode = "disable";
    }


    @Override
    public Integer setNumArgs() {
        return 1;
    }


    @Override
    public String setPermission() {

        return "ssba.admin.disable";
    }


    @Override
    public String setUsage() {
        return "/ssba disable [ArenaID]";
    }


    @Override
    public String setLabel() {
        return "ssba disable";
    }


    @Override
    public String setCmd() {
        return "ssba disable";
    }


    @Override
    public Plugin getPlugin() {
        return SCB.getInstance();
    }
}
