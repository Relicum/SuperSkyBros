package com.relicum.scb.objects.world;

import java.util.List;
import com.relicum.scb.types.SkyApi;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;

/**
 * The type WorldMeta.
 */
public class WorldMeta implements Metadatable {

    /**
     * Instantiates a new World meta.
     */
    public WorldMeta() {
    }


    /**
     * Sets metadata.
     *
     * @param s             the s
     * @param metadataValue the metadata value
     */
    @Override
    public void setMetadata(String s, MetadataValue metadataValue) {

        try {
            this.setMetadata(s, metadataValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SkyApi.getSCB().getLogger().info("New MetaData for world set");

   }


    /**
     * Gets metadata.
     *
     * @param s the s
     * @return the metadata
     * @throws NullPointerException the null pointer exception
     */
    @Override
    public List<MetadataValue> getMetadata(String s) throws NullPointerException {

        if (!this.hasMetadata(s)) {
            List<MetadataValue> l = this.getMetadata(s);
            return l;
        } else return null;
    }


    /**
     * Has metadata.
     *
     * @param s the s
     * @return the boolean true if it does
     */
    @Override
    public boolean hasMetadata(String s) {
        return this.hasMetadata(s);
    }


    /**
     * Remove metadata.
     *
     * @param s      the s
     * @param plugin the plugin
     */
    @Override
    public void removeMetadata(String s, Plugin plugin) {

        try {
            this.removeMetadata(s, plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (this.hasMetadata(s))
            SkyApi.getSCB().getLogger().severe("Unable to remove the MetaData for plugin " + plugin.getName());
 }
}
