package de.wealthpilot.challenge.league.service.impl;

import de.wealthpilot.challenge.league.config.ScheduleSettings;
import de.wealthpilot.challenge.league.model.League;
import de.wealthpilot.challenge.league.model.Schedule;
import de.wealthpilot.challenge.league.model.ScheduledMatch;
import de.wealthpilot.challenge.league.model.Team;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class SimpleSchedulerTestCasesHelper {
    private final static Team Team1 = Team.builder().name("Team1").build();
    private final static Team Team2 = Team.builder().name("Team2").build();
    private final static Team Team3 = Team.builder().name("Team3").build();

    public static final Arguments SimpleCase1 = Arguments.of(League.builder()
                    .teams(Arrays.asList(Team1, Team2, Team3))
                    .build(),

            new ScheduleSettings(
                    LocalDate.of(2022, 3, 5),
                    3,
                    6,
                    LocalTime.of(17, 0)),

            Schedule.builder()
                    .items(Arrays.asList(
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 3, 5))
                                    .homeTeam(Team1)
                                    .awayTeam(Team2)
                                    .build(),
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 3, 12))
                                    .homeTeam(Team1)
                                    .awayTeam(Team3)
                                    .build(),
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 3, 19))
                                    .homeTeam(Team2)
                                    .awayTeam(Team3)
                                    .build(),
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 4, 16))
                                    .homeTeam(Team2)
                                    .awayTeam(Team1)
                                    .build(),
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 4, 23))
                                    .homeTeam(Team3)
                                    .awayTeam(Team1)
                                    .build(),
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 4, 30))
                                    .homeTeam(Team3)
                                    .awayTeam(Team2)
                                    .build()

                    ))
                    .build());

    public static final Arguments SimpleCase2 = Arguments.of(League.builder()
                    .teams(Arrays.asList(Team1, Team2))
                    .build(),

            new ScheduleSettings(
                    LocalDate.of(2022, 3, 6),
                    3,
                    6,
                    LocalTime.of(17, 0)),
            Schedule.builder()
                    .items(Arrays.asList(
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 3, 12))
                                    .homeTeam(Team1)
                                    .awayTeam(Team2)
                                    .build(),
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 4, 9))
                                    .homeTeam(Team2)
                                    .awayTeam(Team1)
                                    .build()

                    ))
                    .build());

    public static final Arguments SimpleCase3 = Arguments.of(League.builder()
                    .teams(Arrays.asList(Team1, Team2, Team3))
                    .build(),

            new ScheduleSettings(
                    LocalDate.of(2022, 3, 10),
                    1,
                    1,
                    LocalTime.of(17, 0)),

            Schedule.builder()
                    .items(Arrays.asList(
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 3, 14))
                                    .homeTeam(Team1)
                                    .awayTeam(Team2)
                                    .build(),
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 3, 21))
                                    .homeTeam(Team1)
                                    .awayTeam(Team3)
                                    .build(),
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 3, 28))
                                    .homeTeam(Team2)
                                    .awayTeam(Team3)
                                    .build(),
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 4, 11))
                                    .homeTeam(Team2)
                                    .awayTeam(Team1)
                                    .build(),
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 4, 18))
                                    .homeTeam(Team3)
                                    .awayTeam(Team1)
                                    .build(),
                            ScheduledMatch.builder()
                                    .matchDate(LocalDate.of(2022, 4, 25))
                                    .homeTeam(Team3)
                                    .awayTeam(Team2)
                                    .build()

                    ))
                    .build());
    public static final Arguments SimpleCase4 = Arguments.of(League.builder()
                    .teams(List.of(Team1))
                    .build(),

            new ScheduleSettings(
                    LocalDate.of(2022, 3, 6),
                    3,
                    6,
                    LocalTime.of(17, 0)),
            new Schedule());


}
