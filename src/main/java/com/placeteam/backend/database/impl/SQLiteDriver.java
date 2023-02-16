package com.placeteam.backend.database.impl;

import com.placeteam.backend.database.DatabaseConnector;
import com.placeteam.backend.database.SQLUtils;
import com.placeteam.backend.model.Karte;
import com.placeteam.backend.model.STD_VALUES;
import com.placeteam.backend.model.database.DBTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDriver implements DatabaseConnector {
    private static SQLiteDriver instance;

    private Connection connection;

    public SQLiteDriver() {
        // Connect to sqlite database

    }

    @Override
    public void connect() throws SQLException {
        // Connect to sqlite database
        connection = DriverManager.getConnection("jdbc:sqlite:" + STD_VALUES.DATABASE_NAME);
    }

    private Statement createStatement() throws SQLException {
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.
        return statement;
    }

    @Override
    public void createTables(DBTable[] tables) throws SQLException {
        Statement statement = createStatement();
        for (DBTable table : tables) {
            String sqlStatement = SQLUtils.createTableSQL(table);
           statement.executeUpdate(sqlStatement);
        }
        statement.close();
    }

    @Override
    public void setPixel(String sessionId, int x, int y, String color) throws SQLException {
        Statement statement = createStatement();
        String sqlStatement = SQLUtils.setPixelSQL(sessionId, x, y, color);
        statement.executeUpdate(sqlStatement);
        statement.close();
    }

    @Override
    public Karte getKarte() throws SQLException {
        return null;
    }
}