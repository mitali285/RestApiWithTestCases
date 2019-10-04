/**
 * 
 */
package com.yash;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Mitali
 *
 */
@Entity
@Table(name = "Employees")
@EntityListeners(AuditingEntityListener.class)
public class Employee  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String designtion;
	// @NotBlank
	// private String expertise;
	// @NotBlank
	// private Date createdAt;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(Long id, @NotBlank String name, @NotBlank String designtion) {
		super();
		this.id = id;
		this.name = name;
		this.designtion = designtion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesigntion() {
		return designtion;
	}

	public void setDesigntion(String designtion) {
		this.designtion = designtion;
	}

	// public String getExpertise() {
	// return expertise;
	// }
	//
	// public void setExpertise(String expertise) {
	// this.expertise = expertise;
	// }
	//
	// public Date getCreatedAt() {
	// return createdAt;
	// }
	//
	// public void setCreatedAt(Date createdAt) {
	// this.createdAt = createdAt;
	// }

}
