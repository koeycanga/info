/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.controller.usermanage;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ichangyun.InforAnalyaizer.model.BaseBean;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoVo;
import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;
import com.ichangyun.InforAnalyaizer.service.common.service.DBUpdateCheckService;
import com.ichangyun.InforAnalyaizer.service.userInfo.UserInfoService;
import com.ichangyun.InforAnalyaizer.service.usermanage.RoleService;
import com.ichangyun.InforAnalyaizer.utils.DateUtils;
import com.ichangyun.InforAnalyaizer.utils.PBKDF2;

/**
 * Controller���˺Ź���
 *
 * @author ichangyun
 * @date 2018/11/09
 */
@RestController
@RequestMapping("/yhgl/user")
public class UserController {
	Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private DBUpdateCheckService dbUpdateCheckService;
	
	/**
	 * addUser�������û�
	 *
	 * @param user    �û��鱨
	 * @param session HttpSession
	 * @return map
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping("/addUser")
	public Map<String, String> addUser(UserInfoVo user, HttpSession session) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		Map<String, String> map = new HashMap<>();
		// ״̬ѡ��Check
		if (user.getUstatus() == null) {
			map.put("msg", "E0031");
			return map;
		}
		// �����û�����ִ��
		String msg = userInfoService.addUser(user, u);

		// handle exception
		map.put("msg", msg);

		return map;
	}

	/**
	 * getRoles����ѯ���н�ɫ
	 *
	 * @return List<RoleManageBean>
	 */
	@RequestMapping("/getRoles")
	public List<RoleManageBean> getRoles() {
		return roleService.queryAllRole();
	}

	/**
	 * update���༭�û���Ϣ
	 *
	 * @param vo
	 * @param session
	 * @return Object
	 */
	@RequestMapping("/updateUser")
	public Object update(UserInfoVo vo, HttpSession session) 
			throws NoSuchAlgorithmException, InvalidKeySpecException{
		
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		Map<String, String> map = new HashMap<>();
		List<String> uid = new ArrayList<>();
		uid.add(vo.getUid());
		//����check
		String dateTimeTemp = vo.getUupdatedatetime();
		if (!dbUpdateCheckService.DBUpdateCheck("1", uid, dateTimeTemp)) {
			map.put("msg", "checkFalse");
			return map;
		}
		
		String msg = userInfoService.updateUser(vo, u);
		map.put("msg", msg);
		return map;
	}

	/**
	 * toList����ת�˺���Ϣ����
	 *
	 * @return Object
	 */
	@RequestMapping("/toUserList")
	public Object toList() {

		return new ModelAndView("usermanage/userList");
	}

	/**
	 * queryAll����ѯ�����û���Ϣ���ɴ�����
	 *
	 * @param vo
	 * @param baseBean
	 * @return Map<String, Object>
	 */
	@RequestMapping("/queryAll")
	public Map<String, Object> queryAll(UserInfoVo vo, BaseBean baseBean,HttpSession session) {
		// ȡ�õ�ǰ��ʾ�û��鱨
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		Map<String, Object> list = userInfoService.queryAllUser(vo, 
					baseBean.getPageNow(), baseBean.getRowSize(),u);			
		
		return list;
	}

	/**
	 * delete�������û�idɾ���û���Ϣ
	 *
	 * @param checkedId
	 * @return String
	 */
	@RequestMapping("/delete")
	public Object delete(Integer[] checkedId,HttpSession session) {
		// ɾ��ѡ�е��û��鱨
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		for (int i : checkedId) {
			// ��������Ա����ȡ��
			UserInfoVo vo = userInfoService.queryUserByNum(i);
			String strTemp = vo.getUsuperuserflag();
			if ((!strTemp.isEmpty()) && "1".equals(strTemp)) {
				// ��������Ա���ܱ�ɾ��
				return userInfoService.queryUserByNum(i).getUid();
			}
			if (vo.getUid().equals(u.getUser_ID())) {
				return "current";
			}
		}
		userInfoService.deleteUser(checkedId);

		return "OK";
	}

	/**
	 * queryOne�������û�num�����û�
	 *
	 * @param unum
	 * @return UserInfoVo
	 */
	@RequestMapping("/queryOne")
	public UserInfoVo queryOne(Integer unum) {
		
		// �����û�No��ѯ�û�
		UserInfoVo vo = userInfoService.queryUserByNum(unum);
		return vo;
	}

	/**
	 * CheckId:�����û�id�����û��Ƿ����
	 *
	 * @param uid �û���
	 * @return ȡ�ü���
	 */
	@RequestMapping("/CheckId")
	public int CheckId(String uid) {
		
		// �����û�����ȡ���û��鱨�ļ���
		int i = userInfoService.queryCountById(uid);
		return i;
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
		vo.setUrolename(role.getUserRoleName());
		return vo;
	}

	/**
	 * returnPwd����������û��������
	 *
	 * @param upwd ����
	 * @param unum �û�No
	 * @return String
	 */
	@RequestMapping("/returnPwd")
	public String returnPwd(String upwd, int unum) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		// �����û�No��ѯ�û�
		UserInfoVo vo = this.userInfoService.queryUserByNum(unum);
		// �������
		String passwd = PBKDF2.getPBKDF2(upwd, 
				DatatypeConverter.printHexBinary(vo.getUid().getBytes()));
		return passwd;
	}
}