package com.relicum.scb.objects.world;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

/**
 * Simple Void world Generator.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class WorldGenerator extends ChunkGenerator {

    public byte[] generate(World world, Random random, int cx, int cz) {

        return new byte[65536];
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {

        if (!world.isChunkLoaded(0, 0)) {
            world.loadChunk(0, 0);
        }
        return new Location(world, 0, 32, 0);
    }

}
