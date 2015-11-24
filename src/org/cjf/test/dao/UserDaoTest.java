package org.cjf.test.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.cjf.dao.Dao;
import org.cjf.entity.User;
import org.cjf.test.DaoTest;
import org.cjf.util.CJFDateUtil;
import org.cjf.util.MD5;
import org.cjf.utils.properties.AppConst;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters; 
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.JVM)
@ContextConfiguration("classpath:resources/test/dao/userContext.xml")
public class UserDaoTest implements DaoTest {
	public static int id = 0;
	
	@Resource
	private Dao<User> userDao;
	
	@Test 
	public void test001Clear() {
		User item = new User();
		userDao.clear(item);
		
		int count = userDao.count(item);
		
		TestCase.assertEquals(count, 0);
	}
	
	@Test
	public void test002Add() {
		User item = new User();
		item.setUserCode("admin");
		item.setUserName("管理员");
		item.setPassword(MD5.encode("123456"));
		item.setCurrentStatus(2);
		item.setCreateDate(new Date());
		item.setValidDate(CJFDateUtil.dateWith(2010, 12, 31, 0, 0, 0));
		
		int count = userDao.add(item);
		id = item.getId();
		
		TestCase.assertEquals(count, 1);
		TestCase.assertNotSame(id, 0);
	}
	
	@Test
	public void test003Single() {
		User item = new User();
		item.setId(id);
		item = userDao.single(item);
		
		TestCase.assertNotNull(item);
		TestCase.assertEquals(item.getUserCode(), "admin");
		TestCase.assertEquals(item.getUserName(), "管理员");
		TestCase.assertEquals(item.getCurrentStatus(), 2);
		TestCase.assertEquals(item.getPassword(), MD5.encode("123456"));
		
		
		Date date = CJFDateUtil.dateWith(2010, 12, 31, 0, 0, 0);
		TestCase.assertEquals(date.getTime(), item.getValidDate().getTime());
		TestCase.assertEquals(true, CJFDateUtil.isEqual(date, item.getValidDate()));
	}
	
	@Test
	public void test004Update() {
		User item = new User();
		item.setId(id);
		
		item = userDao.single(item);
		
		item.setUserName("超级管理员");
		item.setPassword(MD5.encode("aaa"));
		item.setCurrentStatus(2);
		
		int count = userDao.update(item);
		TestCase.assertEquals(1, count);
		
		User newItem = new User();
		newItem.setId(id);
		newItem = userDao.single(newItem);
		
		TestCase.assertNotNull(newItem);
		TestCase.assertNotSame(newItem.getUserName(), "超级管理员");
		TestCase.assertNotSame(newItem.getCurrentStatus(), 2);				
		TestCase.assertNotSame(newItem.getPassword(), MD5.encode("aaa"));
	}
	
	@Test
	public void test005List() {
		User item = new User();
		item.setUserCode("Chimp");
		item.setUserName("ChimpYang");
		item.setPassword(MD5.encode("bbb"));
		item.setCurrentStatus(1);
		item.setCreateDate(new Date());
		item.setValidDate(CJFDateUtil.dateWith(2016, 12, 31, 0, 0, 0));
		
		int add = userDao.add(item);
		TestCase.assertEquals(1, add);
		TestCase.assertNotSame(0, item.getId());
		
		User t = new User();
		List<User> list = userDao.list(t, 0, AppConst.DEFAULT_RESULT_LIMIT);
		int count = list.size();
		TestCase.assertEquals(count, 2);
		
		System.out.println("count: " + count);
		
		for(User u : list) {
			System.out.println(u);
		}
	}
	
	@Test
	public void test00501SingleLogin() {
		User item = new User();
		item.setUserCode("Chimp");
		item.setPassword(MD5.encode("bbb"));
		
		User singleCustom = userDao.singleCustom(item, "singleLogin");
		TestCase.assertNotNull(singleCustom);
		TestCase.assertEquals("ChimpYang", singleCustom.getUserName());
		//
		item.setUserCode("Chimp");
		item.setPassword(MD5.encode("ccc"));
		singleCustom = userDao.singleCustom(item, "singleLogin");
		TestCase.assertNull(singleCustom);
		//
		item.setUserCode("test");
		item.setPassword(MD5.encode("bbb"));
		singleCustom = userDao.singleCustom(item, "singleLogin");
		TestCase.assertNull(singleCustom);
	}
	
	@Test
	public void test006Delete() {
//		User item = new User();
//		item.setId(id);
//		
//		int count = userDao.delete(item);
//		TestCase.assertEquals(count, 1);
//		
//		item = userDao.single(item);
//		TestCase.assertNull(item);
	}
}
