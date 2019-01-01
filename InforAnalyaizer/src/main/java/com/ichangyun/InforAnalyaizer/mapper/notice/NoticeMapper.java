/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.mapper.notice;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.notice.Notice;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeType;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeVo;

public interface NoticeMapper {
    public int deleteByPrimaryKey(String noticeid);

    public int insertSelective(Notice record);

    public int updateByPrimaryKey(Notice record);

    public List<NoticeVo> queryAllNotice(Map<String, Object> key);

    public int queryCount(Map<String, Object> key);

    public List<NoticeType> getNoticeType();

    public NoticeVo queryById(String id);
    
    public Integer checkName(Notice record);
}