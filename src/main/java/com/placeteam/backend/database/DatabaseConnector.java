package com.placeteam.backend.database;

import com.placeteam.backend.model.PaintData;
import com.placeteam.backend.model.database.DBTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseConnector {

    void connect() throws SQLException;

    void createTables(DBTable[] tables) throws SQLException;

    void setPixel(String sessionId, int x, int y, String color) throws SQLException, DatabaseException;

    Long setVisitor(Long id, String sessionId, String addr, String ua) throws DatabaseException;

    PaintData getKarte() throws SQLException, DatabaseException;

    ResultSet executeQuery(String query) throws SQLException;

    void executeUpdate(String query) throws SQLException;

    void close();
}
