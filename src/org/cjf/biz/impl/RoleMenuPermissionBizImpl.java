package org.cjf.biz.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.cjf.biz.RoleMenuPermissionBiz;
import org.cjf.dao.Dao;
import org.cjf.entity.RoleMenuPermission;

public class RoleMenuPermissionBizImpl extends BizImpl<RoleMenuPermission> implements RoleMenuPermissionBiz {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(RoleMenuPermissionBizImpl.class);
	
	@Override
	public List<RoleMenuPermission> getRoleMenuPermissionByUserCode(String userCode, int start, int limit) {
		Dao<RoleMenuPermission> dao = getDao();
		RoleMenuPermission t = new RoleMenuPermission();
		
		List<RoleMenuPermission> list = dao.listCustomParam(t, "menuNodeListByUser", userCode, start, limit);
		
		return list;
	}

}
