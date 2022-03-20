package de.wealthpilot.challenge.league.repository;

import java.io.IOException;

public class SourceAccessException extends RuntimeException {
    public SourceAccessException(String message, Throwable e) {
        super(message, e);
    }
}
