package com.ishangyun.InforAnalyaizer.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ishangyun.InforAnalyaizer.model.User;

/**
�û��Ƿ��¼������
*/
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //��ȡ�����RUi:ȥ��http:localhost:8080�ⲿ��ʣ�µ�
        String uri = request.getRequestURI();
        System.out.println(uri);
        //UTL:����login.jsp�ǿ��Թ������ʵģ�������URL���������ؿ���
       /* if (uri.indexOf("/login") >= 0) {
            return true;
        }*/
     
        //��ȡsession
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //�ж�session���Ƿ����û����ݣ�����У��򷵻�true����������ִ��
        if (user != null) {
            return true;
        }
        //�����������ĸ�����ʾ��Ϣ����ת������¼ҳ��
        //request.setAttribute("msg", "����û�е�¼�����ȵ�¼��");
        //request.getRequestDispatcher("/InforAnalyaizer/login.jsp").forward(request, response);
        response.sendRedirect("/InforAnalyaizer/login.jsp");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
