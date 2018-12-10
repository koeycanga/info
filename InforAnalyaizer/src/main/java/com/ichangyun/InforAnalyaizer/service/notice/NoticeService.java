package com.ichangyun.InforAnalyaizer.service.notice;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeType;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeVo;

public interface NoticeService {

	Map<String, Object> queryAll(int pageNow, int rowSize);

	List<NoticeType> getNoticeType();

	String addNotice(NoticeVo vo,User u);

	NoticeVo queryOne(String id);

	String updateNotice(NoticeVo vo, User u);

	String deleteNotice(String[] ids);
	
}
