package com.ichangyun.InforAnalyaizer.service.userInfo;

import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoVo;

public interface UserInfoService {
	void addUser(UserInfoVo record,User createUser);
	void updateUser(UserInfoVo record,User updateUser);
	void deleteUser(UserInfoVo record);
	Map<String, Object> queryAllUser(UserInfoVo record,int pageNow,int rowSize);
	UserInfoVo queryUserByNum(int unum);
	int queryCountById(String uid);
	UserInfoVo queryById(String user_ID);
}
