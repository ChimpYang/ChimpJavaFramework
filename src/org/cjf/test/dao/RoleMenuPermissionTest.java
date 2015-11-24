package org.cjf.test.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.cjf.dao.Dao;
import org.cjf.entity.MenuTreeNode;
import org.cjf.entity.RoleMenuPermission;
import org.cjf.test.DaoTest;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration("classpath:resources/test/dao/roleMenuPermissionContext.xml")
public class RoleMenuPermissionTest implements DaoTest {

	@Resource
	private Dao<RoleMenuPermission> dao;
	
	@Test
	public void test001Clear() {
		
	}

	@Test
	public void test002Add() {
		
	}

	@Test
	public void test003Single() {
		
	}

	@Test
	public void test004Update() {
		
	}

	@Test
	public void test005List() {
		RoleMenuPermission t = new RoleMenuPermission();
		list = dao.listCustomParam(t, "menuNodeListByUser", "admin", 0, AppConst.DEFAULT_RESULT_LIMIT);
//		for(RoleMenuPermission item : list) {
//			System.out.println(item.getMenuCode() + ", parent: " + item.getParentMenuCode() + ", canQuery:" + item.getCanQuery());
//			System.out.println(item.getStringPermission());
//		}
	}

	@Test
	public void test006Delete() {
		
	}
	
	private static List<RoleMenuPermission> list;
	@Test
	public void test007GetTreeNode() {
		getChildNode(null, list, "");
		
		printTreeNodes(nodes, 0);
		
		String jsonString = JSONUtil.listToJsonStr(nodes, "");
		System.out.println(jsonString);
	}
	
	private void printTreeNodes(List<MenuTreeNode> treeNodes, int layer) {
		String prefix = getPrefix(layer);
		for(MenuTreeNode item : treeNodes) {
			System.out.println(prefix + item.getMenuCode());
			if(null != item.getChildren()) {
				printTreeNodes(item.getChildren(), layer+1);
			}
		}
	}

	private String getPrefix(int layer) {
		String prefix = "";
		for(int i=0;i<layer;i++) {
			prefix += "\t";
		}
		return prefix;
	}

	private static List<MenuTreeNode> nodes = new ArrayList<MenuTreeNode>(); 
	public static void getChildNode(MenuTreeNode parentNode, List<RoleMenuPermission> menuList, String parentCode) {
		MenuTreeNode node;
		List<RoleMenuPermission> tempList = new ArrayList<RoleMenuPermission>();
		List<MenuTreeNode> children = null;
		for(RoleMenuPermission rmp : menuList) {
			if(rmp.getParentMenuCode().equals(parentCode)) {
				node = new MenuTreeNode();
				node = MenuTreeNode.cloneFromRMP(rmp);
				
				if(null == parentNode) {
					children = nodes;
				} else {
					children = parentNode.getChildren();
					if(null == children) {
						children = new ArrayList<MenuTreeNode>();
						parentNode.setChildren(children);
						parentNode.setLeaf(false);
					}
				}
				
				children.add(node);
				tempList.add(rmp);
			}//if equal parentCode
		}//for
		
		for(RoleMenuPermission rmp : tempList) {
			menuList.remove(rmp);
		}
		tempList.clear();
		tempList = null;
		
		if(null != children) {
			for(MenuTreeNode tempNode : children) {
				getChildNode(tempNode, list, tempNode.getMenuCode());
			}
		}
	}
	

}
