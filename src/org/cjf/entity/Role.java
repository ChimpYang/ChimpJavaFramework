package org.cjf.entity;

public class Role implements Entity {

	private static final long serialVersionUID = 1L;

	private int id;
	private String roleCode;
	private String roleName;
	private String roleDesc;
	private int currentStatus;
	private int maxUserNumber;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	public int getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	public int getMaxUserNumber() {
		return maxUserNumber;
	}

	public void setMaxUserNumber(int maxUserNumber) {
		this.maxUserNumber = maxUserNumber;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {  
		return super.clone();
	}
}
