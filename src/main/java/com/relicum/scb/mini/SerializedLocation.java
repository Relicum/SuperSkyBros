package com.relicum.scb.mini;

import lombok.Getter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * SuperSkyBros First Created 13/11/13
 *
 * @author Relicum
 * @version 0.1
 */
@ToString(doNotUseGetters = true)
public class SerializedLocation {

    @Getter
    private final int x;

    @Getter
    private final int y;

    @Getter
    private final int z;

    @Getter
    private final String world;

    @Getter
    private final float yaw;

    @Getter
    private final float pitch;


    public SerializedLocation(int x, int y, int z, String world, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        this.yaw = yaw;
        this.pitch = pitch;
    }


    public Location getLocation() {
        return new Location(Bukkit.getServer().getWorld(world), x, y, z, yaw, pitch);
    }


    public static boolean serialize() {
        return true;
    }


    public static SerializedLocation deserialise() {

        return null;
    }
}
