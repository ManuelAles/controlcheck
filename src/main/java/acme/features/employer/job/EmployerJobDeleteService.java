
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	//	Internal states ------------------

	@Autowired
	private EmployerJobRepository repository;


	// AbstractUpdateService<Employer, Job> interface -----

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
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "descriptor.description", "finalMode");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int jobId;

		jobId = request.getModel().getInteger("id");
		result = this.repository.findJobById(jobId);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// se puede borrar si no tiene applications

		Integer applications;
		applications = this.repository.countApplicationsByJob(entity.getId());
		Boolean hasApplicattions;
		hasApplicattions = applications != 0;
		if (!errors.hasErrors()) {
			errors.state(request, !hasApplicattions, "*", "employer.job.error.applications");
		}

	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		Descriptor descriptor;
		descriptor = entity.getDescriptor();

		int descriptorId;
		descriptorId = descriptor.getId();
		Collection<Duty> duties;
		duties = this.repository.findDutiesByDescriptor(descriptorId);

		Collection<AuditRecord> records = this.repository.findAuditRecordByJob(entity.getId());

		this.repository.deleteAll(records);
		this.repository.delete(entity);
		this.repository.deleteAll(duties);
		this.repository.delete(descriptor);

	}

}
