package com.ichangyun.InforAnalyaizer.mapper.notice;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.notice.NoticeExecuteResult;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeResVo;

public interface NoticeExecuteResultMapper {
    int deleteByPrimaryKey(String noticeid);

    int insert(NoticeExecuteResult record);

    int insertSelective(NoticeExecuteResult record);

    NoticeExecuteResult selectByPrimaryKey(String noticeid);

    int updateByPrimaryKeySelective(NoticeExecuteResult record);

    int updateByPrimaryKey(NoticeExecuteResult record);

	List<NoticeResVo> queryAllRes(Map<String, Object> key);

	int queryCount();
}
