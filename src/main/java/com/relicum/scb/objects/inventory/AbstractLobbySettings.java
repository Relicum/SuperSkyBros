package com.relicum.scb.objects.inventory;

import com.relicum.scb.objects.inventory.interfaces.IISettings;

/**
 * SuperSkyBros First Created 02/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class AbstractLobbySettings implements IISettings {


    @Override
    public double getPlayerHealth() {

        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public double getPlayerDamage() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public int getTotalExperience() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public boolean getAllowedFlight() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public boolean getIsFlying() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
