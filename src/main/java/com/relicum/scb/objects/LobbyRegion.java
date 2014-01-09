package com.relicum.scb.objects;

import com.relicum.scb.utils.SerializedLocation;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.Vector;

import java.util.Map;

/**
 * SuperSkyBros
 * First Created 10/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class LobbyRegion implements ConfigurationSerializable {

    private SerializedLocation max;
    private SerializedLocation min;
    private SerializedLocation spawn;


    /**
     * Creates a Map representation of this class.
     * <p/>
     * This class must provide a method to restore this class, as defined in
     * the {@link org.bukkit.configuration.serialization.ConfigurationSerializable} interface javadocs.
     *
     * @return Map containing the current state of this class
     */
    @Override
    public Map<String, Object> serialize() {

        Location ma = max.getLocation();
        Location mi = min.getLocation();

        boolean check = new Vector().isInAABB(mi.toVector(), ma.toVector());

        return null;
    }

    public LobbyRegion(Map<String, Object> map) {

    }
}
