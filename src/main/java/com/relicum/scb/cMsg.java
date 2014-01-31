package com.relicum.scb;

import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.consoleColors;
import org.bukkit.configuration.ConfigurationSection;

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
    private boolean useColor;
    private boolean useInfo;
    private boolean useWarning;
    private boolean useServe;


    public cMsg() {
        ConfigurationSection consoleSection = SkyApi.getSCB().getConfig().getConfigurationSection("coloredConsole");
       useColor = consoleSection.getBoolean("colorConsole");

        if (useColor) {
            useInfo = consoleSection.getBoolean("infoColor");
            useWarning = consoleSection.getBoolean("warningColor");
            useServe = consoleSection.getBoolean("serveColor");
        }

        if (!useColor) {
            useColor = false;
            useInfo = false;
            useWarning = false;
            useServe = false;
        }


        if (SkyApi.getSCB().getDescription().getPrefix() != null) {
            prefix = BOLDMAGENTA + "[" + SkyApi.getSCB().getDescription().getPrefix() + "]" + " " + consoleColors.RESET;
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
        System.out.println(prefix + BOLDYELLOW + msg + consoleColors.RESET);
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
        System.out.println(warning + prefix + BOLDGREEN + msg + consoleColors.RESET);
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
        System.out.println(serve + prefix + BOLDRED + msg + consoleColors.RESET);
    }

    private static void noColor(String msg) {
        System.out.println(msg);
    }

}
