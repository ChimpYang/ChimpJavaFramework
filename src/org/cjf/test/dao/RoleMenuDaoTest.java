package org.cjf.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.cjf.dao.Dao;
import org.cjf.entity.RoleUser;
import org.cjf.utils.properties.AppConst;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration("classpath:resources/test/dao/roleContext.xml")
public class RoleMenuDaoTest {
	
	@Resource
	private Dao<RoleUser> roleUserDao;
	
	@Test
	public void testListUserInRole() {
		RoleUser t = new RoleUser();
		t.setRoleCode("roleHalo");
		List<RoleUser> list = roleUserDao.listCustom(t, "listUserInRole", 0, AppConst.DEFAULT_RESULT_LIMIT);
		for(RoleUser item : list) {
			System.out.println(item.getUserName() + ", " + item.getRoleCode());
		}
	}
	
	@Test
	public void testListUserNotInRole() {
		RoleUser t = new RoleUser();
		t.setRoleCode("roleHalo");
		List<RoleUser> list = roleUserDao.listCustom(t, "listUserNotInRole", 0, AppConst.DEFAULT_RESULT_LIMIT);
		for(RoleUser item : list) {
			System.out.println(item.getUserName() + ", " + item.getRoleCode());
		}
	}
}
