package com.placeteam.backend.model.database;

public class DBTable {
    private DBColumn[] table;
    private String tableName;

    public DBTable(String tableName) {
        this.tableName = tableName;
        this.table = table;
    }

    public void addColumns(DBColumn[] columns) {
        this.table = columns;
    }

    public DBColumn[] getTable() {
        return table;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTable(DBColumn[] table) {
        this.table = table;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
