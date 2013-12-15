package com.relicum.scb.arena;

import com.relicum.scb.objects.IRegion;
import com.relicum.scb.utils.SerializedLocation;
import org.bukkit.util.Vector;

/**
 * Stores region details for arena likely used also for anything region related
 *
 * @author Relicum
 * @version 0.1
 */
public class ArenaRegion extends IRegion {


    public ArenaRegion(Vector min, Vector max, Vector ad, Integer i, String wo, String mn, SerializedLocation lmax, SerializedLocation lmin, SerializedLocation lad) {
        super(min, max, ad, i, wo, mn, lmax, lmin, lad);
    }
}
