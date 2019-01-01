/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.model.userInfo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * UserInfo：用户情报
 *
 * @author ichangyun
 * @date 2018/11/09
 */
public class UserInfo extends UserInfoKey {

    // 密码
    private String password;

    // 姓名
    private String name;

    // 所属部门
    private String department;

    // 电话号码
    private String telephone;

    // 邮箱
    private String email;

    // 用户角色ID
    private String userroleId;

    // 状态
    private String status;

    // 超级管理员区分（0：非超级管理员，1：超级管理员）
    private String superuserflag;
    
    private String customerId;
    
    private String collectionFieldId;
    

    public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCollectionFieldId() {
		return collectionFieldId;
	}

	public void setCollectionFieldId(String collectionFieldId) {
		this.collectionFieldId = collectionFieldId;
	}

	public String getSuperuserflag() {
        return superuserflag;
    }

    public void setSuperuserflag(String superuserflag) {
        this.superuserflag = superuserflag;
    }

    // 作成者
    private String createuser;
    @JSONField(format="yyyy-MM-dd")
    private Date createdatetime;

    // 更新者
    private String updateuser;
    @JSONField(format="yyyy-MM-dd")
    private Date updatedatetime;

    /** 取得密码  */
    public String getPassword() {
        return password;
    }

    /** 设置密码  */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /** 取得用户的姓名  */
    public String getName() {
        return name;
    }

    /** 设置用户的姓名  */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** 取得用户的所属部门  */
    public String getDepartment() {
        return department;
    }

    /** 设置用户的所属部门  */
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    /** 取得用户的电话  */
    public String getTelephone() {
        return telephone;
    }

    /** 设置用户的电话  */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /** 取得用户的邮箱  */
    public String getEmail() {
        return email;
    }

    /** 设置用户的邮箱  */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /** 取得用户的角色ID  */
    public String getUserroleId() {
        return userroleId;
    }

    /** 设置用户的角色ID  */
    public void setUserroleId(String userroleId) {
        this.userroleId = userroleId == null ? null : userroleId.trim();
    }

    /** 取得用户的状态  */
    public String getStatus() {
        return status;
    }

    /** 设置用户的状态  */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /** 取得作成者 */
    public String getCreateuser() {
        return createuser;
    }

    /** 设置作成者 */
    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    /** 取得作成日时 */
    public Date getCreatedatetime() {
        return createdatetime;
    }

    /** 设置作成日时 */
    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    /** 取得更新者 */
    public String getUpdateuser() {
        return updateuser;
    }

    /** 设置更新者 */
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    /** 取得更新日时 */
    public Date getUpdatedatetime() {
        return updatedatetime;
    }

    /** 设置更新日时 */
    public void setUpdatedatetime(Date updatedatetime) {
        this.updatedatetime = updatedatetime;
    }

    /**
     * UserInfo:作成用户情报
     *
     * @param password 密码
     * @param name 姓名
     * @param department 所属部门
     * @param telephone 电话
     * @param email 邮箱
     * @param userroleId 用户角色ID
     * @param status 状态
     * @param createuser 作成者
     * @param updateuser 更新者
     * @param uid 用户名
     * @param unum 用户No
     */
    public UserInfo(String password, String name, String department, String telephone, String email, String userroleId,
            String status, String createuser, String updateuser, String uid, int unum) {
        super();
        this.password = password;
        this.name = name;
        this.department = department;
        this.telephone = telephone;
        this.email = email;
        this.userroleId = userroleId;
        this.status = status;
        this.createuser = createuser;
        this.updateuser = updateuser;
        this.setUserId(uid);
        this.setUserno(unum);
    }

    public UserInfo() {
        super();
    }

}