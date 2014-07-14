package com.relicum.scb.commands;

import com.relicum.scb.ArenaManager;
import com.relicum.scb.arena.Arena;
import com.relicum.scb.arena.ArenaIO;
import com.relicum.scb.configs.ServerStatus;
import com.relicum.scb.types.SkyApi;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

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
        ArenaManager ar = SkyApi.getArenaManager();
        if (ar.getCurrent() != Integer.parseInt(args[0])) {
            player.sendMessage(tmp = SkyApi.getMessageManager().getErrorMessage("command.message.enableMisMatch").replace("%ID%", args[0])
                    .replace("%MID%", ar.getCurrent().toString()));
            return true;
        }

        if (ar.getCurrent() == 0) {
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("command.message.NoArenaSet"));
            return true;
        }

        Set<String> aids = ar.getArenaIds();
        if (aids.size() == 0 || !aids.contains(args[0])) {

            player.sendMessage(tmp = SkyApi.getMessageManager().getErrorMessage("command.message.enableNoIdFound").replace("%ID%", args[0]));
            return true;
        }
        Arena arena = ar.getArenaById(Integer.parseInt(args[0]));
        arena.setEnable(true);
        arena.setStatus("WAITING");

        ArenaIO arenaIO = new ArenaIO();
        try {
            arenaIO.saveArena(arena);
        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(tmp = SkyApi.getMessageManager().getErrorMessage("command.message.enableFailedToSave").replace("%ID%", args[0]));
            return true;

        }

        player.sendMessage(tmp = SkyApi.getMessageManager().getAdminMessage("command.message.enableSuccess").replace("%ID%", ar.getCurrent().toString()));
        if (SkyApi.getSCB().getConfig().getString("serverStatus").equalsIgnoreCase(ServerStatus.SETENABLE.name())) {
            SkyApi.getSCB().getConfig().set("serverStatus", ServerStatus.CREATEJOINSIGN.name());
            SkyApi.getSCB().saveConfig();
            player.sendMessage("");
            player.sendMessage(SkyApi.getMessageManager().getMessage("setup.arenasign"));

        }
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

    @Override
    public Plugin getPlugin() {
        return SkyApi.getSCB();
    }
}
