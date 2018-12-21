/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.notice;

import java.util.Date;

public class NoticeExecuteResult {
    private String noticeid;

    private String executeresult;

    private String executeid;

    private String createuser;

    private Date createdatetime;

    private String updateuser;

    private Date updatedatetime;

    public String getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(String noticeid) {
        this.noticeid = noticeid == null ? null : noticeid.trim();
    }

    public String getExecuteresult() {
        return executeresult;
    }

    public void setExecuteresult(String executeresult) {
        this.executeresult = executeresult == null ? null : executeresult.trim();
    }

    public String getExecuteid() {
        return executeid;
    }

    public void setExecuteid(String executeid) {
        this.executeid = executeid == null ? null : executeid.trim();
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

	public NoticeExecuteResult(String noticeid, String executeresult, String executeid, String createuser,
			Date createdatetime, String updateuser, Date updatedatetime) {
		super();
		this.noticeid = noticeid;
		this.executeresult = executeresult;
		this.executeid = executeid;
		this.createuser = createuser;
		this.createdatetime = createdatetime;
		this.updateuser = updateuser;
		this.updatedatetime = updatedatetime;
	}

	public NoticeExecuteResult() {
		super();
	}
	
    
}