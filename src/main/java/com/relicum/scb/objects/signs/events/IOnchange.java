package com.relicum.scb.objects.signs.events;

import com.google.common.base.Strings;
import com.relicum.scb.objects.signs.TSign;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;

import java.util.ArrayList;

/**
 * The interface IOnchange.
 */
public interface IOnchange {


    /**
     * @return ArrayList<String>
     */
    ArrayList<String> getLines();

    /**
     * Return Create Permission
     *
     * @return String
     */
    public String getCreatePermission();

    /**
     * Set create permission.
     *
     * @param s the string
     */
    public void setCreatePerm(String s);

    /**
     * Return Create Permission Message
     *
     * @return String
     */
    public String getCreatePermissionMessage();

    /**
     * Set create permission message.
     *
     * @param s the string
     */
    public void setCreatePermMessage(String s);

    /**
     * Set permission prefix.
     *
     * @param s the string
     */
    public void setPermPrefix(String s);

    /**
     * Get create permission prefix.
     *
     * @return the string
     */
    public String getPermPrefix();


    /**
     * Sets sign lines.
     *
     * @param lines the lines
     */
    public void setSignLines(ArrayList<Strings> lines);


}
