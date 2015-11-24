package org.cjf.utils.properties;

import java.util.HashMap;
import java.util.Map;

public final class AppConst {
	public static final int DEFAULT_PAGESIZE = 15;
	public static final int DEFAULT_RESULT_LIMIT = 1000;
	public static final int MAX_RESULT_LIMIT = 1000;
	
	public static final String SESSION_USER = "USER";
	public static final String DEFAULT_PASSWORD = "123456";
	
	public static final String EMPTY_JSONOBJ_STR_TYPE1 = "";
	public static final String EMPTY_JSONOBJ_STR_TYPE2 = "{}";
	
	public static final String JSON_RESULT_LIST_COUNT = "totalCount";
	public static final String JSON_RESULT_LIST = "list";
	
	public static final int STATUS_ENABLE = 1;
	public static final int STATUS_DISABLE = 2;
	
	public enum ImportExportFileType {
		XLS,
		XLSX
	}
	
	public enum ClientType {
		Unity,
		ExtJS,
		WebBrowser,
		Unknown
	}
	
	public enum ImportExportType {
		InsertIgnoreExist,	//增加，已存在的记录将被忽略
		UpdateExist,		//修改，未存在的记录将被忽略
		InsertAndUpdate,	//增加未存在的，修改已存在的
		InsertExistFailed	//增加，如果有存在的记录将会失败
	}
	
	public enum ActionType {
		Create,
		Modify,
		Remove,
		GetList,
		GetOne,
		Special,
		Export,
		Import,
		JustLogin,
		None
	}
	
	public static final int ACTION_ERROR_NOERROR 			= 0;
	public static final int ACTION_ERROR_NOTLOGIN 			= 1;
	public static final int ACTION_ERROR_NORIGHT			= 2;
	public static final int ACTION_ERROR_PROCESS			= 3;
	
	public static final int ACTION_ERROR_LOGIN_USERORPWD 	= 10;
	public static final int ACTION_ERROR_LOGIN_USERDISABLED = 11;
	
	public static final int ACTION_ERROR_PROPERTITES		= 100;
	public static final int ACTION_ERROR_FILESYSTEM			= 200;
	
	public static final int ACTION_ERROR_PARAM_NOTENOUGH	= 2000;
	public static final int ACTION_ERROR_PARAM_PAGEERROR	= 2001;
	public static final int ACTION_ERROR_PARAM_ERROR		= 2002;
	
	public static final int ACTION_ERROR_DB					= 3000;
	public static final int ACTION_ERROR_DB_KEY_DUPLICATE	= 3001;
	public static final int ACTION_ERROR_DB_DATA_NOT_EXIST  = 3002;
	public static final int ACTION_ERROR_DB_RELATIONDATA	= 3003;
	public static final int ACTION_ERROR_DB_GET_IT_BACK		= 3004;
	
	private static Map<Integer, String> errorDescriptMap = new HashMap<Integer, String>();
	static {
		errorDescriptMap.put(ACTION_ERROR_NOERROR, "无错误");
		errorDescriptMap.put(ACTION_ERROR_NOTLOGIN, "未登录");
		errorDescriptMap.put(ACTION_ERROR_NORIGHT, "无权限");
		errorDescriptMap.put(ACTION_ERROR_PROCESS, "处理过程发生错误");
		
		errorDescriptMap.put(ACTION_ERROR_LOGIN_USERORPWD, "用户编码或口令");
		errorDescriptMap.put(ACTION_ERROR_LOGIN_USERDISABLED, "用户被禁用");
		
		errorDescriptMap.put(ACTION_ERROR_FILESYSTEM, "文件系统错误");
		
		errorDescriptMap.put(ACTION_ERROR_PARAM_NOTENOUGH, "没有足够的参数");
		errorDescriptMap.put(ACTION_ERROR_PARAM_PAGEERROR, "给定的参数错误，比如页数大小为0或复数等");
		errorDescriptMap.put(ACTION_ERROR_PARAM_ERROR, "给定的参数错误");
		
		errorDescriptMap.put(ACTION_ERROR_DB, "数据库错误，未指定错误原因");
		errorDescriptMap.put(ACTION_ERROR_DB_KEY_DUPLICATE, "增加时，逻辑主键冲突（表的物理主键是自增的，没有实际含义）");
		errorDescriptMap.put(ACTION_ERROR_DB_DATA_NOT_EXIST, "修改时，被修改的数据在数据库中已不存在");
		errorDescriptMap.put(ACTION_ERROR_DB_RELATIONDATA, "删除的数据存在被引用的情况时");
		errorDescriptMap.put(ACTION_ERROR_DB_GET_IT_BACK, "憎增加或修改成功后，取回该数据为空");
	}
	
	public static final String translateErrorId(int errorId) {
		if(errorDescriptMap.containsKey(errorId)) {
			return errorDescriptMap.get(errorId);
		} else {
			return "";
		}
	} 
	
	
}
