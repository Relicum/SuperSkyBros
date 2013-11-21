package com.relicum.scb;

import com.relicum.scb.configs.SignConfig;
import com.relicum.scb.types.SkyBrosApi;

/**
 * SuperSkyBros First Created 10/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class SignManager {


    public SignManager() {

        setup();
    }


    /**
     * Internal function used to setup SignManager
     */
    private void setup() {

        /*
      Stores BaseSign Config Object
     */
        SignConfig config = SkyBrosApi.getSettingsManager2().getSignConfig();
        SCB.getInstance().getLogger().info("New BaseSign Manager has been created");
        System.out.println(config.getConfig());
    }
}
