package org.cjf.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class CJFDateUtil {
	
	public static Date dateWith(int year, int month, int day, int hour, int min, int second) {
		Calendar c = Calendar.getInstance();
		c.clear(Calendar.MILLISECOND);
		c.set(year, month-1, day, hour, min, second);
		return c.getTime();
	}
	
	public static Date dateWith(int year, int month, int day) {
		return dateWith(year, month, day, 0, 0, 0);
	}
	
	public static boolean isEqual(Date d1, Date d2) {
		Calendar c = Calendar.getInstance();
		
		c.setTime(d1);
		LocalDateTime ldt1 = LocalDateTime.of(
				c.get(Calendar.YEAR),
				c.get(Calendar.MONTH)-1,
				c.get(Calendar.DAY_OF_MONTH),
				c.get(Calendar.HOUR_OF_DAY),
				c.get(Calendar.MINUTE),
				c.get(Calendar.SECOND)
				);
		
		c.setTime(d2);
		LocalDateTime ldt2 = LocalDateTime.of(
				c.get(Calendar.YEAR),
				c.get(Calendar.MONTH)-1,
				c.get(Calendar.DAY_OF_MONTH),
				c.get(Calendar.HOUR_OF_DAY),
				c.get(Calendar.MINUTE),
				c.get(Calendar.SECOND)
				);
		
		return ldt1.isEqual(ldt2);
	}
	
	public static String format(Date date, String template) {
		SimpleDateFormat sdf = new SimpleDateFormat(template);
		return sdf.format(date);
	}
	
	public static String formatyyyyMMddHHmmss(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String formatyyyyMMdd(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public static String formatHHmmss(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(date);
	}
}
