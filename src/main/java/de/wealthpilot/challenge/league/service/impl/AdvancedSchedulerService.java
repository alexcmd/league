package de.wealthpilot.challenge.league.service.impl;

import de.wealthpilot.challenge.league.config.ScheduleSettings;
import de.wealthpilot.challenge.league.model.Schedule;
import de.wealthpilot.challenge.league.model.ScheduledMatch;
import de.wealthpilot.challenge.league.model.Team;
import de.wealthpilot.challenge.league.repository.TeamsRepository;
import de.wealthpilot.challenge.league.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Scheduler service implementation with round-robin round algorithm
 */
@Slf4j
@Service
@ConditionalOnProperty(value = "app.schedulerType", havingValue = "advanced")
public class AdvancedSchedulerService implements SchedulerService {
    private final TeamsRepository teamsRepo;

    public AdvancedSchedulerService(TeamsRepository teamsRepo) {
        this.teamsRepo = teamsRepo;
    }


    @Override
    public Schedule make(ScheduleSettings settings) {
        var teams = teamsRepo.getAll();

        if (teams.size() < 2) {
            return new Schedule();
        }
        // add zero team with avoiding corrupt incoming data
        if (teams.size() % 2 == 1) {
            teams = new ArrayList<>(teams);
            teams.add(null);
        }

        var items = new ArrayList<ScheduledMatch>();

        //process round 1
        var nextGameDate = fillRound1(teams, items, settings);
        //add break
        nextGameDate = nextGameDate.plusWeeks(settings.getWeeksBreak());
        //process round 2
        fillRound2(items, nextGameDate, settings);

        return Schedule.builder().items(items).build();
    }

    private LocalDate fillRound1(List<Team> teams, ArrayList<ScheduledMatch> items, ScheduleSettings settings) {
        //init vars
        var teamCount = teams.size();
        var homeCount = teamCount / 2;
        var nextMatchDate = settings.getStartDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.of(settings.getMatchWeekDay())));
        // pointer for round-robin algorithm
        int pointer = 1;
        // fix first element for round-robin algorithm
        Team fixed = teams.get(0);
        //process match days
        do {
            fillOneDayMatches(teams, homeCount, nextMatchDate, items, pointer, fixed);
            nextMatchDate = nextMatchDate.plusWeeks(1);
            //round-robin shift
            pointer -= 1;
            if (pointer < 1) {
                pointer = teamCount - 1;
            }
        } while (pointer > 1);

        return nextMatchDate;
    }


    private void fillOneDayMatches(List<Team> teams, int homeCount, LocalDate nextMatchDate, ArrayList<ScheduledMatch> items, int pointer, Team fixed) {
        var cur = pointer;

        var queue = new LinkedList<ScheduledMatch.ScheduledMatchBuilder>();
        // fill home teams and match dates
        queue.addFirst(ScheduledMatch.builder().matchDate(nextMatchDate).homeTeam(fixed));
        for (int i = 1; i < homeCount; i++) {

            queue.addFirst(ScheduledMatch.builder().matchDate(nextMatchDate).homeTeam(teams.get(cur)));
            cur++;
            if (cur >= teams.size()) {
                cur = 1;
            }
        }

        //fill away teams
        for (int i = 0; i < homeCount; i++) {
            var builder = queue.pollFirst();
            if (builder == null) {
                log.error("Queue is empty in position {}", cur);
                throw new IllegalStateException("Queue is empty");
            }
            var awayTeam = teams.get(cur);
            var team = builder.awayTeam(awayTeam).build();
            if (team.getAwayTeam() != null && team.getHomeTeam() != null) {
                items.add(team);
            }
            cur++;
            if (cur >= teams.size()) {
                cur = 1;
            }
        }

    }

    private void fillRound2(ArrayList<ScheduledMatch> items, LocalDate nextGameDate, ScheduleSettings settings) {
        // reverse teams
        var matchCount = items.size();
        if (matchCount == 0)
            return;
        var first = items.get(0).getMatchDate();
        for (int i = 0; i < matchCount; i++) {
            var cur = items.get(i);
            var dateDiff = cur.getMatchDate().toEpochDay() - first.toEpochDay();
            items.add(ScheduledMatch.builder()
                    .matchDate(nextGameDate.plusDays(dateDiff))
                    .homeTeam(cur.getAwayTeam())
                    .awayTeam(cur.getHomeTeam())
                    .build());
        }
    }
}
