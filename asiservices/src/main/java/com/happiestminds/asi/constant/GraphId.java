package com.happiestminds.asi.constant;

/**
 * 
 * @author shruti.mishra
 *
 */
public interface GraphId {

	/**
	 * 1.	Graph for employees who left late for home this week
	   2.	Graph for employees who left late for home for the different projects
	   3.	Graph for employees who left late for home in the past 12 months
	   4.	Graph for employees who left for home late in the past 12 months in the following time brackets(I have made hourly brackets, we can think of making the brackets 2 hourly as well.):
				7pm - 8pm
				8pm - 9pm
				9pm - 10pm
				10pm - 11pm
				11pm - 12pm
				12pm - 1am
				1am - 2am
				2am - 3am
				3am - 4am
				4am - 5am
				5am - 6am
				6am - 7am
				7am and beyond.

	 */
	
	String SEVEN_DAYS = "1";
	String PROJECT = "2";
	String CURRENT_YEAR = "3";
	String CURRENT_YEAR_HOUR = "4";
	
}
