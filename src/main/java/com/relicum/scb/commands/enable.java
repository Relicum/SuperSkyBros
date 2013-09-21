package com.relicum.scb.commands;

import com.relicum.scb.ArenaManager;
import com.relicum.scb.SCB;
import com.relicum.scb.arena.Arena;
import com.relicum.scb.arena.ArenaIO;
import org.bukkit.entity.Player;

import java.util.Set;


/**
 * SuperSkyBros Enable Command First Created 11/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class enable extends SubBase {

    @Override
    public boolean onCommand(Player player, String[] args) {
        String tmp;
        ArenaManager ar = SCB.getInstance().ARM;
        if (ar.getCurrent() != Integer.parseInt(args[0])) {
            player.sendMessage(tmp = SCB.getMessageManager().getErrorMessage("command.message.enableMisMatch").replace("%ID%", args[0]).replace("%MID%", ar.getCurrent().toString()));
            return true;
        }

        if (ar.getCurrent() == 0) {
            player.sendMessage(SCB.getMessageManager().getErrorMessage("command.message.setspawnNoArenaSet"));
            return true;
        }

        Set<String> aids = ar.getArenaIds();
        if (aids.size() == 0 || !aids.contains(args[0])) {

            player.sendMessage(tmp = SCB.getMessageManager().getErrorMessage("command.message.enableNoIdFound").replace("%ID%", args[0]));
            return true;
        }
        Arena arena = ar.getArenaById(Integer.parseInt(args[0]));
        arena.setEnable(true);
        arena.setStatus("WAITING");

        ArenaIO arenaIO = new ArenaIO();
        try {
            arenaIO.saveArena(arena);
        }
        catch ( Exception e ) {
            e.printStackTrace();
            player.sendMessage(tmp = SCB.getMessageManager().getErrorMessage("command.message.enableFailedToSave").replace("%ID%", args[0]));
            return true;

        }
        player.sendMessage(tmp = SCB.getMessageManager().getAdminMessage("command.message.enableSuccess").replace("%ID%", ar.getCurrent().toString()));

        return true;
    }


    @Override
    public void setmDescription() {
        mNode = "enable";
    }


    @Override
    public Integer setNumArgs() {
        return 1;
    }


    @Override
    public String setPermission() {
        return "ssba.admin.enable";
    }


    @Override
    public String setUsage() {
        return "/ssba enable [ArenaID]";
    }


    @Override
    public String setLabel() {
        return "ssba enable";
    }


    @Override
    public String setCmd() {
        return "ssba enable";
    }
}
