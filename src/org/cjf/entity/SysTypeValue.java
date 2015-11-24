package org.cjf.entity;

/**
 * @author CJF Generator
 * @version 1.0
 */
public class SysTypeValue implements Entity {

	private static final long serialVersionUID = 1L;

	private int id;
	private String sysTypeCode;
	private String sysValueCode;
	private String sysValueName;
	private int sysFlag;
	private String sysValue;
	private String sysValue2;
	private String sysValue3;

	private String sysTypeName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getSysTypeCode() {
		return sysTypeCode;
	}

	public void setSysTypeCode(String sysTypeCode) {
		this.sysTypeCode = sysTypeCode;
	}
	
	public String getSysValueCode() {
		return sysValueCode;
	}

	public void setSysValueCode(String sysValueCode) {
		this.sysValueCode = sysValueCode;
	}
	
	public String getSysValueName() {
		return sysValueName;
	}

	public void setSysValueName(String sysValueName) {
		this.sysValueName = sysValueName;
	}
	
	public int getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(int sysFlag) {
		this.sysFlag = sysFlag;
	}
	
	public String getSysValue() {
		return sysValue;
	}

	public void setSysValue(String sysValue) {
		this.sysValue = sysValue;
	}
	
	public String getSysValue2() {
		return sysValue2;
	}

	public void setSysValue2(String sysValue2) {
		this.sysValue2 = sysValue2;
	}
	
	public String getSysValue3() {
		return sysValue3;
	}

	public void setSysValue3(String sysValue3) {
		this.sysValue3 = sysValue3;
	}
	
	public String getSysTypeName() {
		return sysTypeName;
	}

	public void setSysTypeName(String sysTypeName) {
		this.sysTypeName = sysTypeName;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {  
		return super.clone();
	}
}
