
package acme.features.authenticated.challenge;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedChallengeShowService implements AbstractShowService<Authenticated, Challenge> {

	//		Internal states ------------------

	@Autowired
	private AuthenticatedChallengeRepository repository;


	// AbstractShowService<Administrator, Announcement> interface -----

	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		Boolean result;

		Challenge a;
		int id;
		id = request.getModel().getInteger("id");
		a = this.repository.findOneById(id);

		Date moment;
		moment = new Date();

		result = a.getDeadline().after(moment);

		return result;
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "goldReward", "silverReward", "bronzeReward", "goldGoal", "silverGoal", "bronzeGoal");

	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;

		Challenge result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
