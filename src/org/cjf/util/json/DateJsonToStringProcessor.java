package org.cjf.util.json;

import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonToStringProcessor implements JsonValueProcessor {
	private String format;
	
	public DateJsonToStringProcessor(String format){
		this.format = format;
	}
	
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		String[] dateString = null;
		
		if (value == null) {
			return "1970/01/01 00:00:00";
		}
		
		if (value instanceof Date[]) {
			SimpleDateFormat sdf  = new SimpleDateFormat(format);
			Date[] dates = (Date[]) value;
			dateString = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				dateString[i] = sdf.format(dates[i]);
			}
		}
		return dateString;
	}
	
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		String dateString = null;
		if (value == null) {
			return "1970/01/01 00:00:00";
		}
		if (value instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = (Date) value;
			dateString = sdf.format(date);
		}
		return dateString;
	}

	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}

}
