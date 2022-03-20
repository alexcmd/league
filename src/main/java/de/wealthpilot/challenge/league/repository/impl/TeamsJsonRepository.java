package de.wealthpilot.challenge.league.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.wealthpilot.challenge.league.config.AppProperties;
import de.wealthpilot.challenge.league.model.League;
import de.wealthpilot.challenge.league.model.Team;
import de.wealthpilot.challenge.league.repository.SourceAccessException;
import de.wealthpilot.challenge.league.repository.TeamsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.List;

/**
 * Teams JSON data represented repository
 */
@Slf4j
@Repository
public class TeamsJsonRepository implements TeamsRepository {

    private final List<Team> teams;

    public TeamsJsonRepository(AppProperties properties, ObjectMapper mapper) {
        try {
            var data = mapper.readValue(ResourceUtils.getFile(properties.getSourceLocation()), League.class);
            teams = data.getTeams();
        } catch (IOException e) {
            throw new SourceAccessException("Cannot read data from file", e);
        }
    }

    @Override
    public List<Team> getAll() {
        return teams;
    }


}
