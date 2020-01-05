
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.entities.xxxx2s.XXXX2;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerApplicationShowService implements AbstractShowService<Employer, Application> {

	// Internal interface --------------------

	@Autowired
	EmployerApplicationRepository repository;


	// AbstractListService<Employer, Application> interface ------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;

		Application application;
		Integer applicationId;
		applicationId = request.getModel().getInteger("id");
		application = this.repository.findApplicationById(applicationId);

		Employer employer;
		Principal principal;
		employer = application.getJob().getEmployer();
		principal = request.getPrincipal();

		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "moment", "status", "statement", "skills", "qualifications", "worker.userAccount.username", "rejectedDecision");

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
		Integer id;

		id = request.getModel().getInteger("id");
		result = this.repository.findApplicationById(id);

		return result;
	}

}
