package com.relicum.scb.events;


import com.relicum.scb.SCB;
import com.relicum.scb.SmashPlayer;
import com.relicum.scb.utils.doublejump.setJumpStatus;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class ToggleFlyEvent extends PlayerToggleFlightEvent {

    public ToggleFlyEvent(Player player, boolean isFlying) {
        super(player, isFlying);
    }


    @Override
    public HandlerList getHandlers() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}