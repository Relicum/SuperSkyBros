package com.relicum.scb.listeners;

import com.relicum.scb.hooks.VaultManager;
import com.relicum.scb.types.SkyApi;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

/**
 * SuperSkyBros First Created 29/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class BlockDamage implements Listener {

    private List<String> blacklist;

    private PotionEffect pe = PotionEffectType.SLOW_DIGGING.createEffect(100, 1);

    private PotionEffect ps = PotionEffectType.SLOW.createEffect(100, 1);

    public BlockDamage() {
        this.blacklist = SkyApi.getSm().blackListed();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void blockDamage(BlockDamageEvent e) {
        if (this.blacklist.contains(e.getPlayer().getWorld().getName()))
            return;

        Player player = e.getPlayer();
        if (SkyApi.getLobbyManager().isInLobby(player) && !VaultManager.getInstance().perms.has(player, "ssba.admin.breakblocks")) {
            e.setCancelled(true);
            player.addPotionEffect(pe, true);
            player.addPotionEffect(ps);
            // player.sendMessage(SCB.MM.getErrorMessage("listeners.blockbreak.lobbyBreak"));

        }
    }

    // @EventHandler(priority = EventPriority.MONITOR)
    public void blockDamages(BlockDamageEvent e) {
        if (this.blacklist.contains(e.getPlayer().getWorld().getName()))
            return;

        Player player = e.getPlayer();
        if (SkyApi.getLobbyManager().isInLobby(player) && !VaultManager.getInstance().perms.has(player, "ssba.admin.breakblocks")) {
            e.setCancelled(true);
            player.addPotionEffect(pe);
            player.sendMessage(SkyApi.getMessageManager().getErrorMessage("listeners.blockbreak.lobbyBreak"));
            return;
        }
    }

}
