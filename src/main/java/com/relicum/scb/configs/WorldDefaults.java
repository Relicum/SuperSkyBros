/**
 * Name: WorldDefaults.java Edited: 30 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.scb.configs;

import org.bukkit.World;
import org.bukkit.WorldType;

public class WorldDefaults {

    // This block is used for settings in server.properties file
    public final static boolean Pforce_game_mode = true;
    public final static boolean Pallow_nether = false;
    public final static String Plevel_name = "world";
    public final static boolean Pspawn_npcs = true;
    public final static boolean Ppvp = true;
    public final static boolean Pgenerate_structures = false;
    public final static int Pdifficulty = 2;
    public final static int Pgamemode = 2;
    public final static int Pview_distance = 7;
    public final static String Plevel_type = "FLAT";
    public final static boolean Pallow_flight = true;
    public final static boolean Pspawn_animals = true;
    public final static boolean Pspawn_monsters = true;
    public final static boolean Pannounce_player_achievements = true;

    // This block is the defaults for world creator
    public final static WorldType worldType = WorldType.FLAT;
    public final static World.Environment environment = World.Environment.NORMAL;
    public final static boolean generate_structures = false;
    public final static String generatorString = "SuperSkyBros";

    public WorldDefaults() {

    }

}
