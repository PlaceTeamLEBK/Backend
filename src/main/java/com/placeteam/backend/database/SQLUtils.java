package com.placeteam.backend.database;

import com.placeteam.backend.model.database.DBColumn;
import com.placeteam.backend.model.database.DBTable;
public class SQLUtils {
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
}
