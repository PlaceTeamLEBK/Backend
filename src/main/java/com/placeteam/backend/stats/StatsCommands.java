package com.placeteam.backend.stats;

public class StatsCommands {
    public static final String GET_LEAST_ACTIVE_USERS = "SELECT COUNT(id) AS count FROM pixel GROUP BY sessionId ORDER BY count ASC LIMIT 10";

    public static final String GET_LEAST_USED_COLORS = "SELECT color, COUNT(id) AS count FROM pixel GROUP BY color ORDER BY count ASC LIMIT 10";

    public static final String GET_MOST_ACTIVE_USERS = "SELECT COUNT(id) AS count FROM pixel GROUP BY sessionId ORDER BY count DESC LIMIT 10";

    public static final String GET_MOST_USED_COLORS = "SELECT color, COUNT(id) AS count FROM pixel GROUP BY color ORDER BY count DESC LIMIT 10";
}
