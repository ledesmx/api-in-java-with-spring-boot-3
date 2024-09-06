package codes.ledesma.runnerz.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<Run>();

    public List<Run> getRuns() {
        return runs;
    }

    public Optional<Run> getRunById(Integer id) {
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    public void create(Run run) {
        this.runs.add(run);
    }

    public void delete(Integer id) {
        this.runs.removeIf(run -> run.id() == id);
    }

    @PostConstruct // anotation: calls the method only once, just after the initialization
    private void init() {
        var time = LocalDateTime.now();
        var run = new Run(1, "First run", time, time.plus(1, ChronoUnit.HOURS), 10, Location.INDOOR);
        runs.add(run);

        time = LocalDateTime.now().plus(15, ChronoUnit.HOURS);
        run = new Run(2, "Al cerro", time, time.plus(1, ChronoUnit.HOURS), 10, Location.INDOOR);
        runs.add(run);
    }

}
