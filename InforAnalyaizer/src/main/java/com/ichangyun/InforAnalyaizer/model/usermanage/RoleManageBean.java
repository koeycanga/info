/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.usermanage;

import javax.validation.constraints.NotEmpty;

/**
 * 角色信息实体类
 * 对应数据表： m_userrole
 * @author renhao
 * @Date:2018-11-9
 */
public class RoleManageBean {

    /**
     * 角色信息ID
     */
    private String UserRole_ID;

    /**
     * 角色名称
     */
    @NotEmpty(message="{usermanagebean.rolename}")
    private String UserRoleName;

    /**
     * 角色权限
     */
    private String Authority;

    /**
     * 角色描述
     */
    private String Description;

    /**
     * 创建者
     */
    private String CreateUser;

    /**
     * 创建时间
     */
    private String CreateDateTime;

    /**
     * 修改者
     */
    private String UpdateUser;

    /**
     * 修改时间
     */
    private String UpdateDateTime;

    /**
     * 角色状态  0:未使用   1:使用中
     */
    private String Status;

    
    //客户ID
    private String Customer_ID;
    
    //采集领域ID
    private String CollectionField_ID;
    
    public String getCustomer_ID() {
		return Customer_ID;
	}

	public void setCustomer_ID(String customer_ID) {
		Customer_ID = customer_ID;
	}

	public String getCollectionField_ID() {
		return CollectionField_ID;
	}

	public void setCollectionField_ID(String collectionField_ID) {
		CollectionField_ID = collectionField_ID;
	}
    
    
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }
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
