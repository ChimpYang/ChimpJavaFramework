package org.cjf.test.biz;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.cjf.biz.BusinessLogic;
import org.cjf.entity.Menu;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration("classpath:resources/test/biz/menuContext.xml")
public class MenuBizTest {
//	private static int lastId;
	
	@Resource
	private BusinessLogic<Menu> menuBiz;
	
//	@Test
//	public void test000Clear() {
//		Menu t = new Menu();
//		int count = menuBiz.clear(t);
//		
//		System.out.println("Clear count: " + count);
//	}
//	
//	@Test
//	public void test001Create() {
//		Menu item = new Menu();
//		
//		item.setCurrentStatus(1);
//		item.setDispOrder(1);
//		item.setMenuAction("action1");
//		item.setMenuCode("0101");
//		item.setMenuTitle("title1");
//		item.setMenuTypeCode("menuAction");
//		item.setSystemTypeCode("sysTest");
//		
//		int count = menuBiz.create(item);
//		TestCase.assertEquals(1, count);
//		TestCase.assertNotSame(0, item.getId());
//		
//		lastId = item.getId();
//	}
//	
//	@Test
//	public void test002QueryOne() {
//		Menu t = new Menu();
//		t.setId(lastId);
//		
//		Menu item = menuBiz.getOne(t);
//		TestCase.assertEquals("action1", item.getMenuAction());
//		TestCase.assertEquals("title1", item.getMenuTitle());
//		TestCase.assertEquals("0101", item.getMenuCode());
//		TestCase.assertEquals("menuAction", item.getMenuTypeCode());
//		TestCase.assertEquals("sysTest", item.getSystemTypeCode());
//		TestCase.assertNull(item.getParentMenuCode());
//	}
//	
//	@Test
//	public void test003Modify() {
//		Menu t = new Menu();
//		t.setId(lastId);
//		Menu item = menuBiz.getOne(t);
//		
//		item.setMenuAction("action1-a");
//		item.setMenuTitle("title1-a");
//		item.setMenuTypeCode("menuAction-a");
//		item.setMenuCode("0101-a");
//		
//		int count = menuBiz.modify(item);
//		TestCase.assertEquals(1, count);
//	}
//	
//	@Test
//	public void test004ModifyCheck() {
//		Menu t = new Menu();
//		t.setId(lastId);
//		
//		Menu item = menuBiz.getOne(t);
//		TestCase.assertNotNull(item);
//		TestCase.assertEquals("action1-a", item.getMenuAction());
//		TestCase.assertEquals("title1-a", item.getMenuTitle());
//		TestCase.assertEquals("0101", item.getMenuCode());
//		TestCase.assertEquals("menuAction-a", item.getMenuTypeCode());
//	}
//	
//	@Test
//	public void test005Delete() {
//		Menu t = new Menu();
//		t.setId(lastId);
//		
//		int count = menuBiz.remove(t);
//		TestCase.assertEquals(1, count);
//	}
	
	@Test
	public void test006Query1() {
		Menu item = new Menu();
		item.setMenuCode("FW");
		item.setMenuTitle("管理");
		
		List<Menu> list = menuBiz.getList(item, 0, 100);
		TestCase.assertNotNull(list);
		for(Menu m : list) {
			System.out.println(m.getMenuCode() + ", " + m.getMenuTitle());
		}
	}
}
