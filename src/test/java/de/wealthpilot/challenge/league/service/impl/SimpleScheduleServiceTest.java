package de.wealthpilot.challenge.league.service.impl;

import de.wealthpilot.challenge.league.config.ScheduleSettings;
import de.wealthpilot.challenge.league.model.League;
import de.wealthpilot.challenge.league.model.Schedule;
import de.wealthpilot.challenge.league.repository.TeamsRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static de.wealthpilot.challenge.league.service.impl.SimpleSchedulerTestCasesHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SimpleScheduleServiceTest {
    @Mock
    private TeamsRepository repository;

    /**
     * Simple test, not cover all cases
     *
     * @return Positive TestCases
     */
    public static Stream<Arguments> testData() {
        return Stream.of(
                SimpleCase1, SimpleCase2, SimpleCase3, SimpleCase4
        );
    }


    @ParameterizedTest
    @MethodSource("testData")
    void makeTest(League input, ScheduleSettings settings, Schedule expected) {
        initRepo(input);
        var scheduler = new SimpleScheduleService(repository);
        var actual = scheduler.make(settings);
        assertEquals(expected, actual);
    }

    private void initRepo(League input) {
        Mockito.when(repository.getAll()).thenReturn(input.getTeams());
    }
}