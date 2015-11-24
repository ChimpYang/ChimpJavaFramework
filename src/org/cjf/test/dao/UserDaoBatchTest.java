package org.cjf.test.dao;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.cjf.dao.Dao;
//import org.cjf.entity.User;
//import org.cjf.test.DaoBatchTest;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@FixMethodOrder(MethodSorters.JVM)
//@ContextConfiguration("classpath:resources/test/dao/userContext.xml")
//public class UserDaoBatchTest implements DaoBatchTest {
//public static int id = 0;
//	
//	@Resource
//	private Dao<User> userDao;
//	
//	@Test
//	public void test001AddBatch() throws Exception {
//		List<User> list = new ArrayList<User>();
//		User item = null;
//		int count = 100;//success
////		int count = 500;//success
////		int count = 1000;//success
////		int count = 2000;//success
////		int count = 5000;//success
//		for(int i=0;i<count;i++) {
//			item = new User();
//			item.setUserCode("Chimp" + i);
//			item.setUserName("ChimpYang" + i);
//			item.setPassword("821AADC87069188E01B09E9370FAB81A");
//			item.setUserStatus(1);
//			item.setMailAddr("chimp@126.com");
//			item.setPhoneNumber("02566688888");
//			
//			list.add(item);
//		}
//		
//		//add error item(重复的编码)
//		//1. 未交容器的事务进行管理时：正确的都增加了，错误的没有增加，并报错
//		//2. 交割容器的事务进行管理时：不管正确、错误都未增加，并报错
////		item = new User();
////		item.setUserCode("Chimp4");
////		item.setUserName("ChimpYang4");
////		item.setPassword("821AADC87069188E01B09E9370FAB81A");
////		item.setUserStatus(1);
////		item.setMailAddr("chimp@126.com");
////		item.setPhoneNumber("02566688888");
////		
////		list.add(item);
//		//error item
//		
//		userDao.addBatch(list, new User());
//		
//		for(User u : list) {
//			System.out.println(u.getUserCode() + " ," + u.getId());
//		}
//	}
//	
//	@Test
//	public void test002UpdateBatch() throws Exception {
//		List<User> list = new ArrayList<User>();
//		User item = null;
//		int count = 100;
//		for(int i=0;i<count;i++) {
//			item = new User();
//			item.setUserCode("Chimp" + i);
//			item.setUserName("AAA" + i);
//			item.setPassword("BBB");
//			item.setUserStatus(2);
//			item.setMailAddr("ccc@126.com");
//			item.setPhoneNumber("12340000");
//			
//			list.add(item);
//		}
//		
//		userDao.updateBatchCustom(list, new User(), "updateByCode");
//	}
//	
//	@Test
//	public void test003DeleteBatch() throws Exception {
//		List<User> list = new ArrayList<User>();
//		User item = null;
//		int count = 100;
//		for(int i=0;i<count;i++) {
//			item = new User();
//			item.setUserCode("Chimp" + i);
//			
//			list.add(item);
//		}
//		
//		userDao.deleteBatchCustom(list, new User(), "deleteByCode");
//	}
//	
//}