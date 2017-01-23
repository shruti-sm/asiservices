package com.happiestminds.asi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author shruti.mishra
 * 
 */
public class CommonUtil {

	public static final String DAYS = "DAYS";
	public static final String HOURSE = "HOURSE";
	public static final String MINUTES = "MINUTES";
	public static final String SECONDS = "SECONDS";
	public static final String MILLISECONDS = "MILLISECONDS";

	public static long findTimeDuration(Date date1, Date date2, String format) {

		// in milliseconds
		long diff = date2.getTime() - date1.getTime();
		if (MILLISECONDS.equals(format)) {
			return diff;
		} else if (MINUTES.equals(format)) {
			return (diff / (60 * 1000) % 60);
		} else if (HOURSE.equals(format)) {
			return (diff / (60 * 60 * 1000) % 24);
		} else if (DAYS.equals(format)) {
			return (diff / (24 * 60 * 60 * 1000));
		} else {
			return (diff / 1000 % 60);
		}
	}

	/*public static void main(String[] args) throws Exception {
		String dateStart = "01/14/2012 23:01:58";
		String dateStop = "01/14/2012 23:31:48";

		// HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		Date d1 = format.parse(dateStart);
		Date d2 = format.parse(dateStop);
		
		System.out.println("date="+d1 + ", date2="+d2);
		
		System.out.println("duration="+ findTimeDuration(d1, d2, MILLISECONDS));
	}*/
}
