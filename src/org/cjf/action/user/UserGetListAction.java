package org.cjf.action.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.biz.UserBiz;
import org.cjf.entity.User;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

public class UserGetListAction extends AbstractWebAction<User> {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserGetListAction.class);
	
	public UserGetListAction() {
		actionType = ActionType.GetList;
	}

	@Override
	protected boolean process(Object obj) {
		User item = (User)obj;
		
		int count = biz.getCount(item);
		List<User> list = biz.getList(item, start, limit);
		
		jsonObject.clear();
		jsonObject.put(AppConst.JSON_RESULT_LIST_COUNT, count);
		jsonObject.put(AppConst.JSON_RESULT_LIST, JSONUtil.listToJsonStr(list, "[]"));
		
		jsonValue = jsonObject.toString();
		
		if(null == list) {
			return false;
		}
		
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
			message = "null param obj.";
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
