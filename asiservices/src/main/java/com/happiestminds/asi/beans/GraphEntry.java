package com.happiestminds.asi.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 
 * @author shruti.mishra
 * 
 */
public class GraphEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5038700947982045759L;
	private Object key;
	private Object count;
	public Object getKey() {
		return key;
	}
	public void setKey(Object key) {
		this.key = key;
	}
	public Object getCount() {
		return count;
	}
	public void setCount(Object count) {
		this.count = count;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		GraphEntry other = (GraphEntry) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GraphEntry [key=" + key + ", count=" + count + "]";
	}
	
	
	

}
