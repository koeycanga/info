/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.userInfo.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichangyun.InforAnalyaizer.controller.usermanage.UserController;
import com.ichangyun.InforAnalyaizer.mapper.userInfoMapper.UserInfoMapper;
import com.ichangyun.InforAnalyaizer.mapper.usermanage.RoleMapper;
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfo;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoKey;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoVo;
import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;
import com.ichangyun.InforAnalyaizer.service.userInfo.UserInfoService;
import com.ichangyun.InforAnalyaizer.utils.Obj2Map;
import com.ichangyun.InforAnalyaizer.utils.PBKDF2;

/**
 * UserInfoService：账号管理service实现类
 *
 * @author ichangyun
 * @Date 2018/11/09
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{
    Logger log = Logger.getLogger(UserController.class);

    // 账号管理mapper
    @Autowired
    private UserInfoMapper userInfoMapper;

    // 角色管理mapper
    @Autowired
    private RoleMapper roleMapper;

    /**
     * addUser：新增用户
     * @param vo 账号详细画面的项目
     * @param u
     */
    @Override
    public void addUser(UserInfoVo vo, User u)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        /* UserInfo作成  */
        UserInfo info = new UserInfo(
                vo.getUpwd(),    // 密码
                vo.getUname(),   // 姓名
                vo.getUdep(),    // 所属部门
                vo.getUtel(),    // 电话
                vo.getUemail(),  // 邮箱
                vo.getUrole(),   // 用户角色ID
                vo.getUstatus(), // 状态
                u.getUser_ID(),  // 作成者：用户情报的用户名
                u.getUser_ID(),  // 更新者：用户情报的用户名
                vo.getUid(),     // 用户名
                vo.getUnum());   // 用户No
        // 密码加密
        String passwd  = PBKDF2.getPBKDF2(
                info.getPassword(),
                DatatypeConverter.printHexBinary(info.getUserId().getBytes()));
        info.setPassword(passwd);
        // 用户情报登陆
        this.userInfoMapper.insert(info);
        // 更新用户的状态为1（使用中）
        this.userInfoMapper.updateRoleStatus(info);
    }

    /**
     * updateUser:更新用户信息
     * @param vo
     * @param u
     */
    @Override
    public void updateUser(UserInfoVo vo,User u)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        /* UserInfo作成  */
        UserInfo info = new UserInfo(
                vo.getUpwd(),    // 密码
                vo.getUname(),   // 姓名
                vo.getUdep(),    // 所属部门
                vo.getUtel(),    // 电话
                vo.getUemail(),  // 邮箱
                vo.getUrole(),   // 用户角色ID
                vo.getUstatus(), // 状态
                null,            // 作成者：null
                u.getUser_ID(),  // 更新者：用户情报的用户名
                vo.getUid(),     // 用户名
                vo.getUnum());   // 用户No
        // 密码加密
        String passwd  = PBKDF2.getPBKDF2(
                info.getPassword(),
                DatatypeConverter.printHexBinary(info.getUserId().getBytes()));
        // 设置密码
        info.setPassword(passwd);
        // 更新用户情报
        this.userInfoMapper.updateByPrimaryKey(info);
        // 更新用户角色情报的状态为1（使用中）    TODO
        this.userInfoMapper.updateRoleStatus(info);
        // 更新用户角色情报的状态为0（暂未使用）  TODO
        this.userInfoMapper.updateRoleStatusToZero();
    }

    /**
     * updateUser2_byold：更新用户情报
     * @param vo
     * @param u
     * @param changePwd
     */
    @Override
    public void updateUser2_byold(UserInfoVo vo, User u, String changePwd)
            throws Exception {
        /* UserInfo作成 */
        UserInfo info = new UserInfo(
                vo.getUpwd(),    // 密码
                vo.getUname(),   // 姓名
                vo.getUdep(),    // 所属部门
                vo.getUtel(),    // 电话
                vo.getUemail(),  // 邮箱
                vo.getUrole(),   // 用户角色ID
                vo.getUstatus(), // 状态
                null,            // 作成者：null
                u.getUser_ID(),  // 更新者：用户情报的用户名
                vo.getUid(),     // 用户名
                vo.getUnum());   // 用户No

        // 密码加密
        String passwd  = PBKDF2.getPBKDF2(
                changePwd,
                DatatypeConverter.printHexBinary(info.getUserId().getBytes()));
        info.setPassword(passwd);

        // 更新用户情报
        log.info("updateUser2_byold:" + "用户名[" + info.getUserId() +"]用户No[" + info.getUserno() + "]");
        this.userInfoMapper.updateByPrimaryKey(info);
    }

    /**
     * deleteUser：删除当前用户
     * @param vo
     */
    @Override
    public void deleteUser(UserInfoVo vo) {
        log.info("deleteUser-" + "用户名[" + vo.getUid() +"]用户No[" + vo.getUnum() + "]");
        // 用户名和用户No设置
        UserInfoKey key = new UserInfoKey(vo.getUid(), vo.getUnum());
        // 用户情报删除
        this.userInfoMapper.deleteByPrimaryKey(key);
    }

    /**
     * queryAllUser：取得当前表示用户情报
     * @param vo
     * @param pageNow
     * @param rowSize
     */
    @Override
    public Map<String, Object> queryAllUser(UserInfoVo vo, int pageNow, int rowSize) {
        /* UserInfo作成  */
        UserInfo info = new UserInfo(
                vo.getUpwd(),    // 密码
                vo.getUname(),   // 姓名
                vo.getUdep(),    // 所属部门
                vo.getUtel(),    // 电话
                vo.getUemail(),  // 邮箱
                vo.getUrole(),   // 用户角色ID
                vo.getUstatus(), // 状态
                null,            // 作成者：null
                null,            // 更新者：null
                vo.getUid(),     // 用户名
                vo.getUnum());   // 用户No

        Map<String, Object> key = new HashMap<>();
        key = Obj2Map.object2Map(info);
        int l_pre = (pageNow-1)*rowSize;
        // 查询条件的map参数
        key.put("l_pre", l_pre);
        key.put("rowSize", rowSize);

        // 取得用户信息
        List<UserInfo> infos = this.userInfoMapper.queryAllUser(key);
        List<UserInfoVo> vos = new ArrayList<>();
        // 取得用户角色信息
        List<RoleManageBean> roles = this.roleMapper.queryAllRole();
        for (UserInfo userInfo : infos) {
            UserInfoVo userVo = new UserInfoVo();
            userVo.loading(userInfo);
            for (RoleManageBean r : roles) {
                if(r.getUserRole_ID().equals(userVo.getUrole())) {
                    // 设置用户的角色名称
                    userVo.setUrolename(r.getUserRoleName());
                }
            }
            vos.add(userVo);
        }
        Map<String, Object> res = new HashMap<>();
        // 取得用户信息的件数
        int count = this.userInfoMapper.queryCount(key);
        res.put("users", vos);
        res.put("rowCount", count);
        return res;
    }

    /**
     * queryUserByNum：根据用户No查询用户
     * @param unum 用户No
     */
    @Override
    public UserInfoVo queryUserByNum(int unum) {
        // 设置检索条件
        UserInfoKey key = new UserInfoKey(null, unum);
        // 根据检索条件，取得用户情报
        UserInfo info = this.userInfoMapper.selectByPrimaryKey(key);
        UserInfoVo vo = new UserInfoVo();
        vo.loading(info);
        return vo;
    }

    /**
     * queryCountById：检查是否存在用户
     * @param uid 用户名
     */
    @Override
    public int queryCountById(String uid) {
        // 根据用户名，取得用户情报的件数
        int cnt = this.userInfoMapper.queryCountById(uid);
        return cnt;
    }

    /**
     * queryById：根据用户id查询用户信息
     * @param user_ID 用户名
     */
    @Override
    public UserInfoVo queryById(String user_ID) {
        // 设置检索条件
        UserInfoKey key = new UserInfoKey();
        key.setUserId(user_ID);
        // 根据检索条件，取得用户情报
        UserInfo info = this.userInfoMapper.selectByPrimaryKey(key);
        UserInfoVo vo = new UserInfoVo();
        vo.loading(info);
        return vo;
    }

    /**
     * updateRoleStatus：将用户角色情报的状态设为0
     * @param user_ID 用户名
     */
    @Override
    public void updateRoleStatus() {
        // 更新用户角色情报的状态为0（暂未使用）
        this.userInfoMapper.updateRoleStatusToZero();
    }


}
