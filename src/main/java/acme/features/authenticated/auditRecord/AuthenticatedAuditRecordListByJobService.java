
package acme.features.authenticated.auditRecord;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuditRecordListByJobService implements AbstractListService<Authenticated, AuditRecord> {

	// Internal interface --------------------

	@Autowired
	AuthenticatedAuditRecordRepository repository;


	// AbstractListService<Authenticated, AuditRecord> interface ------

	@Override
	public boolean authorise(final Request<AuditRecord> request) {
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
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "job.title");
	}

	@Override
	public Collection<AuditRecord> findMany(final Request<AuditRecord> request) {
		assert request != null;

		Collection<AuditRecord> result;
		result = this.repository.findAuditRecordByJob(request.getModel().getInteger("id"));

		return result;
	}

}
