package com.placeteam.backend.server;

import com.placeteam.backend.model.stats.MostActiveUser;
import com.placeteam.backend.model.stats.MostUsedColor;
import com.placeteam.backend.stats.StatsServicer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StaticStatsController {

    @GetMapping("/most-used-colors")
    public List<MostUsedColor> mostUsedColors() {
        return StatsServicer.getMostUsedColors();
    }

    @GetMapping("/least-used-colors")
    public List<MostUsedColor> leastUsedColors() {
        return StatsServicer.getLeastUsedColors();
    }

    @GetMapping("/most-active-users")
    public List<MostActiveUser> mostActiveUsers() {
        return StatsServicer.getMostActiveUsers();
    }

    @GetMapping("/least-active-users")
    public List<MostActiveUser> leastActiveUsers() {
        return StatsServicer.getLeastActiveUsers();
    }
}
