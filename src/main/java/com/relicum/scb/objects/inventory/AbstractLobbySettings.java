package com.relicum.scb.objects.inventory;

import com.relicum.scb.objects.inventory.interfaces.IISettings;
import com.thoughtworks.xstream.XStream;

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
    public Integer getTotalExperience() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public boolean getAllowedFlight() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public boolean getSetFlying() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public boolean clearInventory() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public boolean removeFireTicks() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
