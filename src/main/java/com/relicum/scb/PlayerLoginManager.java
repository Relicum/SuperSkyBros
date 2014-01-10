package com.relicum.scb;

import com.relicum.scb.types.SkyApi;
import org.bukkit.entity.Player;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * SuperSkyBros
 * First Created 15/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerLoginManager {

    public static String path = SkyApi.getSCB().getDataFolder().toString() + File.separatorChar + "players" + File.separatorChar;

    public static boolean seenBefore(Player player) {
        return player.hasPlayedBefore();
    }

    public static boolean hasProfile(String name) {
        //if(Files.exists((SkyApi.getSCB().getDataFolder()).toPath().resolve(File.separatorChar + File.separatorChar + "players" + File.separatorChar + name + ".yml"))){
        if (Files.exists(Paths.get(path).resolve(name + ".yml"))) {

            return true;
        }
        SkyApi.getCMsg().SERVE("No file found here " + Paths.get(path).resolve(name + ".yml").toString());

        return false;

    }

    public static String profilePath(String name) {
        return name + ".yml";

    }


}
