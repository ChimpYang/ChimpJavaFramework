package org.cjf.action.menu;

import java.util.List;

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
public class MenuModifyOrderAction extends AbstractWebAction<Menu> {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MenuModifyOrderAction.class);
	
	public MenuModifyOrderAction() {
		actionType = ActionType.Modify;
	}
	
	@Override
	protected boolean process(Object obj) {
		@SuppressWarnings("unchecked")
		List<Menu> list = (List<Menu>)obj;
		
		Menu t = new Menu();
		boolean flag = biz.batchModifyCustom(list, t, "updateOrder");
		
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
		
		List<Menu> list = JSONUtil.jsonStrToList(jsonString, new Menu());
		
		return list;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == obj) {
			message = "empty list.";
			return false;
		}
		
		@SuppressWarnings("unchecked")
		List<Menu> list = (List<Menu>)obj;
		
		if(0 == list.size()) {
			message = "count == 0";
			return false;
		}
		
		for(Menu item : list) {
			if(item.getId() <= 0) {
				message = "id <= 0";
				return false;
			}
			if(item.getDispOrder() <= 0) {
				message = "dispOrder <= 0";
				return false;
			}
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
