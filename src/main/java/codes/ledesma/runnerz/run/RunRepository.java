package codes.ledesma.runnerz.run;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class RunRepository {
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> getRuns() {
        return this.jdbcClient.sql("SELECT * FROM Run")
                .query(Run.class)
                .list();
    }

    public Optional<Run> getRunById(Integer id) {
        // We us :id to insert a param in the query and then use the param() method
        return this.jdbcClient.sql("SELECT id,title,started_on,completed_on,miles,location FROM Run WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run) {
        // it returns how many rows were affected
        int updated = this.jdbcClient
                .sql("INSERT INTO Run(id,title,started_on,completed_on,miles,location) values(?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(),
                        run.location().toString()))
                .update();

        Assert.state(updated == 1, "Failed to create run " + run.title());
    }

    public void update(Run run, Integer id) {
        int updated = this.jdbcClient
                .sql("UPDATE Run SET title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? WHERE id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(),
                        id))
                .update();

        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    public void delete(Integer id) {
        int updated = this.jdbcClient
                .sql("DELETE FROM Run WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete run with id = " + id);
    }
}
