package com.relicum.scb.objects.world;

import com.relicum.scb.types.SkyBrosApi;

/**
 * SuperSkyBros
 * First Created 20/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class WorldConfigurator implements IWorlds {

    public static BukkitConfig bukkitConfig;

    //TODO If its Default world Get Settings to overwrite server.properties
    //TODO Update bukkit.yml setting the generator and the_end to false
    //TODO apon restart delete old world folder
    //TODO start creation of new world
    //TODO Apply default settings and a spawn platform to first spawn into
    //TODO Register any Listeners|Events specific to the world
    //TODO Create App to copy a current worlds settings to be used as a template
    //TODO Worlds backup facility, could be used to restore if worlds get damaged
    //TODO Ongoing user interface to manage the world

    private WorldConfigurator worldCon;

    public WorldConfigurator WorldConfigurator() {

        return this;

    }

    /**
     * Gets bukkit modifier config.
     *
     * @param name the name
     * @return the bukkit modifier
     */
    public static void setBukkitConfig(String name) {
        if (bukkitConfig == null) {
            bukkitConfig = new BukkitConfig(name);
        }

    }

    public static boolean setBukkit() {
        if (bukkitConfig.getConfig().getString("settings.allow-end").isEmpty()) {
            SkyBrosApi.getSCB().getLogger().severe("Error occurred trying to load the bukkit.yml file, see logs for detail");
            bukkitConfig.saveConfig();
            bukkitConfig.getConfig().options().copyDefaults(true);
        } else {
            bukkitConfig.getConfig().set("settings.allow-end", false);
            bukkitConfig.saveConfig();
            bukkitConfig.reloadConfig();
            if (bukkitConfig.getConfig().contains("worlds")) {
                bukkitConfig.getConfig().getConfigurationSection("worlds").set("world.generator", "CleanroomGenerator:.");
                bukkitConfig.saveConfig();
                SkyBrosApi.getSCB().getLogger().info("Bukkit.yml file should now of been altered");
                return true;
            }

        }
        System.out.println("Error with bukkit");
        return false;
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
