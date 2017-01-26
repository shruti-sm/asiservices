package com.happiestminds.asi.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author shruti.mishra
 *
 */
public class LoggedInUser {

	private static Map<String, Principal> loginMap;
	static {
		loginMap = new HashMap<String, Principal>();
	}
	
	public Principal addLogin(String authToken, Long empId, String userType) {
		try {
			Principal principal = new Principal(empId, userType);
			loginMap.put(authToken, principal);
			return principal;
		} catch(Exception ex) {
			return null;
		}
	}
	
	public Principal removeLogin(String authToken) {
		return loginMap.remove(authToken);	
	}
	
	public Principal getLogin(String authToken) {
		return loginMap.get(authToken);
	}
	
	public Principal getLogin(String authToken, String userType) {
		
		Principal principal = loginMap.get(authToken);
		if(principal != null && userType.equals(principal.getUserType())) {
			return principal;
		} else {
			return null;
		}
	}
	
	public Map<String, Principal> getLoggedInUsers() {
		return loginMap;
	}
}
