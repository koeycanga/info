package com.ichangyun.InforAnalyaizer.model.notice;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Notice {
    private String noticeid;	//��id

    private String title;	//�򱨱���

    private String noticetype;	//������

    private String monitordatestart;	//��⿪ʼ����

    private String monitordateend;	//�����ֹ����

    private String monitortimestart;	//��⿪ʼʱ��

    private String monitortimeend;	//�����ֹʱ��

    private String senddate;	//��������

    private String sendtime;	//����ʱ��

    private String email;	//����

    private String validflag;	//�Ƿ����

    private String createuser;	//������
    @JSONField(format="yyyy-MM-dd")
    private Date createdatetime;	//����ʱ��

    private String updateuser;	//������
    
    private String updatedatetime;	//����ʱ��

    public String getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(String noticeid) {
        this.noticeid = noticeid == null ? null : noticeid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getNoticetype() {
        return noticetype;
    }

    public void setNoticetype(String noticetype) {
        this.noticetype = noticetype == null ? null : noticetype.trim();
    }

    public String getMonitordatestart() {
        return monitordatestart;
    }

    public void setMonitordatestart(String monitordatestart) {
        this.monitordatestart = monitordatestart == null ? null : monitordatestart.trim();
    }

    public String getMonitordateend() {
        return monitordateend;
    }

    public void setMonitordateend(String monitordateend) {
        this.monitordateend = monitordateend == null ? null : monitordateend.trim();
    }

    public String getMonitortimestart() {
        return monitortimestart;
    }

    public void setMonitortimestart(String monitortimestart) {
        this.monitortimestart = monitortimestart == null ? null : monitortimestart.trim();
    }

    public String getMonitortimeend() {
        return monitortimeend;
    }

    public void setMonitortimeend(String monitortimeend) {
        this.monitortimeend = monitortimeend == null ? null : monitortimeend.trim();
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate == null ? null : senddate.trim();
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime == null ? null : sendtime.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getValidflag() {
        return validflag;
    }

    public void setValidflag(String validflag) {
        this.validflag = validflag == null ? null : validflag.trim();
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

    public String getUpdatedatetime() {
        return updatedatetime;
    }

    public void setUpdatedatetime(String updatedatetime) {
        this.updatedatetime = updatedatetime;
    }
}