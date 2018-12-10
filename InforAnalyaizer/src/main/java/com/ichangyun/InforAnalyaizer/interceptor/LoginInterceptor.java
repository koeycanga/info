/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
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
 * �û� Ȩ����֤ ������
 * @author renhao
 * Date:2018-11-9
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        
		WebApplicationContext wa = ContextLoader.getCurrentWebApplicationContext();
    	ServletContext servletContext = wa.getServletContext();
		
		//��ȡ�����RUi:ȥ��http:localhost:8080�ⲿ��ʣ�µ�
        String uri = request.getRequestURI();
      //  System.out.println(uri);
        //UTL:����login.jsp�ǿ��Թ������ʵģ�������URL���������ؿ���
       /* if (uri.indexOf("/login") >= 0) {
            return true;
        }*/
         String root_url = request.getServletContext().getContextPath();
         
        //��ȡsession
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(CommBean.SESSION_NAME);
        //�ж�session���Ƿ����û����ݣ�����У��򷵻�true����������ִ��
        if (user != null) {

        	String auth = user.getAuthority();
        	
        	List<String> au_list = (List<String>) servletContext.getAttribute("au_list");  //�����Ҫ��֤��Ȩ���б�
        	
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
        //�����������ĸ�����ʾ��Ϣ����ת������¼ҳ��
        //request.setAttribute("msg", "����û�е�¼�����ȵ�¼��");
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
