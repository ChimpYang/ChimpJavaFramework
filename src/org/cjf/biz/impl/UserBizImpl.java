package org.cjf.biz.impl;

import org.apache.log4j.Logger;
import org.cjf.biz.UserBiz;
import org.cjf.dao.Dao;
import org.cjf.entity.User;
import org.cjf.util.MD5;

public class UserBizImpl extends BizImpl<User> implements UserBiz {

	private static Logger log = Logger.getLogger(BizImpl.class);
	
	private User loginInner(User item, boolean useMD5) {
		if(null == item) {
			log.info("login, but entity is null.");
			return null;
		}
		
		if(null == item.getPassword() || "".equals(item.getPassword())) {
			log.info("login, but password incorect.");
			return null;
		}
		
		Dao<User> dao = getDao();
		
		if(!useMD5) {
			item.setPassword(MD5.encode(item.getPassword()));
		}
		item = dao.singleCustom(item, "singleLogin");
		
		if(null == item) {
			log.info("login, but userCode or password incorect.");
			return null;
		}
		
		return item;
	}
	
	@Override
	public User login(User item) {
		return loginInner(item, false);
	}

	@Override
	public void modifyUserHead(String userCode, String headImage) {
		User t = new User();
		t.setUserCode(userCode);
		
		Dao<User> dao = getDao();
		User user = dao.singleCustom(t, "singleByCode");
		
		if(null == user) {
			log.info("update user head image, but user is null: " + userCode);
			return ;
		}
		
		user.setUserImage(headImage);
		dao.updateCustom(user, "updateHeadImage");
		
	}
	
}
