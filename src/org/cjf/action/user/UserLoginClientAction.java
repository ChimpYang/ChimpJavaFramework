package org.cjf.action.user;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.biz.UserBiz;
import org.cjf.entity.User;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

public class UserLoginClientAction extends AbstractWebAction<User> {

	private static final long serialVersionUID = -6328780904081368750L;
	private static final Logger logger = Logger.getLogger(UserLoginClientAction.class);
	
	public UserLoginClientAction() {
		this.actionType = ActionType.None;
	}
	
	@Override
	protected boolean getPermission() {
		return true;
	}
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	private String userCode;
	private String password;
	private UserBiz biz;
	@Override
	public void setBiz(BusinessLogic<User> biz) {
		this.biz = (UserBiz)biz;
	}

	@Override
	protected boolean process(Object obj) {
		User item = new User();
		item.setUserCode(userCode);
		item.setPassword(password);
		item = biz.login(item);
		if(null == item) {
			jsonObject.put("success", false);
			jsonObject.put("obj", getEmptyObjString());
			jsonObject.put("message", "登录失败。"); 
			jsonObject.put("errcode", errorCode);
			
			jsonValue = jsonObject.toString();
			
			return false;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute(AppConst.SESSION_USER, item);
		
		jsonObject.put("success", true);
		jsonObject.put("obj", getEmptyObjString());
		jsonObject.put("message", "登录成功。"); 
		jsonObject.put("errcode", errorCode);
		
		jsonValue = jsonObject.toString();
		
		return true;
	}

	@Override
	protected Object getParamObject() {
		return null;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == userCode || "".equals(userCode)) {
			return false;
		}
		
		if(null == password || "".equals(password)) {
			return false;
		}
		
		if(null == biz) {
			return false;
		}
		
		return true;
	}

}
