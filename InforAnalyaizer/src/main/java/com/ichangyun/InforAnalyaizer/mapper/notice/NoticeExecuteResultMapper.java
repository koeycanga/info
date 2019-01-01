/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.mapper.notice;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.notice.NoticeExecuteResult;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeResVo;

public interface NoticeExecuteResultMapper {
    public int deleteByPrimaryKey(String noticeid);

    public int insert(NoticeExecuteResult record);

    public int insertSelective(NoticeExecuteResult record);

    public NoticeExecuteResult selectByPrimaryKey(String noticeid);

    public int updateByPrimaryKeySelective(NoticeExecuteResult record);

    public int updateByPrimaryKey(NoticeExecuteResult record);

    public List<NoticeResVo> queryAllRes(Map<String, Object> key);

    public int queryCount();
}
