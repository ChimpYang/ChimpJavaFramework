package org.cjf.action.role;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.entity.Role;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst.ActionType;

public class RoleGetOneAction extends AbstractWebAction<Role> {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RoleGetOneAction.class);
	
	public RoleGetOneAction() {
		actionType = ActionType.GetOne;
	}

	@Override
	protected boolean process(Object obj) {
		Role item = (Role)obj;
		
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
		
		Role item = (Role)JSONUtil.jsonStrToObj(jsonString, new Role());
		
		return item;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == obj) {
			message = "null param obj.";
			return false;
		}
		
		Role item = (Role)obj;
		
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

	private BusinessLogic<Role> biz;
	@Override
	public void setBiz(BusinessLogic<Role> biz) {
		this.biz = (BusinessLogic<Role>)biz;
	}

}
