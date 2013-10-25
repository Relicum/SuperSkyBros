package com.relicum.scb.listeners;


import com.relicum.scb.SCB;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeWrapper;

import java.util.List;

import static org.bukkit.potion.PotionEffectType.registerPotionEffectType;

/**
 * SuperSkyBros First Created 29/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class BlockDamage implements Listener {

    private SCB plugin;

    private List<String> blacklist;

    private PotionEffect pe = PotionEffectType.SLOW_DIGGING.createEffect(100, 1);

    private PotionEffect ps = PotionEffectType.SLOW.createEffect(100, 1);


    public BlockDamage(SCB p) {
        this.plugin = p;
        this.blacklist = plugin.getBlackList();
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void blockDamage(BlockDamageEvent e) {
        if (this.blacklist.contains(e.getPlayer().getWorld().getName()))
            return;

        Player player = e.getPlayer();
        if (SCB.getInstance().LBS.isInLobby(player) && !SCB.perms.has(player, "ssba.admin.breakblocks")) {
            e.setCancelled(true);
            player.addPotionEffect(pe, true);
            player.addPotionEffect(ps);
            //player.sendMessage(SCB.MM.getErrorMessage("listeners.blockbreak.lobbyBreak"));

        }
    }


    //@EventHandler(priority = EventPriority.MONITOR)
    public void blockDamages(BlockDamageEvent e) {
        if (this.blacklist.contains(e.getPlayer().getWorld().getName()))
            return;

        Player player = e.getPlayer();
        if (SCB.getInstance().LBS.isInLobby(player) && !SCB.perms.has(player, "ssba.admin.breakblocks")) {
            e.setCancelled(true);
            player.addPotionEffect(pe);
            player.sendMessage(SCB.MM.getErrorMessage("listeners.blockbreak.lobbyBreak"));
            return;
        }
    }


}
