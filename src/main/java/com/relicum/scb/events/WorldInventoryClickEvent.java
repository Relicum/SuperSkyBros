package com.relicum.scb.events;

import org.bukkit.World;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.InventoryView;

import java.util.ArrayList;
import java.util.List;

/**
 * SuperSkyBros First Created 04/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class WorldInventoryClickEvent extends InventoryClickEvent {


    private static final HandlerList handlers = new HandlerList();

    private boolean cancel;

    private World world;

    private List<String> blacklist = new ArrayList<>(2);

    private boolean isBlackListed;


    public WorldInventoryClickEvent(InventoryView view, SlotType type, int slot, ClickType click, InventoryAction action) {
        super(view, type, slot, click, action);
        this.world = getWhoClicked().getWorld();
    }


    public WorldInventoryClickEvent(InventoryView view, SlotType type, int slot, ClickType click, InventoryAction action, int key) {
        super(view, type, slot, click, action, key);
        this.world = getWhoClicked().getWorld();
    }


    /**
     * Get world.
     *
     * @return the world
     */
    public World getWorld() {
        return this.world;

    }


    /**
     * Is world black listed.
     *
     * @return the boolean
     */
    public boolean isWorldBlackListed() {

        return this.isBlackListed;
    }


    public boolean isCancelled() {
        return cancel;
    }


    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }
}
