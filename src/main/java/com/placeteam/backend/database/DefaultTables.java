package com.placeteam.backend.database;

import com.placeteam.backend.model.database.DBColumn;
import com.placeteam.backend.model.database.DBTable;

public class DefaultTables {
    public DBTable sessionTable() {
        DBTable  table = new DBTable("session");
        DBColumn id = new DBColumn("id", "VARCHAR(64)");
        id.isPk();
        DBColumn created = new DBColumn("create", "DATETIME(6)");
        created.setNotnull(true);
        DBColumn last = new DBColumn("last", "DATETIME(6)");

        DBColumn[] columns = {id, created, last};
        table.addColumns(columns);
        return table;
    }

    public DBTable pixelTable() {
        DBTable  table = new DBTable("pixel");
        DBColumn id = new DBColumn("id", "INTEGER");
        id.setAutoincrement(true);
        id.isPk();
        DBColumn session = new DBColumn("sessionId", "VARCHAR(64)");
        session.setNotnull(true);
        DBColumn x = new DBColumn("x", "INT");
        x.setNotnull(true);
        DBColumn y = new DBColumn("y", "INT");
        y.setNotnull(true);
        DBColumn color = new DBColumn("color", "VARCHAR(64)");
        color.setNotnull(true);
        DBColumn created = new DBColumn("create", "DATETIME(6)");
        created.setNotnull(true);

        DBColumn[] columns = {id, session, x, y, color, created};
        table.addColumns(columns);
        return table;
    }

    public DBTable[] getTables() {
        DBTable[] tables = {sessionTable(), pixelTable()};

        return tables;
    }
}
