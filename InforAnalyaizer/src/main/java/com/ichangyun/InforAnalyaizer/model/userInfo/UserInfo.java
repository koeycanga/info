package com.ichangyun.InforAnalyaizer.model.userInfo;

import java.util.Date;

public class UserInfo extends UserInfoKey {
    private String password;

    private String name;

    private String department;

    private String telephone;

    private String email;

    private String userroleId;

    private String status;

    private String createuser;

    private Date createdatetime;

    private String updateuser;

    private Date updatedatetime;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getUserroleId() {
        return userroleId;
    }

    public void setUserroleId(String userroleId) {
        this.userroleId = userroleId == null ? null : userroleId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public Date getUpdatedatetime() {
        return updatedatetime;
    }

    public void setUpdatedatetime(Date updatedatetime) {
        this.updatedatetime = updatedatetime;
    }

	public UserInfo(String password, String name, String department, String telephone, String email, String userroleId,
			String status, String createuser, Date createdatetime, String updateuser, Date updatedatetime,String uid,int unum) {
		super();
		this.password = password;
		this.name = name;
		this.department = department;
		this.telephone = telephone;
		this.email = email;
		this.userroleId = userroleId;
		this.status = status;
		this.createuser = createuser;
		this.createdatetime = createdatetime;
		this.updateuser = updateuser;
		this.updatedatetime = updatedatetime;
		this.setUserId(uid);
		this.setUserno(unum);
	}

	public UserInfo() {
		super();
	}
    
}