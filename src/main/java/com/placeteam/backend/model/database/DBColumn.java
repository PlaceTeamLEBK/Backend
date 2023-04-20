package com.placeteam.backend.model.database;

public class DBColumn {
    private boolean autoincrement;
    private String dflt_value;
    private String name;
    private boolean notnull;
    private boolean pk;
    private String type;

    public DBColumn(String name, boolean autoincrement, String type, boolean notnull, String dflt_value, boolean pk) {
        this.name = name;
        this.autoincrement = autoincrement;
        this.type = type;
        this.notnull = notnull;
        this.dflt_value = dflt_value;
        this.pk = pk;
    }

    //create constructor without all fields
    public DBColumn(String name, String type) {
        this.name = name;
        this.type = type;
        this.autoincrement = false;
        this.notnull = false;
        this.pk = false;
    }

    public String getDflt_value() {
        return dflt_value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isAutoincrement() {
        return autoincrement;
    }

    public boolean isNotnull() {
        return notnull;
    }

    public boolean isPk() {
        return pk;
    }

    public void setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
    }

    public void setDflt_value(String dflt_value) {
        this.dflt_value = dflt_value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotnull(boolean notnull) {
        this.notnull = notnull;
    }

    public void setNotNull() {
        this.notnull = true;
    }

    public void setPk(boolean pk) {
        this.pk = pk;
    }

    public void setType(String type) {
        this.type = type;
    }
}
