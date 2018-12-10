/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.interceptor;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;

/**
 * 用户 权限验证 拦截器
 * @author renhao
 * Date:2018-11-9
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        
		WebApplicationContext wa = ContextLoader.getCurrentWebApplicationContext();
    	ServletContext servletContext = wa.getServletContext();
		
		//获取请求的RUi:去除http:localhost:8080这部分剩下的
        String uri = request.getRequestURI();
      //  System.out.println(uri);
        //UTL:除了login.jsp是可以公开访问的，其他的URL都进行拦截控制
       /* if (uri.indexOf("/login") >= 0) {
            return true;
        }*/
         String root_url = request.getServletContext().getContextPath();
         
        //获取session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(CommBean.SESSION_NAME);
        //判断session中是否有用户数据，如果有，则返回true，继续向下执行
        if (user != null) {

        	String auth = user.getAuthority();
        	
        	List<String> au_list = (List<String>) servletContext.getAttribute("au_list");  //获得需要验证的权限列表
        	
        	for(int i=0;i<au_list.size();i++) {
        		if(uri.contains(au_list.get(i))) {
        			if(auth.charAt(i)=='0') {
        				response.sendRedirect(root_url+"/error/no_authority.jsp");
            			return false;
        			}
        		}
        	}
        
            return true;
        }
        //不符合条件的给出提示信息，并转发到登录页面
        //request.setAttribute("msg", "您还没有登录，请先登录！");
        //request.getRequestDispatcher("/InforAnalyaizer/login.jsp").forward(request, response);
        response.sendRedirect(root_url+"/error/session_out.jsp");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
