/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.comprehensivemonitoring.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ichangyun.InforAnalyaizer.mapper.comprehensivemonitoring.ComprehensivemonitoringMapper;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;
import com.ichangyun.InforAnalyaizer.service.comprehensivemonitoring.ComprehensivemonitoringService;
import com.ichangyun.InforAnalyaizer.utils.DateUtils;

/**
 * @author renhao
 * 2018-11-19 10:07
 */

@Service
public class ComprehensivemonitoringServiceImpl implements ComprehensivemonitoringService {

	@Autowired
	private ComprehensivemonitoringMapper comprehensivemonitoringMapper;

	@Override
	public int getArticleRowCount(ArticleInfoBean ab) {
		
		if(ab.getSimart().equals("0")) {  //合并相似文章
			return comprehensivemonitoringMapper.getArticleRowWithHBCount(ab);
		}
		
		if(ab.getSimart().equals("1")) {  //不合并相似文章
			return comprehensivemonitoringMapper.getArticleRowCount(ab);
		}
		
		return 0;
	}

	@Override
	public String getArticleJSON(ArticleInfoBean ab) {
		ab.setL_pre((ab.getPageNow()-1)*ab.getRowSize());
		
		List<ArticleInfoBean> list = null;
		
		if(ab.getSimart().equals("0")) {  //合并相似文章
			list = comprehensivemonitoringMapper.getArticleWithHB(ab);
		}
			
		if(ab.getSimart().equals("1")) {  //不合并相似文章
			list = comprehensivemonitoringMapper.getArticle(ab);
		}
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}

	@Override
	public boolean delarticle(String json) {
		
		try {
			List<String> list = new ArrayList<String>();
			JSONArray ja = JSONArray.parseArray(json);
			
			for(int i=0;i<ja.size();i++) {
				JSONObject obj = (JSONObject) ja.get(i);
				list.add(obj.getString("id"));
			}
			
			if(list.size()>0) {
				comprehensivemonitoringMapper.delarticle(list);
			}
		}catch(Exception e) {
			return false;
		}
		return true;

	}

	@Override
	public boolean toyj(String json, HttpSession session) {
	
		try {
			User user = (User) session.getAttribute(CommBean.SESSION_NAME);
			String userid = user.getUser_ID();
			List<String> list = new ArrayList<String>();
			JSONArray ja = JSONArray.parseArray(json);
			
			for(int i=0;i<ja.size();i++) {
				JSONObject obj = (JSONObject) ja.get(i);
				list.add(obj.getString("id"));
			}
			
			if(list.size()>0) {
				comprehensivemonitoringMapper.toyj(list,userid);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;

	}

	@Override
	public String getSearchLaestRelsetime(ArticleInfoBean ab) {
		String time = comprehensivemonitoringMapper.getSearchLaestRelsetime(ab);
		
		if(time==null||time.equals("")) {
			time = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
		}
		
		return time;
	}

	@Override
	public int getlastestNews(ArticleInfoBean ab) {
		return comprehensivemonitoringMapper.getlastestNews(ab);
	}

	@Override
	public String getSimContent(ArticleInfoBean ab) {
	    List<ArticleInfoBean> list = comprehensivemonitoringMapper.getSimContent(ab);
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}
}
