package com.placeteam.backend.database;

import com.placeteam.backend.model.database.DBTable;

import java.sql.SQLException;

public interface DatabaseConnector {

    public void connect() throws SQLException;

    public void createTable(DBTable table) throws SQLException;

    public void createTables(DBTable[] tables) throws SQLException;
}
