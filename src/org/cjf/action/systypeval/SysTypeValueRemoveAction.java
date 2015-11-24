package org.cjf.action.systypeval;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.entity.SysTypeValue;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

/**
 * @author CJF Generator
 * @version 1.0
 */
public class SysTypeValueRemoveAction extends AbstractWebAction<SysTypeValue> {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SysTypeValueRemoveAction.class);
	
	private int id;
	
	public SysTypeValueRemoveAction() {
		actionType = ActionType.Remove;
		isJsonStringParam = false;
	}

	@Override
	protected boolean process(Object obj) {
		SysTypeValue item = new SysTypeValue();
		item.setId(id);
		
		int count = biz.remove(item);
		
		if(0 == count) {
			logger.error("remove SysTypeValue failed. id = " + item.getId() + ";");
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

	private BusinessLogic<SysTypeValue> biz;
	@Override
	public void setBiz(BusinessLogic<SysTypeValue> biz) {
		this.biz = (BusinessLogic<SysTypeValue>)biz;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
