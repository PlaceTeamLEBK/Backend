package com.placeteam.backend.database;

import com.placeteam.backend.model.STD_VALUES;

public class DataValidator {
    public static void checkColor(String color) throws DatabaseException {
        if (!checkHexColorString(color)) {
            throw new DatabaseException("Invalid color");
        }
    }

    public static void checkCoordinate(int x, int y) throws DatabaseException {
        boolean bedingung1 = x < 0;
        boolean bedingung2 = x > STD_VALUES.CANVAS_WIDTH;
        boolean bedingung3 = y < 0;
        boolean bedingung4 = y > STD_VALUES.CANVAS_WIDTH;

        if (bedingung1 || bedingung2 || bedingung3 || bedingung4) {
            throw new DatabaseException("Invalid coordinate");
        }
    }

    private static boolean checkHexColorString(String str) {
        if (str == null || str.length() != 7 || str.charAt(0) != '#') {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
                return false;
            }
        }
        return true;
    }
}
