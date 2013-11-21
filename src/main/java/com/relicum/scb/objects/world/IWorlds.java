package com.relicum.scb.objects.world;

import org.bukkit.World;
import org.bukkit.command.Command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SuperSkyBros
 * First Created 20/11/13
 * Interface for world management
 *
 * @author Relicum
 * @version 0.1
 */
public interface IWorlds {

    public String name = null;
    public World world = null;
    public List<String> setters = null;
    public List<String> getters = null;
    public Map<String, Command> settings = new HashMap<>();
    public Map<String, Command> gettings = new HashMap<>();

    public boolean Set(String set);

    public Object Get(String get);

    public String addSetting(String setting);

    public String addGetter(String getter);

    public String listSetters();

    public String listGetters();

    public String toString();
}
