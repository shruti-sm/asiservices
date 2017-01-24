package com.happiestminds.asi.constant;

/**
 * Contains all the URL paths
 * 
 * 
 * @author shruti.mishra
 *
 */
public interface URLPath {

	//Declration Forms
	String EMP = "/emp";
	String EMP_FORM = EMP + "/form";
	String EMP_FORM_GET_ALL_FORMS = "/get/all";
	String EMP_FORM_SUBMIT = "/post";
	String EMP_FORM_TODAY = "/get/today";
	
	//Login
	String LOGIN = "/authenticate";
	String LOGOUT = "/logout";
	
	//Dashboard
	String DASHBOARD = "/dashbaord";
	
	//Graph
	String GRAPH = "graphs";
	//Reports
	String REPORT = "reports";
	String REPORT_EMP = "emp";
}
