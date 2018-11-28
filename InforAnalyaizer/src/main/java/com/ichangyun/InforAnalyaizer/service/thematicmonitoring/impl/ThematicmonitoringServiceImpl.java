/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.thematicmonitoring.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ichangyun.InforAnalyaizer.mapper.thematicmonitoring.ThematicmonitoringMapper;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.front.HotWordBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.CollectionBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ThematicmonitoringBean;
import com.ichangyun.InforAnalyaizer.service.numberingcontrol.NumberingcontrolService;
import com.ichangyun.InforAnalyaizer.service.thematicmonitoring.ThematicmonitoringService;
import com.ichangyun.InforAnalyaizer.utils.DateUtils;

/**
 * @author renhao
 * 2018-11-13 10:22
 */
@Service
public class ThematicmonitoringServiceImpl implements ThematicmonitoringService {

	@Autowired
	private ThematicmonitoringMapper thematicmonitoringMapper;

	@Autowired
	private NumberingcontrolService numberingcontrolService;
	
	@Resource(name = "transactionManager")
    private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public String getAllFA() {
		
		List<ThematicmonitoringBean> list = thematicmonitoringMapper.getAllFA();
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}


	@Override
	public boolean SaveNewfa(String planinfo_name, String jcc_json,String planinfo_removeWord, HttpSession session) {
		
		try {
			String next_pl_id = numberingcontrolService.getNextCFID("NC00000003");
			
			JSONArray ja = JSONArray.parseArray(jcc_json);
			
			User user = (User) session.getAttribute(CommBean.SESSION_NAME);
			String userid = user.getUser_ID();
			
			ThematicmonitoringBean tb = new ThematicmonitoringBean();
			
			tb.setUser_ID(userid);
			tb.setCreateUser(userid);
			tb.setUpdateUser(userid);
			tb.setPlan_ID(next_pl_id);
			tb.setPlanName(planinfo_name);
			tb.setUnitNumber(ja.size());
			
			thematicmonitoringMapper.SaveNewfa(tb);
			
			List<ThematicmonitoringBean> detaillist = new ArrayList<ThematicmonitoringBean>();
			
			for(int i=0;i<ja.size();i++) {
				ThematicmonitoringBean atb = new ThematicmonitoringBean();
				atb.setPlan_ID(next_pl_id);
				atb.setCreateUser(userid);
				atb.setUpdateUser(userid);
				atb.setUnitNo(i+1);
				atb.setRemoveWord(planinfo_removeWord);
				JSONArray obj = (JSONArray) ja.get(i);
				for(int j=0;j<obj.size();j++) {
					JSONObject jobj = (JSONObject) obj.get(j);
					if(j==0) {
						atb.setMonitoringWord1(jobj.getString("jcc"));
					}
					if(j==1) {
						atb.setMonitoringWord2(jobj.getString("jcc"));			
					}
					if(j==2) {
						atb.setMonitoringWord3(jobj.getString("jcc"));
					}
					if(j==3) {
						atb.setMonitoringWord4(jobj.getString("jcc"));
					}
				}
				detaillist.add(atb);
			}
			if(detaillist.size()>0) {
				thematicmonitoringMapper.SaveNewfaDetail(detaillist);
			}
			
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean updatefa(String plan_id, String planinfo_name, String jcc_json, String planinfo_removeWord,HttpSession session) {
		try{
			
			thematicmonitoringMapper.delfadetail(plan_id);
			
			JSONArray ja = JSONArray.parseArray(jcc_json);
	
			User user = (User) session.getAttribute(CommBean.SESSION_NAME);
			String userid = user.getUser_ID();
			
			List<ThematicmonitoringBean> detaillist = new ArrayList<ThematicmonitoringBean>();
			
			for(int i=0;i<ja.size();i++) {
				ThematicmonitoringBean atb = new ThematicmonitoringBean();
				atb.setPlan_ID(plan_id);
				atb.setCreateUser(userid);
				atb.setUpdateUser(userid);
				atb.setUnitNo(i+1);
				atb.setRemoveWord(planinfo_removeWord);
				JSONArray obj = (JSONArray) ja.get(i);
				for(int j=0;j<obj.size();j++) {
					JSONObject jobj = (JSONObject) obj.get(j);
					if(j==0) {
						atb.setMonitoringWord1(jobj.getString("jcc"));
					}
					if(j==1) {
						atb.setMonitoringWord2(jobj.getString("jcc"));			
					}
					if(j==2) {
						atb.setMonitoringWord3(jobj.getString("jcc"));
					}
					if(j==3) {
						atb.setMonitoringWord4(jobj.getString("jcc"));
					}
				}
				detaillist.add(atb);
			}
			if(detaillist.size()>0) {
				thematicmonitoringMapper.SaveNewfaDetail(detaillist);
			}
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}


	@Override
	public boolean exist(String planinfo_name) {
		int count = thematicmonitoringMapper.exist(planinfo_name);
		if(count>=1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean existwithID(String plan_id, String planinfo_name) {
		int count = thematicmonitoringMapper.existwithID(plan_id,planinfo_name);
		if(count>=1) {
			return true;
		}
		return false;
	}

	

	@Override
	public boolean delfa(String planid) {
		
		try {
			thematicmonitoringMapper.delfa(planid);
			thematicmonitoringMapper.delfadetail(planid);
			
		}catch(Exception e) {
			return false;
		}
		return true;
	}


	@Override
	public String getDetail(String planid) {
		
		List<ThematicmonitoringBean> list = thematicmonitoringMapper.getDetail(planid);
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}


	@Override
	public int getArticleRowCount(ArticleInfoBean ab) {
		
		if(ab.getSimart().equals("0")) {  //合并相似文章
			return thematicmonitoringMapper.getArticleRowCountWithHB(ab);
		}
		
		if(ab.getSimart().equals("1")) {  //不合并相似文章
			return thematicmonitoringMapper.getArticleRowCount(ab);
		}
		return 0;
	}
	
	@Override
	public String getArticleJSON(ArticleInfoBean ab) {
		
		ab.setL_pre((ab.getPageNow()-1)*ab.getRowSize());
		
		List<ArticleInfoBean> list = null;
		
		if(ab.getSimart().equals("0")) {  //合并相似文章
			list = thematicmonitoringMapper.getArticleWithHB(ab);
		}
		
		if(ab.getSimart().equals("1")) {  //不合并相似文章
		    list = thematicmonitoringMapper.getArticle(ab);
		}
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}
	
	@Override
	public int getlastestNews(ArticleInfoBean ab) {
		
		return thematicmonitoringMapper.getlastestNews(ab);
	}

	@Override
	public String getSearchLaestRelsetime(ArticleInfoBean ab) {
		
		String time = thematicmonitoringMapper.getSearchLaestRelsetime(ab);
		
		if(time==null||time.equals("")) {
			time = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
		}
		
		return time;
	}

	@Override
	public boolean toyj(String json,HttpSession session) {
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
				thematicmonitoringMapper.toyj(list,userid);
			}
			
		}catch(Exception e) {
			return false;
		}
		
		return true;
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
				thematicmonitoringMapper.delarticle(list);
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}


	@Override
	public String getSimContent(ArticleInfoBean ab) {
		
		List<ArticleInfoBean> list = thematicmonitoringMapper.getSimContent(ab);
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}


	@Override
	public String getCollectionType(String user_ID) {
	
		List<CollectionBean> list = thematicmonitoringMapper.getCollectionType(user_ID);
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}


	@Override
	public boolean conllect(CollectionBean cb) {
		TransactionStatus status = null;
		try {
			int count = thematicmonitoringMapper.getConllectCount(cb);
			if(count==0) {
				//关闭Spring事务自动提交
		        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		        status = platformTransactionManager.getTransaction(def);
		        
		        int next_displayorder = thematicmonitoringMapper.getNextDisplayorder(cb)+1;
		        
		        cb.setDisplayOrder(next_displayorder);
		        
		        thematicmonitoringMapper.addnewconllect(cb);
		        
		        platformTransactionManager.commit(status);  //提交事务
			}
		}catch(Exception e) {
			if(status!=null) {platformTransactionManager.rollback(status);}
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public String getQGSXJSON(ArticleInfoBean ab) {  //0：正面，1：中性，2：负面
	
		String res = "";
		
		List<ArticleInfoBean> list = thematicmonitoringMapper.getQGSXJSON(ab);
		
		ArticleInfoBean zm_ab = getNodeByQG(list,"0");
		if(zm_ab!=null) {
			res = "[{\"value\":\""+zm_ab.getCnt()+"\",\"name\":\"正面\"},";
		}else {
			res = "[{\"value\":\"0\",\"name\":\"正面\"},";
		}
		
		ArticleInfoBean fm_ab = getNodeByQG(list,"2");
		if(fm_ab!=null) {
			res += "{\"value\":\""+fm_ab.getCnt()+"\",\"name\":\"负面\"},";
		}else {
			res += "{\"value\":\"0\",\"name\":\"负面\"},";
		}
		
		ArticleInfoBean zx_ab = getNodeByQG(list,"1");
		if(zx_ab!=null) {
			res+= "{\"value\":\""+zx_ab.getCnt()+"\",\"name\":\"中性\"}";
		}else {
			res += "{\"value\":\"0\",\"name\":\"中性\"}";
		}
	
		res+="]";
		return res;
	}


	private ArticleInfoBean getNodeByQG(List<ArticleInfoBean> list, String flag) {
		
		for(ArticleInfoBean ab:list) {
			if(ab.getEmotionDivision()!=null&&ab.getEmotionDivision().equals(flag)) {
				return ab;
			}
		}
		return null;
	}


	@Override
	public String getHotWord() {

		List<HotWordBean> list = thematicmonitoringMapper.getHotWord();

		JSONArray ja = (JSONArray) JSONArray.toJSON(list);

		return ja.toJSONString();

	}

}
