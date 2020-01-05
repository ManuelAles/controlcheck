
package acme.features.authenticated.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.entities.xxxx1s.XXXX1;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findJobById(int id);

	@Query("select j from Job j where j.deadline > current_timestamp and j.finalMode = true")
	Collection<Job> findAllActive();

	@Query("select x from XXXX1 x where x.job.id = ?1")
	XXXX1 XXXX1byJobId(int id);

}
