package com.relicum.scb.objects.signs;

import com.relicum.scb.SmashPlayer;
import com.relicum.scb.objects.signs.abstracts.JoinSign;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;

import java.util.ArrayList;

/**
 * SuperSkyBros First Created 29/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class JoinLobbySign extends JoinSign {


    JoinLobbySign() {
        super(TSign.ACTION, ESign.JOINLOBBY);
    }


    /**
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<String> getLines() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Function to create the sign
     *
     * @param SignChangeEvent the event
     */
    @EventHandler
    public void createSign(SignChangeEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Function to create destroy sign
     *
     * @param BlockBreakEvent the event
     */
    @Override
    @EventHandler
    public void destroySign(BlockBreakEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Player Interacts with sign this function deals with it.
     *
     * @param Sign
     * @param SmashPlayer
     * @return boolean
     */
    @Override
    public boolean useSign(Sign sign, SmashPlayer player) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Save the sign details boolean.
     *
     * @return boolean the boolean
     */
    @Override
    public boolean save() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Load the sign details boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean load() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
