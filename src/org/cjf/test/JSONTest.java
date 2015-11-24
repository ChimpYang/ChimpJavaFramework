package org.cjf.test;

import net.sf.json.JSONArray;

import org.junit.Test;

public class JSONTest {
	
	@Test
	public void testArray2String() {
		int[] ids = {1, 2, 3};
		JSONArray jsonArray = JSONArray.fromObject(ids);
		String json = jsonArray.toString();
		
		System.out.println(json);
	}
	
	@Test
	public void testString2Array() {
		String json = "[1,2,3]";
		JSONArray jsonArray = JSONArray.fromObject(json);
		
		Object[] ids = jsonArray.toArray();
		
		for(Object obj: ids) {
			Integer id = (Integer)obj;
			System.out.println(id);
		}
	}
	
}
