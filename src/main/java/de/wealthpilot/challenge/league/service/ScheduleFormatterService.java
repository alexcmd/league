package de.wealthpilot.challenge.league.service;

import de.wealthpilot.challenge.league.model.Schedule;


/**
 * Schedule formatter
 */
public interface ScheduleFormatterService {

    /**
     * @param schedule Schedule to format
     * @return Formated string of schedule
     */
    String format(Schedule schedule);
}
