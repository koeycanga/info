/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.front.impl;

import java.util.ArrayList;
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
	    if(hb.getFlag().equals("0")) {   // 热词云
	    	return homeMapper.getArticleCountByHotWord(hb);
	    }
		if(hb.getFlag().equals("1")) {   //即将发生
			return homeMapper.getArticleCountByJJFSWord(hb);
		}
	    return 0;
	}

	@Override
	public String getArticleByHotWord(HotWordBean hb) {
		
		hb.setL_pre((hb.getPageNow()-1)*hb.getRowSize());
		
		List<ArticleInfoBean> list = null;
		
		if(hb.getFlag().equals("0")) {  // 热词云
			list = homeMapper.getArticleByHotWord(hb);
		}
	    
		if(hb.getFlag().equals("1")) {  //即将发生
			list = homeMapper.getArticleByJJFSWord(hb);
		}
		
		return ((JSONArray) JSONArray.toJSON(list)).toJSONString();
	}

	@Override
	public String getSimContent(ArticleInfoBean ab) {
		
	    List<ArticleInfoBean> list = homeMapper.getSimContent(ab);
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}

	@Override
	public String getJJFSWord() {
		
		List<HotWordBean> list = homeMapper.getJJFSWord();
		
		int yz = 10 - list.size();
		
		for(int i=0;i<yz;i++) {
			HotWordBean ab = new HotWordBean();
			ab.setKeyword_ID("");
			ab.setHotWord("");
			list.add(ab);
		}
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}

	@Override
	public String getHotWordFromDetial(String flag) {  //flag  0:热词   1 即将发生     
		if(flag.equals("0")) {
			return getHotWord();
		}
		if(flag.equals("1")) {
			return getJJFSWord();
		}
		return null;
	}
	
	@Override
	public String[] getTopTenDatas(HttpSession session) {
		
		User user = (User) session.getAttribute(CommBean.SESSION_NAME);
		
		String userid = user.getUser_ID();
		
		List<ArticleInfoBean> allist = homeMapper.getTopTenDatas(userid);
		
		List<ArticleInfoBean> newlist = new ArrayList<ArticleInfoBean> ();
		
		List<ArticleInfoBean> warnlist = new ArrayList<ArticleInfoBean> ();
		
		List<ArticleInfoBean> negativelist = new ArrayList<ArticleInfoBean> ();
		
		for(ArticleInfoBean ab:allist) {
			if(ab.getFlag().equals("1")) { //最新消息top10
				newlist.add(ab);
			}
			if(ab.getFlag().equals("2")) { //预警信息top10
				warnlist.add(ab);
			}
			if(ab.getFlag().equals("3")) { //负面信息top10
				negativelist.add(ab);
			}
		}
		
		String[] res = new String[3];
		res[0] = ((JSONArray) JSONArray.toJSON(newlist)).toJSONString();
		res[1] = ((JSONArray) JSONArray.toJSON(warnlist)).toJSONString();
		res[2] = ((JSONArray) JSONArray.toJSON(negativelist)).toJSONString();
		return res;
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
