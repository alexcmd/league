package de.wealthpilot.challenge.league.repository;

import de.wealthpilot.challenge.league.model.Team;

import java.util.List;

/**
 * Teams data
 */
public interface TeamsRepository {
    /**
     * @return All available teams in league
     */
    List<Team> getAll();
}
