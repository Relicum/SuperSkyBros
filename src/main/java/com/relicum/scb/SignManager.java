package com.relicum.scb;

import com.relicum.scb.configs.SignConfig;

/**
 * SuperSkyBros First Created 10/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class SignManager {

    /**
     * Stores Sign Config Object
     */
    private SignConfig config;


    public SignManager() {

        setup();
    }


    /**
     * Internal function used to setup SignManager
     */
    private void setup() {

        config = SCB.getInstance().SNC;
        SCB.getInstance().getLogger().info("New Sign Manager has been created");
    }
}
