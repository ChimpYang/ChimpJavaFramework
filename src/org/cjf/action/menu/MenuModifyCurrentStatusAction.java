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
public class MenuModifyCurrentStatusAction extends AbstractWebAction<Menu> {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MenuModifyCurrentStatusAction.class);
	
	public MenuModifyCurrentStatusAction() {
		actionType = ActionType.Modify;
	}
	
	@Override
	protected boolean process(Object obj) {
		Menu item = (Menu)obj;
		
		
		int count = biz.modifyCustom(item, "updateCurrentStatus");
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
	

		//TODO; GENERATOR; 修改判断类型
		if(item.getCurrentStatus() <= 0) {
			message = "CurrentStatus <= 0";
			return false;
		}
		
		//TODO; GENERATOR; 检查其他参数
		
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

	private BusinessLogic<Menu> biz;
	@Override
	public void setBiz(BusinessLogic<Menu> biz) {
		this.biz = (BusinessLogic<Menu>)biz;
	}

}
