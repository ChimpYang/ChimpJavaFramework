package org.cjf.biz;

import org.cjf.entity.User;

public interface UserBiz extends BusinessLogic<User> {
	User login(User item);
	void modifyUserHead(String userCode, String headImage);
}
