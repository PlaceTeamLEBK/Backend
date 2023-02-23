package com.placeteam.backend.database;

import com.placeteam.backend.model.Karte;
import com.placeteam.backend.model.database.DBTable;

import java.sql.SQLException;

public interface DatabaseConnector {

    public void connect() throws SQLException;

    public void createTables(DBTable[] tables) throws SQLException;

    public void setPixel(String sessionId, int x, int y, String color) throws SQLException, DatabaseException;

    public Karte getKarte() throws SQLException, DatabaseException;

    public void close();
}
