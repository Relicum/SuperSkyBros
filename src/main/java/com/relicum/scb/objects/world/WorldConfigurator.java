package com.relicum.scb.objects.world;

import com.relicum.scb.types.SkyBrosApi;
import com.relicum.scb.utils.FileUtils;
import com.relicum.scb.utils.PropertiesManager;
import com.relicum.scb.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * SuperSkyBros
 * First Created 20/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class WorldConfigurator implements IWorlds {
    public String NORMAL = "\033[m";
    public String BLUE_TEXT = "\033[36m";  //Blue
    public String RED_TEXT = "\033[31m";  //Red
    public String GREEN_TEXT = "\033[32m";  //Green

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

    /**
     * Sets the world_the_end to false and adds in WorldGenerator configs
     *
     * @param end   set to false to disable the end.
     * @param gen   set the WorldGenerator plugin
     * @param world the name of the default world as a string
     * @return true on success
     */
    public boolean setBukkit(boolean end, String gen, String wo, Integer stage) {

        if (Bukkit.getAllowEnd() && SkyBrosApi.getSCB().getServer().getAllowEnd() == false) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("bukkit.yml"));
            config.set("settings.allow-end", end);
            StringBuilder pa = new StringBuilder();
            pa.append("Yml world gen value is " + gen);
            System.out.println(StringUtils.replaceUtf8Characters(pa.toString()));
            if (stage > 1) {
                System.out.println(BLUE_TEXT + "Yml at stage 3 world gen value is " + pa + NORMAL);
                config.set("worlds.world.generator", "CleanroomGenerator\\:\\.");
            }
            try {
                config.save(new File("bukkit.yml"));

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        SkyBrosApi.getSCB().getLogger().info(BLUE_TEXT + "The _the_end has successfully been disabled and the WorldGenerator Plugin Added" + NORMAL);

        return true;
    }

    /**
     * Updates server.properties to begin creating void worlds
     *
     * @return true if the properties were successfully updated
     */
    public boolean setMainProperties() {
        Map<String, Object> st = SkyBrosApi.getSettingsManager2().getWorldConfig().getConfig().getConfigurationSection("mainWorld").getValues(true);
        Integer stage = SkyBrosApi.getSCB().getConfig().getInt("worldGenerateStage");
        System.out.println("Stage we are at is " + stage + " lenght of st is " + st.size());
        PropertiesManager prop = new PropertiesManager();
        String wn = "";
        if (stage == 1) {
            wn = SkyBrosApi.getSCB().getConfig().getString("newWorldDefault");
        }

        if (stage == 2) {

            wn = SkyBrosApi.getSCB().getConfig().getString("worldDefault");
        }


        Object obj = (String) prop.getPropertiesConfig("level-name", wn);
        String theWorld = String.valueOf(obj);
        st.put("level-name", wn);
        if (stage == 1) {
            SkyBrosApi.getSCB().getConfig().set("newWorldDefault", wn);

        }


        for (Map.Entry e : st.entrySet()) {
            prop.setPropertiesConfig((String) e.getKey(), e.getValue());

        }
        try {
            prop.savePropertiesConfig();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (stage == 1) {
            SkyBrosApi.getSettingsManager2().getWorldConfig().getConfig().set("mainWorld.level-name", SkyBrosApi.getSCB().getConfig().getString("newWorldDefault"));
        }
        if (stage == 2) {
            SkyBrosApi.getSettingsManager2().getWorldConfig().getConfig().set("mainWorld.level-name", SkyBrosApi.getSCB().getConfig().getString("worldDefault"));
        }

        SkyBrosApi.getSCB().saveConfig();
        SkyBrosApi.getSCB().reloadConfig();
        SkyBrosApi.getSettingsManager2().getWorldConfig().saveConfig();
        SkyBrosApi.getSettingsManager2().getWorldConfig().reloadConfig();


        SkyBrosApi.getSCB().getLogger().info("You have successfully update the properties file");
        return true;
    }

    /**
     * Remove default world, world_the_end and world_nether
     *
     * @param path the path to the world directories
     * @return the boolean true if successfully deleted
     */
    public boolean removeDefaultWorld(String path) {
        FileUtils.clear(new File(path));
        try {
            FileUtils.clear(new File(path));
        } catch (Exception e) {
            SkyBrosApi.getSCB().getLogger().severe(e.getMessage());
            return false;
        }

        SkyBrosApi.getSCB().getLogger().info("Successfully deleted " + path + " folder the server will now restart");

        return true;
    }

    public void stopServer() {
        SkyBrosApi.getSCB().getServer().shutdown();
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
