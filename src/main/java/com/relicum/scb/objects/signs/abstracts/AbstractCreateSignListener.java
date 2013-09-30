package com.relicum.scb.objects.signs.abstracts;

import com.google.common.base.Strings;
import com.relicum.scb.SCB;
import com.relicum.scb.objects.signs.events.IOnchange;
import com.relicum.scb.objects.signs.events.ISignChangeSignature;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.util.ArrayList;

/**
 * SuperSkyBros First Created 30/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class AbstractCreateSignListener implements IOnchange, ISignChangeSignature, Listener {

    /**
     * Gets lines signature. What we check for ro decide what type of sign The user is making
     *
     * @return the lines signature
     */
    @Override
    public ArrayList<Strings> getLinesSignature() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Function to create the sign
     *
     * @param SignChangeEvent the event
     */
    @Override
    public void createSign(SignChangeEvent e) {
        if (!e.isCancelled()) {
            if (!SCB.getInstance().LBS.isSmashPlayer(e.getPlayer().getName())) {
                return;

            } else {

                if (SCB.perms.has(e.getPlayer(), getCreatePermission()) || e.getPlayer().isOp()) {


                }
            }

        }

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
     * Set create permission.
     *
     * @param s the string
     */
    @Override
    public void setCreatePerm(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
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
     * Set create permission message.
     *
     * @param s the string
     */
    @Override
    public void setCreatePermMessage(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Set permission prefix.
     *
     * @param s the string
     */
    @Override
    public void setPermPrefix(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Get create permission prefix.
     *
     * @return the string
     */
    @Override
    public String getPermPrefix() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Sets sign lines.
     *
     * @param lines the lines
     */
    @Override
    public void setSignLines(ArrayList<Strings> lines) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
