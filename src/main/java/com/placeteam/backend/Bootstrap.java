package com.placeteam.backend;

import com.placeteam.backend.database.DatabaseConnector;
import com.placeteam.backend.database.DefaultTables;
import com.placeteam.backend.database.impl.SQLiteDriver;
import com.placeteam.backend.server.Server;

import java.sql.SQLException;

public class Bootstrap {
    private static Server server;
    private static DatabaseConnector databaseConnector;
    public Bootstrap() {
        try {
            databaseConnector = new SQLiteDriver();
            databaseConnector.connect();
            databaseConnector.createTables(DefaultTables.getTables());

            server = Server.getInstance();
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        } finally {
            databaseConnector.close();
        }
    }
    public void start() {
        server.start();
    }
	public static DatabaseConnector getDatabaseConnector() {
		return databaseConnector;
	}
}
