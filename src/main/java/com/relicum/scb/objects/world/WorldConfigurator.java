package com.relicum.scb.objects.world;

import com.relicum.scb.types.SkyApi;

/**
 * SuperSkyBros
 * First Created 20/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class WorldConfigurator implements IWorlds {


    //TODO Apply default settings and a spawn platform to first spawn into
    //TODO Register any Listeners|Events specific to the world
    //TODO Create App to copy a current worlds settings to be used as a template
    //TODO Worlds backup facility, could be used to restore if worlds get damaged
    //TODO Ongoing user interface to manage the world
    private WorldConfigurator worldCon;

    public void stopServer() {
        SkyApi.getSCB().getServer().shutdown();
    }

    public WorldConfigurator WorldConfigurator() {

        return this;

    }

    @Override
    public boolean Set(String set) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object Get(String get) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String addSetting(String setting) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String addGetter(String getter) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String listSetters() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String listGetters() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
