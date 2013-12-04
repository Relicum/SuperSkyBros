package com.relicum.scb;

import com.relicum.scb.utils.consoleColors;
import org.bukkit.plugin.Plugin;

/**
 * SuperSkyBros
 * First Created 04/12/13
 *
 * @author Relicum
 * @version 0.1
 */
public class cMsg implements consoleColors {

    private String prefix;
    private String warning;
    private String serve;
    private boolean useColor = true;
    private boolean useInfo = true;
    private boolean useWarning = true;
    private boolean useServe = true;


    public cMsg(Plugin pl) {
        useColor = pl.getConfig().getBoolean("colorConsole");
        if (!useColor) {
            useColor = false;
            useInfo = false;
            useWarning = false;
            useServe = false;
        }
        if (useColor) {
            useInfo = pl.getConfig().getBoolean("infoColor");
            useWarning = pl.getConfig().getBoolean("warningColor");
            useServe = pl.getConfig().getBoolean("serveColor");
        }

        if (pl.getDescription().getPrefix() != null) {
            prefix = BOLDMAGENTA + "[" + pl.getDescription().getPrefix() + "]" + " " + RESET;
        } else
            prefix = "";
        warning = BOLDGREEN;
        serve = BOLDRED;
    }

    /**
     * Colored console info message
     *
     * @param msg String for info message
     */
    public void INFO(String msg) {
        if (!useColor || !useInfo) {
            noColor(msg);
            return;
        }
        System.out.println(prefix + BOLDYELLOW + msg + RESET);
    }

    /**
     * Colored console warning message
     *
     * @param msg
     */
    public void WARNING(String msg) {
        if (!useColor || !useWarning) {
            noColor(msg);
            return;
        }
        System.out.println(warning + prefix + BOLDGREEN + msg + RESET);
    }

    /**
     * Colored console serve message
     *
     * @param msg
     */
    public void SERVE(String msg) {
        if (!useColor || !useServe) {
            noColor(msg);
            return;
        }
        System.out.println(serve + prefix + BOLDRED + msg + RESET);
    }

    private static void noColor(String msg) {
        System.out.println(msg);
    }

}
