package com.happiestminds.asi.constant;


/**
 * 
 * @author shruti.mishra
 *
 */
public interface MessageCode {
	
	
	String SUCCESS = "1";
	String ERROR = "2";
	String UNAUTHORIZED = "Not authorized to access the service";
	
	
	String LOG_INCORRECT_LOGIN = "Incorrect Username/ password";
	String LOG_OUT_TRUE = "true";
	String LOG_OUT_FALSE = "false";

	String DF_SUBMIT_SUCCESS = "Form submitted successfully";
	String DF_SUBMIT_ERROR = "Error in submitting the form";
	String DF_SUBMIT_DUPLICATE = "Form filled today already";
	String DF_UPDATE_SUCCESS = "Form updated successfully";
	String DF_UPDATE_ERROR = "Error in updating the form";

	String EMP_NOT_FOUND = "Employee record not found";

	String GR_INCORRECT_ID = "Incorrect Graph ID";

	String RR_INCORRECT_ID = "Incorrect Report ID";

	/*String LOG_NOT_AUTHORIZED = "LOG1";//Not authorized to access the service
	String LOG_INCORRECT_LOGIN = "LOG2"; //Incorrect Username/ password
	String LOG_OUT_TRUE = "LOG3"; //true
	String LOG_OUT_FALSE = "LOG4"; //false

	String DF_SUBMIT_SUCCESS = "DF1"; //Form submitted successfully
	String DF_SUBMIT_ERROR = "DF2"; //Error in submitting the form
	String DF_SUBMIT_DUPLICATE = "DF3"; //Form filled today already
	String DF_UPDATE_SUCCESS = "DF4"; //Form updated successfully
	String DF_UPDATE_ERROR = "DF5"; //Error in updating the form

	String EMP_NOT_FOUND = "EMP1"; //Employee record not found

	String GR_INCORRECT_ID = "GR1"; //Incorrect Graph ID

	String RR_INCORRECT_ID = "RR1"; //Incorrect Report ID
*/}
