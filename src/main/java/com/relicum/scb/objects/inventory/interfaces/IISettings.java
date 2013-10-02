package com.relicum.scb.objects.inventory.interfaces;


import java.util.HashMap;

/**
 * SuperSkyBros First Created 02/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public interface IISettings {

    public double getPlayerHealth();

    public double getPlayerDamage();

    public Integer getTotalExperience();

    public boolean getAllowedFlight();

    public boolean getSetFlying();

    public boolean clearInventory();

    public boolean removeFireTicks();

}
