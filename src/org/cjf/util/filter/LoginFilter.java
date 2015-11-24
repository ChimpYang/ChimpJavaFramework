package org.cjf.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.cjf.entity.User;
import org.cjf.utils.properties.AppConst;

public class LoginFilter implements Filter {
	
	private static Logger log = Logger.getLogger(LoginFilter.class);

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String contextPath = request.getContextPath();
		String url = request.getRequestURI();
		
//		http://localhost:8080/ChimpJavaWeb/images/bg.jpg
//		log.info(request.getServletPath());///images/bg.jpg
//		log.info(request.getServletContext().getServletContextName());//ChimpJavaWeb
//		log.info(request.getServletContext().getContextPath());//	/ChimpJavaWeb
//		log.info(request.getServletContext().getServerInfo());//Apache Tomcat/7.0.42
//		log.info(contextPath);//	/ChimpJavaWeb
//		log.info(url);//	/ChimpJavaWeb/images/bg.jpg
		
		String subContent = url.replace(contextPath, "");
		
		if(subContent.startsWith("/login")) {
			log.debug("直接访问登录界面");
			filterChain.doFilter(request, response);
			return ;
		}
		
		int includeDo = subContent.indexOf(".do");
		int includeJsp = subContent.indexOf(".jsp");
		
		if(-1 != includeDo || -1 != includeJsp || "/".equals(subContent)) {
			User user = (User)request.getSession().getAttribute(AppConst.SESSION_USER);
			if (null == user) {
				log.debug("访问受控的界面，但未登录");
				response.sendRedirect(contextPath + "/login.jsp");
				return;
			}
		}
		
		filterChain.doFilter(request, response);		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	public static String getExtensionName(String filename) {  
        int dot = filename.lastIndexOf('.');   
        if ((dot > -1) && (dot < (filename.length() - 1))) {   
            return filename.substring(dot+1);   
        }   
        
        return "";
    }  

}
