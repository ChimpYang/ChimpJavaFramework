package org.cjf.biz.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.cjf.biz.RoleMenuAuthBiz;
import org.cjf.entity.RoleMenuAuth;

public class RoleMenuAuthBizImpl extends BizImpl<RoleMenuAuth> implements RoleMenuAuthBiz {
	
	private static Logger log = Logger.getLogger(RoleMenuAuthBizImpl.class);
	
	@Override
	public boolean createRoleMenuAuthList(List<RoleMenuAuth> list) {
		
		if(null == list || 0 == list.size()) {
			log.info("empty list.");
			
			return false;
		}
		
		RoleMenuAuth t = list.get(0);
		this.getDao().delete(t);
		
		for(RoleMenuAuth item : list) {
			//前台没有选择任何权限时，会只传递一个只有角色编码的行，用例删除该角色的所有权限
			if(null == item.getMenuCode() || "".equals(item.getMenuCode())) {
				continue;
			}
			this.getDao().add(item);
		}
		
		return true;
	}

}