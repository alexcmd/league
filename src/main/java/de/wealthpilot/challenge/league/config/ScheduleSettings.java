package de.wealthpilot.challenge.league.config;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Schedule generation settings and constrains from requirements
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleSettings {
    /**
     * Initial date of season
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate = LocalDate.of(2022, 3, 5);

    /**
     * Break between home-away rounds in weeks
     */
    private int weeksBreak = 3;

    /**
     * Day of week when can play matches
     */
    private int matchWeekDay = 6;
    /**
     * Match begin time at play day
     * Note: not use by requirements
     */
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime matchTime = LocalTime.of(17, 0);
}

