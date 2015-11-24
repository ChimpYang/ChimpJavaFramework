package org.cjf.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.biz.UserBiz;
import org.cjf.entity.User;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

public class ExEnableUsersAction extends AbstractWebAction<User> {

	private static final long serialVersionUID = -3615378791985076924L;
	private static final Logger logger = Logger.getLogger(ExEnableUsersAction.class);
	
	public ExEnableUsersAction() {
		actionType = ActionType.Modify;
	}
	
	@Override
	protected boolean process(Object obj) {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>)obj;
		
		User t = new User();
		boolean flag = biz.batchModifyCustom(list, t, "updateCurrentStatus");
		
		if(!flag) {
			message = "修改失败";
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
		
		List<User> list = new ArrayList<User>();
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Object[] ids = jsonArray.toArray();
		User item;
		for(Object obj: ids) {
			Integer id = (Integer)obj;
			
			item = new User();
			item.setId(id);
			item.setCurrentStatus(AppConst.STATUS_ENABLE);
			item.setLastModifyDate(new Date(System.currentTimeMillis()));
			
			list.add(item);
		}
		
		return list;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == obj) {
			message = "no id found.";
			return false;
		}
		
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>)obj;
		
		if(0 == list.size()) {
			message = "id count == 0";
			return false;
		}
		
		return true;
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected boolean getPermission() {
		//TODO; 得到真实的权限
		return true;
	}
	
	private UserBiz biz;
	@Override
	public void setBiz(BusinessLogic<User> biz) {
		this.biz = (UserBiz)biz;
	}

}
