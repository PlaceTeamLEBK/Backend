package com.placeteam.backend;

import com.placeteam.backend.database.DatabaseConnector;
import com.placeteam.backend.database.impl.SQLiteDriver;
import com.placeteam.backend.server.Server;

public class Bootstrap {
    private final Server server;
    private final DatabaseConnector databaseConnector;
    public Bootstrap() {
        server = Server.getInstance();
        databaseConnector = new SQLiteDriver();

    }
    public void start() {
        server.start();
    }
}
