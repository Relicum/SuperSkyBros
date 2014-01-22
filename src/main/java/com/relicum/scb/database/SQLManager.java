package com.relicum.scb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

/**
 * The type SQL manager.
 */
public final class SQLManager {

    /**
     * The Sql settings.
     */
    @Getter
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
}
