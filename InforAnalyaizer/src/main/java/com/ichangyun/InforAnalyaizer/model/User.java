package com.ichangyun.InforAnalyaizer.model;

/**
 * user µÃÂ¿‡
 */
 
public class User {
    private int id;
    //private String name; 
    private int age;
    private String sex;
 
    private String User_ID;
    private String Name;
    
    private String Password;
    private String Department;
    private String Telephone;
    private String EMail;
    private String UserRole_ID;
    private String UpdateDateTime;
    private String UserRoleName;
    private String Authority;
    
    public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public String getEMail() {
		return EMail;
	}

	public void setEMail(String eMail) {
		EMail = eMail;
	}

	public String getUserRole_ID() {
		return UserRole_ID;
	}

	public void setUserRole_ID(String userRole_ID) {
		UserRole_ID = userRole_ID;
	}

	public String getUpdateDateTime() {
		return UpdateDateTime;
	}

	public void setUpdateDateTime(String updateDateTime) {
		UpdateDateTime = updateDateTime;
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

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}

	public User() {
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
 
    public String getSex() {
        return sex;
    }
 
    public void setSex(String sex) {
        this.sex = sex;
    }
 
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + Name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}

