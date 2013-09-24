package com.relicum.scb.objects.signs.interfaces;


import org.bukkit.entity.LivingEntity;

/**
 * SuperSkyBros First Created 24/09/13 Teleport a LivingEntity to some Location
 *
 * @author Relicum
 * @version 0.1
 */
public interface ITransporter {

    /**
     * Teleport a LivingEntity to some Location
     *
     * @param entity   the entity
     * @param location the location
     */
    void teleport(LivingEntity entity, ISignLocation location);

}
