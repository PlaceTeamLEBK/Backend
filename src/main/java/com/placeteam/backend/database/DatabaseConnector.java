package com.placeteam.backend.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.placeteam.backend.model.PaintData;
import com.placeteam.backend.model.database.DBTable;

public interface DatabaseConnector {

    void close();

    void connect() throws SQLException;

    void createTables(DBTable[] tables) throws SQLException;

    ResultSet executeQuery(String query) throws SQLException;

    void executeUpdate(String query) throws SQLException;

    PaintData getKarte() throws SQLException, DatabaseException;

    void setPixel(String sessionId, int x, int y, String color) throws SQLException, DatabaseException;

    Long setVisitor(Long id, String sessionId, String addr, String ua) throws DatabaseException;
}
