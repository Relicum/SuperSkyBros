package com.relicum.scb.objects.world;

import java.util.Random;
import com.relicum.scb.types.SkyApi;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;

/**
 * SuperSkyBros First Created 11/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class Generator extends ChunkGenerator implements Listener {

    public Plugin ChunkGenerator() {
        return SkyApi.getSCB();
  }


    @Override
    @Deprecated
    public byte[][] generateBlockSections(World world, Random random, int chunkX, int chunkZ, ChunkGenerator.BiomeGrid biomeGrid) {
        byte[][] result = new byte[world.getMaxHeight() / 16][];
        if ((chunkX == 0) && (chunkZ == 0)) {
            setBlock(result, 0, 64, 0, (byte) 7);
        }
        return result;
    }


    private void setBlock(byte[][] result, int x, int y, int z, byte blkid) {
        if (result[(y >> 4)] == null) {
            result[(y >> 4)] = new byte[4096];
        }
        result[(y >> 4)][((y & 0xF) << 8 | z << 4 | x)] = blkid;
    }


    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0.0D, 65.0D, 0.0D);
    }
}
