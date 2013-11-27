package com.relicum.scb.listeners;

import com.relicum.scb.SCB;
import com.relicum.scb.arena.ArenaIO;
import com.relicum.scb.events.ArenaStatusChangeEvents;
import com.relicum.scb.arena.ArenaStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * SuperSkyBros First Created 11/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class ArenaChangeStatus implements Listener {


    private SCB p;

    private ArenaStatusChangeEvents event;


    public ArenaChangeStatus(SCB pl) {
        this.p = pl;
    }


    @EventHandler
    public void ArenaStatusListen(ArenaStatusChangeEvents e) {
        if (!e.isWithGame()) {
            this.event = e;
            this.test();
        } else {
            event = e;
        }
    }


    public void test() {

        p.getLogger().info("The Arena Status Change Event has been fired for Arena ID: " + event.getArena().getArenaId().toString());
        p.getLogger().info("The status was " + event.getPrevious().name() + " it is now set to " + event.getStatus().name());
    }


    public void isDisabled() {
        if ((event.getStatus().equals(event.getPrevious()))) {
            p.getLogger().info("Nothing to do in ArenaStatusChangeEvents Previous and Current status both = DISABLED");
            return;
        }
        if (event.getStatus().equals(ArenaStatus.DISABLED)) {

            ArenaIO arenaIO = new ArenaIO();
            arenaIO.saveArena(event.getArena());
            arenaIO = null;

        }
    }


    /**
     * Tries to remove the arena from Arena Manager List
     *
     * @return boolean
     */
    public boolean removeArena() {

        try {
            SCB.getInstance().ARM.removeArena(event.getArena().getArenaId(), event.getArena().getUniqueMap());
        } catch (Exception e) {
            System.out.println("Error has occurred removing the arena");
            return false;
        }
        return true;
    }
}
