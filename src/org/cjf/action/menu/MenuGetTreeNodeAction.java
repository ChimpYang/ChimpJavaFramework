package org.cjf.action.menu;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.entity.Menu;
import org.cjf.entity.MenuTreeNode;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

/**
 * 对应客户端的菜单维护功能
 * @author Chimp
 *
 */
public class MenuGetTreeNodeAction extends AbstractWebAction<Menu> {

	private static final long serialVersionUID = 2684489017467299239L;
	private static final Logger logger = Logger.getLogger(MenuGetTreeNodeAction.class);
	
	List<Menu> list = null;
	List<MenuTreeNode> nodes = new ArrayList<MenuTreeNode>();
	
	public MenuGetTreeNodeAction() {
		actionType = ActionType.GetList;
	}
	
	public void getChildNode(MenuTreeNode parentNode, List<Menu> menuList, String parentCode) {
		MenuTreeNode node;
		List<Menu> tempList = new ArrayList<Menu>();
		List<MenuTreeNode> children = null;
		for(Menu item : menuList) {
			if(item.getParentMenuCode().equals(parentCode)) {
				node = new MenuTreeNode();
				node = MenuTreeNode.cloneFromMenu(item);
				
				if(null == parentNode) {
					children = nodes;
				} else {
					children = parentNode.getChildren();
					if(null == children) {
						children = new ArrayList<MenuTreeNode>();
						parentNode.setChildren(children);
						parentNode.setLeaf(false);
					}
				}
				
				children.add(node);
				tempList.add(item);
			}//if equal parentCode
		}//for
		
		for(Menu tmp : tempList) {
			menuList.remove(tmp);
		}
		tempList.clear();
		tempList = null;
		
		if(null != children) {
			for(MenuTreeNode tempNode : children) {
				getChildNode(tempNode, list, tempNode.getMenuCode());
			}
		}
	}
	
	@Override
	protected boolean process(Object obj) {
		Menu item = (Menu)obj;
		
		list = biz.getList(item, start, AppConst.MAX_RESULT_LIMIT);
		
		getChildNode(null, list, "");
		
		jsonValue = JSONUtil.listToJsonStr(nodes, "[]");
		
		return true;
	}

	@Override
	protected Object getParamObject() {
		Menu item = null;
		
		if(null == jsonString || "".equals(jsonString)) {
			item = new Menu();
		} else {
			item = (Menu)JSONUtil.jsonStrToObj(jsonString, new Menu());
		}
		
		return item;
	}

	@Override
	protected boolean checkParam(Object obj) {
		return true;
	}

	@Override
	protected boolean getPermission() {
		//TODO; 得到真实的权限
		return true;
	}
	
	@Override
	protected Logger getLogger() {
		return logger;
	}

	private BusinessLogic<Menu> biz;
	@Override
	public void setBiz(BusinessLogic<Menu> biz) {
		this.biz = (BusinessLogic<Menu>)biz;
	}

}
