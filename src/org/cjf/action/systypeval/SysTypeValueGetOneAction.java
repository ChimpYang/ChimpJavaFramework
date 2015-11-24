package org.cjf.action.systypeval;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.entity.SysTypeValue;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst.ActionType;

/**
 * @author CJF Generator
 * @version 1.0
 */
public class SysTypeValueGetOneAction extends AbstractWebAction<SysTypeValue> {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SysTypeValueGetOneAction.class);
	
	public SysTypeValueGetOneAction() {
		actionType = ActionType.GetOne;
	}

	@Override
	protected boolean process(Object obj) {
		SysTypeValue item = (SysTypeValue)obj;
		
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
		
		SysTypeValue item = (SysTypeValue)JSONUtil.jsonStrToObj(jsonString, new SysTypeValue());
		
		return item;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == obj) {
			message = "null param obj.";
			return false;
		}
		
		SysTypeValue item = (SysTypeValue)obj;
		
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

	private BusinessLogic<SysTypeValue> biz;
	@Override
	public void setBiz(BusinessLogic<SysTypeValue> biz) {
		this.biz = (BusinessLogic<SysTypeValue>)biz;
	}

}
