package com.placeteam.backend.model.database;

public class DBColumn {
    private String name;
    private boolean autoincrement;
    private String type;
    private boolean notnull;
    private String dflt_value;
    private boolean pk;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAutoincrement() {
        return autoincrement;
    }

    public void setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isNotnull() {
        return notnull;
    }

    public void setNotnull(boolean notnull) {
        this.notnull = notnull;
    }

    public String getDflt_value() {
        return dflt_value;
    }

    public void setDflt_value(String dflt_value) {
        this.dflt_value = dflt_value;
    }

    public boolean isPk() {
        return pk;
    }

    public void setPk(boolean pk) {
        this.pk = pk;
    }

    public void setNotNull() {
        this.notnull = true;
    }
}
