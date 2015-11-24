package org.cjf.action.rolemenuauth;

import java.util.List;
import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.biz.RoleMenuAuthBiz;
import org.cjf.entity.RoleMenuAuth;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

public class RoleMenuAuthCreateAction extends AbstractWebAction<RoleMenuAuth> {

	private static final long serialVersionUID = 700570585586966463L;
	private static final Logger logger = Logger.getLogger(RoleMenuAuthCreateAction.class); 
	
	public RoleMenuAuthCreateAction() {
		actionType = ActionType.Create;
	}
	
	private RoleMenuAuthBiz biz;
	@Override
	public void setBiz(BusinessLogic<RoleMenuAuth> biz) {
		this.biz = (RoleMenuAuthBiz)biz;
	}

	@Override
	protected boolean process(Object obj) {
		@SuppressWarnings("unchecked")
		List<RoleMenuAuth> list = (List<RoleMenuAuth>)obj;
		
		boolean flag = biz.createRoleMenuAuthList(list);
		
		if(!flag) {
			message = "增加保存失败";
			errorCode = AppConst.ACTION_ERROR_DB;
			
			jsonObject.put("success", false);
			jsonObject.put("obj", getEmptyObjString());
			jsonObject.put("message", message); 
			jsonObject.put("errcode", errorCode);
			
			jsonValue = jsonObject.toString();
			
			return false;
		}
		
		message = "权限保存成功。";
		
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
		
		List<RoleMenuAuth> list = JSONUtil.jsonStrToList(jsonString, new RoleMenuAuth());
		
		return list;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == obj) {
			return false;
		}
		
		@SuppressWarnings("unchecked")
		List<RoleMenuAuth> list = (List<RoleMenuAuth>)obj;
		
		if(null == biz) {
			message = "null biz.";
			return false;
		}
		
		if(0 == list.size()) {
			message = "no item.";
			return false;
		}
		
		for(RoleMenuAuth item : list) {
			if(null == item.getRoleCode() || "".equals(item.getRoleCode())) {
				message = "null or empty RoleCode";
				return false;
			}
		}
		
		return true;
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected boolean getPermission() {
		return true;
	}

}
