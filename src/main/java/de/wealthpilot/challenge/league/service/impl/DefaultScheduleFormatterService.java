package de.wealthpilot.challenge.league.service.impl;

import de.wealthpilot.challenge.league.model.Schedule;
import de.wealthpilot.challenge.league.service.ScheduleFormatterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Default formatter implementation from requirements:
 * Date            Team 1         Team 2
 * 11.12.2018  Erzgebirge Aue     Kreuther Fürth
 * 18.12.2018  Holstein Kiel          Dynamo Dresden
 */
@Slf4j
@Service
public class DefaultScheduleFormatterService implements ScheduleFormatterService {

    private static final String SPACER = "\t\t";
    private static final String HEADER = "Date" + SPACER + "Team 1" + SPACER + "Team 2" + System.lineSeparator();

    @Override
    public String format(Schedule schedule) {
        var builder = new StringBuilder();
        builder.append(HEADER);
        if (schedule != null) {
            for (var item : schedule.getItems()) {
                builder.append(item.getMatchDate());
                builder.append(SPACER);
                builder.append(item.getHomeTeam().getName());
                builder.append(SPACER);
                builder.append(item.getAwayTeam().getName());
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
