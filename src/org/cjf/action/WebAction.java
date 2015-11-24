package org.cjf.action;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.cjf.biz.BusinessLogic;
import org.cjf.entity.Entity;
import org.cjf.logtranslate.LogTranslate;
import org.cjf.utils.properties.AppConst.ActionType;
import org.cjf.utils.properties.AppConst.ClientType;

public interface WebAction<T extends Entity> extends ServletRequestAware {

	/**
	 * 业务逻辑实例
	 * @param biz
	 */
	void setBiz(BusinessLogic<T> biz);
	
	/**
	 * 分页-开始
	 * @param start
	 */
	void setStart(int start);
	
	/**
	 * 分页-个数
	 * @param limit
	 */
	void setLimit(int limit);
	
	/**
	 * 客户端传递的JSON参数
	 * @param jsonString
	 */
	void setJsonString(String jsonString);
	
	/**
	 * 
	 * @param impl 设置日志业务信息翻译实现类
	 */
	void setLogTranslate(LogTranslate impl);
	
	/**
	 * 控制权限时使用
	 * @param resourceId 资源编号
	 */
	void setResourceId(int resourceId);
	
	/**
	 * 控制权限时使用
	 * @param type Action的操作类型（增、删、改、查等）
	 */
	void setActionType(ActionType type);
	
	/**
	 * 删除数据时，如果存在关联数据（或称子表数据）是否强制删除
	 * 如果该标识为false，则程序应该按照不允许删除来处理
	 * @param force
	 */
	void setForceDelete(boolean force);

	/**
	 * 区分调用Action的客户端类别
	 * @param clientType
	 */
	void setClientType(ClientType clientType);
}
