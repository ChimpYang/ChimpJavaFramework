package org.cjf.entity;

/**
 * @author CJF Generator
 * @version 1.0
 */
public class Menu implements Entity {

	private static final long serialVersionUID = 1L;

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
	private int nodeLeaf;
	private String menuIcon;
	private String menuContent;

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
	
	@Override
	protected Object clone() throws CloneNotSupportedException {  
		return super.clone();
	}

	public String getMenuContent() {
		return menuContent;
	}

	public void setMenuContent(String menuContent) {
		this.menuContent = menuContent;
	}

	public int getNodeLeaf() {
		return nodeLeaf;
	}

	public void setNodeLeaf(int nodeLeaf) {
		this.nodeLeaf = nodeLeaf;
	}

}
