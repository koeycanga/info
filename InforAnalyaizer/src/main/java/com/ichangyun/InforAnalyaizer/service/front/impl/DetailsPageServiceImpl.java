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
    public String getArticleByID(String article_id, String userid) {

        ArticleInfoBean ab = detailsPageMapper.getArticleByID(article_id,userid);
        if(ab!=null) {
            JSONObject obj = (JSONObject) JSONObject.toJSON(ab);

            return obj.toJSONString();
        }else {
            return "\"\"";
        }
    }

    @Override
    public String getBDArticleByID(String article_id,String userid) {

        List<ArticleInfoBean> list = detailsPageMapper.getBDArticleByID(article_id,userid);

        JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

        return listArray.toJSONString();
    }

    @Override
    public String getSimContent(ArticleInfoBean ab) {
        List<ArticleInfoBean> list = detailsPageMapper.getSimContent(ab);

        JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

        return listArray.toJSONString();
    }

    @Override
    public String getRelateDataByID(String article_id, String userid) {

        List<ArticleInfoBean> list = detailsPageMapper.getRelateDataByID(article_id,userid);

        JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

        return listArray.toJSONString();
    }

	@Override
	public String getClassificationPathById(String article_id) {
		
		List<String> list = detailsPageMapper.getClassificationPathById(article_id);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<list.size();i++) {
			if(i==0) {
				sb.append(list.get(i));
			}else {
				sb.append("&nbsp;>>&nbsp;");
				sb.append(list.get(i));
			}
		}
		
		return sb.toString();
	}
}
