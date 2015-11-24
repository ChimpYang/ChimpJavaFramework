package org.cjf.dao;

import java.util.List;

import org.cjf.entity.Menu;

public interface MenuDao extends Dao<Menu> {
	/**
	 * 
	 * @param userCode 用户编码
	 * @param system 系统（比如：四管系统、辅机系统、OA系统等）如果system为null或空，则取出所有系统的菜单
	 * @return
	 */
	List<Menu> getMenuListByUserCode(String userCode, String system);
}
