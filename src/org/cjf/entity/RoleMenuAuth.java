package org.cjf.entity;

import java.util.List;

/**
 * 角色赋予菜单权限用
 * @author Chimp
 *
 */
public class RoleMenuAuth implements Entity {

	private static final long serialVersionUID = -427349584124029471L;
	
	private int id;
	private String menuCode;
	private String menuTitle;
	private String parentMenuCode;
	//只在增加时使用
	private String roleCode;
	
	private int canCreate;
	private int canRemove;
	private int canModify;
	private int canQuery;
	private int canExport;
	private int canImport;
	private int canAttachmentCreate;
	private int canAttachmentRemove;
	private int canEnable;
	private int canDisable;
	private int canSpecial1;
	private int canSpecial2;
	private int canSpecial3;
	
	private boolean leaf;
	private List<RoleMenuAuth> children;
	
	public RoleMenuAuth() {
		this.leaf = true;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public int getCanCreate() {
		return canCreate;
	}
	public void setCanCreate(int canCreate) {
		this.canCreate = canCreate;
	}
	public int getCanRemove() {
		return canRemove;
	}
	public void setCanRemove(int canRemove) {
		this.canRemove = canRemove;
	}
	public int getCanModify() {
		return canModify;
	}
	public void setCanModify(int canModify) {
		this.canModify = canModify;
	}
	public int getCanQuery() {
		return canQuery;
	}
	public void setCanQuery(int canQuery) {
		this.canQuery = canQuery;
	}
	public int getCanExport() {
		return canExport;
	}
	public void setCanExport(int canExport) {
		this.canExport = canExport;
	}
	public int getCanImport() {
		return canImport;
	}
	public void setCanImport(int canImport) {
		this.canImport = canImport;
	}
	public int getCanAttachmentCreate() {
		return canAttachmentCreate;
	}
	public void setCanAttachmentCreate(int canAttachmentCreate) {
		this.canAttachmentCreate = canAttachmentCreate;
	}
	public int getCanAttachmentRemove() {
		return canAttachmentRemove;
	}
	public void setCanAttachmentRemove(int canAttachmentRemove) {
		this.canAttachmentRemove = canAttachmentRemove;
	}
	public int getCanEnable() {
		return canEnable;
	}
	public void setCanEnable(int canEnable) {
		this.canEnable = canEnable;
	}
	public int getCanDisable() {
		return canDisable;
	}
	public void setCanDisable(int canDisable) {
		this.canDisable = canDisable;
	}
	public int getCanSpecial1() {
		return canSpecial1;
	}
	public void setCanSpecial1(int canSpecial1) {
		this.canSpecial1 = canSpecial1;
	}
	public int getCanSpecial2() {
		return canSpecial2;
	}
	public void setCanSpecial2(int canSpecial2) {
		this.canSpecial2 = canSpecial2;
	}
	public int getCanSpecial3() {
		return canSpecial3;
	}
	public void setCanSpecial3(int canSpecial3) {
		this.canSpecial3 = canSpecial3;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getParentMenuCode() {
		return parentMenuCode;
	}
	public void setParentMenuCode(String parentMenuCode) {
		this.parentMenuCode = parentMenuCode;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public List<RoleMenuAuth> getChildren() {
		return children;
	}
	public void setChildren(List<RoleMenuAuth> children) {
		this.children = children;
	}

}
