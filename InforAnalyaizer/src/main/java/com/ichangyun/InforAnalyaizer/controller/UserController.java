package com.ichangyun.InforAnalyaizer.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ichangyun.InforAnalyaizer.model.BaseBean;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoVo;
import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;
import com.ichangyun.InforAnalyaizer.service.UserService;
import com.ichangyun.InforAnalyaizer.service.userInfo.UserInfoService;
import com.ichangyun.InforAnalyaizer.service.usermanage.RoleService;
import com.ichangyun.InforAnalyaizer.utils.ImageUtil;
 
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
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleService roleService;
    @RequestMapping("/addUser")    											//����û�
    public Map<String, String> addUser(UserInfoVo user,HttpSession session){
    	User u = (User) session.getAttribute(CommBean.SESSION_NAME);
    	Map<String, String>map = new HashMap<>();
    	try {
    		this.userInfoService.addUser(user,u);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("msg", "�û����Ѵ���");
			return map;
		}
    	map.put("msg", "ok");
    	return map;
    }
    @RequestMapping("/getRoles")			//��ѯ���н�ɫ
    public List<RoleManageBean> getRoles(){
    	return this.roleService.queryAllRole();
    }
    @RequestMapping("/updateUser")		//�����û���Ϣ
    public Object update(UserInfoVo vo,HttpSession session){
    	User u = (User) session.getAttribute(CommBean.SESSION_NAME);
        this.userInfoService.updateUser(vo,u);
        return "OK";
    }

    @RequestMapping("/toUserList")      //��ת�û���Ϣ����
    public Object toList(){
    	
    	return new ModelAndView( "user/userList");
    	}
    
    @RequestMapping("/queryAll")      //��ѯ�����û���Ϣ���ɴ�����
    public Map<String, Object> queryAll(UserInfoVo vo,BaseBean bb){
    	Map<String, Object> list = this.userInfoService.queryAllUser(vo,bb.getPageNow(),bb.getRowSize());
    	return list;
    }
    @RequestMapping("/delete")
    public Object delete( Integer[] checkedId){
        for (int i : checkedId) {
        	UserInfoVo vo = new UserInfoVo();
        	vo.setUnum(i);
			this.userInfoService.deleteUser(vo);
		}
        return "OK";
    }	
    @RequestMapping("/queryOne")
    public UserInfoVo queryOne(Integer unum){
    	UserInfoVo vo = this.userInfoService.queryUserByNum(unum);
    	return vo;
    }
    @RequestMapping("/CheckId")
    public int CheckId(String uid){
    	int i = this.userInfoService.queryCountById(uid);
    	return i;
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
    		    session.setAttribute(CommBean.SESSION_NAME, user);
    		    if(isremberusrname.equals("true")) {  //��ס�û�
    		    	userService.addCookie(response,user);
    	    	}else {  //����ס�û�
    	    		userService.delCookie(request,response);
    	    	}
    		}
    	}
    	return res;
    }
 
    @RequestMapping("/toLogin")
    public Object login() {

    	return new ModelAndView("main");
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
    @RequestMapping("/thisUser")
    public UserInfoVo thisUser(HttpSession session) {
    	User u = (User)session.getAttribute(CommBean.SESSION_NAME);
    	UserInfoVo vo = this.userInfoService.queryById(u.getUser_ID());
    	RoleManageBean role = this.roleService.queryById(vo.getUrole());
    	vo.setUrolename(role.getUserRoleName());
    	return vo;
    }
}

