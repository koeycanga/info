/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.front.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ichangyun.InforAnalyaizer.mapper.front.DetailsPageMapper;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;
import com.ichangyun.InforAnalyaizer.service.front.DetailsPageService;

/**
 * @author renhao
 * 2018-11-19 17:03
 */

@Service
public class DetailsPageServiceImpl implements DetailsPageService {

	@Autowired
	private DetailsPageMapper detailsPageMapper;

	@Override
	public String getArticleByID(String article_id) {
		
		ArticleInfoBean ab = detailsPageMapper.getArticleByID(article_id);
		
		JSONObject obj = (JSONObject) JSONObject.toJSON(ab);
		
		return obj.toJSONString();
	}

	@Override
	public String getBDArticleByID(String article_id) {
		
		List<ArticleInfoBean> list = detailsPageMapper.getBDArticleByID(article_id);
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);
		
		return listArray.toJSONString();
	}
	
}
