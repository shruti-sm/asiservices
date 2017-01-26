package com.happiestminds.asi.vo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author shruti.mishra
 * 
 */
public class DeclarationFormDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5942860304235385491L;

	private Long id;

	private Long contactNumber;

	private Long empId;

	private String empCode;

	private String firstName;

	private String lastName;

	private Long projectId;

	private String projectName;

	private Long officeId;

	private String officeName;

	private String leavingDateTime;

	private String expectedArrivalDateTime;

	private String status;

	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getLeavingDateTime() {
		return leavingDateTime;
	}

	public void setLeavingDateTime(String leavingDateTime) {
		this.leavingDateTime = leavingDateTime;
	}

	public String getExpectedArrivalDateTime() {
		return expectedArrivalDateTime;
	}

	public void setExpectedArrivalDateTime(String expectedArrivalDateTime) {
		this.expectedArrivalDateTime = expectedArrivalDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((empCode == null) ? 0 : empCode.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime
				* result
				+ ((expectedArrivalDateTime == null) ? 0
						: expectedArrivalDateTime.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((leavingDateTime == null) ? 0 : leavingDateTime.hashCode());
		result = prime * result
				+ ((officeId == null) ? 0 : officeId.hashCode());
		result = prime * result
				+ ((officeName == null) ? 0 : officeName.hashCode());
		result = prime * result
				+ ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result
				+ ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		DeclarationFormDTO other = (DeclarationFormDTO) obj;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (empCode == null) {
			if (other.empCode != null)
				return false;
		} else if (!empCode.equals(other.empCode))
			return false;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (expectedArrivalDateTime == null) {
			if (other.expectedArrivalDateTime != null)
				return false;
		} else if (!expectedArrivalDateTime
				.equals(other.expectedArrivalDateTime))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (leavingDateTime == null) {
			if (other.leavingDateTime != null)
				return false;
		} else if (!leavingDateTime.equals(other.leavingDateTime))
			return false;
		if (officeId == null) {
			if (other.officeId != null)
				return false;
		} else if (!officeId.equals(other.officeId))
			return false;
		if (officeName == null) {
			if (other.officeName != null)
				return false;
		} else if (!officeName.equals(other.officeName))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeclarationFormDTO [id=" + id + ", contactNumber="
				+ contactNumber + ", empId=" + empId + ", empCode=" + empCode
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", projectId=" + projectId + ", projectName=" + projectName
				+ ", officeId=" + officeId + ", officeName=" + officeName
				+ ", leavingDateTime=" + leavingDateTime
				+ ", expectedArrivalDateTime=" + expectedArrivalDateTime
				+ ", status=" + status + ", remarks=" + remarks + "]";
	}

}
