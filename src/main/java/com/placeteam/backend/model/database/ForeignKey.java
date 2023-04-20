package com.placeteam.backend.model.database;

public class ForeignKey {
    private final String foreignColumn;
    private final String foreignKey;
    private final String foreignTable;

    public ForeignKey(String foreignKey, String foreignTable, String foreignColumn) {
        this.foreignKey = foreignKey;
        this.foreignTable = foreignTable;
        this.foreignColumn = foreignColumn;
    }

    public String getForeignColumn() {
        return foreignColumn;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public String getForeignTable() {
        return foreignTable;
    }
}
