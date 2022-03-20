package de.wealthpilot.challenge.league;

import de.wealthpilot.challenge.league.config.AppProperties;
import de.wealthpilot.challenge.league.service.ScheduleFormatterService;
import de.wealthpilot.challenge.league.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final SchedulerService scheduler;
    private final ScheduleFormatterService formatter;
    private final AppProperties properties;

    public Application(SchedulerService scheduler, ScheduleFormatterService formatter, AppProperties properties) {
        this.scheduler = scheduler;
        this.formatter = formatter;
        this.properties = properties;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        var schedule = scheduler.make(properties.getScheduleSettings());
        var text = formatter.format(schedule);
        System.out.println(text);
    }
}
