/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.controller.login;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.service.userInfo.UserService;
import com.ichangyun.InforAnalyaizer.utils.ImageUtil;

/**
 * ǰ��&��̨��½����Controller
 *
 * @author renhao
 * @date 2018/11/09
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    // �û��鱨Service
    @Autowired
    private  UserService userService;

    /**
     * Cookieȡ��
     *
     * @param request Login����
     * @return ����Cookies
     */
    @RequestMapping("/cookies")
    public Object cookie(HttpServletRequest request) {

        String str = "{";
        // �����������ݣ��ҵ�cookie����
        Cookie[] cookies = request.getCookies();
        String cookie_name = "";
        String cookie_pwd = "";
        if (cookies!=null) {
            for(int i=0;i<cookies.length;i++){
                if(cookies[i].getName().equals(CommBean.COOKIE_NAME)) {
                    cookie_name="\"username\":"+"\""+cookies[i].getValue()+"\"";
                }
                if(cookies[i].getName().equals(CommBean.COOKIE_PWD)) {
                    cookie_pwd = "\"userpwd\":"+"\""+cookies[i].getValue()+"\"";
                }
            }
        }
        str+=cookie_name+","+cookie_pwd;
        str+="}";
        return str;
    }

    /**
     * Login����
     *
     * @param username        �û���
     * @param passwd          ����
     * @param checkCode       ��֤��
     * @param isremberusrname �Ƿ��ס�û���
     * @param request         ����
     * @param response        ����
     * @param session         Session
     * @return
     */
    @RequestMapping("/login")
    public Object userLogin(String username, String passwd, String checkCode,
            String isremberusrname, HttpServletRequest request,
            HttpServletResponse response, HttpSession session) {
    	
        String res = "ok";
        // ȡ��session�е���֤��
        String s_checkCode = (String) session.getAttribute("checkCode");
        // ��֤����ȷ���ж�
        if(!checkCode.toUpperCase().equals(s_checkCode.toUpperCase())) {
            // �������֤���session�е���֤��ʱ���쳣Message����������ж�
            res = "checkCode_n_ok";   // ��֤�벻��
        }else {
            // ͨ��������û���&���룬ȡ���û��鱨
            User user = userService.isUserExist(username, passwd);
            // ȡ���û��鱨Ϊ��ʱ���쳣Message����������ж�
            if(user==null) {
                //�û������������ TODO messageID����
                res = "";
            }else {

                // ��¼�ɹ�
                dealSession(user,session);
                if(isremberusrname.equals("true")) {  // ��ס�û�
                    userService.addCookie(response,user,passwd);
                }else {  // ����ס�û�
                    userService.delCookie(request,response);
                }
            }
        }
        return res;
    }

    /**
     * Session��UserInfo����
     *
     * @param user �û��鱨
     * @param session
     */
    private void dealSession(User user, HttpSession session) {
        session.setAttribute(CommBean.SESSION_NAME, user);
        // �û���
        session.setAttribute("UserInfo.UserID",user.getUser_ID());
        // ����
        session.setAttribute("UserInfo.Password", user.getPassword());
        // ����
        session.setAttribute("UserInfo.Name",user.getName());
        // ��������
        session.setAttribute("UserInfo.Department",user.getDepartment());
        // ��ϵ��ʽ
        session.setAttribute("UserInfo.Telephone", user.getTelephone());
        // ����
        session.setAttribute("UserInfo.EMail", user.getEMail());
        // �û���ɫID
        session.setAttribute("UserInfo.UserRole_ID",user.getUserRole_ID());
        // ������ʱ
        session.setAttribute("UserInfo.UpdateDateTime",user.getUpdateDateTime());
        // �û���ɫ��
        session.setAttribute("UserInfo.UserRoleName", user.getUserRoleName());
        // Ȩ��
        session.setAttribute("UserInfo.Authority", user.getAuthority());
    }

    /**
     * Login�ɹ������ϵͳ
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/toLogin")
    public Object login(HttpServletRequest request, HttpSession session) {
        request.setAttribute("user",session.getAttribute(CommBean.SESSION_NAME));
        return new ModelAndView("main");
    }

    /**
     * valicode��֤��ȡ�ã���ʾ�ڻ�����
     *
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("/valicode")
    public void valicode(HttpServletResponse response, HttpSession session) throws IOException {
        // ����ͼƬ��������ͼƬ
        // ��һ�����������ɵ���֤�룬�ڶ������������ɵ�ͼƬ
        Object[] objs = ImageUtil.createImage();
        // ����֤�����Session
        session.setAttribute("checkCode",objs[0]);
        // ��ͼƬ����������
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
}
