
package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.entities.xxxx1s.XXXX1;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerJobShowService implements AbstractShowService<Employer, Job> {

	//		Internal states ------------------

	@Autowired
	private EmployerJobRepository repository;


	// AbstractShowService<Employer, Job> interface -----

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "descriptor.description", "finalMode");

		int numberOfApplications = 0;
		numberOfApplications = this.repository.countApplicationsByJob(entity.getId());
		model.setAttribute("applications", numberOfApplications);

		Boolean hasXXXX1 = false;
		XXXX1 xxxx1;
		if (this.repository.XXXX1byJobId(entity.getId()) != null) {
			hasXXXX1 = true;
			xxxx1 = this.repository.XXXX1byJobId(entity.getId());
			model.setAttribute("text", xxxx1.getText());
			model.setAttribute("moreInfox", xxxx1.getMoreInfo());
		}
		model.setAttribute("hasXXXX1", hasXXXX1);

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findJobById(id);

		return result;
	}
}
