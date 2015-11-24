package org.cjf.test.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.cjf.dao.Dao;
import org.cjf.entity.RoleMenuAuth;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration("classpath:resources/test/dao/roleContext.xml")
public class RoleMenuAuthTest {
	@Resource
	private Dao<RoleMenuAuth> roleMenuAuthDao;
	
	@Test
	public void test001Count() {
		RoleMenuAuth t = new RoleMenuAuth();
		int count = roleMenuAuthDao.count(t);
		System.out.println(count);
	}
	
	@Test
	public void test002List() {
		RoleMenuAuth t = new RoleMenuAuth();
		t.setRoleCode("roleAdmin");
		List<RoleMenuAuth> list = roleMenuAuthDao.list(t, 0, 1000);
		for(RoleMenuAuth item : list) {
			System.out.println(item.getMenuCode() + ", " + item.getCanCreate());
		}
	}
	
	@Test
	public void test003Add() throws Exception {
		System.out.println("-------");
		RoleMenuAuth item;
		
		List<RoleMenuAuth> list = new ArrayList<RoleMenuAuth>();
		
		item = new RoleMenuAuth();
		item.setRoleCode("roleHalo");
		item.setMenuCode("FW");
		item.setCanCreate(1);
		list.add(item);
		
		item = new RoleMenuAuth();
		item.setRoleCode("roleHalo");
		item.setMenuCode("FW01");
		item.setCanCreate(1);
		list.add(item);
		
		item = new RoleMenuAuth();
		item.setRoleCode("roleHalo");
		item.setMenuCode("FW0101");
		item.setCanCreate(1);
		list.add(item);
		
		item = new RoleMenuAuth();
		item.setRoleCode("roleHalo");
		item.setMenuCode("FW0102");
		item.setCanCreate(0);
		list.add(item);
		
		roleMenuAuthDao.addBatch(list, new RoleMenuAuth());
		
		RoleMenuAuth t = new RoleMenuAuth();
		t.setRoleCode("roleHalo");
		List<RoleMenuAuth> list1 = roleMenuAuthDao.list(t, 0, 1000);
		for(RoleMenuAuth item1 : list1) {
			System.out.println(item1.getMenuCode() + ", " + item1.getCanCreate());
		}
	}
	
	@Test
	public void test004Delete() {
		RoleMenuAuth t = new RoleMenuAuth();
		t.setRoleCode("roleHalo");
		roleMenuAuthDao.delete(t);
	}
}
