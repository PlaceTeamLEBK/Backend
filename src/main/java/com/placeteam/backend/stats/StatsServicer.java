package com.placeteam.backend.stats;

import com.placeteam.backend.Bootstrap;
import com.placeteam.backend.database.DatabaseConnector;
import com.placeteam.backend.model.stats.MostActiveUser;
import com.placeteam.backend.model.stats.MostUsedColor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatsServicer {
    private static final DatabaseConnector databaseConnector = Bootstrap.getDatabaseConnector();

    public static List<MostUsedColor> getMostUsedColors() {
        String query = StatsCommands.GET_MOST_USED_COLORS;
        return getUsedColors(query);
    }

    public static List<MostUsedColor> getLeastUsedColors() {
        String query = StatsCommands.GET_LEAST_USED_COLORS;
        return getUsedColors(query);
    }

    public static List<MostActiveUser> getMostActiveUsers() {
        String query = StatsCommands.GET_MOST_ACTIVE_USERS;
        return getActiveUsers(query);
    }

    public static List<MostActiveUser> getLeastActiveUsers() {
        String query = StatsCommands.GET_LEAST_ACTIVE_USERS;
        return getActiveUsers(query);
    }
    private static List<MostUsedColor> getUsedColors(String query) {
        try {
            ResultSet result = databaseConnector.executeQuery(query);

            List<MostUsedColor> mostUsedColors = new ArrayList<>();
            while (result.next()) {
                String color = result.getString("color");
                int count = result.getInt("count");
                MostUsedColor mostUsedColor = new MostUsedColor(color, count);
                mostUsedColors.add(mostUsedColor);
            }
            return mostUsedColors;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static List<MostActiveUser> getActiveUsers(String query) {
        try {
            ResultSet result = databaseConnector.executeQuery(query);

            List<MostActiveUser> mostActiveUsers = new ArrayList<>();
            while (result.next()) {
                int count = result.getInt("count");
                MostActiveUser mostActiveUser = new MostActiveUser(count);
                mostActiveUsers.add(mostActiveUser);
            }
            return mostActiveUsers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
