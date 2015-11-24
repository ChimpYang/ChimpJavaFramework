package org.cjf.action.user;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.biz.UserBiz;
import org.cjf.entity.User;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst.ActionType;

public class UserGetOneAction extends AbstractWebAction<User> {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserGetOneAction.class);
	
	public UserGetOneAction() {
		actionType = ActionType.GetOne;
	}

	@Override
	protected boolean process(Object obj) {
		User item = (User)obj;
		
		item = biz.getOne(item);
		jsonValue = JSONUtil.objToJsonStr(item, getEmptyObjString());
		
		if(null == item) {
			return false;
		}
		
		return true;
	}

	@Override
	protected Object getParamObject() {
		if(null == jsonString || "".equals(jsonString)) {
			message = "null param object.";
			return null;
		} else {
			message = jsonString;
		}
		
		User item = (User)JSONUtil.jsonStrToObj(jsonString, new User());
		
		return item;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == obj) {
			message = "null param obj.";
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
