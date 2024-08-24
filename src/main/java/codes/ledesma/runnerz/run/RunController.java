package codes.ledesma.runnerz.run;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController // anotation: says that this class responds to requests
@RequestMapping("/api/runs") // Every request mapping falls under this URL
public class RunController {
    private final RunRepository runRepository;

    // Dont instanciate a new RunRepository
    // instead use depencendy injection
    // we ask for the RunRepository instance that is managed by spring boot
    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    // GET

    @GetMapping("")
    public List<Run> findAll() {
        return this.runRepository.getRuns();
    }

    @GetMapping("/{id}")
    public Run findById(@PathVariable Integer id) {
        Optional<Run> run = this.runRepository.getRunById(id);
        if (!run.isPresent()) {
            // To sen error status use this exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return run.get();
    }

    // POST

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void addRun(@RequestBody Run run) {
        try {
            this.runRepository.create(run);
        } catch (Error e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
