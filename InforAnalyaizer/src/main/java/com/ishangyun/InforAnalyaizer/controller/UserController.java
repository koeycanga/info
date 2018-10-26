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
 * controller��ӿ���
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
        System.out.println("���Գɹ�~~"+id);
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
    	Cookie[] cookies = request.getCookies();//�����������ݣ��ҵ�cookie����
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
    		res = "checkCode_n_ok";    //��֤�벻��ȷ
    	}else {
    		User user = userService.isUserExist(username,passwd);
    		if(user==null) { //�û������������
    			res = "";
    		}else {   //��¼�ɹ�
    		    session.setAttribute("user", user);
    		    if(isremberusrname.equals("true")) {  //��ס�û�
    		    	userService.addCookie(response,user);
    	    	}else {
    	    		userService.delCookie(request,response);
    	    	}
    		}
    	}
    	return res;
    }
 
    @RequestMapping("/toLogin")
    public Object login() {

    	return new ModelAndView("User");
    }
    
    @RequestMapping("/valicode")
    public void valicode(HttpServletResponse response,HttpSession session) throws IOException {
    	//����ͼƬ��������ͼƬ
        //��һ�����������ɵ���֤�룬�ڶ������������ɵ�ͼƬ
        Object[] objs = ImageUtil.createImage();
        //����֤�����Session
        session.setAttribute("checkCode",objs[0]);
        //��ͼƬ����������
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
}

