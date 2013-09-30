package com.relicum.scb.objects.signs.abstracts;

import com.relicum.scb.objects.signs.ESign;
import com.relicum.scb.objects.signs.TSign;
import com.relicum.scb.objects.signs.interfaces.ISubType;
import com.relicum.scb.objects.signs.interfaces.PSign;
import com.relicum.scb.objects.signs.interfaces.SaveAble;

import java.util.UUID;


/**
 * SuperSkyBros First Created 23/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public abstract class JoinSign extends BaseSign implements SaveAble, PSign {

    protected String permPrefix;

    protected String createPerm;

    protected String createPermMessage;

    protected String usePrefix;

    protected String usePerm;

    protected String usePermMessage;

    protected UUID signID;


    public JoinSign(TSign action, ESign joinlobby) {
        super(action, joinlobby);

    }


    /**
     * Return Use Permission
     *
     * @return String
     */
    @Override
    public String getUsePermission() {
        return this.usePrefix + "." + this.usePerm;
    }


    /**
     * Set use permission.
     *
     * @param s the string
     */
    public void setUsePerm(String s) {
        this.usePerm = s;
    }


    /**
     * Return Use Permission Message
     *
     * @return String
     */
    @Override
    public String getUserPermissionMessage() {
        return this.usePermMessage;
    }


    /**
     * Set use perm message.
     *
     * @param s the string
     */
    public void setUsePermMessage(String s) {
        this.usePermMessage = s;
    }


    /**
     * Set use prefix.
     *
     * @param s the string
     */
    public void setUsePrefix(String s) {
        this.usePrefix = s;
    }


    /**
     * Get use prefix.
     *
     * @return the string
     */
    public String getUsePrefix() {
        return this.usePrefix;
    }


    /**
     * Gets the signs ID in a UUID format
     *
     * @return UUID of the sign
     */
    @Override
    public UUID getID() {
        return this.signID;
    }


    /**
     * Set sign iD. as UUID
     *
     * @param id the id
     */
    public void setSignID(UUID id) {
        this.signID = id;
    }

}
