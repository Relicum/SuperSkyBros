package com.relicum.scb.objects.signs.ssb;

import com.relicum.scb.SmashPlayer;
import com.relicum.scb.objects.signs.ESign;
import com.relicum.scb.objects.signs.TSign;
import com.relicum.scb.objects.signs.interfaces.ISigns;
import com.relicum.scb.objects.signs.interfaces.ISubType;
import com.relicum.scb.objects.signs.interfaces.ISuperType;
import com.relicum.scb.objects.signs.interfaces.PSign;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

import java.util.ArrayList;

/**
 * SuperSkyBros First Created 23/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class JoinLobby implements ISigns, ISuperType, ISubType, PSign {

    /**
     * Gets the signs ID
     *
     * @return Integer
     */
    @Override
    public Integer getID() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<String> getLines() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Return Create Permission
     *
     * @return String
     */
    @Override
    public String getCreatePermission() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Return Create Permission Message
     *
     * @return String
     */
    @Override
    public String getCreatePermissionMessage() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Function to create the sign
     *
     * @param SignChangeEvent
     * @return boolean
     */
    @Override
    public boolean createSign(SignChangeEvent e) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Gets Sub type. From the enum ESign
     *
     * @return ESign the sub type of sign
     */
    @Override
    public ESign getType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Gets super type. From the enum TSign
     *
     * @return TSign the super type of sign
     */
    @Override
    public TSign getSuperType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Return Use Permission
     *
     * @return String
     */
    @Override
    public String getUsePermission() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Return Use Permission Message
     *
     * @return String
     */
    @Override
    public String getUserPermissionMessage() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
}
