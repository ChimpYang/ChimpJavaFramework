package org.cjf.biz.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.cjf.biz.MenuBiz;
import org.cjf.dao.Dao;
import org.cjf.entity.Menu;
import org.cjf.entity.MenuTreeNode;
import org.cjf.utils.properties.AppConst;

public class MenuBizImpl extends BizImpl<Menu> implements MenuBiz {
	
	private static Logger log = Logger.getLogger(MenuBizImpl.class);
	
	@Override
	public List<MenuTreeNode> getMenuTree(String userCode) {
		return null;
	}

}
