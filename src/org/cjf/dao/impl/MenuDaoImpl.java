package org.cjf.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.cjf.dao.MenuDao;
import org.cjf.entity.Menu;

public class MenuDaoImpl extends DaoImpl<Menu> implements MenuDao {
	
	private static Logger log = Logger.getLogger(MenuDaoImpl.class);
	
	/**
	 * 1. 根据用户编码，取出该用户所有角色具备的菜单
	 * 2. 根据具备的菜单，按菜单编码进行权限的累加计算（相同的多个记录只返回一行）
	 */
	@Override
	public List<Menu> getMenuListByUserCode(String userCode, String system) {
		return null;
	}


}
