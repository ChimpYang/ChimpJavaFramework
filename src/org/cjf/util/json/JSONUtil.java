package org.cjf.util.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

public class JSONUtil {
	
	static {
		String[] dateFormats = new String[] {"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss","dd/MM/yyyy"};   
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpherEmpty(dateFormats));
	}
	
	public static <T> String listToJsonStr(List<T> list, String emptyStr) {
		if(list == null){
			return emptyStr;
		}
		
		JSONArray ja = null;
		try {
			JsonConfig jc = new JsonConfig();
			jc.registerJsonValueProcessor(java.util.Date.class, new DateJsonToStringProcessor("yyyy/MM/dd HH:mm:ss"));
			ja  = JSONArray.fromObject(list, jc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ja.toString();
	}
	
	public static <T> String objToJsonStr(T t, String emptyStr) {
		if(t == null){
			return emptyStr;
		}
		
		JSONObject jo = null;
		try {
			JsonConfig jc = new JsonConfig();
			jc.registerJsonValueProcessor(java.util.Date.class, new DateJsonToStringProcessor("yyyy/MM/dd HH:mm:ss"));
			jo  = JSONObject.fromObject(t, jc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	public static <T> List<T> jsonStrToList(String jsonString, T t){
		if(null == jsonString || "".equals(jsonString)) {
			return null;
		}
		
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		@SuppressWarnings("unchecked")
		Collection<T> ct = JSONArray.toCollection(jsonArray, t.getClass());
		List<T> list = new ArrayList<T>(ct);
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T jsonStrToObj(String jsonString, T t){
		if(null == jsonString || "".equals(jsonString)) {
			return null;
		}
		
		T t1 = null;
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		t1 = (T)JSONObject.toBean(jsonObject, t.getClass());
		
		return t1;
	}
}
