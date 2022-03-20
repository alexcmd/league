package de.wealthpilot.challenge.league.service;

import de.wealthpilot.challenge.league.config.ScheduleSettings;
import de.wealthpilot.challenge.league.model.Schedule;


/**
 * Schedule generator service
 */
public interface SchedulerService {


    /**
     * @param settings settings to generate schedule
     * @return Container with list of scheduled matches
     */
    Schedule make(ScheduleSettings settings);
}
