package com.relicum.scb.mini;

import lombok.Getter;
import lombok.ToString;

/**
 * SuperSkyBros First Created 13/11/13
 *
 * @author Relicum
 * @version 0.1
 */
@ToString(doNotUseGetters = true)
public class SignArenaJoin {

    @Getter
    private SerializedLocation signLocation;

    @Getter
    private SerializedLocation spawnLocation;


    public SignArenaJoin(SerializedLocation signLocation, SerializedLocation spawnLocation) {
        this.signLocation = signLocation;
        this.spawnLocation = spawnLocation;
    }
}
