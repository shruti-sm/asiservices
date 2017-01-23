package com.happiestminds.asi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author shruti.mishra
 * 
 */
@Entity
@Table(name = "declaration_form")
public class DeclarationForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 985491859094422679L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "emp_id", referencedColumnName = "id")
	private Employee employee;

	@NotNull
	@Column(name = "contact_number")
	private Long contactNumber;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	private Project project;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "office_id", referencedColumnName = "id")
	private Office office;

	@Column(name = "leaving_date_time")
	private Date leavingDateTime;

	@Column(name = "expected_arrival_date_time")
	private Date expectedArrivalDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Date getLeavingDateTime() {
		return leavingDateTime;
	}

	public void setLeavingDateTime(Date leavingDateTime) {
		this.leavingDateTime = leavingDateTime;
	}

	public Date getExpectedArrivalDateTime() {
		return expectedArrivalDateTime;
	}

	public void setExpectedArrivalDateTime(Date expectedArrivalDateTime) {
		this.expectedArrivalDateTime = expectedArrivalDateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result
				+ ((employee == null) ? 0 : employee.hashCode());
		result = prime
				* result
				+ ((expectedArrivalDateTime == null) ? 0
						: expectedArrivalDateTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((leavingDateTime == null) ? 0 : leavingDateTime.hashCode());
		result = prime * result + ((office == null) ? 0 : office.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeclarationForm other = (DeclarationForm) obj;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (expectedArrivalDateTime == null) {
			if (other.expectedArrivalDateTime != null)
				return false;
		} else if (!expectedArrivalDateTime
				.equals(other.expectedArrivalDateTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (leavingDateTime == null) {
			if (other.leavingDateTime != null)
				return false;
		} else if (!leavingDateTime.equals(other.leavingDateTime))
			return false;
		if (office == null) {
			if (other.office != null)
				return false;
		} else if (!office.equals(other.office))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeclarationForm [id=" + id + ", employee=" + employee
				+ ", contactNumber=" + contactNumber + ", project=" + project
				+ ", office=" + office + ", leavingDateTime=" + leavingDateTime
				+ ", expectedArrivalDateTime=" + expectedArrivalDateTime + "]";
	}

}
