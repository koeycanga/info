/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.controller.usermanage;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
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
import com.ichangyun.InforAnalyaizer.utils.PBKDF2;

/**
 * Controller：账号管理
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
     * addUser：新增用户
     *
     * @param user    用户情报
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
        // 状态选择Check
        if (user.getUstatus() == null) {
            map.put("msg", "E0031");
            return map;
        }
        // 添加用户处理执行
        String msg = userInfoService.addUser(user, u);

        // handle exception
        map.put("msg", msg);

        return map;
    }

    /**
     * getRoles：查询所有角色
     *
     * @return List<RoleManageBean>
     */
    @RequestMapping("/getRoles")
    public List<RoleManageBean> getRoles(HttpSession session) {
        return roleService.queryAllRole(session);
    }

    /**
     * update：编辑用户信息
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
        //排他check
        String dateTimeTemp = vo.getUupdatedatetime();
        if (!dbUpdateCheckService.DBUpdateCheck("1", uid, dateTimeTemp,session)) {
            map.put("msg", "checkFalse");
            return map;
        }
        if(vo.getUid().equals(u.getUser_ID())&&vo.getUstatus().equals("0")) {
        	map.put("msg", "statusFalse");
            return map;
        }

        String msg = userInfoService.updateUser(vo, u);
        map.put("msg", msg);
        return map;
    }

    /**
     * toList：跳转账号信息界面
     *
     * @return Object
     */
    @RequestMapping("/toUserList")
    public Object toList() {

        return new ModelAndView("usermanage/userList");
    }

    /**
     * queryAll：查询所有用户信息，可带条件
     *
     * @param vo
     * @param baseBean
     * @return Map<String, Object>
     */
    @RequestMapping("/queryAll")
    public Map<String, Object> queryAll(UserInfoVo vo, BaseBean baseBean,HttpSession session) {
        // 取得当前表示用户情报
        User u = (User) session.getAttribute(CommBean.SESSION_NAME);
        Map<String, Object> list = userInfoService.queryAllUser(vo,
                baseBean.getPageNow(), baseBean.getRowSize(),u);

        return list;
    }

    /**
     * delete：根据用户id删除用户信息
     *
     * @param checkedId
     * @return String
     */
    @RequestMapping("/delete")
    public Object delete(Integer[] checkedId,HttpSession session) {
        // 删除选中的用户情报
        User u = (User) session.getAttribute(CommBean.SESSION_NAME);
        for (int i : checkedId) {
            // 超级管理员区分取得
            UserInfoVo vo = userInfoService.queryUserByNum(i);
            String strTemp = vo.getUsuperuserflag();
            if ((!strTemp.isEmpty()) && "1".equals(strTemp)) {
                // 超级管理员不能被删除
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
     * queryOne：根据用户num查找用户
     *
     * @param unum
     * @return UserInfoVo
     */
    @RequestMapping("/queryOne")
    public UserInfoVo queryOne(Integer unum) {

        // 根据用户No查询用户
        UserInfoVo vo = userInfoService.queryUserByNum(unum);
        return vo;
    }

    /**
     * CheckId:根据用户id检查此用户是否存在
     *
     * @param uid 用户名
     * @return 取得件数
     */
    @RequestMapping("/CheckId")
    public int CheckId(String uid) {

        // 根据用户名，取得用户情报的件数
        int i = userInfoService.queryCountById(uid);
        return i;
    }

    /**
     * thisUser：返回当前登录中的用户信息
     *
     * @param session
     * @return UserInfoVo
     */
    @RequestMapping("/thisUser")
    public UserInfoVo thisUser(HttpSession session) {

        User u = (User) session.getAttribute(CommBean.SESSION_NAME);
        // 根据用户id查询用户信息
        UserInfoVo vo = userInfoService.queryById(u.getUser_ID());
        // 根据用户的角色ID查询角色信息
        RoleManageBean role = roleService.queryById(vo.getUrole(),session);
        // 设置角色名称
        vo.setUrolename(role.getUserRoleName());
        return vo;
    }

    /**
     * returnPwd：将传入的用户密码加密
     *
     * @param upwd 密码
     * @param unum 用户No
     * @return String
     */
    @RequestMapping("/returnPwd")
    public String returnPwd(String upwd, int unum)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        // 根据用户No查询用户
        UserInfoVo vo = this.userInfoService.queryUserByNum(unum);
        // 密码加密
        String passwd = PBKDF2.getPBKDF2(upwd,
                DatatypeConverter.printHexBinary(vo.getUid().getBytes()));
        return passwd;
    }
}
