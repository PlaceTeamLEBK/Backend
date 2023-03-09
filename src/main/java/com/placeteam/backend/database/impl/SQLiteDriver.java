package com.placeteam.backend.database.impl;

import com.placeteam.backend.database.DataValidator;
import com.placeteam.backend.database.DatabaseConnector;
import com.placeteam.backend.database.DatabaseException;
import com.placeteam.backend.database.SQLUtils;
import com.placeteam.backend.model.PaintData;
import com.placeteam.backend.model.STD_VALUES;
import com.placeteam.backend.model.database.DBTable;

import java.sql.*;

public class SQLiteDriver implements DatabaseConnector {
    private static SQLiteDriver instance;

    private Connection connection;

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

    private static final String SET_PIXEL_SQL = "INSERT INTO pixel (sessionId,x, y, color) VALUES (?, ?, ?, ?);";

    @Override
    public void setPixel(String sessionId, int x, int y, String color) throws DatabaseException {
        try {
            DataValidator.checkCoordinate(x, y);
            DataValidator.checkColor(color);

            PreparedStatement statement = connection.prepareStatement(SET_PIXEL_SQL);
            statement.setString(1, sessionId);
            statement.setInt(2, x);
            statement.setInt(3, y);
            statement.setString(4, color);
            statement.executeUpdate();
            statement.close();
        } catch (DatabaseException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private static final String GET_KARTE_SQL = "SELECT max(id),x,y,color FROM pixel GROUP BY x,y;";
    @Override
    public PaintData getKarte() throws DatabaseException {
        try {
            Statement statement = createStatement();
            ResultSet resultSet = statement.executeQuery(GET_KARTE_SQL);
            PaintData karte = new PaintData();
            while (resultSet.next()) {
                int x = resultSet.getInt("x");
                int y = resultSet.getInt("y");
                String color = resultSet.getString("color");
                karte.setPixel(x, y, color);
            }
            return karte;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = createStatement();
        return statement.executeQuery(query);
    }

    @Override
    public void executeUpdate(String query) throws SQLException {
        Statement statement = createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}