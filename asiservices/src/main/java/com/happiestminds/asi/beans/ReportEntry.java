package com.happiestminds.asi.beans;

import java.io.Serializable;

/**
 * 
 * @author shruti.mishra
 * 
 */
public class ReportEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5290063152908752907L;

	private Long empId;
	private String empCode;
	private String emailId;
	private Long lateCount;

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getLateCount() {
		return lateCount;
	}

	public void setLateCount(Long lateCount) {
		this.lateCount = lateCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((empCode == null) ? 0 : empCode.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result
				+ ((lateCount == null) ? 0 : lateCount.hashCode());
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
		ReportEntry other = (ReportEntry) obj;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
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
		if (lateCount == null) {
			if (other.lateCount != null)
				return false;
		} else if (!lateCount.equals(other.lateCount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReportEntry [empId=" + empId + ", empCode=" + empCode
				+ ", emailId=" + emailId + ", lateCount=" + lateCount + "]";
	}

}
