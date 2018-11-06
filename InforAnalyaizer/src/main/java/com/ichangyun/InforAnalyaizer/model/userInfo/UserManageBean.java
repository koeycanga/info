package com.ichangyun.InforAnalyaizer.model.userInfo;

public class UserManageBean {

	private String UserRole_ID;
	private String UserRoleName;
	private String Authority;
	private String Description;
	private String CreateUser;
	private String CreateDateTime;
	private String UpdateUser;
	private String UpdateDateTime;
	private String Status;
	
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
