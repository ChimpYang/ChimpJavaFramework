package org.cjf.action.role;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.biz.RoleBiz;
import org.cjf.dao.Dao;
import org.cjf.entity.Role;
import org.cjf.entity.RoleUser;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

public class ExRoleAndUsersSaveAsAction extends AbstractWebAction<Role> {

	private static final long serialVersionUID = -8344481502663127959L;
	private static final Logger logger = Logger.getLogger(ExRoleAndUsersSaveAsAction.class); 
	
	private String oldRoleCode;
	private Dao<RoleUser> roleUserDao;
	
	public Dao<RoleUser> getRoleUserDao() {
		return roleUserDao;
	}

	public void setRoleUserDao(Dao<RoleUser> roleUserDao) {
		this.roleUserDao = roleUserDao;
	}

	public ExRoleAndUsersSaveAsAction() {
		actionType = ActionType.Create;
	}
	
	@Override
	protected boolean process(Object obj) {
		Role item = (Role)obj;
		
		boolean flag = false;
		try {
			flag = biz.createRoleIncludeUsers(oldRoleCode, item, roleUserDao);
		} catch (Exception e) {
			
		}
		
		if(!flag) {
			message = "增加失败.";
			errorCode = AppConst.ACTION_ERROR_DB;
			
			jsonObject.put("success", false);
			jsonObject.put("obj", getEmptyObjString());
			jsonObject.put("message", message); 
			jsonObject.put("errcode", errorCode);
			
			jsonValue = jsonObject.toString();
			
			return false;
		}
		
		Role newItem = new Role();
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
		
		Role item = (Role)JSONUtil.jsonStrToObj(jsonString, new Role());
		
		return item;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == obj) {
			return false;
		}
		
		if(null == roleUserDao) {
			return false;
		};
		
		if(null == oldRoleCode || "".equals(oldRoleCode)) {
			return false;
		}
		
		if(null == biz) {
			message = "null biz.";
			return false;
		}
		
		Role item = (Role)obj;
		item.setId(0);
		
		int count = biz.getCountBiz(item);
		
		if(count > 0) {
			//TODO; GENERATOR; 提示哪个编码重复
			message = "add Role, but " + " ? " + " already exists.";
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

	private RoleBiz biz;
	@Override
	public void setBiz(BusinessLogic<Role> biz) {
		this.biz = (RoleBiz)biz;
	}

	public String getOldRoleCode() {
		return oldRoleCode;
	}

	public void setOldRoleCode(String oldRoleCode) {
		this.oldRoleCode = oldRoleCode;
	}
}
