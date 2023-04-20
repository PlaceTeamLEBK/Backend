package com.placeteam.backend.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placeteam.backend.Bootstrap;
import com.placeteam.backend.database.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/visitors")
public class StaticVisitorsController {

    @GetMapping("/count")
    public int count() {
        DatabaseConnector databaseConnector = Bootstrap.getDatabaseConnector();

        int count = 0;

        try {
            ResultSet result = databaseConnector.executeQuery("SELECT COUNT(id) AS c FROM visitor");

            if (result.next()) {
                count = result.getInt("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}
