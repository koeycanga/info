/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.model.userInfo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * UserInfo���û��鱨
 *
 * @author ichangyun
 * @date 2018/11/09
 */
public class UserInfo extends UserInfoKey {

    // ����
    private String password;

    // ����
    private String name;

    // ��������
    private String department;

    // �绰����
    private String telephone;

    // ����
    private String email;

    // �û���ɫID
    private String userroleId;

    // ״̬
    private String status;

    // ��������Ա���֣�0���ǳ�������Ա��1����������Ա��
    private String superuserflag;

    public String getSuperuserflag() {
        return superuserflag;
    }

    public void setSuperuserflag(String superuserflag) {
        this.superuserflag = superuserflag;
    }

    // ������
    private String createuser;
    @JSONField(format="yyyy-MM-dd")
    private Date createdatetime;

    // ������
    private String updateuser;
    @JSONField(format="yyyy-MM-dd")
    private Date updatedatetime;

    /** ȡ������  */
    public String getPassword() {
        return password;
    }

    /** ��������  */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /** ȡ���û�������  */
    public String getName() {
        return name;
    }

    /** �����û�������  */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** ȡ���û�����������  */
    public String getDepartment() {
        return department;
    }

    /** �����û�����������  */
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    /** ȡ���û��ĵ绰  */
    public String getTelephone() {
        return telephone;
    }

    /** �����û��ĵ绰  */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /** ȡ���û�������  */
    public String getEmail() {
        return email;
    }

    /** �����û�������  */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /** ȡ���û��Ľ�ɫID  */
    public String getUserroleId() {
        return userroleId;
    }

    /** �����û��Ľ�ɫID  */
    public void setUserroleId(String userroleId) {
        this.userroleId = userroleId == null ? null : userroleId.trim();
    }

    /** ȡ���û���״̬  */
    public String getStatus() {
        return status;
    }

    /** �����û���״̬  */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /** ȡ�������� */
    public String getCreateuser() {
        return createuser;
    }

    /** ���������� */
    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    /** ȡ��������ʱ */
    public Date getCreatedatetime() {
        return createdatetime;
    }

    /** ����������ʱ */
    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    /** ȡ�ø����� */
    public String getUpdateuser() {
        return updateuser;
    }

    /** ���ø����� */
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    /** ȡ�ø�����ʱ */
    public Date getUpdatedatetime() {
        return updatedatetime;
    }

    /** ���ø�����ʱ */
    public void setUpdatedatetime(Date updatedatetime) {
        this.updatedatetime = updatedatetime;
    }

    /**
     * UserInfo:�����û��鱨
     *
     * @param password ����
     * @param name ����
     * @param department ��������
     * @param telephone �绰
     * @param email ����
     * @param userroleId �û���ɫID
     * @param status ״̬
     * @param createuser ������
     * @param updateuser ������
     * @param uid �û���
     * @param unum �û�No
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