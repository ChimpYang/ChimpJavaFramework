package org.cjf.test.dao;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import junit.framework.TestCase;
//
//import org.cjf.dao.Dao;
//import org.cjf.entity.Role;
//import org.cjf.entity.RoleUser;
//import org.cjf.entity.User;
//import org.cjf.test.DaoTest;
//import org.cjf.utils.properties.AppConst;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@FixMethodOrder(MethodSorters.JVM)
//@ContextConfiguration("classpath:resources/test/dao/roleUserContext.xml")
//public class RoleUserDaoTest implements DaoTest {
//	public static int id = 0;
//	
//	@Resource
//	private Dao<RoleUser> ruDao;
//	
//	@Resource
//	private Dao<Role> roleDao;
//	
//	@Resource
//	private Dao<User> userDao;
//	
//	@Test
//	public void test001Clear() {
//		roleDao.clear(new Role());
//		userDao.clear(new User());
//		
//		RoleUser item = new RoleUser();
//		ruDao.clear(item);
//		
//		int count = ruDao.count(item);
//		TestCase.assertEquals(count, 0);
//	}
//
//	@Test
//	public void test002Add() {
//		Role roleA = new Role();
//		roleA.setRoleCode("roleA");
//		roleA.setRoleName("roleA");
//		
//		Role roleB = new Role();
//		roleB.setRoleCode("roleB");
//		roleB.setRoleName("roleB");
//		
//		User userA1 = new User();
//		userA1.setUserCode("userA1");
//		userA1.setUserName("userA1");
//		userA1.setPassword("");
//		
//		User userA2 = new User();
//		userA2.setUserCode("userA2");
//		userA2.setUserName("userA2");
//		userA2.setPassword("");
//		
//		User userB1 = new User();
//		userB1.setUserCode("userB1");
//		userB1.setUserName("userB1");
//		userB1.setPassword("");
//		
//		User userB2 = new User();
//		userB2.setUserCode("userB2");
//		userB2.setUserName("userB2");
//		userB2.setPassword("");
//		
//		User userB3 = new User();
//		userB3.setUserCode("userB3");
//		userB3.setUserName("userB3");
//		userB3.setPassword("");
//		
//		roleDao.add(roleA);
//		roleDao.add(roleB);
//		
//		userDao.add(userA1);
//		userDao.add(userA2);
//		userDao.add(userB1);
//		userDao.add(userB2);
//		userDao.add(userB3);
//		
//		RoleUser item = new RoleUser();
//		item.setRoleCode(roleA.getRoleCode());
//		item.setUserCode(userA1.getUserCode());
//		ruDao.add(item);
//		id = item.getId();
//		
//		item.setRoleCode(roleA.getRoleCode());
//		item.setUserCode(userA2.getUserCode());
//		ruDao.add(item);
//		
//		item.setRoleCode(roleB.getRoleCode());
//		item.setUserCode(userB1.getUserCode());
//		ruDao.add(item);
//		
//		item.setRoleCode(roleB.getRoleCode());
//		item.setUserCode(userB2.getUserCode());
//		ruDao.add(item);
//		
//		item.setRoleCode(roleB.getRoleCode());
//		item.setUserCode(userB3.getUserCode());
//		ruDao.add(item);
//		
//		int count = ruDao.count(new RoleUser());
//		TestCase.assertEquals(count, 5);
//	}
//
//	@Test
//	public void test003Single() {
//		RoleUser item = new RoleUser();
//		item.setId(id);
//		RoleUser ru = ruDao.single(item);
//		TestCase.assertNotNull(ru);
//		TestCase.assertEquals("roleA", ru.getRoleCode());
//		TestCase.assertEquals("userA1", ru.getUserCode());
//	}
//
//	@Test
//	public void test004Update() {
//		//no update function
//	}
//
//	@Test
//	public void test005List() {
//		RoleUser item = new RoleUser();
//		List<RoleUser> list = ruDao.list(item, 0, AppConst.DEFAULT_RESULT_LIMIT);
//		TestCase.assertNotNull(list);
//		TestCase.assertEquals(5, list.size());
//		
//		item.setRoleCode("roleB");
//		List<RoleUser> list2 = ruDao.list(item, 0, AppConst.DEFAULT_RESULT_LIMIT);
//		TestCase.assertNotNull(list2);
//		TestCase.assertEquals(3, list2.size());
//		
//		item.setRoleCode("");
//		item.setUserCode("userA2");
//		List<RoleUser> list3 = ruDao.list(item, 0, AppConst.DEFAULT_RESULT_LIMIT);
//		TestCase.assertNotNull(list3);
//		TestCase.assertEquals(1, list3.size());
//	}
//
//	@Test
//	public void test006Delete() {
//		RoleUser item = new RoleUser();
//		item.setId(id);
//		
//		int count = ruDao.delete(item);
//		TestCase.assertEquals(count, 1);
//		
//		item = ruDao.single(item);
//		TestCase.assertNull(item);
//	}
//	
//	/**
//	 * 在数据库中配置了级联删除
//	 * 删除用户时，应该自动删除对应的角色用户管理关系
//	 */
//	@Test
//	public void test007DeleteUser() {
//		RoleUser ru = new RoleUser();
//		ru.setUserCode("userB3");
//		List<RoleUser> list = ruDao.list(ru, 0, AppConst.DEFAULT_RESULT_LIMIT);
//		TestCase.assertNotNull(list);
//		TestCase.assertEquals(1, list.size());
//		
//		User u = new User();
//		u.setUserCode("userB3");
//		u = userDao.singleCustom(u, "singleByCode");
//		userDao.delete(u);
//		
//		list = ruDao.list(ru, 0, AppConst.DEFAULT_RESULT_LIMIT);
//		TestCase.assertNotNull(list);
//		TestCase.assertEquals(0, list.size());
//	}
//	
//	/**
//	 * 在数据库中配置了级联删除
//	 * 删除角色时，应该自动删除对应的角色用户管理关系
//	 */
//	@Test 
//	public void test008DeleteRole() {
//		RoleUser ru = new RoleUser();
//		ru.setRoleCode("roleA");
//		List<RoleUser> list = ruDao.list(ru, 0, AppConst.DEFAULT_RESULT_LIMIT);
//		TestCase.assertNotNull(list);
//		TestCase.assertEquals(1, list.size());
//		
//		Role r = new Role();
//		r.setRoleCode("roleA");
//		r = roleDao.singleCustom(r, "singleByCode");
//		System.out.println(r);
//		roleDao.delete(r);
//		
//		list = ruDao.list(ru, 0, AppConst.DEFAULT_RESULT_LIMIT);
//		TestCase.assertNotNull(list);
//		TestCase.assertEquals(0, list.size());
//	}
//	
//	@Test
//	public void test009ListUserByRoleCode() {
//		User item = new User();
//		String strParam = "roleB";
//		List<User> list = userDao.listCustomParam(item, "listByRole", strParam, 0, AppConst.DEFAULT_RESULT_LIMIT);
//		
//		TestCase.assertEquals(2, list.size());
//	}
//	
//	@Test
//	public void test010ListRoleByUserCode() {
//		Role item = new Role();
//		String strParam = "userB1";
//		List<Role> list = roleDao.listCustomParam(item, "listByUser",strParam, 0, AppConst.DEFAULT_RESULT_LIMIT);
//		TestCase.assertEquals(1, list.size());
//	}
//
//}
