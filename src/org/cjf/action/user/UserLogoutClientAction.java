package org.cjf.action.user;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.entity.User;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

public class UserLogoutClientAction extends AbstractWebAction<User> {

	private static final long serialVersionUID = 4869825595960007294L;
	private static final Logger logger = Logger.getLogger(UserLogoutClientAction.class);
	
	public UserLogoutClientAction() {
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
	
	@Override
	public void setBiz(BusinessLogic<User> biz) {
	}

	@Override
	protected boolean process(Object obj) {
		HttpSession session = request.getSession();
		session.setAttribute(AppConst.SESSION_USER, null);
		
		jsonObject.put("success", true);
		jsonObject.put("obj", getEmptyObjString());
		jsonObject.put("message", "退出成功。"); 
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
		return true;
	}

}
