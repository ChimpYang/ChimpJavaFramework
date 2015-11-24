package org.cjf.action.roleuser;

import java.util.List;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.entity.RoleUser;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

public class RoleUserCreateAction extends AbstractWebAction<RoleUser> {

	private static final long serialVersionUID = -2966887228986459214L;
	private static final Logger logger = Logger.getLogger(RoleUserCreateAction.class); 
	
	public RoleUserCreateAction() {
		actionType = ActionType.Create;
	}
	
	@Override
	protected boolean process(Object obj) {
		@SuppressWarnings("unchecked")
		List<RoleUser> list = (List<RoleUser>)obj;
		
		boolean flag = biz.batchCreate(list, new RoleUser());
		
		if(!flag) {
			message = "创建失败";
			errorCode = AppConst.ACTION_ERROR_DB;
			
			jsonObject.put("success", false);
			jsonObject.put("obj", getEmptyObjString());
			jsonObject.put("message", message); 
			jsonObject.put("errcode", errorCode);
			
			jsonValue = jsonObject.toString();
			
			return false;
		}
		
		message = "创建成功。";
		
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
		
		List<RoleUser> list = JSONUtil.jsonStrToList(jsonString, new RoleUser());
		
		return list;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == obj) {
			return false;
		}
		
		@SuppressWarnings("unchecked")
		List<RoleUser> list = (List<RoleUser>)obj;
		
		if(null == biz) {
			message = "null biz.";
			return false;
		}
		
		if(0 == list.size()) {
			message = "no item.";
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

	private BusinessLogic<RoleUser> biz;
	@Override
	public void setBiz(BusinessLogic<RoleUser> biz) {
		this.biz = biz;
	}

}
