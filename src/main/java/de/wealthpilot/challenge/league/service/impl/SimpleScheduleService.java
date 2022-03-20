package de.wealthpilot.challenge.league.service.impl;

import de.wealthpilot.challenge.league.config.ScheduleSettings;
import de.wealthpilot.challenge.league.model.Schedule;
import de.wealthpilot.challenge.league.model.ScheduledMatch;
import de.wealthpilot.challenge.league.repository.TeamsRepository;
import de.wealthpilot.challenge.league.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;


/**
 * Schedule service implementation with simple algorithm: one match per day
 */
@Slf4j
@Service
@ConditionalOnProperty(value = "app.schedulerType", matchIfMissing = true, havingValue = "simple")
public class SimpleScheduleService implements SchedulerService {

    private final TeamsRepository teamsRepo;

    public SimpleScheduleService(TeamsRepository teamsRepo) {
        this.teamsRepo = teamsRepo;
    }

    @Override
    public Schedule make(ScheduleSettings settings) {
        var teams = teamsRepo.getAll();
        if (teams.size() < 2) {
            return new Schedule();
        }

        var nextMatchDate = settings.getStartDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.of(settings.getMatchWeekDay())));
        var teamCount = teams.size();
        var items = new ArrayList<ScheduledMatch>();
        for (int i = 0; i < teamCount - 1; i++) {
            for (int j = i + 1; j < teamCount; j++) {
                items.add(ScheduledMatch.builder()
                        .homeTeam(teams.get(i))
                        .awayTeam(teams.get(j))
                        .matchDate(nextMatchDate)
                        .build()
                );
                nextMatchDate = nextMatchDate.plusWeeks(1);
            }
        }
        nextMatchDate = nextMatchDate.plusWeeks(settings.getWeeksBreak());
        var matchCount = items.size();
        for (int i = 0; i < matchCount; i++) {
            var cur = items.get(i);
            items.add(ScheduledMatch.builder()
                    .matchDate(nextMatchDate)
                    .homeTeam(cur.getAwayTeam())
                    .awayTeam(cur.getHomeTeam())
                    .build());
            nextMatchDate = nextMatchDate.plusWeeks(1);
        }


        return Schedule.builder()
                .items(items)
                .build();

    }
}
