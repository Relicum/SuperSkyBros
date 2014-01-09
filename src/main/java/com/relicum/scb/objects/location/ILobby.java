package com.relicum.scb.objects.location;

import com.relicum.scb.objects.LocationType;
import com.relicum.scb.utils.SerializedLocation;
import org.bukkit.util.Vector;

/**
 * SuperSkyBros
 * First Created 01/01/14
 *
 * @author Relicum
 * @version 0.1
 */
public interface ILobby {

    Vector getMinVector();

    Vector getMaxVector();

    SerializedLocation getSpawn();

    String getLobbyPermission();

    LocationType getLobbyType();

    LobbyStatus getStatus();

    boolean canFly();

    boolean canBuild();

    boolean isInLobby(String name);

}
