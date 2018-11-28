/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.model.userInfo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.ichangyun.InforAnalyaizer.utils.DateUtils;

/**
 * UserInfoVo���˺���ϸ�������Ŀ����
 *
 * @author ichangyun
 * @date 2018/11/09
 */
public class UserInfoVo {
    private int unum;           // �û�No
    private String uid;         // �û���
    private String urole;       // �û���ɫ
    private String uname;       // �û�����
    private String udep;        // ��������
    private String utel;        // �绰
    private String uemail;      // ����
    private String ustatus;     // ״̬
    private String upwd;        // ����
    private String urolename;   // ��ɫ��
    private String usuperuserflag; // ��������Ա���֣�0���ǳ�������Ա��1����������Ա��

    private String uupdatedatetime;   // ������ʱ
    
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
     * UserInfoVo������UserInfoVo
     * @param unum �û�No
     * @param uid �û���
     * @param urole �û���ɫID
     * @param uname �û���ɫ��
     * @param udep ��������
     * @param utel �绰
     * @param uemail ����
     * @param ustatus ״̬
     * @param pwd ����
     * @param superuserflag ��������Ա����
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
     * loading:��ʵ����userinfo����װ���ͼ����
     *
     * @param info �û��鱨
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
