package com.relicum.scb.objects.signs.interfaces;

/**
 * SuperSkyBros First Created 23/09/13 Is the Sign SaveAble if so How is it save and How is it ReLoaded
 *
 * @author Relicum
 * @version 0.1
 */
public interface SaveAble {

    /**
     * Save the sign details boolean.
     *
     * @return boolean the boolean
     */
    boolean save();

    /**
     * Load the sign details boolean.
     *
     * @return the boolean
     */
    boolean load();

}
