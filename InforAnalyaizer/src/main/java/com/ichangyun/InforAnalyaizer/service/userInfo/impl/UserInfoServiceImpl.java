package com.ichangyun.InforAnalyaizer.service.userInfo.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichangyun.InforAnalyaizer.mapper.userInfoMapper.UserInfoMapper;
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfo;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoKey;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoVo;
import com.ichangyun.InforAnalyaizer.service.userInfo.UserInfoService;
import com.ichangyun.InforAnalyaizer.utils.Obj2Map;
import com.ichangyun.InforAnalyaizer.utils.PBKDF2;
@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Override
	public void addUser(UserInfoVo vo,User u) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		Date t = new Date();
		Date time= new java.sql.Date(t.getTime());

		UserInfo info = new UserInfo(vo.getUpwd(), vo.getUname(),
				vo.getUdep(), vo.getUtel(), vo.getUemail(), vo.getUrole(),
				vo.getUstatus(), u.getUser_ID(), time, null, null,vo.getUid(),vo.getUnum());
		String passwd  = PBKDF2.getPBKDF2(info.getPassword(),DatatypeConverter.printHexBinary((info.getUserId()+info.getName()).getBytes()));
		info.setPassword(passwd);
		this.userInfoMapper.insert(info);
	}

	@Override
	public void updateUser(UserInfoVo vo,User u) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		Date t = new Date();
		Date time= new java.sql.Date(t.getTime());
		UserInfo info = new UserInfo(vo.getUpwd(), vo.getUname(),
				vo.getUdep(), vo.getUtel(), vo.getUemail(), vo.getUrole(),
				vo.getUstatus(), null, null, u.getUser_ID(), time,vo.getUid(),vo.getUnum());
		String passwd  = PBKDF2.getPBKDF2(info.getPassword(),DatatypeConverter.printHexBinary((info.getUserId()+info.getName()).getBytes()));
		info.setPassword(passwd);
		this.userInfoMapper.updateByPrimaryKey(info);
	}

	@Override
	public void deleteUser(UserInfoVo vo) {
		// TODO Auto-generated method stub
		UserInfoKey key = new UserInfoKey(vo.getUid(), vo.getUnum());
		this.userInfoMapper.deleteByPrimaryKey(key);
	}

	@Override
	public Map<String, Object> queryAllUser(UserInfoVo vo,int pageNow,int rowSize) {
		// TODO Auto-generated method stub
		UserInfo info = new UserInfo(vo.getUpwd(), vo.getUname(),
				vo.getUdep(), vo.getUtel(), vo.getUemail(), vo.getUrole(),
				vo.getUstatus(), null, null, null, null,vo.getUid(),vo.getUnum());
		Map<String, Object> key = new HashMap<>();
		key=Obj2Map.object2Map(info);
		int l_pre = (pageNow-1)*rowSize;
		key.put("l_pre",l_pre);
		key.put("rowSize", rowSize);
		List<UserInfo> infos = this.userInfoMapper.queryAllUser(key);
		List<UserInfoVo> vos = new ArrayList<>();
		for (UserInfo userInfo : infos) {
			UserInfoVo userVo = new UserInfoVo();
			userVo.loading(userInfo);
			vos.add(userVo);
		}
		Map<String, Object> res = new HashMap<>();
		int count = this.userInfoMapper.queryCount(key);
		res.put("users", vos);
		res.put("rowCount", count);
		return res;
	}

	@Override
	public UserInfoVo queryUserByNum(int unum) {
		// TODO Auto-generated method stub
		UserInfoKey key = new UserInfoKey(null, unum);
		UserInfo info = this.userInfoMapper.selectByPrimaryKey(key);
		UserInfoVo vo = new UserInfoVo();
		vo.loading(info);
		return vo;
	}

	@Override
	public int queryCountById(String uid) {
		// TODO Auto-generated method stub
		int i = this.userInfoMapper.queryCountById(uid);
		return i;
	}

	@Override
	public UserInfoVo queryById(String user_ID) {
		// TODO Auto-generated method stub
		UserInfoKey key = new UserInfoKey();
		key.setUserId(user_ID);
		UserInfo info = this.userInfoMapper.selectByPrimaryKey(key);
		UserInfoVo vo = new UserInfoVo();
		vo.loading(info);
		return vo;
	}
	

}
