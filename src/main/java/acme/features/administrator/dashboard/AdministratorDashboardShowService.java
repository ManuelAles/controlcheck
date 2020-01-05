
package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.dashboards.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	//		Internal states ------------------

	@Autowired
	private AdministratorDashboardRepository repository;


	// AbstractShowService<Administrator, Announcement> interface -----

	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Double r1 = 0.0, r2 = 0.0, r3 = 0.0;

		r1 = this.repository.ratio1();
		r2 = this.repository.ratio2();
		r3 = this.repository.ratio3();

		if (r1 != null) {
			entity.setRatio1(r1);
		}

		if (r2 != null) {
			entity.setRatio2(r2);
		}

		if (r3 != null) {
			entity.setRatio3(r3);
		}

		request.unbind(entity, model, "ratio1", "ratio2", "ratio3");

	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;

		result = this.repository.findOne();

		return result;
	}
}
