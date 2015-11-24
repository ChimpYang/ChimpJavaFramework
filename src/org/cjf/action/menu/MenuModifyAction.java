package org.cjf.action.menu;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.entity.Menu;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

/**
 * @author CJF Generator
 * @version 1.0
 */
public class MenuModifyAction extends AbstractWebAction<Menu> {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MenuModifyAction.class);
	
	public MenuModifyAction() {
		actionType = ActionType.Modify;
	}
	
	@Override
	protected boolean process(Object obj) {
		Menu item = (Menu)obj;
		
		int count = biz.modify(item);
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
		
		Menu newItem = new Menu();
		newItem.setId(item.getId());
		newItem = biz.getOne(newItem);
		if(null == newItem) {
			message = "修改失败，数据取回失败.";
			errorCode = AppConst.ACTION_ERROR_DB_GET_IT_BACK;
			
			jsonObject.put("success", false);
			jsonObject.put("obj", getEmptyObjString());
			jsonObject.put("message", message); 
			jsonObject.put("errcode", errorCode);
			
			jsonValue = jsonObject.toString();
			
			return false;
		}
		
		message = "修改成功。";
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
			message = "null param object.";
			return null;
		}
		
		Menu item = (Menu)JSONUtil.jsonStrToObj(jsonString, new Menu());
		
		return item;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == obj) {
			return false;
		}
		
		Menu item = (Menu)obj;
		
		if(item.getId() <= 0) {
			message = "id <= 0";
			return false;
		}
		
		//TODO; GENERATOR; 检查参数
		
		if(null == biz) {
			message = "null biz.";
			return false;
		}
		
		int count = biz.getCountModify(item);
		
		if(count > 0) {
			message = "modify Menu, but Menu " + item.getMenuCode() + "already exists.";
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

	private BusinessLogic<Menu> biz;
	@Override
	public void setBiz(BusinessLogic<Menu> biz) {
		this.biz = (BusinessLogic<Menu>)biz;
	}

}
