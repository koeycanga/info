/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
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
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.service.userInfo.UserService;
import com.ichangyun.InforAnalyaizer.utils.ImageUtil;

/**
 * 前端&后台登陆画面Controller
 *
 * @author renhao
 * @date 2018/11/09
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    // 用户情报Service
    @Autowired
    private  UserService userService;

    /**
     * Cookie取得
     *
     * @param request Login请求
     * @return 返回Cookies
     */
    @RequestMapping("/cookies")
    public Object cookie(HttpServletRequest request) {

        String str = "{";
        // 根据请求数据，找到cookie数组
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
     * Login处理
     *
     * @param username        用户名
     * @param passwd          密码
     * @param checkCode       验证码
     * @param isremberusrname 是否记住用户名
     * @param request         请求
     * @param response        返回
     * @param session         Session
     * @return
     */
    @RequestMapping("/login")
    public Object userLogin(String username, String passwd, String checkCode,
            String isremberusrname, HttpServletRequest request,
            HttpServletResponse response, HttpSession session) {
    	
        String res = "";
        // 取得session中的验证码
        String s_checkCode = (String) session.getAttribute("checkCode");
        // 验证码正确性判断
        if(!checkCode.toUpperCase().equals(s_checkCode.toUpperCase())) {
            // 输入的验证码≠session中的验证码时，异常Message输出，处理中断
            res = "checkCode_n_ok";   // 验证码不对
        }else {
            // 通过输入的用户名&密码，取得用户情报
            User user = userService.isUserExist(username, passwd);
            // 取得用户情报为空时，异常Message输出，处理中断
            if(user==null) {
                //用户名或密码错误 TODO messageID？？
                res = "";
            }else {

                // 登录成功
                dealSession(user,session);
               
                if(isremberusrname.equals("true")) {  // 记住用户
                    userService.addCookie(response,user,passwd);
                }else {  // 不记住用户
                    userService.delCookie(request,response);
                }
                res = "success"+user.getAuthority();
            }
        }
        return res;
    }

    /**
     * Session的UserInfo保存
     *
     * @param user 用户情报
     * @param session
     */
    private void dealSession(User user, HttpSession session) {
        session.setAttribute(CommBean.SESSION_NAME, user);
        // 用户名
        session.setAttribute("UserInfo.UserID",user.getUser_ID());
        // 密码
        session.setAttribute("UserInfo.Password", user.getPassword());
        // 姓名
        session.setAttribute("UserInfo.Name",user.getName());
        // 所属部门
        session.setAttribute("UserInfo.Department",user.getDepartment());
        // 联系方式
        session.setAttribute("UserInfo.Telephone", user.getTelephone());
        // 邮箱
        session.setAttribute("UserInfo.EMail", user.getEMail());
        // 用户角色ID
        session.setAttribute("UserInfo.UserRole_ID",user.getUserRole_ID());
        // 更新日时
        session.setAttribute("UserInfo.UpdateDateTime",user.getUpdateDateTime());
        // 用户角色名
        session.setAttribute("UserInfo.UserRoleName", user.getUserRoleName());
        // 权限
        session.setAttribute("UserInfo.Authority", user.getAuthority());
    }

    /**
     * Login成功后进入系统
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/toLogin")
    public Object login(HttpServletRequest request, HttpSession session) {

        return new ModelAndView("main");
    }

    /**
     * valicode验证码取得，表示在画面上
     *
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("/valicode")
    public void valicode(HttpServletResponse response, HttpSession session) throws IOException {
        // 利用图片工具生成图片
        // 第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = ImageUtil.createImage();
        // 将验证码存入Session
        session.setAttribute("checkCode",objs[0]);
        // 将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
}
