package org.cjf.biz.impl;

import java.util.List;

import org.cjf.biz.RoleBiz;
import org.cjf.dao.Dao;
import org.cjf.entity.Role;
import org.cjf.entity.RoleUser;
import org.cjf.utils.properties.AppConst;

public class RoleBizImpl extends BizImpl<Role> implements RoleBiz {

	@Override
	public boolean createRoleIncludeUsers(String oldRoleCode, Role newRole, Dao<RoleUser> roleUserDao) throws Exception {
		int count = this.create(newRole);
		if(0 == count) {
			throw new Exception("create role failed.");
		}
		
		RoleUser ru = new RoleUser();
		ru.setRoleCode(oldRoleCode);
		List<RoleUser> list = roleUserDao.listCustom(ru, "listUserInRole", 0, AppConst.MAX_RESULT_LIMIT);
		if(null == list || 0 == list.size()) {
			return true;
		}
		
		for(RoleUser item : list) {
			item.setRoleCode(newRole.getRoleCode());
			roleUserDao.add(item);
		}
		
		/*
		try {
			//Cannot change the ExecutorType when there is an existing transaction
			//不能在一个Biz里即执行batch＝false的操作，又执行batch＝true的操作
			//所以这里就不用batch方式执行增加操作了
			roleUserDao.addBatch(newList, new RoleUser());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		*/
		
		return true;
	}


}
