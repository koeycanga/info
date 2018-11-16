/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.mapper.userInfoMapper;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfo;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoKey;

/**
 * UserInfoMapper:角色管理Mapper
 *
 * @author lan
 * @Date:2018-11-9
 */
public interface UserInfoMapper {

    /**
     * deleteByPrimaryKey：通过主键删除用户情报
     *
     * @param key UserInfoKey
     * @return
     */
    public int deleteByPrimaryKey(UserInfoKey key);

    /**
     * insert：登陆用户情报
     *
     * @param record UserInfo
     * @return
     */
    public int insert(UserInfo record);

    /**
     * insertSelective：登陆用户情报
     *
     * @param record UserInfo
     * @return
     */
    public int insertSelective(UserInfo record);

    /**
     * selectByPrimaryKey：通过主键取得用户情报
     *
     * @param key UserInfoKey
     * @return
     */
    public UserInfo selectByPrimaryKey(UserInfoKey key);

    /**
     * updateByPrimaryKeySelective：通过主键更新用户情报
     *
     * @param record UserInfo
     * @return
     */
    public int updateByPrimaryKeySelective(UserInfo record);

    /**
     * updateByPrimaryKey：通过主键更新用户情报
     *
     * @param record UserInfo
     * @return
     */
    public int updateByPrimaryKey(UserInfo record);

    /**
     * queryAllUser：取得符合条件的用户情报
     *
     * @param key
     * @return
     */
    public List<UserInfo> queryAllUser(Map<String, Object> key);

    /**
     * queryCount：取得符合条件的用户情报的件数
     *
     * @param key
     * @return
     */
    public int queryCount(Map<String, Object> key);

    /**
     * queryCountById：通过User_ID取得用户情报的件数
     *
     * @param uid User_ID
     * @return
     */
    public int queryCountById(String uid);

    /**
     * updateRoleStatus：更新用户角色情报的状态为1（使用中）
     *
     * @param info
     * @return
     */
    void updateRoleStatus(UserInfo info);

    /**
     * updateRoleStatusToZero：更新用户角色情报的状态为0（暂未使用）
     *
     * @param info
     * @return
     */
    void updateRoleStatusToZero();
}