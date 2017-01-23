package com.happiestminds.asi.beans;

import java.io.Serializable;

/**
 * 
 * @author shruti.mishra
 * 
 */
public class Principal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -194981151396436183L;

	private Long id;
	private String userType;
	
	/*public Principal() {
	}
	*/
	public Principal(Long id, String userType) {
		super();
		this.id = id;
		this.userType = userType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((userType == null) ? 0 : userType.hashCode());
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
		Principal other = (Principal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (userType == null) {
			if (other.userType != null)
				return false;
		} else if (!userType.equals(other.userType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Principal [id=" + id + ", userType=" + userType + "]";
	}

}
