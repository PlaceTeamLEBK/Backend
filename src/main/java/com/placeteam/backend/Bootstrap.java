package com.placeteam.backend;

import com.placeteam.backend.database.DatabaseConnector;
import com.placeteam.backend.database.DefaultTables;
import com.placeteam.backend.database.impl.SQLiteDriver;
import com.placeteam.backend.server.Server;

import java.sql.SQLException;

public class Bootstrap {
    private Server server;
    private DatabaseConnector databaseConnector;
    public Bootstrap() {
        try {
            databaseConnector = new SQLiteDriver();
            databaseConnector.connect();
            databaseConnector.createTables(DefaultTables.getTables());

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
}
