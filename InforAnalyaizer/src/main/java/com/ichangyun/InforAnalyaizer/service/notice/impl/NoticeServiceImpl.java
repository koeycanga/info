/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.notice.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichangyun.InforAnalyaizer.mapper.notice.NoticeExecuteResultMapper;
import com.ichangyun.InforAnalyaizer.mapper.notice.NoticeMapper;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeType;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeVo;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.service.notice.NoticeResService;
import com.ichangyun.InforAnalyaizer.service.notice.NoticeService;
import com.ichangyun.InforAnalyaizer.service.numberingcontrol.NumberingcontrolService;
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
    @Autowired
    private NoticeExecuteResultMapper noticeResMapper;
    @Autowired
    private NumberingcontrolService numberService;
    /**
     * queryAll：查询所有简报
     *
     *@param int pageNow, int rowSize
     *@return map
     */
    @Override
    public Map<String, Object> queryAll(int pageNow, int rowSize,User u) {
        // TODO Auto-generated method stub
        Map<String, Object> key = new HashMap<>();
        int l_pre = (pageNow-1)*rowSize;
        // 查询条件的map参数
        key.put("l_pre", l_pre);
        key.put("rowSize", rowSize);
        key.put("CollectionField_ID",u.getCollectionField_ID());
        key.put("Customer_ID",u.getCustomer_ID());
        key.put("userId", u.getUser_ID());
        List<NoticeVo> list = this.noticeMapper.queryAllNotice(key);

        int count = this.noticeMapper.queryCount(key);
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
        vo.setCollectionField_ID(u.getCollectionField_ID());
        vo.setCustomer_ID(u.getCustomer_ID());
        vo.setValidflag("1");
        vo.setUpdateuser(u.getUser_ID());
        try {
            String noticeID = this.numberService.getNextCFID("NC00000001");//为简报命名
            vo.setNoticeid(noticeID);
            Integer checkName = this.noticeMapper.checkName(vo);
            if (checkName>0) {
            	return "checkName";
            }
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
        Integer checkName = this.noticeMapper.checkName(vo);
        if (checkName>0) {
			return "checkName";
		}
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
        String msg ;
        StringBuilder nids = new StringBuilder();
        nids.append("'");
        for (int i = 0; i < ids.length; i++){
        	if(i<ids.length-1) {
        		nids.append(ids[i]+"','");
        	}else {
        		nids.append(ids[i]+"'");
        	}
        }
        try {
            for (String id : ids) {
                this.noticeMapper.deleteByPrimaryKey(nids.toString());
                this.noticeResMapper.deleteByPrimaryKey(nids.toString());
            }
        } catch (Exception e) {
        	e.printStackTrace();
            msg="fault";
        }
        msg="ok";
        return msg;
    }

}
