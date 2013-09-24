package com.relicum.scb.objects.signs.interfaces;

import org.bukkit.World;

import java.util.UUID;

/**
 * SuperSkyBros First Created 24/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public interface ISignWorld {

    String getWorldName();

    World getWorld();

    UUID getWorldUniqueID();
}
