package com.placeteam.backend.database.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.placeteam.backend.database.DataValidator;
import com.placeteam.backend.database.DatabaseConnector;
import com.placeteam.backend.database.DatabaseException;
import com.placeteam.backend.database.SQLUtils;
import com.placeteam.backend.model.PaintData;
import com.placeteam.backend.model.STD_VALUES;
import com.placeteam.backend.model.database.DBTable;

public class SQLiteDriver implements DatabaseConnector {
    private static final String GET_KARTE_SQL = "SELECT max(id),x,y,color FROM pixel GROUP BY x,y;";

    private static SQLiteDriver instance;

    private static final String SET_PIXEL_SQL = "INSERT INTO pixel (sessionId,x, y, color) VALUES (?, ?, ?, ?);";

    private static final String SET_VISITOR_SQL = "INSERT INTO visitor (sessionId, addr, ua) VALUES (?, ?, ?);";

    private static final String UPDATE_VISITOR_SQL = "UPDATE visitor SET sessionId = ?, addr = ?, ua = ? WHERE id = ?;";

    private Connection connection;

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    @Override
    public Long setVisitor(Long id, String sessionId, String addr, String ua) throws DatabaseException {
        try {
            if (id == null) {
                PreparedStatement statement = connection.prepareStatement(SET_VISITOR_SQL);
                statement.setString(1, sessionId);
                statement.setString(2, addr);
                statement.setString(3, ua);
                statement.executeUpdate();
                Long newid = null;
                ResultSet rs = statement.getGeneratedKeys();

                if (rs.next()) {
                    newid = rs.getLong(1);
                }
                statement.close();
                return newid;
            } else {
                PreparedStatement statement = connection.prepareStatement(UPDATE_VISITOR_SQL);
                statement.setString(1, sessionId);
                statement.setString(2, addr);
                statement.setString(3, ua);
                statement.setLong(4, id);
                statement.executeUpdate();
                return id;
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}