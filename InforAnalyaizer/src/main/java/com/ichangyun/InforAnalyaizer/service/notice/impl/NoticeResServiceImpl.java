package com.ichangyun.InforAnalyaizer.service.notice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichangyun.InforAnalyaizer.mapper.notice.NoticeExecuteResultMapper;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeResVo;
import com.ichangyun.InforAnalyaizer.service.notice.NoticeResService;
/**
 * NoticeResServiceImpl���򱨽��
 *
 * @author lan
 * @date 2018��11��27��
 */
@Service
public class NoticeResServiceImpl implements NoticeResService{
	@Autowired
	private NoticeExecuteResultMapper noticeExecuteResultMapper;
	@Override
	public Map<String, Object> queryAll(int pageNow, int rowSize) {
		// TODO Auto-generated method stub
		Map<String, Object> key = new HashMap<>();
		int l_pre = (pageNow-1)*rowSize;
        // ��ѯ������map����
        key.put("l_pre", l_pre);
        key.put("rowSize", rowSize);
        List<NoticeResVo> list = this.noticeExecuteResultMapper.queryAllRes(key);
        //��ѯ����
        int count = this.noticeExecuteResultMapper.queryCount();
        Map<String, Object> map = new HashMap<>();
        map.put("vos", list);
        map.put("rowCount", count);
		return map;
	}

}
