package com.placeteam.backend;

import java.sql.SQLException;

import com.placeteam.backend.database.DatabaseConnector;
import com.placeteam.backend.database.DefaultTables;
import com.placeteam.backend.database.impl.SQLiteDriver;
import com.placeteam.backend.server.Server;

public class Bootstrap {
    private static DatabaseConnector databaseConnector;
    private static Server server;
    public static DatabaseConnector getDatabaseConnector() {
		return databaseConnector;
	}
    public Bootstrap() {
        try {
            databaseConnector = new SQLiteDriver();
            databaseConnector.connect();
            databaseConnector.createTables(DefaultTables.getTables());

            server = Server.getInstance();
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }
        Thread thread = new Thread(() -> databaseConnector.close());
		Runtime.getRuntime().addShutdownHook(thread);
    }
	public void start() {
        server.start();
    }
}
