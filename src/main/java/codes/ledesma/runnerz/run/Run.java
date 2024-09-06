package codes.ledesma.runnerz.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

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
        @NotEmpty String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive Integer miles,
        Location location) {
}
