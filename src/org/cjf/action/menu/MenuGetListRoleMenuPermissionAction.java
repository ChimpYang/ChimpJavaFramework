package org.cjf.action.menu;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.biz.RoleMenuPermissionBiz;
import org.cjf.entity.MenuTreeNode;
import org.cjf.entity.RoleMenuPermission;
import org.cjf.entity.User;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

/**
 * 对应客户端的左侧导航菜单
 * @author Chimp
 *
 */
public class MenuGetListRoleMenuPermissionAction extends AbstractWebAction<RoleMenuPermission> {

	private static final long serialVersionUID = -6040433229737246909L;
	private static final Logger logger = Logger.getLogger(MenuGetListRoleMenuPermissionAction.class);
	
	private String userCode;
	
	public MenuGetListRoleMenuPermissionAction() {
		actionType = ActionType.JustLogin;
	}
	
	List<RoleMenuPermission> list = null;
	List<MenuTreeNode> nodes = new ArrayList<MenuTreeNode>(); 
	
	@Override
	protected boolean process(Object obj) {
		list = biz.getRoleMenuPermissionByUserCode(userCode, 0, AppConst.MAX_RESULT_LIMIT);
		
		getChildNode(null, list, "");
		
		jsonValue = JSONUtil.listToJsonStr(nodes, "[]");
		
		return true;
	}

	@Override
	protected Object getParamObject() {
		return null;
	}

	@Override
	protected boolean checkParam(Object obj) {
//		不需要从客户端传递用户编码参数，改从Session中获取
//		if(null == userCode || "".equals(userCode)) {
//			return false;
//		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(AppConst.SESSION_USER);
		userCode = user.getUserCode();
		
		logger.info("get user menus: " + userCode);
		
		return true;
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected boolean getPermission() {
		//TODO; 得到真实的权限
		return true;
	}
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	private RoleMenuPermissionBiz biz;
	@Override
	public void setBiz(BusinessLogic<RoleMenuPermission> biz) {
		this.biz = (RoleMenuPermissionBiz)biz;
	}
	
	/*
	 * 根据List结构得到Tree结构
	 */
	public void getChildNode(MenuTreeNode parentNode, List<RoleMenuPermission> menuList, String parentCode) {
		MenuTreeNode node;
		List<RoleMenuPermission> tempList = new ArrayList<RoleMenuPermission>();
		List<MenuTreeNode> children = null;
		for(RoleMenuPermission rmp : menuList) {
			if(rmp.getParentMenuCode().equals(parentCode)) {
				node = new MenuTreeNode();
				node = MenuTreeNode.cloneFromRMP(rmp);
				
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
				tempList.add(rmp);
			}//if equal parentCode
		}//for
		
		for(RoleMenuPermission rmp : tempList) {
			menuList.remove(rmp);
		}
		tempList.clear();
		tempList = null;
		
		if(null != children) {
			for(MenuTreeNode tempNode : children) {
				getChildNode(tempNode, list, tempNode.getMenuCode());
			}
		}
	}

}
