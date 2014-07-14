package com.relicum.scb.database;

import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type SQL manager.
 */
public final class SQLManager {

    /**
     * The Sql settings.
     */
    private final SQLSettings sqlSettings;

    private Connection connection;

    /**
     * Instantiates a new SQL manager.
     *
     * @param pl the pl
     */
    public SQLManager(Plugin pl) throws ClassNotFoundException, SQLException {

        this.sqlSettings = new SQLSettings(pl);
        connect();

    }

    public Connection getConnection() {
        return this.connection;
    }

    public void connect() throws SQLException {

        this.connection = DriverManager.getConnection(sqlSettings.getUrl(), sqlSettings.getUsername(), sqlSettings.getPassword());

    }

    public SQLSettings getSqlSettings() {
        return sqlSettings;
    }
}
