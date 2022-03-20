package de.wealthpilot.challenge.league.model;

import java.util.List;

import lombok.*;

/**
 * League definition and teams container
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class League {
    /**
     * League residence country
     */
    private String country;
    /**
     * League name
     */
    private String league;

    /**
     * Leas of teams
     */
    private List<Team> teams;


}