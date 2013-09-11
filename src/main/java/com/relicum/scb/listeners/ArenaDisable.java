package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.arena.Arena;
import com.relicum.scb.events.ArenaDisableEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * SuperSkyBros
 * First Created 11/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class ArenaDisable implements Listener {

    private Arena arena;
    private SCB p;

    public ArenaDisable(SCB pl) {
        this.p = pl;
    }

    @EventHandler
    public void ArenaDisableListen(ArenaDisableEvent e) {

        this.arena = e.getArena();
        this.test();
    }

    public void test() {

        p.getLogger().info("The Arena Disable Event has been fired for Arena ID: " + arena.getArenaId().toString());
    }

    /**
     * Tries to remove the arena from Arena Manager List
     *
     * @return boolean
     */
    public boolean removeArena() {

        try {
            SCB.getInstance().ARM.removeArena(arena.getArenaId(), arena.getUniqueMap());
        } catch (Exception e) {
            System.out.println("Error has occurred removing the arena");
            return false;
        }
        return true;
    }
}
