package org.cjf.entity;

import java.util.List;

public class MenuTreeNode implements Entity {
	
	private static final long serialVersionUID = 9112279943259673514L;
	
	private int id;
	private String menuCode;
	private String menuMemo;
	private String text;
	private String menuAction;
	private String menuTypeCode;
	private int dispOrder;
	private String parentMenuCode;
	private String systemTypeCode;
	private int currentStatus;
	private String menuIcon;
	private String permission;
	private boolean leaf;
	private String menuContent;
	private List<MenuTreeNode> children;
	
	public MenuTreeNode() {
		setLeaf(true);
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public List<MenuTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTreeNode> children) {
		this.children = children;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {  
		return super.clone();
	}
	
	public static MenuTreeNode cloneFromRMP(RoleMenuPermission rmp) {
		MenuTreeNode node = new MenuTreeNode();
		
		node.setCurrentStatus(rmp.getCurrentStatus());
		node.setDispOrder(rmp.getDispOrder());
		node.setMenuIcon(rmp.getMenuIcon());
		node.setId(rmp.getId());
		node.setMenuAction(rmp.getMenuAction());
		node.setMenuCode(rmp.getMenuCode());
		node.setMenuTypeCode(rmp.getMenuTypeCode());
		node.setParentMenuCode(rmp.getParentMenuCode());
		node.setSystemTypeCode(rmp.getSystemTypeCode());
		node.setText(rmp.getMenuTitle());
		node.setPermission(rmp.getStringPermission());
		node.setMenuContent(rmp.getMenuContent());
		
		node.setMenuMemo("");
		
		return node;
	}
	

	public static MenuTreeNode cloneFromMenu(Menu item) {
		MenuTreeNode node = new MenuTreeNode();
		
		node.setCurrentStatus(item.getCurrentStatus());
		node.setMenuMemo(item.getMenuMemo());
		node.setDispOrder(item.getDispOrder());
		node.setMenuIcon(item.getMenuIcon());
		node.setId(item.getId());
		node.setMenuAction(item.getMenuAction());
		node.setMenuCode(item.getMenuCode());
		node.setMenuTypeCode(item.getMenuTypeCode());
		node.setParentMenuCode(item.getParentMenuCode());
		node.setSystemTypeCode(item.getSystemTypeCode());
		node.setText(item.getMenuTitle());
		node.setMenuContent(item.getMenuContent());
		
		node.setPermission("");
		
		return node;
	}
	
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuContent() {
		return menuContent;
	}

	public void setMenuContent(String menuContent) {
		this.menuContent = menuContent;
	}

	public String getMenuMemo() {
		return menuMemo;
	}

	public void setMenuMemo(String menuMemo) {
		this.menuMemo = menuMemo;
	}
	
}
