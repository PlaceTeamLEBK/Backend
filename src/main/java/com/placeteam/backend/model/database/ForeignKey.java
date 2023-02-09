package com.placeteam.backend.model.database;

public class ForeignKey {
    private String foreignKey;
    private String foreignTable;
    private String foreignColumn;

    public ForeignKey(String foreignKey, String foreignTable, String foreignColumn) {
        this.foreignKey = foreignKey;
        this.foreignTable = foreignTable;
        this.foreignColumn = foreignColumn;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public String getForeignTable() {
        return foreignTable;
    }

    public String getForeignColumn() {
        return foreignColumn;
    }
}
