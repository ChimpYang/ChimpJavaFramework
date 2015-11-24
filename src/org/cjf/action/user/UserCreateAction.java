package org.cjf.action.user;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.biz.UserBiz;
import org.cjf.entity.User;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

public class UserCreateAction extends AbstractWebAction<User> {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserCreateAction.class); 
	
	public UserCreateAction() {
		actionType = ActionType.Create;
	}
	
	@Override
	protected boolean process(Object obj) {
		User item = (User)obj;
		
		int count = biz.create(item);
		
		if(0 == count || 0 == item.getId()) {
			message = "创建失败，增加个数或编号为0。";
			errorCode = AppConst.ACTION_ERROR_DB;
			
			jsonObject.put("success", false);
			jsonObject.put("obj", getEmptyObjString());
			jsonObject.put("message", message); 
			jsonObject.put("errcode", errorCode);
			
			jsonValue = jsonObject.toString();
			
			return false;
		}
		
		User newItem = new User();
		newItem.setId(item.getId());
		newItem = biz.getOne(newItem);
		if(null == newItem) {
			message = "增加失败，数据取回失败.";
			errorCode = AppConst.ACTION_ERROR_DB_GET_IT_BACK;
			
			jsonObject.put("success", false);
			jsonObject.put("obj", getEmptyObjString());
			jsonObject.put("message", message); 
			jsonObject.put("errcode", errorCode);
			
			jsonValue = jsonObject.toString();
			
			return false;
		}
		
		message = "创建成功。";
		objString = JSONUtil.objToJsonStr(newItem, "");
		
		jsonObject.put("success", true);
		jsonObject.put("obj", objString);
		jsonObject.put("message", message); 
		jsonObject.put("errcode", errorCode);
		
		jsonValue = jsonObject.toString();
		
		return true;
	}
	
	@Override
	protected Object getParamObject() {
		if(null == jsonString || "".equals(jsonString)) {
			logger.warn("null param object.");
			return null;
		} else {
			logger.debug(jsonString);
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
		item.setId(0);
		
		//TODO; GENERATOR; 检查参数
		
		if(null == biz) {
			message = "null biz.";
			return false;
		}
		
		int count = biz.getCountBiz(item);
		
		if(count > 0) {
			//TODO; GENERATOR; 提示哪个编码重复
			message = "add User, but " + " ? " + " already exists.";
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
