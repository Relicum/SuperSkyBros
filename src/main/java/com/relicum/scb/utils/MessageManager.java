package com.relicum.scb.utils;


import com.relicum.scb.SCB;
import com.relicum.scb.configs.Messages;
import com.relicum.scb.types.SkyBrosApi;
import org.bukkit.ChatColor;

import java.util.Arrays;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class MessageManager {


    private static SCB pl;

    private static Helper hp;

    private Messages messConfig;

    private String prefix;

    private String standardColor;

    private String errorColor;

    private String adminColor;

    private String noPerm;


    /**
     * Used to setup the Message Manager You should not need to ever call this as it is automatically setup in the
     * SettingsManager class.
     *
     * @param p SCB
     */
    public MessageManager() {
        pl = SkyBrosApi.getSCB();
        setup();

    }


    private void setup() {


        hp = Helper.getInstance();
        hp.setup();
        if (hp.fileExists("messages.yml")) {
            try {
                hp.loadFile("messages.yml");
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
                return;
            }
        }
        messConfig = new Messages(pl, "messages.yml");
        messConfig.getConfig().options().copyDefaults(true);
        messConfig.saveDefaultConfig();
        setupMessageColors("system.color");
        setPrefix("system.prefix");
        noPerm = setNoPermMessage();
        pl.getLogger().info("Messages Config Successfully Loaded");

    }


    /**
     * Gets string config.
     *
     * @param String takes a string
     * @return String a string
     */
    public String getStringConfig(String s) {

        return messConfig.getConfig().getString(s);
    }


    private void setupMessageColors(String s) {

        String tmp = getStringConfig(s);
        String[] sp = tmp.split(":");
        standardColor = convertColor(sp[0]);
        errorColor = convertColor(sp[1]);
        adminColor = convertColor(sp[2]);
    }


    /**
     * Gets the Prefix for all messages This has already had the Color codes translated
     *
     * @return String
     */
    public String getPrefix() {
        return prefix;
    }


    /**
     * Gets the prefix and message color for standard messages
     *
     * @return String
     */
    public String standardColor() {

        return prefix + standardColor;
    }


    /**
     * Gets the prefix and message color for error messages
     *
     * @return String
     */
    public String getErrorCol() {
        return prefix + errorColor;
    }


    /**
     * Gets the prefix and message color for admin messages
     *
     * @return String
     */
    public String getAdminCol() {
        return prefix + adminColor;
    }


    private void setPrefix(String s) {
        String pre;
        pre = getStringConfig(s);
        pre = convertColor(pre);
        prefix = pre;
    }


    /**
     * Converts color code formats to the correct character
     *
     * @param s String
     * @return String
     */
    public String convertColor(String s) {

        String tmp;
        tmp = ChatColor.translateAlternateColorCodes('&', s);
        return tmp;
    }


    /**
     * This is used to get a standard message pre-formatted Pass it the message path and it returns a string which You
     * send direct to the player. Handel's all errors automatically.
     *
     * @param s String
     * @return String
     */
    public String getMessage(String s) {
        String tmp = getStringMessageFullyFormatted(s);
        return sendBack(standardColor(), tmp);
    }


    /**
     * This is used to get a admin when admin commands are run it is pre-formatted Pass it the message path and it
     * returns a string which You send direct to the player. Handel's all errors automatically.
     *
     * @param s String
     * @return String
     */
    public String getAdminMessage(String s) {
        String tmp = getStringMessageFullyFormatted(s);
        return sendBack(getAdminCol(), tmp);
    }


    /**
     * Send Error Message
     *
     * @param s String
     * @return String
     */
    public String getErrorMessage(String s) {
        String tmp;
        tmp = getStringMessageFullyFormatted(s);
        return sendBack(getErrorCol(), tmp);
    }


    /**
     * This is used to send the default No Permission Message on commands
     *
     * @return String
     */
    private String setNoPermMessage() {
        String tmp;
        tmp = getStringMessageFullyFormatted("system.noPerm");
        return sendBack(getErrorCol(), tmp);
    }


    /**
     * Returns the user a standard message pre-formatted if they do not have permission to do something.
     *
     * @return String
     */
    public String getNoPerm() {
        return noPerm;
    }


    public String noConsole() {

        String tmp = getStringConfig("system.noConsole");

        return sendBack(stripColor(prefix), stripColor(tmp));
    }


    /**
     * Internal method that does all the checking and formatting of the string
     *
     * @param s String
     * @return String
     */
    private String getStringMessageFullyFormatted(String s) {

        String tmp;
        try {
            if (!isAString(s)) {
                tmp = getStringConfig("internal.messageIsNull");
                tmp = sendBack(getErrorCol(), tmp);
                return tmp;
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
        if (!checkExists(s)) {
            tmp = getStringConfig("internal.messsageNotFound");
            tmp = sendBack(getErrorCol(), tmp);
            return tmp;
        }

        tmp = getStringConfig(s);
        tmp = convertColor(tmp);
        return tmp;

    }


    /**
     * Checks if the requested method actually exists. Returns True if it exists and false if it doesn't
     *
     * @param s String
     * @return boolean
     */
    public boolean checkExists(String s) {

        return messConfig.getConfig().contains(s);

    }


    /**
     * Internal function to see if the method has been based a string True if it is a string and false if it isn't
     *
     * @param s String
     * @return boolean
     * @throws Exception
     */
    public boolean isAString(String s) {

        return !(s == null);

    }


    /**
     * Central function that concatenates the formatted prefix and message text into a single string
     *
     * @param start String
     * @param messa String
     * @return String
     */
    public String sendBack(String start, String messa) {

        return start + messa;
    }


    /**
     * Strips all color formatting from a string
     *
     * @param s String
     * @return String
     */
    public String stripColor(String s) {

        return ChatColor.stripColor(s);
    }


    /**
     * Get raw message.
     *
     * @param String
     * @return String the string
     */
    public String getRawMessage(String s) {

        return messConfig.getConfig().getString(s);
    }

}
