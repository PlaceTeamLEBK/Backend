package com.placeteam.backend.database;

import com.placeteam.backend.model.database.DBColumn;
import com.placeteam.backend.model.database.DBTable;

public class SQLUtils {
    private static final String SET_PIXEL_SQL = "INSERT INTO pixel (sessionId,x, y, color) VALUES (%s, %d, %d, %s);";
    private static String convertRow(DBColumn row) {
        String tableSQLString = row.getName() + " " + row.getType();
        if (row.isNotnull()) {
            tableSQLString += " NOT NULL";
        }
        if (row.isPk()) {
            tableSQLString += " PRIMARY KEY";
        }
        if (row.isAutoincrement()) {
            tableSQLString += " AUTOINCREMENT";
        }
        if (row.getDflt_value() != null) {
            tableSQLString += " DEFAULT " + row.getDflt_value();
        }

        return tableSQLString;
    }
    public static String createTableSQL(DBTable table) {
        String tableName = table.getTableName();
        DBColumn[] rows = table.getTable();

        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
        for (int i = 0; i < rows.length; i++) {
            DBColumn row = rows[i];
            String tableSQLString = convertRow(row);

            if (i < rows.length - 1) {
                tableSQLString += ", ";
            }

            sql += tableSQLString;
        }
        sql += ");";
        return sql;
    }

    public static String setPixelSQL(String sessionId, int x, int y, String color) {
        if (color.length() > 6) {
            color = color.substring(1, 7);
        }
        String sql = String.format(SET_PIXEL_SQL, sessionId, x, y, color);
        return sql;
    }
}
