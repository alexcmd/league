package de.wealthpilot.challenge.league.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Application main settings
 */
@Data
@Component
@ConfigurationProperties("app")
public class AppProperties {
    /**
     * Path to JSON file with teams data
     */
    private String sourceLocation = "classpath:static/soccer_teams.json";
    /**
     * Schedule generation settings and constrains
     */
    private ScheduleSettings scheduleSettings = new ScheduleSettings();
}
