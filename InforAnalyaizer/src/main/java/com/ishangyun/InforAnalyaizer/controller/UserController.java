package com.ishangyun.InforAnalyaizer.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ishangyun.InforAnalyaizer.model.CommBean;
import com.ishangyun.InforAnalyaizer.model.User;
import com.ishangyun.InforAnalyaizer.service.UserService;
import com.ishangyun.InforAnalyaizer.utils.ImageUtil;
 
/**
 * created by viking on 2018/07/04
 * controller层接口类
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private  UserService userService;
    Logger log = Logger.getLogger(UserController.class);
 
    
    @RequestMapping("/select")
    @ResponseBody
    public Object userTest(int id){
        System.out.println("测试成功~~"+id);
        List<User> user = userService.getUser(id);
        System.out.println(user.toString());
        log.info(user);
        return user;
    }
    @RequestMapping("/addUser")
    public Object insertUser(int id,String name,int age,String sex){
           userService.insertUser(id,name,age,sex);
       return "OK";
    }
    @RequestMapping("/selectAll")
    public Object selectAll(){
        return userService.selectAll();
    }
    @RequestMapping("/update")
    public Object update(){
        return userService.update();
    }
    
    @RequestMapping("/cookies")
    public Object cookie(HttpServletRequest request) {
    	
    	String str = "{";
    	Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组
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
    
    @RequestMapping("/login")
    public Object userLogin(String username,String passwd,String checkCode,String isremberusrname,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	String res = "ok";
    	
    	String s_checkCode = (String) session.getAttribute("checkCode");
    	
    	if(!checkCode.toUpperCase().equals(s_checkCode.toUpperCase())) {
    		res = "checkCode_n_ok";    //验证码不正确
    	}else {
    		User user = userService.isUserExist(username,passwd);
    		if(user==null) { //用户名或密码错误
    			res = "";
    		}else {   //登录成功
    		    session.setAttribute("user", user);
    		    if(isremberusrname.equals("true")) {  //记住用户
    		    	userService.addCookie(response,user);
    	    	}else {  //不记住用户
    	    		userService.delCookie(request,response);
    	    	}
    		}
    	}
    	return res;
    }
 
    @RequestMapping("/toLogin")
    public Object login() {

    	return new ModelAndView("user/test");
    }
    
    @RequestMapping("/valicode")
    public void valicode(HttpServletResponse response,HttpSession session) throws IOException {
    	//利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = ImageUtil.createImage();
        //将验证码存入Session
        session.setAttribute("checkCode",objs[0]);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
}

