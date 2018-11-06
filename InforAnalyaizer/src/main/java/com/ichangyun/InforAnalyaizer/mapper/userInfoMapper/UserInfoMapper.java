package com.ichangyun.InforAnalyaizer.mapper.userInfoMapper;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfo;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoKey;

public interface UserInfoMapper {
    int deleteByPrimaryKey(UserInfoKey key);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(UserInfoKey key);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    List<UserInfo> queryAllUser(Map<String, Object> key);
    
    int queryCount(Map<String, Object> key);
    
    int queryCountById(String uid);

	String getUerNameById(String user_ID);
}