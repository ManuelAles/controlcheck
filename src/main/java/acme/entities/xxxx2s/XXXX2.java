
package acme.entities.xxxx2s;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.entities.applications.Application;
import acme.entities.xxxx1s.XXXX1;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class XXXX2 extends DomainEntity {

	// Serialisation identifier ---------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -----------------------------------

	@Pattern(regexp = "^(?=.*[a-zA-Z]{8})(?=.*?[0-9]{1})(?=.*?[ºª!\"·$%&/()=?¿¡'|@#~€¬`+´ç.,;:_^*¨Ç}{\\[\\]/-]{1}).{10,}$")
	private String				password;

	private String				text;

	// Relationships -------------------------------------------------------------

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private XXXX1				xxxx1;

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private Application			application;

}
