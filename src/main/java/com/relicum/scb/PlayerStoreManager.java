package com.relicum.scb;

import com.relicum.scb.types.SkyApi;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO Check if the player 1)Not a new player nd 2) We havn't already stored a copy in the Hashmap
//TODO Check if t5here is already a profile for the player
//TODO If no create a nerw profile and save the defaults to file, each player will have own file
//TODO If yes return the players profile using the contents of the players profile file
//TODO player file needs to be serializable

/**
 * SuperSkyBros
 * First Created 19/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class PlayerStoreManager {

    private Map<String, PlayerSettings> store;
    private List<String> playersLookup;
    private String path;


    public PlayerStoreManager() {
        this.path = SkyApi.getSCB().getDataFolder().toPath().resolve(File.separatorChar + "player" + File.separatorChar + "%N%." + "yml").toString();
        this.store = new HashMap<>();
        this.playersLookup = new ArrayList<>();

    }

    /**
     * Gets path to player file.
     *
     * @param String the name of the player you want to get the file for
     * @return the path including replacing the placeholder with the players name
     */
    public String getPathToPlayerFile(String player) {
        return path.replace("%N%", player);
    }

    public PlayerSettings createNewProfile(String name) {
        if (Files.exists(Paths.get(getPathToPlayerFile(name)))) {

        }
        return null;
    }


    /**
     * Checks to see if player Profile is already loaded by using
     * Lookup List of names. Uses less resource having to keep checking HashMap.
     *
     * @param name the players name
     * @return the boolean true if the ArrayList contains the name, or false if not
     */
    public boolean profileIsInMap(String name) {

        return !(playersLookup.size() == 0 || !playersLookup.contains(name));
    }

    /**
     * Add playerData to Lookup List and HashMap
     *
     * @param playerData instance of playerData belonging to player
     * @return the boolean true if successfully added , false if any exception was thrown
     */
    public boolean addPlayer(PlayerSettings playerData) {

        if (!profileIsInMap(playerData.getPlayerName())) {
            try {
                this.playersLookup.add(playerData.getPlayerName());
                this.store.put(playerData.getPlayerName(), playerData);
            } catch (Exception e) {
                SkyApi.getCMsg().SERVE("Error saving PlayerData to HashMap or LookupList");
                e.printStackTrace();
                return false;
            }
            return true;
        } else
            return false;
    }


    /**
     * Remove player from Lookup List and from HashMap.
     *
     * @param name the players name
     */
    public void removePlayer(String name) {

        this.playersLookup.remove(name);

        try {
            this.store.remove(name);
        } catch (NullPointerException ignore) {

        }
    }

    public boolean savePlayerData(PlayerSettings playerData) {

        playerData.save(getPathToPlayerFile(playerData.getPlayerName()));
        return true;
    }


}
