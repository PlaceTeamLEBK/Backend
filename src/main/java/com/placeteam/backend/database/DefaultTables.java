package com.placeteam.backend.database;

import com.placeteam.backend.model.database.DBColumn;
import com.placeteam.backend.model.database.DBTable;

public class DefaultTables {

    public static DBTable[] getTables() {
        DBTable[] tables = {
            pixelTable(),
            visitorTable()
        };

        return tables;
    }

    private static DBTable pixelTable() {
        DBTable  table = new DBTable("pixel");
        DBColumn id = new DBColumn("id", "INTEGER");
        id.setAutoincrement(true);
        id.setPk(true);
        DBColumn session = new DBColumn("sessionId", "VARCHAR(64)");
        session.setNotnull(true);
        DBColumn x = new DBColumn("x", "INT");
        x.setNotnull(true);
        DBColumn y = new DBColumn("y", "INT");
        y.setNotnull(true);
        DBColumn color = new DBColumn("color", "CHAR(6)");
        color.setNotnull(true);
        DBColumn created = new DBColumn("created", "DATETIME(6)");
        created.setNotnull(true);
        created.setDflt_value("CURRENT_TIMESTAMP");

        DBColumn[] columns = {id, session, x, y, color, created};
        table.addColumns(columns);
        return table;
    }

    private static DBTable visitorTable() {
        DBTable table = new DBTable("visitor");
        DBColumn id = new DBColumn("id", "INTEGER");
        id.setAutoincrement(true);
        id.setPk(true);
        DBColumn session = new DBColumn("sessionId", "VARCHAR(64)");
        session.setNotnull(true);
        DBColumn ua = new DBColumn("ua", "TEXT");
        ua.setNotnull(false);
        DBColumn addr = new DBColumn("addr", "TEXT");
        addr.setNotnull(false);
        DBColumn created = new DBColumn("created", "DATETIME(6)");
        created.setNotnull(true);
        created.setDflt_value("CURRENT_TIMESTAMP");

        DBColumn[] columns = {id, session, ua, addr, created};
        table.addColumns(columns);
        return table;
    }
}
