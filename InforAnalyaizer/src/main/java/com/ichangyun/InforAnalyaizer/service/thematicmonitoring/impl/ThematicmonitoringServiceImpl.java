/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.thematicmonitoring.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.front.HotWordBean;
import com.ichangyun.InforAnalyaizer.model.front.TreeMapBean;
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
	public String getAllFA(HttpSession session) {
		
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		
		List<ThematicmonitoringBean> list = thematicmonitoringMapper.getAllFA(u.getUser_ID());
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}


	@Override
	public boolean SaveNewfa(String planinfo_name, String jcc_json,String planinfo_removeWord, HttpSession session,String fromDate, String toDate) {
		
		try {
			
			if(!fromDate.isEmpty()) {
				fromDate = fromDate+":00:00";
			}
			if(!toDate.isEmpty()) {
				toDate = toDate+":00:00";
			}
			
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
			tb.setPlanStartTime(fromDate);
			tb.setPlanEndTime(toDate);
			
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
			
			tb.setUnitNumber(detaillist.size());
			thematicmonitoringMapper.SaveNewfa(tb);
			
			if(detaillist.size()==0) {
				if(!planinfo_removeWord.isEmpty()) {
					ThematicmonitoringBean atb = new ThematicmonitoringBean();
					atb.setPlan_ID(next_pl_id);
					atb.setCreateUser(userid);
					atb.setUpdateUser(userid);
					atb.setUnitNo(1);
					atb.setRemoveWord(planinfo_removeWord);
					detaillist.add(atb);
				}
			}
			if(detaillist.size()>0) {
				thematicmonitoringMapper.SaveNewfaDetail(detaillist);
			}
			
			//新增方案后处理文章
			this.dealPlanAfterUpdate(next_pl_id,userid,fromDate,toDate,false);
				
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public void FlashThematicmonitoringDB() {
		
		thematicmonitoringMapper.delAllArticleWithPlanInfo();
		List<ThematicmonitoringBean> falist = thematicmonitoringMapper.getAllFa();
		for(ThematicmonitoringBean tb:falist) {
			dealFAandArticle(tb.getPlan_ID(),tb.getUser_ID(),tb.getPlanStartTime(),tb.getPlanEndTime());
		}
	}

	
	/**
	 * 新增或修改方案后处理 方案相关联的文章
	 * @param pl_id     方案ID
	 * @param userid    用户ID
	 * @param toDate 
	 * @param fromDate 
	 * @param isdel     是否删除旧的数据
	 */
	private void dealPlanAfterUpdate(String pl_id, String userid,String fromDate, String toDate, boolean isdel) {
		new Thread() {
			public void run() {
				if(isdel) {
					thematicmonitoringMapper.delArticleWithPlanInfo(pl_id);
				}
				dealFAandArticle(pl_id,userid,fromDate,toDate);
			}
		}.start();
	}
	
	/**
	 * 将文章和相关方案关联起来
	 * @param pl_id
	 * @param userid
	 * @param fromDate
	 * @param toDate
	 */
	private void dealFAandArticle(String pl_id,String userid,String fromDate,String toDate) {
		List<ThematicmonitoringBean> falist = thematicmonitoringMapper.getFAByIdAndUser(pl_id,userid);
		if(falist.size()>0) {
			String[] removeword = falist.get(0).getRemoveWord().split(",");
			String remove_articleTitle = createRmoveWordSql(removeword,"ArticleTitle");
			String remove_articleAbstract = createRmoveWordSql(removeword,"ArticleAbstract");
			String remove_articleText = createRmoveWordSql(removeword,"ArticleText");
			String sql = createPlanInfoSql(falist,remove_articleTitle,remove_articleAbstract,remove_articleText,fromDate,toDate);
			if(isEffective(sql)) {
				
				String sql_m1="",sql_m2="",sql_m3="";
				if(falist.size()>=1) { sql_m1 = createCountFASql(falist.get(0),remove_articleTitle,remove_articleAbstract,remove_articleText,"m_lg1"); }
				if(falist.size()>=2) { sql_m2 = createCountFASql(falist.get(1),remove_articleTitle,remove_articleAbstract,remove_articleText,"m_lg2"); }
				if(falist.size()>=3) { sql_m3 = createCountFASql(falist.get(2),remove_articleTitle,remove_articleAbstract,remove_articleText,"m_lg3"); }

				int rowCount = thematicmonitoringMapper.getArticleRowCountWithPlanInfo(sql);
				int rowSize = CommBean.DB_BATCH_NUM;
				int flag = rowCount/rowSize;
				for(int i=0;i<=flag;i++) {
					int l_pre = i*rowSize;
					List<ThematicmonitoringBean> alist = thematicmonitoringMapper.getArticleWithPlanInfo(sql,l_pre,rowSize,sql_m1,sql_m2,sql_m3);
					if(alist.size()>0) {
						for(ThematicmonitoringBean tb:alist) {
							tb.setUser_ID(userid);
							tb.setPlan_ID(pl_id);
							String planinfWord = getPlanInfoWord(tb,falist);
							tb.setMonitoringWord(planinfWord);
						}
						thematicmonitoringMapper.insertArtileWithPlanInfoBatch(alist);
					}
				}
			}
		}
	}
	
	
	private String getPlanInfoWord(ThematicmonitoringBean tb, List<ThematicmonitoringBean> falist) {
		ThematicmonitoringBean atb = null;
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		if(tb.getM_lg1()>0) {
			atb = falist.get(0);
			
			if(cnt==0) {
				sb.append(createGLC(atb));
				cnt++;
			}else {
				sb.append(";"+createGLC(atb));
			}
		}
		if(tb.getM_lg2()>0) {
			atb = falist.get(1);
			if(cnt==0) {
				sb.append(createGLC(atb));
				cnt++;
			}else {
				sb.append(";"+createGLC(atb));
			}
		}
		if(tb.getM_lg3()>0) {
			atb = falist.get(2);
			if(cnt==0) {
				sb.append(createGLC(atb));
				cnt++;
			}else {
				sb.append(";"+createGLC(atb));
			}
		}
		return sb.toString();
	}

    private String createGLC(ThematicmonitoringBean atb) {
    	String[] glc = {atb.getMonitoringWord1(),atb.getMonitoringWord2(),atb.getMonitoringWord3(),atb.getMonitoringWord4()}; //关联词词组
    	StringBuilder sb = new StringBuilder();
    	int count = 0;
		for(String str:glc) {
			if(str!=null&&!"".equals(str)) {
				if(count==0) {
					sb.append(str);
					count++;
				}else {
					sb.append("+"+str);
				}
			}
		}
    	return sb.toString();
    }
	
	private String createCountFASql(ThematicmonitoringBean tb, String remove_articleTitle, String remove_articleAbstract, String remove_articleText,String aliasname) {
		StringBuilder sb = new StringBuilder("case when (");
		String[] glc = {tb.getMonitoringWord1(),tb.getMonitoringWord2(),tb.getMonitoringWord3(),tb.getMonitoringWord4()}; //关联词词组
		String create_str_articleTitle  = createSql(glc,"ArticleTitle");
		String create_str_articleAbstract  = createSql(glc,"ArticleAbstract");
		String create_str_articleText  = createSql(glc,"ArticleText");
		sb.append(create_str_articleTitle);
		sb.append(" or "+create_str_articleAbstract);
		sb.append(" or "+create_str_articleText);
		sb.append(" "+remove_articleTitle);
		sb.append(" "+remove_articleAbstract);
		sb.append(" "+remove_articleText);
		sb.append(") then 1 else 0 end "+aliasname);
		String res = sb.toString();
		if(!isEffective(res)) {
			res = "";
		}
		return res;
	}
	

	private boolean isEffective(String res) {
		if(res.isEmpty()) {
			return false;
		}
		Pattern p = Pattern.compile("[(][\\s]*[)]"); //匹配（   ）这样的字符串
		Matcher m = p.matcher(res);
		if(m.find()) {
		    return false;
		}else {
			return true;
		}
	}


	/**
	 * 根据方案的关联词及排除词 拼装SQL
	 * @param falist
	 * @param remove_articleText 
	 * @param remove_articleAbstract 
	 * @param remove_articleTitle 
	 * @param toDate 
	 * @param fromDate 
	 * @return
	 */
	private String createPlanInfoSql(List<ThematicmonitoringBean> falist, String remove_articleTitle, String remove_articleAbstract, String remove_articleText, String fromDate, String toDate) {
		StringBuilder sb = new StringBuilder(" and ");
		for(int i=0;i<falist.size();i++) {
			ThematicmonitoringBean tb = falist.get(i);
			String[] glc = {tb.getMonitoringWord1(),tb.getMonitoringWord2(),tb.getMonitoringWord3(),tb.getMonitoringWord4()};
			String create_str_articleTitle  = createSql(glc,"ArticleTitle");
			String create_str_articleAbstract  = createSql(glc,"ArticleAbstract");
			String create_str_articleText  = createSql(glc,"ArticleText");
			if(i==0) {
				sb.append("(");
			}else {
				sb.append("or (");
			}
			sb.append(create_str_articleTitle);
			sb.append(" or "+create_str_articleAbstract);
			sb.append(" or "+create_str_articleText);
			sb.append(")");
			
			if(!isEffective(sb.toString())/*.contains("(() or () or ())")*/) {
				sb = new StringBuilder("");
			}
			
			if(!remove_articleTitle.isEmpty()) {
				sb.append(" "+remove_articleTitle);
			}
			if(!remove_articleAbstract.isEmpty()) {
				sb.append(" "+remove_articleAbstract);
			}
			if(!remove_articleText.isEmpty()) {
				sb.append(" "+remove_articleText);
			}
			if(!fromDate.isEmpty()) {
				sb.append(" and Releasetime>='"+fromDate+"'");
			}
			if(!toDate.isEmpty()) {
				sb.append(" and Releasetime<='"+toDate+"'");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 根据方案的关联词及排除词 拼装SQL
	 * @param glc         关联词数组
	 * @param removeword  排除词数组
	 * @param colname     数据表列名  
	 * @return
	 */
	private String createSql(String[] glc,String colname) {
		StringBuilder sb = new StringBuilder("(");
		int cnt = 0;
		for(int i=0;i<glc.length;i++) {
			String str = glc[i];
			if(str!=null&&!str.equals("")) {
				if(cnt==0) {
					sb.append(colname+"  like '%"+str+"%' ");
					cnt++;
				}else {
					sb.append(" and "+colname+"  like '%"+str+"%' ");
				}
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * 根据方案的排除词 拼装SQL
	 * @param removeword 排除词数组
	 * @param colname    数据表列名 
	 * @return
	 */
	private String createRmoveWordSql(String[] removeword, String colname) {
		StringBuilder sb = new StringBuilder();
		for(String str:removeword) {
			if(str!=null&&!str.equals("")) {
				sb.append(" and "+colname+" not like '%"+str+"%' ");
			}
		}
		return sb.toString();
	}


	@Override
	public boolean updatefa(String plan_id, String planinfo_name, String jcc_json, String planinfo_removeWord,HttpSession session,String fromDate, String toDate) {
		try{
			
			User user = (User) session.getAttribute(CommBean.SESSION_NAME);
			String userid = user.getUser_ID();
			if(!fromDate.isEmpty()) {
				fromDate = fromDate+":00:00";
			}
			if(!toDate.isEmpty()) {
				toDate = toDate+":00:00";
			}
			thematicmonitoringMapper.delfadetail(plan_id);
			
			JSONArray ja = JSONArray.parseArray(jcc_json);
	
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
			thematicmonitoringMapper.updatefa(plan_id,planinfo_name,fromDate,toDate,detaillist.size(),userid);
			
			if(detaillist.size()==0) {
				if(!planinfo_removeWord.isEmpty()) {
					ThematicmonitoringBean atb = new ThematicmonitoringBean();
					atb.setPlan_ID(plan_id);
					atb.setCreateUser(userid);
					atb.setUpdateUser(userid);
					atb.setUnitNo(1);
					atb.setRemoveWord(planinfo_removeWord);
					detaillist.add(atb);
				}
			}
			if(detaillist.size()>0) {
				thematicmonitoringMapper.SaveNewfaDetail(detaillist);
			}
			
			//修改方案后处理文章
			this.dealPlanAfterUpdate(plan_id,userid,fromDate,toDate,true);
			
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}


	@Override
	public boolean exist(String planinfo_name,HttpSession session) {
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		int count = thematicmonitoringMapper.exist(planinfo_name,u.getUser_ID());
		if(count>=1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean existwithID(String plan_id, String planinfo_name,HttpSession session) {
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		int count = thematicmonitoringMapper.existwithID(plan_id,planinfo_name,u.getUser_ID());
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
			thematicmonitoringMapper.delArticleWithPlanInfo(planid);
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
	public boolean delarticle(String json,String userid ,String deletemode) {
		
		try {
			List<String> list = new ArrayList<String>();
			JSONArray ja = JSONArray.parseArray(json);
			
			for(int i=0;i<ja.size();i++) {
				JSONObject obj = (JSONObject) ja.get(i);
				list.add(obj.getString("id"));
			}
			
			if(list.size()>0) {
				thematicmonitoringMapper.delarticle(list,userid,deletemode);
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}


	@Override
	public String getSimContent(ArticleInfoBean ab) {
		
		ab.setRowSize(CommBean.SIM_MAX_NUM);
		
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
			//关闭Spring事务自动提交
	        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	        status = platformTransactionManager.getTransaction(def);
			int count = thematicmonitoringMapper.getConllectCount(cb);
			if(count==0) {
		        int next_displayorder = thematicmonitoringMapper.getNextDisplayorder(cb)+1;
		        
		        cb.setDisplayOrder(next_displayorder);
		        
		        thematicmonitoringMapper.addnewconllect(cb);
			}
			platformTransactionManager.commit(status);  //提交事务
		}catch(Exception e) {
			if(status!=null) {platformTransactionManager.rollback(status);}
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<ArticleInfoBean> getContentList(ArticleInfoBean ab) {
		
		return thematicmonitoringMapper.getContentList(ab);
	}

	@Override
	public String getQGSXJSON(List<ArticleInfoBean> list) {  //0：正面，1：中性，2：负面
	
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("0", 0);map.put("1", 0);map.put("2", 0);
		
		for(ArticleInfoBean ab:list) {
			if(ab.getEmotionDivision()!=null&&ab.getEmotionDivision().equals("0")) {
				 map.put("0", map.get("0")+1);
			}
			if(ab.getEmotionDivision()!=null&&ab.getEmotionDivision().equals("1")) {
				 map.put("1", map.get("1")+1);
			}
			if(ab.getEmotionDivision()!=null&&ab.getEmotionDivision().equals("2")) {
				 map.put("2", map.get("2")+1);
			}
		}
		
		String res = "[{\"value\":\""+map.get("0")+"\",\"name\":\"正面\"},";
		res+= "{\"value\":\""+map.get("2")+"\",\"name\":\"负面\"},";
		res += "{\"value\":\""+map.get("1")+"\",\"name\":\"中性\"}";
		
		res+="]";
		return res;
	}

	
	@Override
	public String getSJMLJSON(List<ArticleInfoBean> list) {
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

	    return listArray.toJSONString();
	}


	@Override
	public String getHotWord() {

		List<HotWordBean> list = thematicmonitoringMapper.getHotWord();

		JSONArray ja = (JSONArray) JSONArray.toJSON(list);

		return ja.toJSONString();

	}


	@Override
	public String getMTFBData(ArticleInfoBean ab,HttpSession session) {
		
		//[{"name":"123","value":"20"}]
		
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		ab.setUserid(u.getUser_ID());
		
		List<ArticleInfoBean> list = thematicmonitoringMapper.getMTFBData(ab);
		
		StringBuilder sb = new StringBuilder("[");
		
		for(int i=0;i<list.size();i++) {
			ArticleInfoBean aab = list.get(i);
			if(i==0) {
				sb.append("{\"name\":\""+aab.getMasterValue()+"\",\"value\":\""+aab.getCnt()+"\"}");
			}else {
				sb.append(",{\"name\":\""+aab.getMasterValue()+"\",\"value\":\""+aab.getCnt()+"\"}");
			}
		}
		
		
		sb.append("]");
		
		return sb.toString();
	}


	@Override
	public String getFZQS(ArticleInfoBean ab,String flag,String time_datas,HttpSession session) {
		
		List<ArticleInfoBean> list = null;
		
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		ab.setUserid(u.getUser_ID());
		
		if(flag.equals("hour")) {  //按小时查询
			list = thematicmonitoringMapper.getFZQSByHour(ab);
			
		}
		if(flag.equals("day")) {  //按天查询
			list = thematicmonitoringMapper.getFZQSByDay(ab);
		}
		JSONArray ja = (JSONArray) JSONArray.toJSON(list);

		return ja.toJSONString();
	}

	

	@Override
	public String getFZQS_DS(ArticleInfoBean ab, String flag, String time_datas, HttpSession session) {
		
		List<ArticleInfoBean> list = new ArrayList<ArticleInfoBean>();
		
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		ab.setUserid(u.getUser_ID());
		
		if(flag.equals("hour")) {  //按小时查询
			list = thematicmonitoringMapper.getFZQSByHourDS(ab);
		}
		
		JSONArray ja = (JSONArray) JSONArray.toJSON(list);

		return ja.toJSONString();
	}

	@Override
	public String getCBTHData(ArticleInfoBean ab,HttpSession session) {
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		ab.setUserid(u.getUser_ID());
		List<ArticleInfoBean> list = thematicmonitoringMapper.getCBTHData(ab);
		List<TreeMapBean> rlist = new ArrayList<TreeMapBean>();
		for(int i=list.size()-1;i>=0;i--) {
			ArticleInfoBean a_ab = list.get(i);
			if(a_ab.getReprintType()==null||a_ab.getReprintType().equals("")||
					a_ab.getReprintSourceArticle_ID()==null||a_ab.getReprintSourceArticle_ID().equals("")) {  //去掉没有转载标示的文章
				list.remove(i);
				continue;
			}
			if(isHaveNoFarther(a_ab,list)) { //0 原文   1转载
				TreeMapBean tb = new TreeMapBean();
				tb.setName(a_ab.getWebsiteName());
				diGuiChildren(tb,tb,a_ab.getArticle_ID(),list);
				rlist.add(tb);
				list.remove(i);
			}
		}
		
		TreeMapBean tb = null;
		int lg = -1;
		
		for(TreeMapBean atb:rlist) {
			if(atb.getMaxChildren_depth()>lg) {
				tb = atb;
				lg = atb.getMaxChildren_depth();
			}
		}
		
		Map<String,TreeMapBean> tmap = new HashMap<String,TreeMapBean>();
		rmoveSameByWeb(tb,tmap);
		
		String res = "{}";
		
		if(tb!=null) {
			//res = createTBTJJSON(tb);
			JSONObject json = (JSONObject) JSONObject.toJSON(tb);
			if(json!=null) {
				res = json.toJSONString();
			}
		}
		return res ;
	}

	
	/**
	 * 去除传播途径中的相同的网站，保留传播途径最长的那一条数据
	 * @param tb
	 * @param tmap 
	 */
	private void rmoveSameByWeb(TreeMapBean tb, Map<String, TreeMapBean> map) {
	    if(tb!=null) {
			for(int i=tb.getChildren().size()-1;i>=0;i--) {
				TreeMapBean chtb = tb.getChildren().get(i);
				if(map.get(chtb.getName())==null) {
					map.put(chtb.getName(), chtb);
				}else {
					TreeMapBean m_tb = map.get(chtb.getName());
					int o_lg = maxDepth(m_tb);
					int n_lg = maxDepth(chtb);
					if(n_lg>o_lg) {
						map.put(chtb.getName(), chtb);
						tb.getChildren().remove(m_tb);
					}
				}
			}
			Map<String,TreeMapBean> tmap = new HashMap<String,TreeMapBean>();
			for(int i=tb.getChildren().size()-1;i>=0;i--) {
				rmoveSameByWeb(tb.getChildren().get(i),tmap);
			}
	    }
	}


	/**
	 * 判断节点是否有父节点
	 * @param ab
	 * @param list
	 * @return
	 */
	private boolean isHaveNoFarther(ArticleInfoBean ab,List<ArticleInfoBean> list) {
		if(ab.getReprintType().equals("0")) {
			return true;
		}
		boolean b  = true;
		for(int i=0;i<list.size();i++) {
			ArticleInfoBean aab = list.get(i);
			if(aab.getArticle_ID().equals(ab.getReprintSourceArticle_ID())) {
				b  = false;
				break;
			}
		}
		return b;
	}

	/**
	 * 递归处理树形节点，将list中TreeMapBean tb的子节点添加到tb的children list中去
	 * @param root
	 * @param tb
	 * @param Article_ID
	 * @param list
	 */
	private void diGuiChildren(TreeMapBean root,TreeMapBean tb,String Article_ID, List<ArticleInfoBean> list) {

		for(ArticleInfoBean ab:list) {
			if(ab.getReprintSourceArticle_ID()!=null&&ab.getReprintSourceArticle_ID().equals(Article_ID)) {
				TreeMapBean a_tb = new TreeMapBean();
				a_tb.setName(ab.getWebsiteName());
				if(tb.getChildren().size()==0) {
					root.setMaxChildren_depth(root.getMaxChildren_depth()+1);
				}
				tb.getChildren().add(a_tb);
				diGuiChildren(root,a_tb,ab.getArticle_ID(),list);
			}
		}
		
	}

	/**
	 * 求树的最大深度
	 */
	private int maxDepth(TreeMapBean root) {
        if (root == null) {
            return 0;
        } else {
            int max = 0;
            for (int i = 0; i < root.getChildren().size(); i++) {  // 遍历子结点，找出子结点中的最大深度
                max = Math.max(max, maxDepth(root.getChildren().get(i)));
            }
            return 1+max;
        }
    }

	/*private String createTBTJJSON(TreeMapBean tb) {
	StringBuilder sb  =  new StringBuilder("{");
    sb.append("\"name\":\""+tb.getName()+"\"");
    if(tb.getChildren().size()>0) {
    	sb.append(",\"children\":[");
    	for(int i=0;i<tb.getChildren().size();i++) {
    		if(i==0) {
    			sb.append(createTBTJJSON(tb.getChildren().get(i)));
    		}else {
    			sb.append(","+createTBTJJSON(tb.getChildren().get(i)));
    		}
    	}
    	sb.append("]");
    }
	sb.append("}");
	return sb.toString();
}*/

}
