package com.relicum.scb.objects.world;

import com.relicum.scb.types.SkyApi;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World;

/**
 * SuperSkyBros
 * First Created 09/01/14
 *
 * @author Relicum
 * @version 0.1
 */
public class ApplyWorldSettings {

    public static void apply(World world) {

        world.setKeepSpawnInMemory(true);

        world.setDifficulty(Difficulty.HARD);

        world.setStorm(false);
        world.setThundering(false);
        world.setWeatherDuration(9999999);
        world.setTime(6000);
        if (SkyApi.getSm().isDedicated()) {

            Bukkit.getServer().setDefaultGameMode(GameMode.ADVENTURE);
        }
        world.setGameRuleValue("doDaylightCycle", "false");
        world.setGameRuleValue("doFireTick", "false");
        world.setGameRuleValue("doMobSpawning", "false");
        world.setGameRuleValue("mobGriefing", "false");
        world.save();
        world.setAutoSave(true);
    }
}
