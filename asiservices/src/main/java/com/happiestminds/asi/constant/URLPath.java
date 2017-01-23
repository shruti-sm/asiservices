package com.happiestminds.asi.constant;

/**
 * Contains all the URL paths
 * 
 * 
 * @author shruti.mishra
 *
 */
public interface URLPath {

	//Employee
	String EMP = "/emp";
	String EMP_FORM = EMP + "/form";
	String EMP_FORM_GET_ALL_FORMS = "/get/all";
	String EMP_FORM_SUBMIT = "/post";
	
	//Login
	String LOGIN = "/authenticate";
	String LOGOUT = "/logout";
	
	//Dashboard
	String DASHBOARD = "/requests";
}
