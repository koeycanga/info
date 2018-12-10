/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */

package com.ichangyun.InforAnalyaizer.controller.usermanage;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoVo;
import com.ichangyun.InforAnalyaizer.service.userInfo.UserInfoService;
import com.ichangyun.InforAnalyaizer.utils.PBKDF2;

/**
 * 前台页面用户请求的Controller
 * @author renhao
 * 2018-11-12 18:13
 */

@RestController
@RequestMapping("/userfront")
public class UserFrontController {

	@Autowired
    private UserInfoService userInfoService;
	
	
	@RequestMapping("/updateUser")	
	public Object updateUser(UserInfoVo vo,String oripwd,String changePwd,HttpSession session) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String res = "ok"; 
		
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		
		UserInfoVo s_vo = this.userInfoService.queryUserByNum(vo.getUnum());
		 
		String passwd  = PBKDF2.getPBKDF2(oripwd,DatatypeConverter.printHexBinary((s_vo.getUid()/*+vo.getUname()*/).getBytes()));
		
		if(!passwd.equals(s_vo.getUpwd())) {
			res = "pwd not right";
		}else {
			try {
	            this.userInfoService.updateUser2_byold(vo,u,changePwd);
	        } catch (Exception e) {
	        	res = "fault";
	        }
		}
		 
		 return res;
	}
	
}
