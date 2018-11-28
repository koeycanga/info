package com.ichangyun.InforAnalyaizer.model.userInfo;

public class UserInfoVo {
	private int unum;
	private String uid;
	private String urole;
	private String uname;
	private String udep;
	private String utel;
	private String uemail;
	private String ustatus;
	private String upwd;
	private String urolename;
	
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
	
	public UserInfoVo(int unum, String uid, String urole, String uname, String udep, String utel, String uemail,
			String ustatus, String pwd) {
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
	}
	public UserInfoVo() {
		super();
	}
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
	}
}
