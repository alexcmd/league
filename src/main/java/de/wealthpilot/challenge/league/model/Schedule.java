package de.wealthpilot.challenge.league.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Schedule {
    private final List<ScheduledMatch> items;

    public Schedule(List<ScheduledMatch> items) {
        this.items = items;
    }

    public Schedule() {
        this.items = new ArrayList<>();
    }
}
