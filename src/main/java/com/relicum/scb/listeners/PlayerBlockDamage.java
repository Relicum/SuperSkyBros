package com.relicum.scb.listeners;


import com.relicum.scb.SCB;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * SuperSkyBros First Created 29/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerBlockDamage implements Listener {


    public PlayerBlockDamage() {

    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void blockDamage(PlayerInteractEvent e) {


        Player player = e.getPlayer();
        if (SCB.getInstance().LBS.isInLobby(player) && !SCB.perms.has(player, "ssba.admin.breakblocks")) {
            if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                e.setCancelled(true);
                e.setUseInteractedBlock(Result.DENY);
                player.sendMessage(SCB.MM.getErrorMessage("listeners.blockbreak.lobbyBreak"));
            }
        }
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void MblockDamage(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        if (SCB.getInstance().LBS.isInLobby(player) && !SCB.perms.has(player, "ssba.admin.breakblocks")) {
            if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                e.setCancelled(true);
                e.setUseInteractedBlock(Result.DENY);
                player.sendMessage(SCB.MM.getErrorMessage("listeners.blockbreak.lobbyBreak"));
            }
        }
    }

}
