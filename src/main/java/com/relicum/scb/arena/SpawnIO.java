package com.relicum.scb.arena;

import com.relicum.scb.SCB;
import com.relicum.scb.configs.SpawnConfig;
import com.relicum.scb.objects.spawns.ArenaGroupSpawn;
import com.relicum.scb.objects.spawns.ArenaSpawn;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type SpawnIO.
 */
public class SpawnIO {

    /**
     * The Config.
     */
    private SpawnConfig config;


    /**
     * The Path.
     */
    private String path = "group.groups.";


    /**
     * Instantiates a new Spawn iO.
     */
    public SpawnIO() {
        this.setConfig(SCB.getInstance().SPC);
    }


    /**
     * Create new spawn group.
     *
     * @param ArenaSpawn the as
     * @return the boolean
     */
    @SuppressWarnings("unchecked")
    public boolean createNewGroup(ArenaSpawn as) {

        Integer id = as.getArenaid();
        String pa = this.getPath() + id.toString();
        if (!this.config.getConfig().contains(pa)) {
            this.config.getConfig().createSection(pa);
        }
        try {
            ConfigurationSection cs = this.config.getConfig().getConfigurationSection(pa);
            ArenaGroupSpawn ags = new ArenaGroupSpawn(as);

            Map<String, Object> map = ags.toMap();

            List<ArenaSpawn> sw = ags.getSpawns();
            for (ArenaSpawn a : sw) {
                cs.set("groupspawn.spawns." + a.getHashStore().get("groupid"), a.getHashStore());
            }
            System.out.println("There is " + sw.size() + " arena spawns");
            cs.set("groupspawn.chunks", (List<Vector>) map.get("chunks"));

            config.saveConfig();
            config.reloadConfig();
        } catch (Exception e) {
            System.out.println("Error saving spawn");
            e.printStackTrace();
            return false;
        }

        ConfigurationSection cs = this.config.getConfig().getConfigurationSection(pa);
        ArenaGroupSpawn ar = this.makeGroupSpawn(id, cs);
        SCB.getInstance().ARM.getArenaById(id).setArenaSpawnGroup(ar);

        return true;
    }


    /**
     * Save spawn.
     *
     * @param ArenaSpawn the as
     * @return the boolean
     */
    public boolean saveSpawn(ArenaSpawn as) {

        String pa = this.path + as.getArenaid();
        Integer id = as.getArenaid();
        try {
            ConfigurationSection cs = this.config.getConfig().getConfigurationSection(pa);

            Map<String, Object> map = as.getHashStore();

            cs.set("groupspawn.spawns." + as.getHashStore().get("groupid"), as.getHashStore());
            config.saveConfig();
            config.reloadConfig();

        } catch (Exception e) {

            System.out.println("Error saving spawn");
            e.printStackTrace();
            return false;
        }

        ConfigurationSection lcon = this.config.getConfig().getConfigurationSection(pa + ".groupspawn.spawns." + as.getHashStore().get("groupid"));
        ArenaSpawn nas = new ArenaSpawn(lcon.getVector("vector"), lcon.getInt("arenaid"), lcon.getString("world"));
        nas.setGroupId(lcon.getInt("groupid"));

        if (SCB.getInstance().ARM.getArenaById(id).getSpawnGroup().addSpawn(nas)) {

            System.out.println("New spawn successfully saved and reloaded and added to Arena");
        }

        return true;
    }


    public ArenaGroupSpawn makeGroupSpawn(Integer id, ConfigurationSection st) {

        ArenaGroupSpawn as = new ArenaGroupSpawn(id);
        Set<String> s = st.getConfigurationSection("groupspawn.spawns").getKeys(false);
        for (String k : s) {

            ConfigurationSection m = st.getConfigurationSection("groupspawn.spawns." + k);

            ArenaSpawn sp = new ArenaSpawn(m.getVector("vector"), m.getInt("arenaid"), m.getString("world"));
            sp.setGroupId(m.getInt("groupid"));

            as.addSpawn(sp);
        }

        return as;
    }


    /**
     * Gets path.
     *
     * @return String the path
     */
    public String getPath() {
        return path;
    }


    /**
     * Sets path.
     *
     * @param String the path
     */
    public void setPath(String path) {
        this.path = path;
    }


    /**
     * Gets config.
     *
     * @return SpawnConfig the config
     */
    public SpawnConfig getConfig() {
        return config;
    }


    /**
     * Sets config.
     *
     * @param SpawnConfig the config
     */
    public void setConfig(SpawnConfig config) {
        this.config = config;
    }
}
