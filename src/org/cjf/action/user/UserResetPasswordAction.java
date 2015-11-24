package org.cjf.action.user;

import java.util.Date;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.biz.UserBiz;
import org.cjf.entity.User;
import org.cjf.util.MD5;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

public class UserResetPasswordAction extends AbstractWebAction<User> {

	private static final long serialVersionUID = 523860442506901381L;
	private static final Logger logger = Logger.getLogger(UserResetPasswordAction.class);
	
	public UserResetPasswordAction() {
		actionType = ActionType.Modify;
	}
	
	@Override
	protected boolean process(Object obj) {
		User item = (User)obj;
		item.setLastModifyDate(new Date(System.currentTimeMillis()));
		item.setPassword(MD5.encode(AppConst.DEFAULT_PASSWORD));
		
		int count = biz.modifyCustom(item, "updatePassword");
		if(count != 1) {
			message = "修改失败，修改个数为0.";
			errorCode = AppConst.ACTION_ERROR_DB;
			
			jsonObject.put("success", false);
			jsonObject.put("obj", getEmptyObjString());
			jsonObject.put("message", message); 
			jsonObject.put("errcode", errorCode);
			
			jsonValue = jsonObject.toString();
			
			return false;
		}
		
		message = "修改成功。";
		
		jsonObject.put("success", true);
		jsonObject.put("obj", getEmptyObjString());
		jsonObject.put("message", message); 
		jsonObject.put("errcode", errorCode);
		
		jsonValue = jsonObject.toString();
		
		return true;
	}

	@Override
	protected Object getParamObject() {
		if(null == jsonString || "".equals(jsonString)) {
			message = "null param object.";
			return null;
		}
		
		User item = (User)JSONUtil.jsonStrToObj(jsonString, new User());
		
		return item;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == obj) {
			return false;
		}
		
		User item = (User)obj;
		
		if(item.getId() <= 0) {
			message = "id <= 0";
			return false;
		}
		
		if(null == biz) {
			message = "null biz.";
			return false;
		}
		
		return true;
	}

	@Override
	protected boolean getPermission() {
		//TODO; 得到真实的权限
		return true;
	}
	
	@Override
	protected Logger getLogger() {
		return logger;
	}

	private UserBiz biz;
	@Override
	public void setBiz(BusinessLogic<User> biz) {
		this.biz = (UserBiz)biz;
	}

}
