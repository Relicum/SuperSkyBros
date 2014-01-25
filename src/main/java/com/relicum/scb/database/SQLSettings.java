package com.relicum.scb.database;

import lombok.ToString;
import org.bukkit.plugin.Plugin;

/**
 * Stores details of MySQL connection settings First Created 10/01/14
 * 
 * @author Relicum
 * @version 0.1
 */
@ToString
public final class SQLSettings {
    /**
     * The Host.
     */
    private final String host;
    /**
     * The Username.
     */
    private final String username;
    /**
     * The Password.
     */
    private final String password;
    /**
     * The Dbname.
     */
    private final String dbname;
    /**
     * The Port.
     */
    private final String port;
    /**
     * The Connector.
     */
    private final String connector = "jdbc:mysql://";
    /**
     * The Url.
     */
    private final String url;

    /**
     * Instantiates a new SQL settings.
     * 
     * @param pl the main plugin class
     */
    public SQLSettings(Plugin pl) {

        this.host = pl.getConfig().getString("database.host");
        this.username = pl.getConfig().getString("database.username");
        this.password = pl.getConfig().getString("database.password");
        this.dbname = pl.getConfig().getString("database.dbname");
        this.port = pl.getConfig().getString("database.port");
        this.url = connector + host + ":" + port + "/" + dbname;

    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDbname() {
        return dbname;
    }

    public String getPort() {
        return port;
    }

    public String getConnector() {
        return connector;
    }

    public String getUrl() {
        return url;
    }
}
