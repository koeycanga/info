/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.model.userInfo;

/**
 * UserManageBean���û���ɫ�鱨
 *
 * @author ichangyun
 * @date 2018/11/09
 */
public class UserManageBean {

    // �û���ɫID
    private String UserRole_ID;
    // ��ɫ��
    private String UserRoleName;
    // Ȩ��
    private String Authority;
    // ��ɫ����
    private String Description;
    // ״̬��0����δʹ��  1��ʹ���У�
    private String Status;
    // ������
    private String CreateUser;
    // ������ʱ
    private String CreateDateTime;
    // ������
    private String UpdateUser;
    // ������ʱ
    private String UpdateDateTime;

    public String getUserRole_ID() {
        return UserRole_ID;
    }
    public void setUserRole_ID(String userRole_ID) {
        UserRole_ID = userRole_ID;
    }
    public String getUserRoleName() {
        return UserRoleName;
    }
    public void setUserRoleName(String userRoleName) {
        UserRoleName = userRoleName;
    }
    public String getAuthority() {
        return Authority;
    }
    public void setAuthority(String authority) {
        Authority = authority;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }
    public String getCreateUser() {
        return CreateUser;
    }
    public void setCreateUser(String createUser) {
        CreateUser = createUser;
    }
    public String getCreateDateTime() {
        return CreateDateTime;
    }
    public void setCreateDateTime(String createDateTime) {
        CreateDateTime = createDateTime;
    }
    public String getUpdateUser() {
        return UpdateUser;
    }
    public void setUpdateUser(String updateUser) {
        UpdateUser = updateUser;
    }
    public String getUpdateDateTime() {
        return UpdateDateTime;
    }
    public void setUpdateDateTime(String updateDateTime) {
        UpdateDateTime = updateDateTime;
    }
}
