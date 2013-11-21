package com.relicum.scb;


import java.util.List;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class ClassManager implements Cloneable {


    private List<String> active;

    //private Map<String, BaseCreateClass> pclasses = new HashMap<>();


    public ClassManager() {

        active = SCB.getInstance().getConfig().getStringList("classesEnabled");

        if (active.contains("Creeper")) {
            //pclasses.put("Creeper",new Creeper());
            SCB.getInstance().getLogger().info("Creeper Class Appears to of loaded");

        }
    }


}
