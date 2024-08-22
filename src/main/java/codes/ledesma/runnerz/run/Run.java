package codes.ledesma.runnerz.run;

import java.time.LocalDateTime;

/**
 * Create data of a Run.
 * 
 * @param id
 * @param title
 * @param starteOn
 * @param completedOn
 * @param miles
 * @param location
 */
public record Run(
        Integer id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        Integer miles,
        Location location) {
}
