/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.controller.thematicmonitoring;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.CollectionBean;
import com.ichangyun.InforAnalyaizer.service.thematicmonitoring.ThematicmonitoringService;
import com.ichangyun.InforAnalyaizer.utils.DateUtils;

/**
 * 专题监测Controller
 * @author renhao 
 * 2018-11-12 14:52
 */

@RestController
@RequestMapping("/thematicmonitoring")
public class ThematicmonitoringController {

	@Autowired
	private ThematicmonitoringService thematicmonitoringService;
	
	
	/**
	 * 进入到专题监测-信息汇总页面
	 * @return
	 */
	@RequestMapping("/informationaggregation")
	@ResponseBody
	public Object index() {
		return new ModelAndView("frontpage/thematicmonitoring/informationaggregation");
	}
	
	
	/**
	 * 进入到专题监测-内容分析页面
	 * @return
	 */
	@RequestMapping("/contentanalysis")
	public Object contentanalysis() {
		
	    return new 	ModelAndView("frontpage/thematicmonitoring/contentanalysis");
	}
	
	/**
	 * 进入到专题监测-传播分析页面
	 * @return
	 */
	@RequestMapping("/propagationanalysis")
	public Object propagationanalysis() {
		
		 return new ModelAndView("frontpage/thematicmonitoring/propagationanalysis");
	}
	
	
	
	/**
	 * 获得所有的方案
	 * @return
	 */
	@RequestMapping("/getallfa")
	public Object getAllFA(HttpSession session) {
		
		String json = thematicmonitoringService.getAllFA(session);
		
		return json;
	}
	
	/**
	 * 保存新的方案
	 * @param planinfo_name  //方案名称
	 * @param jcc_json       //方案监测词JSON字面量
	 * @param session
	 * @return
	 */
	@RequestMapping("/savenewfa")
	public Object SaveNewfa(@RequestBody Map map,HttpSession session) {
		
		String res = "ok";
		
		String planinfo_name  = (String) map.get("planinfo_name");
		String jcc_json  = (String) map.get("jcc_json");
		String planinfo_removeWord  = (String) map.get("planinfo_removeWord");
		planinfo_removeWord = planinfo_removeWord.replace("，", ",");
		
		String fromDate = (String) map.get("fromDate");
		String toDate = (String) map.get("toDate");
		
		if(thematicmonitoringService.exist(planinfo_name)) {
			res = "exist";
		}else {
			if(!thematicmonitoringService.SaveNewfa(planinfo_name,jcc_json,planinfo_removeWord,session,fromDate,toDate)) {
				res = "nok";
			}
		}
		return res;
		
	}
	
	/**
	 * 预警文章
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping("/toyj")
	public Object toyj(@RequestBody Map map,HttpSession session) {
		String res = "ok";
		
		String json = (String) map.get("json");
		
		if(!thematicmonitoringService.toyj(json,session)) {
			res = "nok"; 
		}
		
		return res;
	}
	
	
	/**
	 * 删除文章
	 * @param map
	 * @return  ok:删除成功   nok  删除失败
	 */
	@RequestMapping("/delarticle")
	public Object delarticle(@RequestBody Map map,HttpSession session) {
		String res = "ok";
		String json = (String) map.get("json");
		String deletemode = (String) map.get("deletemode");
		User user = (User) session.getAttribute(CommBean.SESSION_NAME);
		String userid = user.getUser_ID();
		if(!thematicmonitoringService.delarticle(json,userid,deletemode)) {
			res = "nok"; 
		}
		return res;
	}
	
	
	/**
	 * 获得相似文章信息的JSON字面量
	 * @param ab  参数
	 * @return
	 */
	@RequestMapping("/getSimContent")
	public Object getSimContent(ArticleInfoBean ab,HttpSession session) {
		
		String[] montime = DateUtils.dealMontime(ab.getMontime());
		
		ab.setMontime_start(montime[0]);
		ab.setMontime_end(montime[1]);

		User user = (User) session.getAttribute(CommBean.SESSION_NAME);
		String userid = user.getUser_ID();
		ab.setUserid(userid);
		
	    String json = thematicmonitoringService.getSimContent(ab);
	    
	    return json;
	}
	
	/**
	 * 修改方案
	 * @param map  要修改的方案的参数集合
	 * @param session
	 * @return  ok 成功  exist 方案名称已存在   nok 异常
	 */
	@RequestMapping("/updatefa")
	public Object updatefa(@RequestBody Map map,HttpSession session) {
		String res = "ok";
		
		String plan_id  = (String) map.get("plan_id");
		String planinfo_name  = (String) map.get("planinfo_name");
		String jcc_json  = (String) map.get("jcc_json");
		String planinfo_removeWord  = (String) map.get("planinfo_removeWord");
		planinfo_removeWord = planinfo_removeWord.replace("，", ",");
		
		String fromDate = (String) map.get("fromDate");
		String toDate  = (String) map.get("toDate");
		
		if(thematicmonitoringService.existwithID(plan_id,planinfo_name)) {
			 res = "exist";
		}else {
			if(!thematicmonitoringService.updatefa(plan_id,planinfo_name,jcc_json,planinfo_removeWord,session,fromDate,toDate)) {
				res = "nok";
			}
		}
		return res;
	}
	
	/**
	 * 删除方案
	 * @param planid 要删除的方案ID 
	 * @return ok 删除成功  nok  删除失败
	 */
	@RequestMapping("/delfa")
	public Object delfa(String planid) {
		String res = "ok";
		
		if(!thematicmonitoringService.delfa(planid)) {
			res = "nok";
		}
		
		return res;
	}
	
	/**
	 * 获得方案详细信息
	 * @param planid 方案id
	 * @return
	 */
	@RequestMapping("/getDetail")
	public Object getDetail(String planid) {
		
		String json = thematicmonitoringService.getDetail(planid);
		
		return json;
	}
	
	
	/**
	 * 处理信息汇总页面的查询请求
	 * @param ab
	 * @return
	 */
	@RequestMapping("/search")
	public Object search(ArticleInfoBean ab,HttpSession session) {
		
		String[] montime = DateUtils.dealMontime(ab.getMontime());
		
		ab.setMontime_start(montime[0]);
		ab.setMontime_end(montime[1]);

		User user = (User) session.getAttribute(CommBean.SESSION_NAME);
		String userid = user.getUser_ID();
		ab.setUserid(userid);

		String lastest_relsetime = thematicmonitoringService.getSearchLaestRelsetime(ab);
		
		session.setAttribute(CommBean.LAST_CONTENT_SEARCH_TIME_ZTJC,lastest_relsetime);
		
		int rowCount = thematicmonitoringService.getArticleRowCount(ab);
		
		String json_res = thematicmonitoringService.getArticleJSON(ab);
		
		String res = "{\"rowCount\":\""+rowCount+"\",\"resdata\":"+json_res+"}";
		
		return res;

	}
	
	/**
	 * 获得最新消息条目数
	 * @param ab
	 * @return
	 */
	@RequestMapping("/getlastestNews")
	public Object getlastestNews(ArticleInfoBean ab,HttpSession session) {
		
		String last_time = (String) session.getAttribute(CommBean.LAST_CONTENT_SEARCH_TIME_ZTJC);
		
		ab.setReleasetime(last_time);
		
		int count = thematicmonitoringService.getlastestNews(ab);
		
		return count;
	}
	
	/**
	 * 处理内容分析页面的查询请求
	 * @param ab
	 * @return
	 */
	@RequestMapping("/searchcontent")
	public Object searchcontent(ArticleInfoBean ab) {
		
		String[] montime = DateUtils.dealMontime(ab.getMontime());
		ab.setMontime_start(montime[0]);
		ab.setMontime_end(montime[1]);
		
		List<ArticleInfoBean> list = thematicmonitoringService.getContentList(ab);
		
		String qgsx_data = thematicmonitoringService.getQGSXJSON(list); // 获得页面情感分析环状图所需的信息
		
		String sjml_data = thematicmonitoringService.getSJMLJSON(list); //获得文章脉络序列图所需的信息
		
		String res = "{\"qgsx_data\":"+qgsx_data+",\"sjml_data\":"+sjml_data+"}";
		
		return res;
	}

    /**
     * 处理传播分析页面的查询请求
     * @param ab
     * @return
     */
	@RequestMapping("/searchpropaga")
	public Object searchpropaga(ArticleInfoBean ab) {
		
		String[] montime = DateUtils.dealMontime(ab.getMontime());
		ab.setMontime_start(montime[0]);
		ab.setMontime_end(montime[1]);
		
		return "";
	}
	
	
	
	/**
	 * 获得我的收藏种类
	 * @param session
	 * @return
	 */
	@RequestMapping("/getCollectionType")
	public Object getCollectionType(HttpSession session) {
		
		User user = (User)session.getAttribute(CommBean.SESSION_NAME);
		
		String json = thematicmonitoringService.getCollectionType(user.getUser_ID());
		
		return json;
	}
	
	/**
	 * 收藏文章到我的收藏
	 * @param cb
	 * @return
	 */
	@RequestMapping("/conllect")
	public Object conllect(CollectionBean cb,HttpSession session) {
		String res = "ok";
		User user = (User)session.getAttribute(CommBean.SESSION_NAME);
		cb.setUser_ID(user.getUser_ID());
		
		if(!thematicmonitoringService.conllect(cb)) {
			res = "nok";
		}
		return res;
	}
	
	/**
	 * 热词云
	 * @return
	 */
	@RequestMapping("/gethotwords")
	public Object getHotWord() {

		String json_hotword = thematicmonitoringService.getHotWord();

		return json_hotword;
	}
	
}
