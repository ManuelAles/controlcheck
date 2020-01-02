
package acme.entities.xxxx1s;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import acme.entities.jobs.Job;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class XXXX1 extends DomainEntity {

	// Serialisation identifier ---------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -----------------------------------

	@NotBlank
	private String				text;

	@URL
	private String				moreInfo;

	// Relationships -------------------------------------------------------------

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private Job					job;

}
