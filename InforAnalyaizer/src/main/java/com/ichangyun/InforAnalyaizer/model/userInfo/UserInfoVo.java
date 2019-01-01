/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.model.userInfo;

import com.ichangyun.InforAnalyaizer.utils.DateUtils;

/**
 * UserInfoVo：账号详细画面的项目定义
 *
 * @author ichangyun
 * @date 2018/11/09
 */
public class UserInfoVo {
    private int unum;           // 用户No
    private String uid;         // 用户名
    private String urole;       // 用户角色
    private String uname;       // 用户姓名
    private String udep;        // 所属部门
    private String utel;        // 电话
    private String uemail;      // 邮箱
    private String ustatus;     // 状态
    private String upwd;        // 密码
    private String urolename;   // 角色名
    private String usuperuserflag; // 超级管理员区分（0：非超级管理员，1：超级管理员）

    private String uupdatedatetime;   // 更新日时

    public String getUupdatedatetime() {
        return uupdatedatetime;
    }
    public void setUupdatedatetime(String updatedatetime) {
        this.uupdatedatetime = updatedatetime;
    }

    public String getUsuperuserflag() {
        return usuperuserflag;
    }
    public void setUsuperuserflag(String superuserflag) {
        this.usuperuserflag = superuserflag;
    }
    public String getUrolename() {
        return urolename;
    }
    public void setUrolename(String urolename) {
        this.urolename = urolename;
    }
    public int getUnum() {
        return unum;
    }
    public void setUnum(int unum) {
        this.unum = unum;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUrole() {
        return urole;
    }
    public void setUrole(String urole) {
        this.urole = urole;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getUdep() {
        return udep;
    }
    public void setUdep(String udep) {
        this.udep = udep;
    }
    public String getUtel() {
        return utel;
    }
    public void setUtel(String utel) {
        this.utel = utel;
    }
    public String getUemail() {
        return uemail;
    }
    public void setUemail(String uemail) {
        this.uemail = uemail;
    }
    public String getUstatus() {
        return ustatus;
    }
    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    public String getUpwd() {
        return upwd;
    }
    public void setupwd(String upwd) {
        this.upwd = upwd;
    }

    /**
     * UserInfoVo：创建UserInfoVo
     * @param unum 用户No
     * @param uid 用户名
     * @param urole 用户角色ID
     * @param uname 用户角色名
     * @param udep 所属部门
     * @param utel 电话
     * @param uemail 邮箱
     * @param ustatus 状态
     * @param pwd 密码
     * @param superuserflag 超级管理员区分
     */
    public UserInfoVo(int unum, String uid, String urole, String uname,
            String udep, String utel, String uemail, String ustatus,
            String pwd, String superuserflag) {
        super();
        this.unum = unum;
        this.uid = uid;
        this.urole = urole;
        this.uname = uname;
        this.udep = udep;
        this.utel = utel;
        this.uemail = uemail;
        this.ustatus = ustatus;
        this.upwd = pwd;
        this.usuperuserflag = superuserflag;
    }
    public UserInfoVo() {
        super();
    }

    /**
     * loading:将实体类userinfo属性装填到视图类中
     *
     * @param info 用户情报
     */
    public void loading(UserInfo info) {
        this.unum = info.getUserno();
        this.uid = info.getUserId();
        this.urole = info.getUserroleId();
        this.uname = info.getName();
        this.udep = info.getDepartment();
        this.utel = info.getTelephone();
        this.uemail = info.getEmail();
        this.ustatus = info.getStatus();
        this.upwd = info.getPassword();
        this.usuperuserflag = info.getSuperuserflag();
        this.uupdatedatetime = DateUtils.format(info.getUpdatedatetime(), DateUtils.DATE_TIME_PATTERN);

    }
}
