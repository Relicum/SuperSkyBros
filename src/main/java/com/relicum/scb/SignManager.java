package com.relicum.scb;

import com.relicum.scb.configs.SignConfig;
import com.relicum.scb.types.SkyApi;

/**
 * SuperSkyBros First Created 10/09/13
 * 
 * @author Relicum
 * @version 0.1
 */
public class SignManager {

    private SignConfig config;

    public SignManager() {

        setup();
    }

    /**
     * Internal function used to setup SignManager
     */
    private void setup() {

        /*
         * Stores BaseSign Config Object
         */
        config = SkyApi.getSm().getSignConfig();
        SkyApi.getSCB().getLogger().info("New BaseSign Manager has been created");

    }
}
