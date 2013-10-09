package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.objects.signs.utils.Col;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.util.List;

/**
 * SuperSkyBros First Created 08/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class SignChange implements Listener {

    private SCB plugin;

    public List<String> blacklist;

    public boolean isDedicated;


    public SignChange(SCB p) {
        this.plugin = p;
        blacklist = plugin.getBlackList();
        this.isDedicated = plugin.getConfig().getBoolean("dedicatedSSB");

    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void signChange(SignChangeEvent e) throws InterruptedException {

        if (this.blacklist.contains(e.getPlayer().getWorld().getName()))
            return;

        String[] lines = e.getLines();
        if (lines[0].equalsIgnoreCase("ssb") && lines[1].equalsIgnoreCase("join")) {

            if (SCB.perms.has(e.getPlayer(), "ssba.admin.createsign")) {
                e.setLine(0, "§4[SSB JOIN]");
                e.setLine(1, "RIGHT CLICK TO");
                e.setLine(2, "JOIN SSB LOBBY");
                e.getPlayer().sendMessage(SCB.getMessageManager().getAdminMessage("listeners.signchange.success"));
                System.out.println("A sign has been place by " + e.getPlayer().getName());
            } else {
                e.setCancelled(true);
                e.getBlock().breakNaturally();
                e.getPlayer().sendMessage(SCB.getMessageManager().getErrorMessage("listeners.signchange.noPerms"));
                System.out.println("A Join sign was canceled due to no perms by " + e.getPlayer().getName());
            }


        }


    }

}
