package com.relicum.scb;

import java.util.List;
import com.relicum.scb.types.SkyApi;

/**
 * Bukkit-SCB
 * 
 * @author Relicum
 * @version 0.1
 */
public class ClassManager implements Cloneable {

    private List<String> active;

    // private Map<String, BaseCreateClass> pclasses = new HashMap<>();

    public ClassManager() {

        active = SkyApi.getSCB().getConfig().getStringList("classesEnabled");

        if (active.contains("Creeper")) {
            // pclasses.put("Creeper",new Creeper());
            SkyApi.getSCB().getLogger().info("Creeper Class Appears to of loaded");

        }
    }

}
