package org.cjf.action.roleuser;

import java.util.List;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.entity.RoleUser;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

public class RoleUserGetListInRoleAction extends AbstractWebAction<RoleUser> {

	private static final long serialVersionUID = -700321135419194194L;
	private static final Logger logger = Logger.getLogger(RoleUserGetListInRoleAction.class);
	
	public RoleUserGetListInRoleAction() {
		actionType = ActionType.GetList;
	}
	
	private BusinessLogic<RoleUser> biz;
	@Override
	public void setBiz(BusinessLogic<RoleUser> biz) {
		this.biz = biz;
	}

	@Override
	protected boolean process(Object obj) {
		RoleUser item = (RoleUser)obj;
		
		int count = biz.getCountCustom(item, "countListUserInRole");
		List<RoleUser> list = biz.getListCustom(item, "listUserInRole", start, limit);
		
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
		
		RoleUser item = (RoleUser)JSONUtil.jsonStrToObj(jsonString, new RoleUser());
		
		return item;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == obj) {
			message = "null param obj.";
			return false;
		}
		
		RoleUser item = (RoleUser)obj;
		if( null == item.getRoleCode() || "".equals(item.getRoleCode()) ) {
			message = "null or empty roleCode";
			return false;
		}
		
		if(null == biz) {
			message = "null biz.";
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

}
