package org.cjf.biz;

import java.util.List;

import org.cjf.entity.RoleMenuAuth;

public interface RoleMenuAuthBiz extends BusinessLogic<RoleMenuAuth>{
	boolean createRoleMenuAuthList(List<RoleMenuAuth> list);
}
