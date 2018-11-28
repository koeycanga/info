package com.ichangyun.InforAnalyaizer.mapper.notice;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.notice.Notice;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeType;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeVo;

public interface NoticeMapper {
    int deleteByPrimaryKey(String noticeid);

    int insertSelective(Notice record);

    int updateByPrimaryKey(Notice record);

	List<NoticeVo> queryAllNotice(Map<String, Object> key);

	int queryCount();

	List<NoticeType> getNoticeType();

	NoticeVo queryById(String id);
}