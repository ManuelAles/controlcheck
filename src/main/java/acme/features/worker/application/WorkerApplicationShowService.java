
package acme.features.worker.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Worker;
import acme.entities.xxxx2s.XXXX2;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerApplicationShowService implements AbstractShowService<Worker, Application> {
	//	Internal states ------------------

	@Autowired
	private WorkerApplicationRepository repository;


	// AbstractShowService<Worker, Application> interface -----

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Worker worker;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findApplicationById(applicationId);
		worker = application.getWorker();
		principal = request.getPrincipal();
		result = worker.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "moment", "status", "statement", "skills", "qualifications", "job.title", "rejectedDecision");

		Boolean hasXXXX2 = false;
		XXXX2 xxxx2;
		if (this.repository.XXXX2byApplicationId(entity.getId()) != null) {
			hasXXXX2 = true;
			xxxx2 = this.repository.XXXX2byApplicationId(entity.getId());
			model.setAttribute("text", xxxx2.getText());
			model.setAttribute("password", xxxx2.getPassword());
		}
		model.setAttribute("hasXXXX2", hasXXXX2);

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findApplicationById(id);

		return result;
	}

}
