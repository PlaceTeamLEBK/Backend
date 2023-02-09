package com.placeteam.backend.model.database;

public class DBTable {
    private String tableName;
    private DBColumn[] table;

    public DBTable(String tableName) {
        this.tableName = tableName;
        this.table = table;
    }

    public String getTableName() {
        return tableName;
    }

    public DBColumn[] getTable() {
        return table;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setTable(DBColumn[] table) {
        this.table = table;
    }

    public void addColumns(DBColumn[] columns) {
        this.table = columns;
    }
}
