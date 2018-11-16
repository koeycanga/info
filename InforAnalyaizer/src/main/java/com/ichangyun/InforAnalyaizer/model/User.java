/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 */
package com.ichangyun.InforAnalyaizer.model;

/**
 * user实体类
 * @author renhao
 * @date 2018/11/09
 */
public class User {
    // ID
    private int id;
    // 用户名
    private String User_ID;
    // 姓名
    private String Name;
    // 密码
    private String Password;
    // 所属部门
    private String Department;
    // 联系方式
    private String Telephone;
    // 用户邮箱
    private String EMail;
    // 用户角色ID
    private String UserRole_ID;
    // 更新日时
    private String UpdateDateTime;
    // 用户角色名
    private String UserRoleName;
    // 用户权限
    private String Authority;

    public User() {
    }

    /* 所属部门  */
    public String getDepartment() {
        return Department;
    }

    /* 所属部门  */
    public void setDepartment(String department) {
        Department = department;
    }

    /* 联系方式  */
    public String getTelephone() {
        return Telephone;
    }

    /* 联系方式  */
    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    /* 用户邮箱  */
    public String getEMail() {
        return EMail;
    }

    /* 用户邮箱  */
    public void setEMail(String eMail) {
        EMail = eMail;
    }

    /* 用户角色ID  */
    public String getUserRole_ID() {
        return UserRole_ID;
    }

    /* 用户角色ID  */
    public void setUserRole_ID(String userRole_ID) {
        UserRole_ID = userRole_ID;
    }

    /* 更新日时 */
    public String getUpdateDateTime() {
        return UpdateDateTime;
    }

    /* 更新日时 */
    public void setUpdateDateTime(String updateDateTime) {
        UpdateDateTime = updateDateTime;
    }

    /* 用户角色名  */
    public String getUserRoleName() {
        return UserRoleName;
    }

    /* 用户角色名  */
    public void setUserRoleName(String userRoleName) {
        UserRoleName = userRoleName;
    }

    /* 用户权限  */
    public String getAuthority() {
        return Authority;
    }

    /* 用户权限  */
    public void setAuthority(String authority) {
        Authority = authority;
    }

    /* 密码  */
    public String getPassword() {
        return Password;
    }

    /* 密码  */
    public void setPassword(String password) {
        Password = password;
    }

    /* 姓名  */
    public String getName() {
        return Name;
    }

    /* 姓名  */
    public void setName(String name) {
        Name = name;
    }

    /* 用户名  */
    public String getUser_ID() {
        return User_ID;
    }

    /* 用户名  */
    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    /* ID */
    public int getId() {
        return id;
    }

    /* ID */
    public void setId(int id) {
        this.id = id;
    }
}

