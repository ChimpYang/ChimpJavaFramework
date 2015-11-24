package org.cjf.action.menu;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.entity.Menu;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

/**
 * @author CJF Generator
 * @version 1.0
 */
public class MenuRemoveAction extends AbstractWebAction<Menu> {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MenuRemoveAction.class);
	
	private int id;
	
	public MenuRemoveAction() {
		actionType = ActionType.Remove;
		isJsonStringParam = false;
	}

	@Override
	protected boolean process(Object obj) {
		Menu item = new Menu();
		item.setId(id);
		
		int count = biz.remove(item);
		
		if(0 == count) {
			logger.error("remove Menu failed. id = " + item.getId() + ";");
			message = "删除失败，删除个数为0.";
			errorCode = AppConst.ACTION_ERROR_DB;
			
			jsonObject.put("success", false);
			jsonObject.put("obj", getEmptyObjString());
			jsonObject.put("message", message); 
			jsonObject.put("errcode", errorCode);
			
			jsonValue = jsonObject.toString();
			
			return false;
		}
		
		message = "删除成功。"; 
		
		jsonObject.put("success", true);
		jsonObject.put("obj", objString);
		jsonObject.put("message", message); 
		jsonObject.put("errcode", errorCode);
		
		jsonValue = jsonObject.toString();
		
		return true;
	}
	
	@Override
	protected Object getParamObject() {
		return null;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(id <= 0) {
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

	private BusinessLogic<Menu> biz;
	@Override
	public void setBiz(BusinessLogic<Menu> biz) {
		this.biz = (BusinessLogic<Menu>)biz;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
