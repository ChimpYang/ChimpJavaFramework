package org.cjf.test.dao;

//import java.util.List;
//
//import javax.annotation.Resource;
//
//import junit.framework.TestCase;
//
//import org.cjf.dao.Dao;
//import org.cjf.entity.Role;
//import org.cjf.test.DaoTest;
//import org.cjf.utils.properties.AppConst;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@FixMethodOrder(MethodSorters.JVM)
//@ContextConfiguration("classpath:resources/test/dao/roleContext.xml")
//public class RoleDaoTest implements DaoTest {
//	public static int id = 0;
//	
//	@Resource
//	private Dao<Role> roleDao;
//	
//	@Test
//	public void test001Clear() {
//		Role item = new Role();
//		roleDao.clear(item);
//		
//		int count = roleDao.count(item);
//		
//		TestCase.assertEquals(count, 0);
//	}
//	
//	@Test
//	public void test002Add() {
//		Role item = new Role();
//		item.setRoleCode("r1");
//		item.setRoleName("rr1");
//		item.setRoleDesc("rrr1");
//		
//		int count = roleDao.add(item);
//		id = item.getId();
//		
//		TestCase.assertEquals(count, 1);
//		TestCase.assertNotSame(id, 0);
//	}
//
//	@Test
//	public void test003Single() {
//		Role item = new Role();
//		item.setId(id);
//		
//		item = roleDao.single(item);
//		
//		TestCase.assertNotNull(item);
//		TestCase.assertEquals(item.getRoleCode(), "r1");
//		TestCase.assertEquals(item.getRoleName(), "rr1");
//		TestCase.assertEquals(item.getRoleDesc(), "rrr1");		
//	}
//
//	@Test
//	public void test004Update() {
//		Role item = new Role();
//		item.setId(id);
//		
//		item = roleDao.single(item);
//		
//		item.setRoleCode("r2");
//		item.setRoleName("rr2");
//		item.setRoleDesc("rrr2");
//		
//		int count = roleDao.update(item);
//		TestCase.assertEquals(1, count);
//		
//		Role newItem = new Role();
//		newItem.setId(id);
//		newItem = roleDao.single(newItem);
//		
//		TestCase.assertNotNull(newItem);
//		TestCase.assertEquals(newItem.getRoleCode(), "r2");
//		TestCase.assertEquals(newItem.getRoleName(), "rr2");
//		TestCase.assertEquals(newItem.getRoleDesc(), "rrr2");
//		
//	}
//
//	@Test
//	public void test005List() {
//		Role item = new Role();
//		item.setRoleCode("r3");
//		item.setRoleName("rr3");
//		item.setRoleDesc("rrr3");
//		
//		roleDao.add(item);
//		
//		Role t = new Role();
//		List<Role> list = roleDao.list(t, 0, AppConst.DEFAULT_RESULT_LIMIT);
//		int count = list.size();
//		TestCase.assertEquals(count, 2);
//		
//		for(Role r : list) {
//			System.out.println(r);
//		}
//	}
//
//	@Test
//	public void test006Delete() {
//		Role item = new Role();
//		item.setId(id);
//		
//		int count = roleDao.delete(item);
//		TestCase.assertEquals(count, 1);
//		
//		item = roleDao.single(item);
//		TestCase.assertNull(item);
//	}
//}
