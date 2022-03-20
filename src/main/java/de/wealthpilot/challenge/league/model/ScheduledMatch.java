package de.wealthpilot.challenge.league.model;

import lombok.*;

import java.time.LocalDate;

/**
 * Scheduled match definition
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ScheduledMatch {
    private LocalDate matchDate;
    private Team homeTeam;
    private Team awayTeam;
}
