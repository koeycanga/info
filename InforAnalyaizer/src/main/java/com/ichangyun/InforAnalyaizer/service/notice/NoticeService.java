/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.notice;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeType;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeVo;

public interface NoticeService {

	public Map<String, Object> queryAll(int pageNow, int rowSize);

	public List<NoticeType> getNoticeType();

	public String addNotice(NoticeVo vo,User u);

	public NoticeVo queryOne(String id);

	public String updateNotice(NoticeVo vo, User u);

	public String deleteNotice(String[] ids);
	
}
