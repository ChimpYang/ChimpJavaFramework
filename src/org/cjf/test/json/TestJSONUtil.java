package org.cjf.test.json;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.cjf.entity.User;
import org.cjf.util.CJFDateUtil;
import org.cjf.util.json.JSONUtil;
import org.junit.Test;

public class TestJSONUtil {
	
	@Test
	public void testUserEntity2JsonString() {
		User u = new User();
		
		u.setId(100);
		u.setUserCode("userCode");
		u.setUserName("userName");
		u.setEmpCode("empCode");
		u.setEmpName("empName");
		u.setLastModifyDate(CJFDateUtil.dateWith(2014, 1, 1, 0, 0, 0));
		u.setCreateDate(CJFDateUtil.dateWith(2015, 10, 1, 0, 0, 0));
		u.setPassword("password");
		u.setCurrentStatus(2);
		u.setUserImage("/aaa/user.jpg");
		u.setValidDate(CJFDateUtil.dateWith(2015, 10, 1, 10, 20, 30));
		
		String jsonString = JSONUtil.objToJsonStr(u, "");
		System.out.println(jsonString);
	}
	
	@Test
	public void testJsonString2UserEntity() {
		String jsonString = "{\"validDate\":\"2015/10/01 10:20:30\",\"userDisabled\":1,\"userName\":\"userName\",\"userCode\":\"userCode\",\"password\":\"password\",\"userImage\":\"/aaa/user.jpg\",\"empCode\":\"empCode\",\"empName\":\"empName\",\"id\":100,\"lastModifyDate\":\"2014/01/01 00:00:00\",\"deptCode\":\"\",\"createDate\":\"2015/10/01 00:00:00\"}";
		User u = JSONUtil.jsonStrToObj(jsonString, new User());
		
		TestCase.assertEquals(100, u.getId());
		TestCase.assertEquals("userCode", u.getUserCode());
		TestCase.assertEquals("userName", u.getUserName());
		TestCase.assertEquals("empCode", u.getEmpCode());
		TestCase.assertEquals("empName", u.getEmpName());
		TestCase.assertEquals(CJFDateUtil.dateWith(2014, 1, 1, 0, 0, 0), u.getLastModifyDate());
		TestCase.assertEquals(CJFDateUtil.dateWith(2015, 10, 1, 0, 0, 0), u.getCreateDate());
		TestCase.assertEquals("password", u.getPassword());
		TestCase.assertEquals(1, u.getCurrentStatus());
		TestCase.assertEquals("/aaa/user.jpg", u.getUserImage());
		TestCase.assertEquals(CJFDateUtil.dateWith(2015, 10, 1, 10, 20, 30), u.getValidDate());
	}
	
	@Test
	public void testJsonString2UserEntityList() {
		List<User> list = new ArrayList<User>();
		
		User u = new User();
		u.setId(100);
		u.setUserCode("userCode");
		u.setUserName("userName");
		u.setEmpCode("empCode");
		u.setEmpName("empName");
		u.setLastModifyDate(CJFDateUtil.dateWith(2014,  1, 1,  0,  0,  0));
//		u.setCreateDate(    CJFDateUtil.dateWith(2015, 10, 1,  0,  0,  0));
		u.setPassword("password");
		u.setCurrentStatus(1);
		u.setUserImage("/aaa/user.jpg");
		u.setValidDate(     CJFDateUtil.dateWith(2015, 10, 1, 10, 20, 30));
		list.add(u);
		
		u = new User();
		u.setId(200);
		u.setUserCode("userCode2");
		u.setUserName("userName2");
		u.setEmpCode("empCode2");
		u.setEmpName("empName2");
		u.setLastModifyDate(CJFDateUtil.dateWith(2014,  1, 2, 0,  0,  0));
//		u.setCreateDate(    CJFDateUtil.dateWith(2015, 10, 2, 0,  0,  0));
		u.setPassword("password2");
		u.setCurrentStatus(1);
		u.setUserImage("/aaa/user2.jpg");
		u.setValidDate(     CJFDateUtil.dateWith(2015, 10, 2, 10, 20, 30));
		list.add(u);
		
		String jsonString = JSONUtil.listToJsonStr(list, "");
		System.out.println(jsonString);
	} 
	
	@Test
	public void testJsonString2List() {
		String jsonString = "[{\"validDate\":\"2015/10/01 10:20:30\",\"userDisabled\":1,\"userName\":\"userName\",\"userCode\":\"userCode\",\"password\":\"password\",\"userImage\":\"/aaa/user.jpg\",\"empCode\":\"empCode\",\"empName\":\"empName\",\"id\":100,\"lastModifyDate\":\"2014/01/01 00:00:00\",\"deptCode\":\"\",\"createDate\":\"2015/10/01 00:00:00\"},{\"validDate\":\"2015/10/02 10:20:30\",\"userDisabled\":1,\"userName\":\"userName2\",\"userCode\":\"userCode2\",\"password\":\"password2\",\"userImage\":\"/aaa/user2.jpg\",\"empCode\":\"empCode2\",\"empName\":\"empName2\",\"id\":200,\"lastModifyDate\":\"2014/01/02 00:00:00\",\"deptCode\":\"\",\"createDate\":\"2015/10/02 00:00:00\"}]";
		
		List<User> list = JSONUtil.jsonStrToList(jsonString, new User());
		
		TestCase.assertNotNull(list);
		TestCase.assertEquals(2, list.size());
		
		User u = list.get(0);
		TestCase.assertEquals("userCode", u.getUserCode());
		TestCase.assertEquals(CJFDateUtil.dateWith(2015, 10, 1, 10, 20, 30), u.getValidDate());
		
		u = list.get(1);
		TestCase.assertEquals("userCode2", u.getUserCode());
		TestCase.assertEquals(CJFDateUtil.dateWith(2015, 10, 2, 10, 20, 30), u.getValidDate());
	}
}
