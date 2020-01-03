
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.dashboards.Dashboard;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select d from Dashboard d")
	Dashboard findOne();

	@Query("select (cast(count(x) as float)/(select count(j) from Job j)) from XXXX1 x")
	double ratio1();

	@Query("select (cast(count(x2) as float)/(select count(x1) from XXXX1 x1)) from XXXX2 x2")
	double ratio2();

	@Query("select (cast(count(x) as float)/(select count(a) from Application a)) from XXXX2 x")
	double ratio3();

}
