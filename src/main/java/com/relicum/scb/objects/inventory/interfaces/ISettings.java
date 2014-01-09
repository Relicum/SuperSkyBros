package com.relicum.scb.objects.inventory.interfaces;

/**
 * SuperSkyBros
 * First Created 09/01/14
 *
 * @author Relicum
 * @version 0.1
 */
public interface ISettings {

    public void setHealth(double v);

    public void setFlying(boolean v);

    public void setAllowFlight(boolean v);

    public void setMaximumNoDamageTicks(int v);

    public void setFoodLevel(int v);

    public void setSaturation(float v);

    public void setExhaustion(float v);

}
