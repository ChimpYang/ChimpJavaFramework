package org.cjf.test.biz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.cjf.biz.BusinessLogic;
import org.cjf.entity.User;
import org.cjf.test.BizTest;
import org.cjf.utils.properties.AppConst;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration("classpath:resources/test/biz/userContext.xml")
public class UserBizTest { //implements BizTest {
	private static int lastId;
	
	@Resource
	private BusinessLogic<User> userBiz;
//	
//	@Test
//	public void test000Clear() {
//		User t = new User();
//		int count = userBiz.clear(t);
//		
//		System.out.println("Clear count: " + count);
//	}
//	
//	@Test
//	public void test001Create() {
//		User item = new User();
//		item.setUserCode("Chimp");
//		item.setUserName("ChimpYang");
//		item.setPassword("821AADC87069188E01B09E9370FAB81A");
//		item.setUserStatus(1);
//		item.setMailAddr("chimp@126.com");
//		item.setPhoneNumber("02566688888");
//		
//		int count = userBiz.create(item);
//		lastId = item.getId();
//		
//		TestCase.assertEquals(1, count);
//		TestCase.assertNotSame(0, lastId);
//	}
//
//	@Test
//	public void test002GetCount() {
//		int count = userBiz.getCount(new User());
//		TestCase.assertEquals(1, count);		
//	}
//
//	@Test
//	public void test003GetOne() {
//		User item = new User();
//		item.setId(lastId);
//		
//		User one = userBiz.getOne(item);
//		TestCase.assertNotNull(one);
//		TestCase.assertEquals("ChimpYang", one.getUserName());		
//		TestCase.assertEquals("Chimp", one.getUserCode());
//	}
//
//	@Test
//	public void test004GetList() {
//		User item = new User();
//		item.setUserCode("Chimp1");
//		item.setUserName("ChimpYang1");
//		item.setPassword("821AADC87069188E01B09E9370FAB81A");
//		item.setUserStatus(1);
//		item.setMailAddr("chimp1@126.com");
//		item.setPhoneNumber("02566688888");
//		
//		userBiz.create(item);
//		
//		User t = new User();
//		int count = userBiz.getCount(t);
//		List<User> list = userBiz.getList(t, 0, AppConst.DEFAULT_RESULT_LIMIT);
//		
//		TestCase.assertEquals(2, count);
//		TestCase.assertNotNull(list);
//		TestCase.assertEquals(2, list.size());
//		
//		for(User u : list) {
//			System.out.println(u);
//		}
//	};
//	
//
//	@Test
//	public void test005Modify() {
//		User item = new User();
//		item.setId(lastId);
//		item.setUserName("NewName");
//		
//		int count = userBiz.modify(item);
//		TestCase.assertEquals(1, count);
//		
//		User newOne = new User();
//		newOne.setId(lastId);
//		
//		User one = userBiz.getOne(newOne);
//		TestCase.assertNotNull(one);
//		TestCase.assertEquals("NewName", one.getUserName());
//	}
//
//	@Test
//	public void test006Delete() {
//		User item = new User();
//		item.setId(lastId);
//		
//		int count = userBiz.remove(item);
//		TestCase.assertEquals(1, count);
//	}
	
	public void test007BatchCreateSuccess() {
		User item1 = new User();
		item1.setUserCode("Chimp1");
		item1.setUserName("ChimpYang2");
		item1.setPassword("821AADC87069188E01B09E9370FAB81A");
		item1.setCurrentStatus(1);
		
		User item2 = new User();
		item2.setUserCode("Chimp2");
		item2.setUserName("ChimpYang2");
		item2.setPassword("821AADC87069188E01B09E9370FAB81A");
		item2.setCurrentStatus(1);
		
		List<User> list = new ArrayList<User>();
		list.add(item1);
		list.add(item2);
		
		boolean batchCreate = userBiz.batchCreate(list, new User());
		TestCase.assertTrue(batchCreate);
	}
	
	@Test
	public void test008BatchCreateFailed() {
		User item1 = new User();
		item1.setUserCode("Chimp3");
		item1.setUserName("ChimpYang2");
		item1.setPassword("821AADC87069188E01B09E9370FAB81A");
		item1.setCurrentStatus(1);
		
		User item2 = new User();
		item2.setUserCode("Chimp");
		item2.setUserName("ChimpYang");
		item2.setPassword("821AADC87069188E01B09E9370FAB81A");
		item2.setCurrentStatus(1);
		
		List<User> list = new ArrayList<User>();
		list.add(item1);
		list.add(item2);
		
		boolean batchCreate = userBiz.batchCreate(list, new User());
		TestCase.assertTrue(batchCreate);
	}
	
}
