package com.ichangyun.InforAnalyaizer.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

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
import com.ichangyun.InforAnalyaizer.utils.PBKDF2;
 
/**
 * created by viking on 2018/07/04
 * controller层接口类
 */
@RestController
@RequestMapping("/yhgl/user")
public class UserController {
    @Autowired
    private  UserService userService;
    Logger log = Logger.getLogger(UserController.class);
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleService roleService;
    @RequestMapping("/addUser")    											//添加用户
    public Map<String, String> addUser(UserInfoVo user,HttpSession session){
    	User u = (User) session.getAttribute(CommBean.SESSION_NAME);
    	Map<String, String>map = new HashMap<>();
    	try {
    		this.userInfoService.addUser(user,u);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("msg", "fault");
			return map;
		}
    	map.put("msg", "ok");
    	return map;
    }
    @RequestMapping("/getRoles")			//查询所有角色
    public List<RoleManageBean> getRoles(){
    	return this.roleService.queryAllRole();
    }
    @RequestMapping("/updateUser")		//更新用户信息
    public Object update(UserInfoVo vo,HttpSession session){
    	User u = (User) session.getAttribute(CommBean.SESSION_NAME);

        Map<String, String>map = new HashMap<>();
    	try {
    		this.userInfoService.updateUser(vo,u);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("msg", "fault");
			return map;
		}
    	map.put("msg", "ok");
    	return map;
    }

    @RequestMapping("/toUserList")      //跳转用户信息界面
    public Object toList(){
    	
    	return new ModelAndView( "user/userList");
    	}
    
    @RequestMapping("/queryAll")      //查询所有用户信息，可带条件
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
   
    
    
    @RequestMapping("/thisUser")
    public UserInfoVo thisUser(HttpSession session) {
    	User u = (User)session.getAttribute(CommBean.SESSION_NAME);
    	UserInfoVo vo = this.userInfoService.queryById(u.getUser_ID());
    	RoleManageBean role = this.roleService.queryById(vo.getUrole());
    	vo.setUrolename(role.getUserRoleName());
    	return vo;
    }
    @RequestMapping("/returnPwd")
    public String returnPwd(String upwd,int unum) throws NoSuchAlgorithmException, InvalidKeySpecException{
    	UserInfoVo vo = this.userInfoService.queryUserByNum(unum);
    	String passwd  = PBKDF2.getPBKDF2(upwd,DatatypeConverter.printHexBinary((vo.getUid()+vo.getUname()).getBytes()));
    	return passwd;
    }
}

