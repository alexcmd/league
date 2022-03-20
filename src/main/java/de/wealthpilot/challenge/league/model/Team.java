package de.wealthpilot.challenge.league.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Team definition
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Team {
    private String name;
    @JsonProperty("founding_date")
    private String foundingDate;
}