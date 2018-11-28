package com.ichangyun.InforAnalyaizer.service.notice.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichangyun.InforAnalyaizer.mapper.notice.NoticeMapper;
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeType;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeVo;
import com.ichangyun.InforAnalyaizer.service.notice.NoticeService;
/**
 * NoticeService：简报任务
 *
 *@author:ichangyun
 *@date:2018/11/27
 */
@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeMapper noticeMapper;
	/**
     * queryAll：查询所有简报
     *
     *@param int pageNow, int rowSize
     *@return map
     */
	@Override
	public Map<String, Object> queryAll(int pageNow, int rowSize) {
		// TODO Auto-generated method stub
		Map<String, Object> key = new HashMap<>();
		int l_pre = (pageNow-1)*rowSize;
        // 查询条件的map参数
        key.put("l_pre", l_pre);
        key.put("rowSize", rowSize);
        List<NoticeVo> list = this.noticeMapper.queryAllNotice(key);

        int count = this.noticeMapper.queryCount();
        Map<String, Object> map = new HashMap<>();
        map.put("vos", list);
        map.put("rowCount", count);
		return map;
	}
	/**
     * getNoticeType：获取简报类型
     *
     *@return List
     */
	@Override
	public List<NoticeType> getNoticeType() {
		// TODO Auto-generated method stub
		return this.noticeMapper.getNoticeType();
	}
	/**
     * addNotice：添加
     *
     *@return List
     */
	@Override
	public String addNotice(NoticeVo vo,User u) {
		// TODO Auto-generated method stub
		vo.setCreateuser(u.getUser_ID());
		Date t = new Date();
		Date time= new java.sql.Date(t.getTime());
		vo.setCreatedatetime(time);
		try {
			this.noticeMapper.insertSelective(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fault";
		}
		return "ok";
	}
	/**
     * queryOne：查询单个
     *
     *@return NoticeVo
     */
	@Override
	public NoticeVo queryOne(String id) {
		// TODO Auto-generated method stub
		return this.noticeMapper.queryById(id);
	}
	/**
     * updateNotice：更新
     *
     *@return String
     */	
	@Override
	public String updateNotice(NoticeVo vo, User u) {
		// TODO Auto-generated method stub
		vo.setUpdateuser(u.getUser_ID());
		try {
			this.noticeMapper.updateByPrimaryKey(vo);
		} catch (Exception e) {
			// TODO: handle exception
			return "fault";
		}
		return "ok";
	}
	/**
     * deleteNotice：删除
     *
     *@return String
     */	
	@Override
	public String deleteNotice(String[] ids) {
		// TODO Auto-generated method stub
		String msg ;
		try {
			for (String id : ids) {
				this.noticeMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
			msg="fault";
		}
		msg="ok";
		return msg;
	}
	
}
