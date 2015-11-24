package org.cjf.biz;

import java.util.List;

import org.cjf.entity.Menu;
import org.cjf.entity.MenuTreeNode;

public interface MenuBiz extends BusinessLogic<Menu> {
	
	List<MenuTreeNode> getMenuTree(String userCode);
	
}
