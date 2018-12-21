/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.notice;

import java.util.Date;

public class NoticeResVo extends NoticeExecuteResult{
	private String title;//简报标题

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public NoticeResVo(String noticeid, String executeresult, String executeid, String createuser, Date createdatetime,
			String updateuser, Date updatedatetime, String title) {
		super(noticeid, executeresult, executeid, createuser, createdatetime, updateuser, updatedatetime);
		this.title = title;
	}

	public NoticeResVo() {
		super();
	}


	
}
