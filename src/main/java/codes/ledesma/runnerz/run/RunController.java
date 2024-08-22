package codes.ledesma.runnerz.run;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/hello") // anotation: handle the /hello endpoint
    public String home() {
        return "Hello, run";
    }

    @GetMapping("")
    public List<Run> findAll() {
        return this.runRepository.getRuns();
    }

    @GetMapping("/{id}")
    public Run findById(@PathVariable Integer id) {
        return this.runRepository.getRunById(id);
    }
}
