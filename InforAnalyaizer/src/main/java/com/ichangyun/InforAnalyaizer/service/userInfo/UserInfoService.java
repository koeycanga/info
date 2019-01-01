/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.userInfo;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoVo;

/**
 * UserInfoService接口
 *
 * @author ichangyun
 * @date 2018/11/09
 */
public interface UserInfoService {
    // 添加用户
    String addUser(UserInfoVo record, User createUser)throws NoSuchAlgorithmException, InvalidKeySpecException;
    // 编辑用户
    String updateUser(UserInfoVo record, User updateUser) throws NoSuchAlgorithmException, InvalidKeySpecException;
    // 删除用户
    void deleteUser(Integer[] checkedId);
    // 查询所有用户
    Map<String, Object> queryAllUser(UserInfoVo record, int pageNow, int rowSize, User u);
    // 根据用户num查询用户
    UserInfoVo queryUserByNum(int unum);
    // 根据用户名查询用户是否存在
    int queryCountById(String uid);
    // 根据用户名查询用户
    UserInfoVo queryById(String user_ID);
    // 将用户的角色设为空
    void updateRoleStatus();
    // 修改用户密码
    void updateUser2_byold(UserInfoVo vo, User u, String changePwd) throws Exception;

}
