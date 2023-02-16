package com.placeteam.backend;

import com.placeteam.backend.database.DatabaseConnector;
import com.placeteam.backend.database.DefaultTables;
import com.placeteam.backend.database.impl.SQLiteDriver;
import com.placeteam.backend.server.Server;

import java.sql.SQLException;

public class Bootstrap {
    private Server server;
    private static DatabaseConnector databaseConnector;
    public Bootstrap() {
        try {
            databaseConnector = new SQLiteDriver();
            getDatabaseConnector().connect();
            getDatabaseConnector().createTables(DefaultTables.getTables());

            server = Server.getInstance();
            databaseConnector = new SQLiteDriver();
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }
    }
    public void start() {
        server.start();
    }
	public static DatabaseConnector getDatabaseConnector() {
		return databaseConnector;
	}
}
