/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.notice;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Notice {
    private String noticeid;	//简报id

    private String title;	//简报标题

    private String noticetype;	//简报类型

    private String monitordatestart;	//监测开始日期

    private String monitordateend;	//监测终止日期

    private String monitortimestart;	//监测开始时间

    private String monitortimeend;	//监测终止时间

    private String senddate;	//发送日期

    private String sendtime;	//发送时间

    private String email;	//邮箱

    private String validflag;	//是否可用

    private String createuser;	//创建人
    @JSONField(format="yyyy-MM-dd")
    private Date createdatetime;	//创建时间

    private String updateuser;	//更新者
    
    private String updatedatetime;	//更新时间

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