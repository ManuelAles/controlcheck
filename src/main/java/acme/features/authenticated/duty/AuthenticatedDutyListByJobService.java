
package acme.features.authenticated.duty;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedDutyListByJobService implements AbstractListService<Authenticated, Duty> {

	// Internal interface --------------------

	@Autowired
	AuthenticatedDutyRepository repository;


	// AbstractListService<Authenticated, Duty> interface ------

	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		Boolean result;

		Job job;
		job = this.repository.findJobById(request.getModel().getInteger("id"));

		Date moment;
		moment = new Date();
		result = job.getDeadline().after(moment) && job.isFinalMode();

		return result;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description", "title", "percentage");
	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;

		Collection<Duty> result;
		Job job;

		job = this.repository.findJobById(request.getModel().getInteger("id"));
		result = this.repository.findDutiesByDescriptor(job.getDescriptor().getId());

		return result;
	}

}
