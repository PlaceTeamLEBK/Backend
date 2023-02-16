package com.placeteam.backend.database;

import java.sql.SQLException;

public class DatabaseException extends Exception {
    public DatabaseException(String message) {
        super(message);
    }
}
