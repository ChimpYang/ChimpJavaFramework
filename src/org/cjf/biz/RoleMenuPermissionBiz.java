package org.cjf.biz;

import java.util.List;

import org.cjf.entity.RoleMenuPermission;

public interface RoleMenuPermissionBiz {
	List<RoleMenuPermission> getRoleMenuPermissionByUserCode(String userCode, int start, int limit);
}
