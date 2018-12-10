/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.front.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.ichangyun.InforAnalyaizer.mapper.front.HomeMapper;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.front.HotWordBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;
import com.ichangyun.InforAnalyaizer.service.front.HomeService;
import com.ichangyun.InforAnalyaizer.utils.DateUtils;

/**
 * @author renhao
 * 2018-11-16 10:19
 */

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private HomeMapper homeMapper;

	@Override
	public String getHotWord() {
		
		List<HotWordBean> list = homeMapper.getHotWord();
		
		JSONArray ja = (JSONArray) JSONArray.toJSON(list);
		
		return ja.toJSONString();
		
	}

	@Override
	public int getArticleCountByHotWord(HotWordBean hb) {
	
		return homeMapper.getArticleCountByHotWord(hb);
	}

	@Override
	public String getArticleByHotWord(HotWordBean hb) {
		
		hb.setL_pre((hb.getPageNow()-1)*hb.getRowSize());
		
		List<ArticleInfoBean> list = homeMapper.getArticleByHotWord(hb);
		
		JSONArray ja = (JSONArray) JSONArray.toJSON(list);
	    
		return ja.toJSONString();
	}

	@Override
	public String getSimContent(ArticleInfoBean ab) {
		
	    List<ArticleInfoBean> list = homeMapper.getSimContent(ab);
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}

	@Override
	public String getNewestDatas() {
		
		List<ArticleInfoBean> list = homeMapper.getNewestDatas();
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}

	@Override
	public String getWarnDatas(HttpSession session) {
		
		User user = (User) session.getAttribute(CommBean.SESSION_NAME);
		
		String userid = user.getUser_ID();
		
		List<ArticleInfoBean> list = homeMapper.getWarnDatas(userid);
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}

	@Override 
	public String getNegativeDatas() {

		List<ArticleInfoBean> list = homeMapper.getNegativeDatas();
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}

	@Override
	public String getJCMsg(HttpSession session) {
		
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		
		String userid = u.getUser_ID();
		
		String today = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
		String yesterday = DateUtils.format(DateUtils.addDateDays(new Date(),-1), DateUtils.DATE_PATTERN);
		
		Map map = homeMapper.getJCMsg(today,yesterday,userid);
		
		String res = "\"t_total\":\""+map.get("t_total")+"\","+
					  "\"t_ztjc\":\""+map.get("t_ztjc")+"\","+
					  "\"t_fmxx\":\""+map.get("t_fmxx")+"\","+
					   "\"t_yjxx\":\""+map.get("t_yjxx")+"\","+
					   "\"y_total\":\""+map.get("y_total")+"\","+
					   "\"y_ztjc\":\""+map.get("y_ztjc")+"\","+
					  "\"y_fmxx\":\""+map.get("y_fmxx")+"\","+
					 "\"y_yjxx\":\""+map.get("y_yjxx")+"\"";
		
		return res;
	}
}
