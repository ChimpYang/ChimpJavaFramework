package org.cjf.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.cjf.entity.Entity;
import org.cjf.entity.User;
import org.cjf.logtranslate.LogTranslate;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;
import org.cjf.utils.properties.AppConst.ClientType;

import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractWebAction<T extends Entity> extends ActionSupport implements WebAction<T> {

	private static final long serialVersionUID = -1625827478794243952L;
	
	protected String message = "";
	protected int errorCode = AppConst.ACTION_ERROR_NOERROR;
	protected String objString = "";
	
	protected String jsonValue;
	protected HttpServletRequest request;
	protected int start = 0;
	protected int limit = AppConst.DEFAULT_PAGESIZE;
	protected LogTranslate logTranslate;
	protected int resourceId;
	protected ActionType actionType;
	protected ClientType clientType = ClientType.Unknown;
	protected boolean foreceDelete;
	protected String jsonString;
	protected JSONObject jsonObject = new JSONObject();
	protected boolean isJsonStringParam = true;
	
	/**
	 * 根据ClientType得到空Json的字符串表示
	 * ExtJS与Unity对JSON空对象的格式要求不一样
	 * @return
	 */
	protected final String getEmptyObjString() {
		String emptyStr = "";
		
		switch(clientType) {
		case Unity:
			emptyStr = AppConst.EMPTY_JSONOBJ_STR_TYPE1;
			break;
		case ExtJS:
			emptyStr = AppConst.EMPTY_JSONOBJ_STR_TYPE2;
			break;
		case WebBrowser:
			emptyStr = AppConst.EMPTY_JSONOBJ_STR_TYPE2;
			break;
		case Unknown:
			emptyStr = AppConst.EMPTY_JSONOBJ_STR_TYPE2;
			break;
		}
		
		return emptyStr;
	}
	
	private User getSessionUser() {
		if(null == request) {
			return null;
		}
		
		HttpSession session = request.getSession();
		if(null == session) {
			return null;
		}
		
		User user = (User)session.getAttribute(AppConst.SESSION_USER);
		return user;
	}
	
	/**
	 * 具体的Action处理
	 * @return
	 */
	protected abstract boolean process(Object obj);
	
	/**
	 * 得到参数对象，比如用jsonString转换成Entitiy，或多个参数组装成Entity
	 * @return
	 */
	protected abstract Object getParamObject();
	
	/**
	 * 检查参数是否正确
	 * @param obj
	 * @return
	 */
	protected abstract boolean checkParam(Object obj);
	
	/**
	 * 得到日志处理类
	 * @return
	 */
	protected abstract Logger getLogger();
	
	/**
	 * 根据用户、类名称（资源）、ActionType得到该用户是否有该操作权限
	 * 类名称的全写就是资源编码
	 * @return
	 */
	protected abstract boolean getPermission();
	
	/**
	 * 模版类-主方法
	 * 在execute方法中，按照固定的流程进行处理
	 */
	public String execute() throws Exception {
		if(isJsonStringParam) {
			if(null == jsonString || "".equals(jsonString)) {
				getLogger().warn("null or empty jsonString.");
			}
		}
		
		if(ActionType.GetList == actionType && (start<0 || limit<=0 || limit>AppConst.MAX_RESULT_LIMIT) ) {
			jsonObject.put("success", false);
			jsonObject.put("obj", getEmptyObjString());
			jsonObject.put("message", AppConst.translateErrorId(AppConst.ACTION_ERROR_PARAM_PAGEERROR));
			jsonObject.put("errcode", AppConst.ACTION_ERROR_PARAM_PAGEERROR);
			jsonValue = jsonObject.toString();
			
			return INPUT;
		}
		
		User user = getSessionUser();
		
		boolean sessionError = false;
		if(null == user) {
			if(ActionType.None != actionType) {
				sessionError = true;
			}
			
			if(sessionError) {
				jsonObject.put("success", false);
				jsonObject.put("obj", getEmptyObjString());
				jsonObject.put("message", AppConst.translateErrorId(AppConst.ACTION_ERROR_NOTLOGIN)); 
				jsonObject.put("errcode", AppConst.ACTION_ERROR_NOTLOGIN);
				jsonValue = jsonObject.toString();
				
				return LOGIN;
			}
		}

		if(ActionType.None != actionType) {
			boolean hasPermission = getPermission();
			
			if(!hasPermission) {
				jsonObject.put("success", false);
				jsonObject.put("obj", getEmptyObjString());
				jsonObject.put("message", AppConst.translateErrorId(AppConst.ACTION_ERROR_NORIGHT)); 
				jsonObject.put("errcode", AppConst.ACTION_ERROR_NORIGHT);
				jsonValue = jsonObject.toString();
				
				return LOGIN;
			}
		}
		
		Object obj = getParamObject();
		if(!checkParam(obj)) {
			jsonObject.put("success", false);
			jsonObject.put("obj", getEmptyObjString());
			jsonObject.put("message", message); 
			jsonObject.put("errcode", AppConst.ACTION_ERROR_PARAM_ERROR);
			jsonValue = jsonObject.toString();
			
			return INPUT;
		}
		
		boolean flag = process(obj);
		getLogger().debug(jsonValue);
		
		if(!flag) {
			return ERROR;
		}
		
		return SUCCESS;
	}

	@Override
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setStart(int start) {
		this.start = start;
	}

	@Override
	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public void setLogTranslate(LogTranslate impl) {
		this.logTranslate = impl;
	}

	@Override
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	public void setActionType(ActionType type) {
		this.actionType = type;
	}

	@Override
	public void setForceDelete(boolean force) {
		this.foreceDelete = force;
	}
	
	@Override
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public String getJsonValue() {
		return this.jsonValue;
	}

}
