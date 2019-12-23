
package acme.features.authenticated.request;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Request;
import acme.framework.components.Model;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedRequestShowService implements AbstractShowService<Authenticated, Request> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedRequestRepository repository;


	// AbstractListService<Administrator, Request> interface ---------------

	@Override
	public boolean authorise(final acme.framework.components.Request<Request> request) {
		assert request != null;

		Boolean result;

		Request a;
		int id;
		id = request.getModel().getInteger("id");
		a = this.repository.findOneById(id);

		Date moment;
		moment = new Date();

		result = a.getDeadline().after(moment);

		return result;
	}

	@Override
	public void unbind(final acme.framework.components.Request<Request> request, final Request entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title", "deadline", "text", "reward", "ticker");
	}

	@Override
	public Request findOne(final acme.framework.components.Request<Request> request) {
		assert request != null;

		Request result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
