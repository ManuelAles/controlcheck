
package acme.entities.dashboards;

import javax.persistence.Entity;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Dashboard extends DomainEntity {

	// Serialisation identifier ---------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -----------------------------------

	private Double				ratio1;

	private Double				ratio2;

	private Double				ratio3;

}
