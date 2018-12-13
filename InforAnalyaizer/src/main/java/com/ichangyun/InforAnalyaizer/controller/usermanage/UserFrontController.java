/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
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
import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;
import com.ichangyun.InforAnalyaizer.service.userInfo.UserInfoService;
import com.ichangyun.InforAnalyaizer.service.usermanage.RoleService;
import com.ichangyun.InforAnalyaizer.utils.PBKDF2;

/**
 * ǰ̨ҳ���û������Controller
 * @author renhao
 * 2018-11-12 18:13
 */

@RestController
@RequestMapping("/userfront")
public class UserFrontController {

	@Autowired
    private UserInfoService userInfoService;
	
	@Autowired
	private RoleService roleService;
	
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
	
	/**
	 * thisUser�����ص�ǰ��¼�е��û���Ϣ
	 *
	 * @param session
	 * @return UserInfoVo
	 */
	@RequestMapping("/thisUser")
	public UserInfoVo thisUser(HttpSession session) {
		
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		// �����û�id��ѯ�û���Ϣ
		UserInfoVo vo = userInfoService.queryById(u.getUser_ID());
		// �����û��Ľ�ɫID��ѯ��ɫ��Ϣ
		RoleManageBean role = roleService.queryById(vo.getUrole());
		// ���ý�ɫ����
		if(role!=null) {
			vo.setUrolename(role.getUserRoleName());
		}else if(vo.getUsuperuserflag().equals("1")){
			vo.setUrolename("��������Ա");
		}
		return vo;
	}
}
