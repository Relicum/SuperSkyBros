package com.relicum.scb.listeners;


import com.relicum.scb.SCB;
import com.relicum.scb.events.ToggleFlyEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;

/**
 * SuperSkyBros First Created 04/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerToggleFly extends Event implements Listener {

    private static final HandlerList handlers = new HandlerList();

    private SCB plugin;

    public boolean isJumping = false;

    public Integer c = 0;

    public Integer hel;

    public Integer cooldown = 5;

    private HashMap<String, Long> lastJumped = new HashMap<>();

    private HashMap<String, Boolean> jumping = new HashMap<>();

    private List<String> blackWorlds;


    public PlayerToggleFly(SCB plugin) {
        this.plugin = plugin;
    }


    @EventHandler(priority = EventPriority.HIGH)
    public void pToggleFly(ToggleFlyEvent e) {


        System.out.println("Fly Toogle has switched");
    }


    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
    public void toggleFly(PlayerToggleFlightEvent e) {
        long now = System.currentTimeMillis();
        if (this.blackWorlds.contains(e.getPlayer().getName())) return;
        PlayerToggleFlightEvent event = new PlayerToggleFlightEvent(e.getPlayer().getPlayer(), false);
        Bukkit.getServer().getPluginManager().callEvent(event);

/*        if (!VaultManager.perms.has(e.getPlayer(), "scb.mario.use") && (!VaultManager.perms.has(e.getPlayer(), "scb.mario.triple")))
            return;

  */

        Player p = e.getPlayer();

        Vector jump;
        Vector look;
        if (p.isSprinting()) {
            p.setNoDamageTicks(160);
            jump = p.getVelocity().multiply(1.2D).setY(2.0D);
            look = p.getLocation().getDirection().multiply(0.6D);
            p.playSound(p.getLocation(), Sound.EXPLODE, 1.0F, -5.0F);
        } else {
            p.setNoDamageTicks(140);
            jump = p.getVelocity().multiply(1.8D).setY(1.6D);
            look = p.getLocation().getDirection().multiply(0.7D);
            p.playSound(p.getLocation(), Sound.HORSE_ZOMBIE_HIT, 1.0F, 5.0F);

        }

        if ((e.isFlying()) && (p.getGameMode() != GameMode.CREATIVE)) {
            p.setFlying(false);
            p.setAllowFlight(false);
            jump.add(look);
            p.setVelocity(jump);


        }


    }


    @Override
    public HandlerList getHandlers() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
