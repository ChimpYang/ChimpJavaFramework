package org.cjf.entity;

import java.util.Date;

import org.cjf.utils.properties.AppConst.ActionType;

public class SystemLog implements Entity {

	private static final long serialVersionUID = 1522984683508562933L;

	private int id;
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 保存userId是因为系统允许修改用户编码，这样就无法仅仅通过用户编码日志知道谁做了哪些操作
	 */
	private int userId;
	private String userCode;
	private String clientIp;
	private String inputParams;
	private String outputParams;
	private String actionName;

	/**
	 * 执行的开始时间 
	 */
	private Date executeTime;
	
	/**
	 * 执行耗费的毫秒数
	 */
	private long executeMillis;
	private ActionType actionType;
	/**
	 * 该字段使用业务语音描述日志信息，因为inputParams,outputParams,actionName,actionType完全是技术型的，用户很难看懂
	 * 需要一个翻译类将上述技术信息翻译成业务信息
	 */
	private String translateInfo;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getInputParams() {
		return inputParams;
	}

	public void setInputParams(String inputParams) {
		this.inputParams = inputParams;
	}

	public String getOutputParams() {
		return outputParams;
	}

	public void setOutputParams(String outputParams) {
		this.outputParams = outputParams;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public long getExecuteMillis() {
		return executeMillis;
	}

	public void setExecuteMillis(long executeMillis) {
		this.executeMillis = executeMillis;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public String getTranslateInfo() {
		return translateInfo;
	}

	public void setTranslateInfo(String translateInfo) {
		this.translateInfo = translateInfo;
	}

}
