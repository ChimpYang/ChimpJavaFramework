package org.cjf.entity;

/**
 * 给菜单用
 * @author Chimp
 *
 */
public class RoleMenuPermission implements Entity {
	
	private static final long serialVersionUID = -5493820348161128489L;
	
	private int id;
	private String menuCode;
	private String menuMemo;
	private String menuTitle;
	private String menuAction;
	private String menuTypeCode;
	private int dispOrder;
	private String parentMenuCode;
	private String systemTypeCode;
	private int currentStatus;
	private String menuIcon;
	private String menuContent;
	
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
	
	/**
	 * 
	 * @return json数组
	 * 按顺序为： canCreate, canRemove, canModify, canQuery,
				canExport, canImport, canAttachmentCreate, canAttachmentRemove,
				canEnable, canDisable,
				canSpecial1, canSpecial2, canSpecial3
	 */
	public String getStringPermission() {
		String temp = String.format("[%d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d]",
				canCreate, canRemove, canModify, canQuery,
				canExport, canImport, canAttachmentCreate, canAttachmentRemove,
				canEnable, canDisable,
				canSpecial1, canSpecial2, canSpecial3
		);
		
		return temp;
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
	public String getMenuMemo() {
		return menuMemo;
	}
	public void setMenuMemo(String menuMemo) {
		this.menuMemo = menuMemo;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public String getMenuAction() {
		return menuAction;
	}
	public void setMenuAction(String menuAction) {
		this.menuAction = menuAction;
	}
	public String getMenuTypeCode() {
		return menuTypeCode;
	}
	public void setMenuTypeCode(String menuTypeCode) {
		this.menuTypeCode = menuTypeCode;
	}
	public int getDispOrder() {
		return dispOrder;
	}
	public void setDispOrder(int dispOrder) {
		this.dispOrder = dispOrder;
	}
	public String getParentMenuCode() {
		return parentMenuCode;
	}
	public void setParentMenuCode(String parentMenuCode) {
		this.parentMenuCode = parentMenuCode;
	}
	public String getSystemTypeCode() {
		return systemTypeCode;
	}
	public void setSystemTypeCode(String systemTypeCode) {
		this.systemTypeCode = systemTypeCode;
	}
	public int getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
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

	public String getMenuContent() {
		return menuContent;
	}

	public void setMenuContent(String menuContent) {
		this.menuContent = menuContent;
	}

}
