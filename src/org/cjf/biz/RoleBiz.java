package org.cjf.biz;

import org.cjf.dao.Dao;
import org.cjf.entity.Role;
import org.cjf.entity.RoleUser;

public interface RoleBiz extends BusinessLogic<Role> {
	boolean createRoleIncludeUsers(String oldRoleCode, Role newRole, Dao<RoleUser> roleUserDao) throws Exception;
}
