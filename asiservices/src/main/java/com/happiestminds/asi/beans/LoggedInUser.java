package com.happiestminds.asi.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author shruti.mishra
 *
 */
public class LoggedInUser {

	private static Map<String, Long> loginMap;
	static {
		loginMap = new HashMap<String, Long>();
	}
	
	public Long addLogin(String authToken, Long empId) {
		try {
			loginMap.put(authToken, empId);
			return empId;
		} catch(Exception ex) {
			return null;
		}
	}
	
	public boolean removeLogin(String authToken) {
		try{
			loginMap.remove(authToken);
			return true;
		} catch(Exception ex) {
			return false;
		}
	}
	
	public Long getLogin(String authToken) {
		return loginMap.get(authToken);
	}
	
	public Map<String, Long> getLoggedInUsers() {
		return loginMap;
	}
}
