package org.cjf.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.cjf.biz.UserBiz;
import org.cjf.entity.User;
import org.cjf.utils.properties.AppConst;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UserLoginAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = -1621118639401445484L;

	private HttpServletRequest request;
	
	private String userCode;
	private String password;
	private UserBiz biz;
	
	private User item = null;
	public void validate() {
		
		boolean error = false;
		
		if(null == userCode || "".equals(userCode)) {
			addFieldError( "userCode", "userCode is required." );
			error = true;
		}
		
		if(null == password || "".equals(password)) {
			addFieldError( "password", "password is required." );
			error = true;
		}
		
		if(null == biz) {
			addFieldError( "biz", "BizImpl is required." );
			error = true;
		}
		
		if(error) {
			return ;
		}
		
		item = new User();
		item.setUserCode(userCode);
		item.setPassword(password);
		item = biz.login(item);
		if(null == item) {
			addFieldError( "password", "userCode or password is incorrect." );
		} else {
			System.out.println(item);
		}
	}
	
	@Override
	public String execute() throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute(AppConst.SESSION_USER, item);
		
		session.setAttribute("userCode", item.getUserCode());
		System.out.println("save session userCode:" + item.getUserCode());
		
		return Action.SUCCESS;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public UserBiz getBiz() {
		return biz;
	}

	public void setBiz(UserBiz biz) {
		this.biz = biz;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
