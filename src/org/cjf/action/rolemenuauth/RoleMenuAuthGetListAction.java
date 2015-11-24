package org.cjf.action.rolemenuauth;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.cjf.action.AbstractWebAction;
import org.cjf.biz.BusinessLogic;
import org.cjf.entity.RoleMenuAuth;
import org.cjf.util.json.JSONUtil;
import org.cjf.utils.properties.AppConst;
import org.cjf.utils.properties.AppConst.ActionType;

/**
 * 对应客户端的角色菜单权限设置功能
 * @author Chimp
 *
 */
public class RoleMenuAuthGetListAction extends AbstractWebAction<RoleMenuAuth> {

	private static final long serialVersionUID = 5728484408049423969L;
	private static final Logger logger = Logger.getLogger(RoleMenuAuthGetListAction.class);
	
	public RoleMenuAuthGetListAction() {
		actionType = ActionType.GetList;
	}
	
	private BusinessLogic<RoleMenuAuth> biz;
	@Override
	public void setBiz(BusinessLogic<RoleMenuAuth> biz) {
		this.biz = biz;
	}

	
	List<RoleMenuAuth> nodes = new ArrayList<RoleMenuAuth>();
	@Override
	protected boolean process(Object obj) {
		RoleMenuAuth t = (RoleMenuAuth)obj;
		
		List<RoleMenuAuth> rmaList = biz.getList(t, start, AppConst.MAX_RESULT_LIMIT);
		for(RoleMenuAuth item : rmaList ) {
			item.setRoleCode(t.getRoleCode());
		}
		
		getChildNode(null, rmaList, "");
		
		jsonValue = JSONUtil.listToJsonStr(nodes, "[]");
		
		return true;
	}

	@Override
	protected Object getParamObject() {
		if(null == jsonString || "".equals(jsonString)) {
			message = "null param object.";
			return null;
		}
		
		RoleMenuAuth item = (RoleMenuAuth)JSONUtil.jsonStrToObj(jsonString, new RoleMenuAuth());
		
		return item;
	}

	@Override
	protected boolean checkParam(Object obj) {
		if(null == obj) {
			logger.error("null param");
			return false;
		}
		
		RoleMenuAuth item = (RoleMenuAuth)obj;
		if(null == item.getRoleCode() || "".equals(item.getRoleCode())) {
			logger.error("null or empty roleCode");
		}
		
		return true;
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected boolean getPermission() {
		//TODO;
		return true;
	}
	
	private void getChildNode(RoleMenuAuth parentNode, List<RoleMenuAuth> list, String parentCode) {
		RoleMenuAuth node;
		List<RoleMenuAuth> tempList = new ArrayList<RoleMenuAuth>();
		List<RoleMenuAuth> children = null;
		for(RoleMenuAuth rma : list) {
			if(rma.getParentMenuCode().equals(parentCode)) {
				node = rma;
				
				if(null == parentNode) {
					children = nodes;
				} else {
					children = parentNode.getChildren();
					if(null == children) {
						children = new ArrayList<RoleMenuAuth>();
						parentNode.setChildren(children);
						parentNode.setLeaf(false);
					}
				}
				
				children.add(node);
				tempList.add(rma);
			}//if equal parentCode
		}//for
		
		for(RoleMenuAuth rma : tempList) {
			list.remove(rma);
		}
		tempList.clear();
		tempList = null;
		
		if(null != children) {
			for(RoleMenuAuth tempNode : children) {
				getChildNode(tempNode, list, tempNode.getMenuCode());
			}
		}
	}

}
